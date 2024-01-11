/**
 * Copyright 2023 Karl Kegel
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
package graphmodel

import ecore.DeepComparable
import ecore.EObjectSource
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import util.GraphStats
import java.util.*
import kotlin.random.Random

class Graph (
    val nodes: MutableList<Node> = LinkedList<Node>(),
    val edges: MutableList<Edge> = LinkedList<Edge>(),
    private val predef: EObject? = null,
    val isRoot: Boolean = false
) : EObjectSource, DeepComparable {

    private val description = "Graph"
    private var buffer: EObject? = predef

    init {
        if(predef != null){
            val nodesComposition = predef.eClass().getEStructuralFeature("nodes")

            val eNodes = predef.eGet(nodesComposition) as java.util.List<EObject>
            val genSimpleNodes = eNodes.filter { e -> e.eClass().name == "SimpleNode" }.map { e ->
                SimpleNode.construct(
                    e
                )
            }
            val genRegions = eNodes.filter { e -> e.eClass().name == "Region" }.map { e -> Region.construct(e) }
            nodes.addAll(genSimpleNodes)
            nodes.addAll(genRegions)

            if(isRoot){
                constructEdgesFromPredef(getNodesRecursive())
            }
        }
    }

    fun getStats(recursive: Boolean): GraphStats {
        val stats = GraphStats(
            nodes.filterIsInstance<SimpleNode>().map { n -> n as SimpleNode }.toMutableSet(),
            nodes.filterIsInstance<Region>().map { n -> n as Region }.toMutableSet(),
            edges.toMutableSet()
        )
        if(recursive){
            val recursiveStats = stats.allRegions.map { r -> r.getStats(true) }
            recursiveStats.forEach{rstat ->
                stats.allSimpleNodes.addAll(rstat.allSimpleNodes)
                stats.allRegions.addAll(rstat.allRegions)
                stats.allEdges.addAll(rstat.allEdges)
            }
        }
        return stats
    }

    fun randomNode(random: Random): Node {
        return nodes[random.nextInt(0, nodes.size)]
    }

    fun countDistortedEdges(recursive: Boolean = false): Int {
        val countNonDistortedEdges = edges.count { edge ->
            nodes.contains(edge.a) && nodes.contains(edge.b)
        }
        var count = edges.size - countNonDistortedEdges

        if(recursive){
            val recursiveResults = nodes.filterIsInstance<Region>()
                .map { r -> r.graph.countDistortedEdges(true) }

            var subCount = 0
            for(v in recursiveResults){
                subCount += v
            }
            count += subCount
        }
        return count
    }

    fun isSinglePartition(): Boolean {
        if(nodes.isEmpty()) return true
        val partition: MutableSet<Node> = HashSet<Node>()
        val undistributedNodes: MutableSet<Node> = HashSet()
        undistributedNodes.addAll(nodes)
        partition.add(nodes[0])
        undistributedNodes.remove(nodes[0])

        while (undistributedNodes.size > 0){
            var progress = false
            for (unknownNode in undistributedNodes){
                for ( knownNode in partition ){
                    if(edges.find { e ->
                        (e.a == unknownNode && e.b == knownNode) || (e.a == knownNode && e.b == unknownNode)
                    } != null){
                        partition.add(unknownNode)
                        undistributedNodes.remove(unknownNode)
                        progress = true
                        break
                    }
                }
                if(progress) break;
            }
            if(!progress) return false
        }
        return partition.size == nodes.size
    }

    private fun getNodesRecursive(): Set<Node> {
        val nodeSet: MutableSet<Node> = HashSet()
        nodeSet.addAll(nodes)
        nodes.filterIsInstance<Region>().forEach { region ->
            nodeSet.addAll(region.graph.getNodesRecursive())
        }
        return nodeSet
    }

    private fun constructEdgesFromPredef(nodes: Set<Node>){
        val edgesComposition = predef!!.eClass().getEStructuralFeature("edges")
        val eEdges = predef.eGet(edgesComposition) as java.util.List<EObject>

        eEdges.forEach{edge ->
            val nodesComposition = edge.eClass().getEStructuralFeature("nodes")
            val edgeList = (edge.eGet(nodesComposition) as java.util.List<EObject>)
            val a = edgeList[0]
            val b = edgeList[1]
            val aName = a.eGet(a.eClass().getEStructuralFeature("name"), true) as String
            val bName = b.eGet(b.eClass().getEStructuralFeature("name"), true) as String
            //println("$aName <---> $bName")
            val aNode = nodes.find { n -> n.name == aName }!!
            val bNode = nodes.find { n -> n.name == bName }!!
            val genEdge = Edge(aNode, bNode)
            edges.add(genEdge)
        }

        this.nodes.filterIsInstance<Region>().forEach { region ->
            region.graph.constructEdgesFromPredef(nodes)
        }
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                                       label: EEnum?, nodeType: EEnum?): EObject {
        val graph = buffer ?: factory.create(classes[description])
        buffer = graph
        val nodesComposition = graph.eClass().getEStructuralFeature("nodes")
        val edgesComposition = graph.eClass().getEStructuralFeature("edges")
        if(filter.contains("Node")){
            graph.eSet(nodesComposition, LinkedList<Any>())
            val eSimpleNodes = nodes.filterIsInstance<SimpleNode>().map { n ->
                n.generate(classes, factory, filter, label, nodeType) }
            val eRegions = nodes.filterIsInstance<Region>().map { n ->
                n.generate(classes, factory, filter, label, nodeType) }
            val eNodes = LinkedList<EObject>()
            eNodes.addAll(eSimpleNodes)
            eNodes.addAll(eRegions)
            (graph.eGet(nodesComposition) as java.util.List<EObject>).addAll(eNodes)
        }
        if(filter.contains("Edge")){
            graph.eSet(edgesComposition, LinkedList<Any>())
            val eEdges = edges.map { edge -> edge.generate(classes, factory, filter, label, nodeType) }
            (graph.eGet(edgesComposition) as java.util.List<EObject>).addAll(eEdges)
            nodes.filterIsInstance<Region>().forEach{ r -> r.generate(classes, factory, filter, label, nodeType)}
        }
        return graph
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is Graph){
            if (nodes.size != other.nodes.size || edges.size != other.edges.size) return false
            for(node in nodes){
                if(other.nodes.find { otherNode -> otherNode.deepEquals(node) } == null) return false
            }
            for(edge in edges){
                if(other.edges.find { otherEdge -> otherEdge.deepEquals(edge) } == null) return false
            }
            return true
        }
        return false
    }


}