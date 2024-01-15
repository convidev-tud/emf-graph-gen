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
    private val conf: Configuration
) {

    private val random: Random = Random(conf.randomSeed)
    private val rootStats: GraphStats = graph.getStats(true)

    private var simpleNodeNameIncrement = 0
    private var regionNameIncrement = 0

    /**
     * Viable operations are:
     *  - Add Node
     *      - Simple Node
     *      - Region
     *  - Delete Node
     *  - Move Node
     *  - Change Label
     *  - Add Edge
     *  - Move Edge (implicitly only)
     *  - Delete Edge
     */
    //TODO config file with edit weights / probabilities
    private val changeOperationWeights: Map<String, Int> = mapOf(
        Pair("ADD_SIMPLE", 18),
        Pair("ADD_REGION", 2),
        Pair("DELETE_NODE", 20),
        Pair("MOVE_NODE", 20),
        Pair("CHANGE_LABEL", 20),
        Pair("ADD_EDGE", 10),
        Pair("DELETE_EDGE", 10)
    )

    private val operationDistribution: MutableMap<Pair<Int, Int>, String> = HashMap()

    private lateinit var stage: Stage

    private val globalDeltaSequence: DeltaSequence = DeltaSequence()
    private var globalGraph: Graph = graph
    private val impactType: ImpactType

    init {
        assert(conf.branchEditLength > 0)
        assert(conf.branchEditFocus in 0.0..1.0)
        assert(changeOperationWeights.values.reduce { d1, d2 -> d1 + d2 } == 100)

        impactType = if (conf.atomicCounting) {
            ImpactType.ATOMIC
        } else {
            ImpactType.SUM
        }

        var start = 0
        for (element in changeOperationWeights) {
            operationDistribution[Pair(start, start + element.value)] = element.key
            start += element.value
        }

        clearStage()
    }

    /**
     * Loop until the desired edit length is reached.
     * Iteration time of the implemented naive (try-check-rollback) algorithm is non-deterministic but termination is
     * guaranteed because addNode is always viable as a 1-impact operation.
     */
    fun exec(): Stage {
        var currentEditLength = 0
        var workingRegionName: String? = null

        while (currentEditLength <= conf.branchEditLength) {

            val p = random.nextInt(0, 100)
            var operation: String? = null

            for (candidate in operationDistribution) {
                if (candidate.key.first <= p && p < candidate.key.second) {
                    operation = candidate.value
                    break
                }
            }

            val allRegions = stage.graph.getRegionsRecursive().toList()
            var workingRegion = allRegions.find { r -> r.name == workingRegionName }

            val changeRegionP = random.nextDouble(0.0, 1.0)
            if (changeRegionP > conf.branchEditFocus || workingRegion == null) {
                workingRegion = allRegions[random.nextInt(0, allRegions.size)]
            }
            workingRegionName = workingRegion.name

            val impact = executeOnStageWithImpact(operation, workingRegion, impactType)

            if (currentEditLength + impact <= conf.branchEditLength) {
                //apply stage
                applyStageGlobal()
                currentEditLength += impact
            }

            //clear stage (and reject if not applied beforehand)
            clearStage()
        }
        return Stage(globalGraph, globalDeltaSequence)
    }

    fun executeOnStageWithImpact(operation: String?, workingRegion: Region?, impactType: ImpactType): Int {
        if (operation == null) return 0

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
            ImpactType.SUM -> stage.deltaSequence.getLength()
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
        val p = random.nextInt(0, allRegions.size + 1)
        if ( p == allRegions.size + 1) return null
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
        val op = AddNode(node, NodeType.SIMPLE, region)
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
        val op = AddNode(node, NodeType.REGION, region)
        stage.deltaSequence.pushOperation(op)
    }

    private fun deleteNode(region: Region?) {
        //TODO
    }

    private fun moveNode(region: Region?) {
        //TODO
    }

    /**
     *
     */
    private fun changeLabel(region: Region?) {
        val graph = region?.graph ?: stage.graph
        val simpleNodesInGraph = graph.nodes.filterIsInstance<SimpleNode>()
        val simpleNode = simpleNodesInGraph[random.nextInt(0, simpleNodesInGraph.size)]
        val oldLabel = simpleNode.label
        do {
            simpleNode.label = SimpleNode.randomLabel(random)
        } while (oldLabel == simpleNode.label)
        val op = ChangeLabel(simpleNode, simpleNode.label, oldLabel)
        stage.deltaSequence.pushOperation(op)
    }

    /**
     * 
     */
    private fun addEdge(region: Region?) {
        val p = random.nextDouble(0.0, 1.0)
        val isDistorted = p <= conf.edgeDistortion
        val graph = region?.graph ?: stage.graph
        val nodeA: Node = graph.randomNode(random)
        val nodeB: Node = if (!isDistorted) {
            graph.randomNode(random)
        } else {
            val graphB = randomGlobalStageRegion()?.graph ?: stage.graph
            graphB.randomNode(random)
        }
        val edge = Edge(nodeA, nodeB)
        graph.edges.add(edge)
        val op = AddEdge(edge)
        stage.deltaSequence.pushOperation(op)
    }

    private fun deleteEdge(region: Region?) {
        //TODO
    }

}