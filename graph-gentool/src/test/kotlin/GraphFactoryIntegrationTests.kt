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

import graphmodel.Graph
import graphmodel.Region
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import util.Configuration

class GraphFactoryIntegrationTests {

    private var randomSeed: Long = 0
    private var graph: Graph = Graph()

    @BeforeEach
    fun init(){
        graph = Graph()
        randomSeed = 7
    }

    @Test
    fun modelSizeMinimalModelNoNodesTest() {
        val configuration = Configuration(
            modelSize = 1
        )
        val factory = GraphFactory(graph, configuration)
        try {
            factory.exec()
            Assertions.fail<String>("Operation should not complete with success")
        } catch (e: Exception){
          assertEquals("Configuration must allow at least one node!", e.message)
        }
    }

    @Test
    fun modelSizeMinimalModelTest() {
        val configuration = Configuration(
            modelSize = 2
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val stats = graph.getStats(true)
        assertEquals(1, stats.allSimpleNodes.size)
        assertEquals(0, stats.allRegions.size)
        assertEquals(1, stats.allEdges.size)
    }

    @Test
    fun modelSizeLargeModelTest() {
        val configuration = Configuration(
            modelSize = 900,
            edgesPerNode = 2.0,
            regionProbability = 0.0
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val stats = graph.getStats(true)
        assertEquals(300, stats.allSimpleNodes.size)
        assertEquals(0, stats.allRegions.size)
        assertEquals(600, stats.allEdges.size)
    }

    @Test
    fun generationEdgesPerNodeTest() {
        val configuration = Configuration(
            modelSize = 900,
            edgesPerNode = 2.0,
            regionProbability = 0.3,
            allowPartitions = false
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val stats = graph.getStats(true)
        assertEquals(300, stats.allSimpleNodes.size + stats.allRegions.size)
        assertEquals(600, stats.allEdges.size)
    }

    @Test
    fun generationToManyNodesTest() {
        val configuration = Configuration(
            modelSize = 90,
            edgesPerNode = 0.5,
            regionProbability = 0.0,
            allowPartitions = false
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val stats = graph.getStats(true)
        assertEquals(60, stats.allSimpleNodes.size)
        assertEquals(59, stats.allEdges.size)
    }

    @Test
    fun generationToManyNodesAllowPartitionsTest() {
        val configuration = Configuration(
            modelSize = 90,
            edgesPerNode = 0.5,
            regionProbability = 0.0,
            allowPartitions = true
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val stats = graph.getStats(true)
        assertEquals(60, stats.allSimpleNodes.size)
        assertEquals(30, stats.allEdges.size)
    }

    @Test
    fun regionProbabilityLargeModelTest() {
        val configuration = Configuration(
            modelSize = 1000,
            edgesPerNode = 0.0,
            regionProbability = 0.1,
            allowPartitions = true
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val stats = graph.getStats(true)
        assertEquals(stats.allRegions.size.toDouble(), 100.0, 1.0 )
    }

    @Test
    fun distortedEdgesProbabilityLargeModelNoRegionsTest() {
        val configuration = Configuration(
            modelSize = 900,
            edgesPerNode = 2.0,
            regionProbability = 0.0,
            edgeDistortion = 0.1,
            allowPartitions = true
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val numberDistortedEdges = graph.countDistortedEdges(recursive = true)
        assertEquals(0, numberDistortedEdges)
    }

    @Test
    fun distortedEdgesProbabilityLargeModelTest() {
        val configuration = Configuration(
            modelSize = 900,
            edgesPerNode = 2.0,
            regionProbability = 0.2,
            edgeDistortion = 0.1,
            allowPartitions = true
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val numberDistortedEdges = graph.countDistortedEdges(recursive = true)
        val stats = graph.getStats(true)
        assertEquals(600, stats.allEdges.size)
        assertEquals(60.0, numberDistortedEdges.toDouble(), 10.0)
    }

    @Test
    fun regionsHierarchicallyContainmentTest(){
        val configuration = Configuration(
            modelSize = 200,
            edgesPerNode = 1.0,
            regionProbability = 0.1,
            allowPartitions = true
        )
        val factory = GraphFactory(graph, configuration,)
        factory.exec()
        val stats = graph.getStats(true)
        assertEquals(10, stats.allRegions.size)

        try {

            //if cyclic, this will lead to an endless recursion and finally a stack-overflow error
            //if no containment, the result contains not all regions
            val visitedRegions = visitRegions(graph)
            assertEquals(10, visitedRegions.size)

        }catch (e: StackOverflowError){
            Assertions.fail<String>("Operation should terminate if hierarchy is acyclic containment!")
        }

    }

    /**
     * Tests that the algorithm used to handle the above test works
     */
    @Test
    fun detectArtificialCyclesTest(){
        val configuration = Configuration(
            modelSize = 200,
            edgesPerNode = 1.0,
            regionProbability = 0.1,
            allowPartitions = true
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val stats = graph.getStats(true)
        assertEquals(10, stats.allRegions.size)

        val regionList = stats.allRegions.toList()
        regionList[5].graph.nodes.add(regionList[0])

        try {
            //if cyclic, this will lead to an endless recursion and finally a stack-overflow error
            //if no containment, the result contains not all regions
            val visitedRegions = visitRegions(graph)
            Assertions.fail<String>("Operation must not terminate if it contains cycles")
        }catch (e: StackOverflowError){
            Assertions.assertNotNull(e)
        }

    }

    private fun visitRegions(graph: Graph): Set<Region> {
        val regions = graph.getStats(false).allRegions
        val result: MutableSet<Region> = HashSet<Region>()
        result.addAll(regions)
        regions.forEach{r -> result.addAll(visitRegions(r.graph))}
        return result
    }

    @Test
    fun equallyDistributedSimpleNodesOverRegionsTest(){
        val configuration = Configuration(
            modelSize = 20,
            edgesPerNode = 0.0,
            regionProbability = 0.25,
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val numberDistortedEdges = graph.countDistortedEdges(recursive = true)
        val stats = graph.getStats(true)
        assertEquals(15, stats.allSimpleNodes.size)
        assertEquals(5, stats.allRegions.size)
        val allSubGraphs: MutableSet<Graph> = HashSet<Graph>()
        allSubGraphs.add(graph)
        allSubGraphs.addAll(stats.allRegions.map { r -> r.graph })
        val sizes = allSubGraphs.map { g -> g.getStats(false).allSimpleNodes.size }
        for(g in allSubGraphs){
            val subStats = g.getStats(recursive = false)
            assertTrue(subStats.allSimpleNodes.size == 2 || subStats.allSimpleNodes.size == 3)
        }
    }

    @Test
    fun isSinglePartitionPerGraphTest(){
        val configuration = Configuration(
            modelSize = 900,
            edgesPerNode = 2.0,
            regionProbability = 0.2,
            edgeDistortion = 0.1,
            allowPartitions = false
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val stats = graph.getStats(true)
        assertEquals(60, stats.allRegions.size)
        val allSubGraphs: MutableSet<Graph> = HashSet<Graph>()
        allSubGraphs.add(graph)
        allSubGraphs.addAll(stats.allRegions.map { r -> r.graph })
        for(g in allSubGraphs){
            assertTrue(g.isSinglePartition())
        }
    }

    @Test
    fun isNoSinglePartitionPerGraphTest(){
        val configuration = Configuration(
            modelSize = 900,
            edgesPerNode = 0.5,
            regionProbability = 0.2,
            allowPartitions = true
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val stats = graph.getStats(true)
        val allSubGraphs: MutableSet<Graph> = HashSet<Graph>()
        allSubGraphs.add(graph)
        allSubGraphs.addAll(stats.allRegions.map { r -> r.graph })
        Assertions.assertFalse(allSubGraphs.map { g -> g.isSinglePartition() }.reduce{a, b -> a && b})
    }

    @Test
    fun isEnforcedSinglePartitionPerGraphTest(){
        val configuration = Configuration(
            modelSize = 900,
            edgesPerNode = 0.5,
            regionProbability = 0.2,
            allowPartitions = false
        )
        val factory = GraphFactory(graph, configuration)
        factory.exec()
        val stats = graph.getStats(true)
        val allSubGraphs: MutableSet<Graph> = HashSet<Graph>()
        allSubGraphs.add(graph)
        allSubGraphs.addAll(stats.allRegions.map { r -> r.graph })
        assertTrue(allSubGraphs.map { g -> g.isSinglePartition() }.reduce{a, b -> a && b})
        assertTrue(stats.allEdges.size > 300)
    }

}