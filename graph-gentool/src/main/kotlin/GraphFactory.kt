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
import meta.Configuration
import meta.GraphStats
import model.*
import java.util.*
import kotlin.random.Random

class GraphFactory(private val root: Graph, private val conf: Configuration, randomSeed: Long = 42) {

    private val random: Random = Random(randomSeed)
    private val rootStats: GraphStats = root.getStats(true)

    init {
        assert(rootStats.allEdges.size == 0)
        assert(rootStats.allRegions.size == 0)
        assert(rootStats.allSimpleNodes.size == 0)
    }

    fun exec(): Unit{
        //calculate target stats
        val numberOfNodes: Int = (conf.modelSize / (1 + conf.edgesPerNode)).toInt()
        val numberOfRegions: Int = (numberOfNodes * conf.regionProbability).toInt()
        val numberOfSimpleNodes: Int = numberOfNodes - numberOfRegions
        val numberOfEdges: Int = conf.modelSize - numberOfNodes
        val numberOfDistortedEdges: Int = (numberOfEdges * conf.edgeDistortion).toInt()

        if(numberOfNodes == 0) throw IllegalArgumentException("Configuration must allow at least one node!")

        //create regions
        val regions = regions(numberOfRegions)
        //create sub graphs for each region
        val subGraphs = subGraphs(regions, root)
        // Security assertion to prevent rounding mistakes leading to zero regions
        assert(subGraphs.size >= 1)
        //create simple nodes
        val simpleNodes = simpleNodes(numberOfSimpleNodes)

        //distribute simple nodes evenly over regions
        distributeSimpleNodes(simpleNodes, subGraphs)
        //distribute sub graphs (Nodes that are Regions) over other Regions
        distributeSubGraphs(regions, root)

        val edges: MutableList<Edge> = LinkedList<Edge>()

        addMandatoryEdges(edges, subGraphs)

        val allNodes = ArrayList<Node>()
        allNodes.addAll(simpleNodes)
        allNodes.addAll(regions)

        //add random connections otherwise
        fillMissingEdges(edges, subGraphs, numberOfEdges, numberOfDistortedEdges)
    }

    private fun regions(numberOfRegions: Int): MutableList<Region> {
        val regions: MutableList<Region> = LinkedList<Region>()
        repeat(numberOfRegions) { i ->
            val r = Region("R$i", Graph(LinkedList<Node>(), LinkedList<Edge>(), null))
            regions.add(r)
        }
        return regions
    }

    private fun subGraphs(regions: List<Region>, root: Graph): MutableList<Graph> {
        val subGraphs: MutableList<Graph> = ArrayList<Graph>()
        subGraphs.add(0, root)
        subGraphs.addAll(regions.map { r -> r.graph }.toMutableList())
        return subGraphs
    }

    private fun simpleNodes(numberOfSimpleNodes: Int): MutableList<SimpleNode> {
        val simpleNodes: MutableList<SimpleNode> = LinkedList<SimpleNode>()
        repeat(numberOfSimpleNodes) { i ->
            val n = SimpleNode("N$i", SimpleNode.randomLabel())
            simpleNodes.add(n)
        }
        return simpleNodes
    }

    private fun distributeSimpleNodes(simpleNodes: List<SimpleNode>, subGraphs: List<Graph>): Unit {
        var distributedSimpleNodes: Int = 0
        while (distributedSimpleNodes < simpleNodes.size) {
            for (graph in subGraphs) {
                if (distributedSimpleNodes == simpleNodes.size) break
                graph.nodes.add(simpleNodes[distributedSimpleNodes])
                distributedSimpleNodes++
            }
        }
    }

    /**
     * distribute regions over graphs (1 / region probability = number of regions per region)
     * this must not have cycles, as a result, regions can only be added to graphs (of regions) that are already added
     * the distribution is not evenly, regions with lower indices will have more sub-regions, however this is realistic
     */
    private fun distributeSubGraphs(regions: List<Region>, root: Graph): Unit {
        val distributedSubGraphs: MutableList<Graph> = LinkedList<Graph>()
        distributedSubGraphs.add(root)

        var distributedRegions = 0
        while (distributedRegions < regions.size){
            val randomIndex = random.nextInt(0, distributedSubGraphs.size)
            val nextRegion = regions[distributedRegions]
            distributedSubGraphs[randomIndex].nodes.add(nextRegion)
            distributedSubGraphs.add(nextRegion.graph)
            distributedRegions++
        }
    }

    private fun addMandatoryEdges(edges: MutableList<Edge>, subGraphs: List<Graph>): Unit {
        //if everything has to be connected, start by systematically connecting the nodes of each sub-graph
        if(!conf.allowPartitions){
            subGraphs.forEach { graph ->

                //only do something if number of nodes is larger then 1
                if(graph.nodes.size > 1){

                    //remember the connected nodes (put the first node inside)
                    //then, connect each not connected node to an already connected node
                    //this forms a hierarchical connected structure (cycle-free)
                    val connectedNodes: MutableList<Node> = LinkedList()
                    connectedNodes.add(graph.nodes[0])

                    var nodeIndex = 1
                    while (nodeIndex < graph.nodes.size){
                        val connectedNodeSource = connectedNodes[random.nextInt(0, connectedNodes.size)]
                        val unconnectedNodeTarget = graph.nodes[nodeIndex]
                        val edge = Edge(connectedNodeSource, unconnectedNodeTarget)
                        graph.edges.add(edge)
                        edges.add(edge)
                        connectedNodes.add(unconnectedNodeTarget)
                        nodeIndex++
                    }
                }
            }
        }
    }

    private fun fillMissingEdges(edges: MutableList<Edge>, subGraphs: List<Graph>,
                                 numberOfEdges: Int, numberOfDistortedEdges: Int): Unit {
        if(edges.size < numberOfEdges){
            val missingEdges = numberOfEdges - edges.size
            //the existing edges are not distorted (because they connect nodes in Regions together)
            //try to keep the distorted edges in case of too few remaining edges
            var missingDistortedEdges = numberOfDistortedEdges.coerceAtMost(missingEdges)
            var missingNormalEdges = (missingEdges - missingDistortedEdges).coerceAtLeast(0)

            while (missingDistortedEdges > 0){
                val randomGraphSource = subGraphs[random.nextInt(0, subGraphs.size)]
                val randomGraphTarget = subGraphs[random.nextInt(0, subGraphs.size)]
                val randomCrossRegionNodeSource = randomGraphSource.randomNode(random)
                val randomCrossRegionNodeTarget = randomGraphTarget.randomNode(random)
                val edge = Edge(randomCrossRegionNodeSource, randomCrossRegionNodeTarget)
                randomGraphSource.edges.add(edge)
                edges.add(edge)
                missingDistortedEdges--
            }

            while (missingNormalEdges > 0){
                val randomGraph = subGraphs[random.nextInt(0, subGraphs.size)]
                val randomNodeSource = randomGraph.randomNode(random)
                val randomNodeTarget = randomGraph.randomNode(random)
                val edge = Edge(randomNodeSource, randomNodeTarget)
                randomGraph.edges.add(edge)
                edges.add(edge)
                missingNormalEdges--
            }

        }
    }

}