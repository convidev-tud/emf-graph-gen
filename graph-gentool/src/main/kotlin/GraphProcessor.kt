/**
 * Copyright 2024 Karl Kegel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import deltamodel.*
import graphmodel.*
import util.Configuration
import util.GraphStats
import util.ImpactType
import util.Stage
import java.util.*
import kotlin.random.Random

/**
 * The GraphProcessor edits a [Graph] according to a configuration and
 *
 * The GraphProcessor is a 1-time use implementation.
 * After the exec() method terminates, the object must be discarded.
 * For further edits, create a new GraphProcessor object.
 */
class GraphProcessor(
    private val graph: Graph,
    private val conf: Configuration,
    val weights: List<Pair<String, Int>>
) {

    private val random: Random = Random(conf.randomSeed)
    private val rootStats: GraphStats = graph.getStats(true)

    private var simpleNodeNameIncrement = 0
    private var regionNameIncrement = 0


    private val changeOperationWeights = weights

    // list: ( (min, max) -> operation )
    private val operationDistribution: MutableList<Pair<Pair<Int, Int>, String>> = LinkedList()

    private lateinit var stage: Stage

    private val globalDeltaSequence: DeltaSequence = DeltaSequence()
    private var globalGraph: Graph = graph
    private val impactType: ImpactType

    init {
        assert(conf.branchEditLength > 0)
        assert(conf.branchEditFocus in 0.0..1.0)
        assert(changeOperationWeights.map { e -> e.second }.reduce { d1, d2 -> d1 + d2 } == 100)

        //Assure convergence of the nondeterministic edit algorithm:
        assert(getWeightOf("ADD_SIMPLE") + getWeightOf("ADD_REGION") >= 1)

        impactType = if (conf.atomicCounting) {
            ImpactType.ATOMIC
        } else {
            ImpactType.SUM
        }

        var start = 0
        for (element in changeOperationWeights) {
            operationDistribution.add(Pair(Pair(start, start + element.second), element.first))
            start += element.second
        }

        clearStage()
    }

    private fun getWeightOf(operation: String): Int {
        return changeOperationWeights.find { values -> values.first == operation }?.second ?: 0
    }

    /**
     * Loop until the desired edit length is reached.
     * Iteration time of the implemented naive (try-check-rollback) algorithm is non-deterministic but termination is
     * guaranteed because addNode is always viable as a 1-impact operation.
     */
    fun exec(persistGraph: (Stage) -> Unit, persistDeltas: (Stage) -> Unit): Stage {

        var currentEditLength = 0
        var workingRegionName: String? = null

        conf.atomicCounting

        while (currentEditLength < conf.branchEditLength) {

            val p = random.nextInt(0, 100)
            var operation: String? = null

            for (candidate in operationDistribution) {
                val interval = candidate.first
                if (interval.first <= p && p < interval.second) {
                    operation = candidate.second
                    break
                }
            }

            val allRegions = stage.graph.getRegionsRecursive().toList()
            var workingRegion: Region? = allRegions.find { r -> r.name == workingRegionName }

            val changeRegionP = random.nextDouble(0.0, 1.0)
            if (allRegions.isNotEmpty() && (changeRegionP > conf.branchEditFocus || workingRegion == null)) {
                workingRegion = allRegions[random.nextInt(0, allRegions.size)]
            }
            workingRegionName = workingRegion?.name

            val impact = executeOnStageWithImpact(operation, workingRegion, impactType)

            if (currentEditLength + impact <= conf.branchEditLength) {
                //apply stage
                applyStageGlobal()
                currentEditLength += impact

                if(impact > 0 && conf.stepwiseExport){
                    persistGraph(Stage(globalGraph, globalDeltaSequence))
                    persistDeltas(Stage(globalGraph, globalDeltaSequence))
                }

            }
            //clear stage (and reject if not applied beforehand)
            clearStage()
        }

        val finalStage = Stage(globalGraph, globalDeltaSequence)
        if(!conf.stepwiseExport){
            persistGraph(finalStage)
            persistDeltas(finalStage)
        }

        return finalStage
    }

    private fun executeOnStageWithImpact(operation: String?, workingRegion: Region?, impactType: ImpactType): Int {
        if (operation == null) return 0
        //println(operation)
        when (operation) {
            "ADD_SIMPLE" -> addSimpleNode(workingRegion)
            "ADD_REGION" -> addRegion(workingRegion)
            "DELETE_NODE" -> deleteNode(workingRegion)
            "MOVE_NODE" -> moveNode(workingRegion)
            "CHANGE_LABEL" -> changeLabel(workingRegion)
            "ADD_EDGE" -> addEdge(workingRegion)
            "DELETE_EDGE" -> deleteEdge(workingRegion)
        }

        val impact = when (impactType) {
            ImpactType.ATOMIC -> stage.deltaSequence.getAtomicLength()
            ImpactType.SUM -> {
                assert(stage.deltaSequence.getLength() <= 1)
                return stage.deltaSequence.getLength()
            }
        }

        return impact
    }

    private fun applyStageGlobal() {
        globalDeltaSequence.pushOperations(stage.deltaSequence.operations())
        globalGraph = stage.graph
    }

    private fun clearStage() {
        stage = Stage(globalGraph.deepCopy(), DeltaSequence())
    }

    private fun randomGlobalStageRegion(): Region? {
        val allRegions = stage.graph.getRegionsRecursive().toList()
        if(allRegions.isEmpty()) return null
        val p = random.nextInt(0, allRegions.size + 1)
        if ( p == allRegions.size) return null
        return allRegions[p]
    }

    /**
     * Adds a new [SimpleNode] to the specified (sub-)[Graph].
     * This operation does not add edges. Therefore, the connectedness property may become violated.
     *
     * @param region the region to add the new [Node] to. If null, the root [Graph] is used.
     */
    private fun addSimpleNode(region: Region?) {
        val node = SimpleNode("NE$simpleNodeNameIncrement", SimpleNode.randomLabel(random))
        simpleNodeNameIncrement++
        val targetGraph = region?.graph ?: stage.graph
        targetGraph.nodes.add(node)
        val op = AddNode(DeltaOperation.generateId(), node.name, NodeType.SIMPLE, region?.name ?: "")
        stage.deltaSequence.pushOperation(op)
    }

    /**
     * Adds a new [Region] to the specified (sub-)[Graph].
     * This operation does not add edges. Therefore, the connectedness property may become violated.
     *
     * @param region the region to add the new [Node] to. If null, the root [Graph] is used.
     */
    private fun addRegion(region: Region?) {
        val node = Region("RE$regionNameIncrement", Graph())
        regionNameIncrement++
        val targetGraph = region?.graph ?: stage.graph
        targetGraph.nodes.add(node)
        val op = AddNode(DeltaOperation.generateId(), node.name, NodeType.REGION, region?.name ?: "")
        stage.deltaSequence.pushOperation(op)
    }

    /**
     * If the given [Region] contains no [Node], this operation returns without a result.
     */
    private fun deleteNode(region: Region?) {
        val graph = region?.graph ?: stage.graph
        if (graph.nodes.isEmpty()) return
        val node = graph.randomNode(random)
        val op = deleteNode(region, node)
        stage.deltaSequence.pushOperation(op)
    }

    /**
     * Delete a [Node] from the given [Region].
     * This method assumes that the given Region contains the given Node.
     * This method removes the Node from the stage [Graph]. It also removes all [Edge]s from the stage Graph that
     * contain the Node as start or end.
     * If the given Node is a Region itself, the operation is executed recursively on all Nodes its subgraph contains.
     * The [DeleteNode] delta operation is returned. All recursive deletes are executed in order and part of the
     * returned object.
     *
     * @param region
     * @param node
     */
    private fun deleteNode(region: Region?, node: Node): DeleteNode {
        val graph = region?.graph ?: stage.graph
        if (node !is Region) {
            //identify deleted nodes
            //delete edges containing deleted nodes
            val connectedEdgeDeletes = deleteEdgesContaining(stage.graph, node)
            graph.nodes.remove(node)
            return DeleteNode(DeltaOperation.generateId(), node.name, LinkedList(), connectedEdgeDeletes.toMutableList(), region?.name ?: "")
        }
        //identify deleted nodes
        val impactedNodes = LinkedList(node.graph.nodes)
        val impactedNodeDeletes: MutableList<DeleteNode> = LinkedList<DeleteNode>()
        //delete edges targeting deleted nodes
        for (nodeToDelete in impactedNodes) {
            //walk recursive through deleted nodes with this function
            val deleteNode = deleteNode(node, nodeToDelete)
            impactedNodeDeletes.add(deleteNode)
        }
        val impactedEdgeDeletes = deleteEdgesContaining(stage.graph, node)
        graph.nodes.remove(node)

        return DeleteNode(DeltaOperation.generateId(), node.name, impactedNodeDeletes, impactedEdgeDeletes.toMutableList(), region?.name ?: "")
    }

    /**
     * This operation deletes all [Edge] objects from the staged [Graph] containing the given [Node] either as start
     * or end reference.
     * The staged Graph is manipulated by this operation.
     * The traversal happens recursively.
     */
    private fun deleteEdgesContaining(graph: Graph, node: Node): List<DeleteEdge> {
        val edgesToDelete = graph.edges.filter { e -> e.a == node || e.b == node }
        val deleteOperations: MutableList<DeleteEdge> = LinkedList<DeleteEdge>()
        for (edge in edgesToDelete){
            graph.edges.remove(edge)
            deleteOperations.add(DeleteEdge(DeltaOperation.generateId(), edge.a.name, edge.b.name))
        }
        graph.nodes.filterIsInstance<Region>().forEach { region ->
            deleteOperations.addAll(deleteEdgesContaining(region.graph, node))
        }
        return deleteOperations
    }

    /**
     * Delete an [Edge] from the staged [Graph].
     * If the given [Region] contains no Edge, this operation returns without a result.
     */
    private fun deleteEdge(region: Region?) {
        val graph = region?.graph ?: stage.graph
        if(graph.edges.isEmpty()) return
        val edge = graph.edges[random.nextInt(0, graph.edges.size)]
        graph.edges.remove(edge)
        val op = DeleteEdge(DeltaOperation.generateId(), edge.a.name, edge.b.name)
        stage.deltaSequence.pushOperation(op)
    }

    /**
     * Move a [Node] from the given [Region] to another [Region].
     * If the given Region contains no Nodes, this operation returns without a result.
     * If the graph contains only one Region, this operation returns without a result.
     * This operation also moves all [Edge]s to assure that each Edge is located within the [Graph] of its start Node.
     *
     * REGIONS CAN NOT BE MOVED BECAUSE THIS WOULD VIOLATE THE COMPOSITION TREE STRUCTURE!
     */
    private fun moveNode(region: Region?) {
        val graph = region?.graph ?: stage.graph
        val simpleNodes = graph.nodes.filterIsInstance<SimpleNode>()
        if(simpleNodes.isEmpty()) return
        val allRegions = stage.graph.getRegionsRecursive().toList()
        if(allRegions.size < 2) return
        var targetRegion: Region? = null
        do {
            val p = random.nextInt(0, allRegions.size + 1)
            if(p < allRegions.size){
                targetRegion = allRegions[p]
            }
        } while (targetRegion == region)
        val node = simpleNodes[random.nextInt(0, simpleNodes.size)]
        graph.nodes.remove(node)
        val targetGraph = targetRegion?.graph ?: stage.graph
        targetGraph.nodes.add(node)
        val edgeImplications = moveEdgesWithNode(region, targetRegion, node)
        stage.deltaSequence.pushOperation(MoveNode(DeltaOperation.generateId(), node.name,
            targetRegion?.name ?: "", region?.name ?: "", edgeImplications))
    }

    private fun moveEdgesWithNode(oldRegion: Region?, newRegion: Region?, node: Node): MutableList<MoveEdge>{
        val oldGraph = oldRegion?.graph ?: stage.graph
        val newGraph = newRegion?.graph ?: stage.graph
        val result: MutableList<MoveEdge> = LinkedList()
        val edgesToMove = oldGraph.edges.filter { e -> e.a == node }

        for(edge in edgesToMove){
            oldGraph.edges.remove(edge)
            newGraph.edges.add(edge)
            result.add( MoveEdge(DeltaOperation.generateId(), edge.a.name, edge.b.name,
                newRegion?.name ?: "", oldRegion?.name ?: ""))
        }
        return result
    }

    /**
     * If the given [Region] contains no [SimpleNode], this operation returns without a result.
     */
    private fun changeLabel(region: Region?) {
        val graph = region?.graph ?: stage.graph
        if (graph.nodes.isEmpty()) return
        val simpleNodesInGraph = graph.nodes.filterIsInstance<SimpleNode>()
        if (simpleNodesInGraph.isEmpty()) return
        val simpleNode = simpleNodesInGraph[random.nextInt(0, simpleNodesInGraph.size)]
        val oldLabel = simpleNode.label
        do {
            simpleNode.label = SimpleNode.randomLabel(random)
        } while (oldLabel == simpleNode.label)
        val op = ChangeLabel(DeltaOperation.generateId(), simpleNode.name, simpleNode.label, oldLabel)
        stage.deltaSequence.pushOperation(op)
    }

    /**
     * Add a new [Edge].
     * In certain random cases (especially if the given [Region] is empty), this operation
     * returns without a result.
     */
    private fun addEdge(region: Region?) {
        val p = random.nextDouble(0.0, 1.0)
        val isDistorted = p <= conf.edgeDistortion
        val graph = region?.graph ?: stage.graph
        if(graph.nodes.isEmpty()) return
        val nodeA: Node = graph.randomNode(random)
        val nodeB: Node = if (!isDistorted) {
            graph.randomNode(random)
        } else {
            val graphB = randomGlobalStageRegion()?.graph ?: stage.graph
            if(graphB.nodes.isEmpty()) return
            graphB.randomNode(random)
        }
        val edge = Edge(nodeA, nodeB)
        graph.edges.add(edge)
        val op = AddEdge(DeltaOperation.generateId(), edge.a.name, edge.b.name)
        stage.deltaSequence.pushOperation(op)
    }

}