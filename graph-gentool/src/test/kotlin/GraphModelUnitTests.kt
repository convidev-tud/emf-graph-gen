
import graphmodel.Graph
import graphmodel.Label
import graphmodel.Region
import graphmodel.SimpleNode
import util.Configuration
import kotlin.test.*

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

class GraphModelUnitTests {

    @Test
    fun modelDeepEqualsTest(){

        //The same random seed for both generations must lead to the same result
        val randomSeed: Long = 100

        val configuration = Configuration(
            modelSize = 3000,
            edgesPerNode = 3.0,
            regionProbability = 0.1,
            edgeDistortion = 0.2
        )
        val graphA = Graph(isRoot = true)
        val factoryA = GraphFactory(graphA, configuration)
        factoryA.exec()

        val graphB = Graph(isRoot = true)
        val factoryB = GraphFactory(graphB, configuration)
        factoryB.exec()

        val statsA = graphA.getStats(true).toString()
        val statsB = graphB.getStats(true).toString()
        assertEquals(statsA, statsB)
        assertTrue(graphA.deepEquals(graphB))
        assertTrue(graphB.deepEquals(graphA))
    }

    @Test
    fun modelDeepNotEqualsTest(){

        val configuration = Configuration(
            modelSize = 3000,
            edgesPerNode = 3.0,
            regionProbability = 0.1,
            edgeDistortion = 0.0,
            randomSeed = 10
        )
        val graphA = Graph(isRoot = true)
        val factoryA = GraphFactory(graphA, configuration)
        factoryA.exec()

        val configuration2 = Configuration(
            modelSize = 3000,
            edgesPerNode = 3.0,
            regionProbability = 0.1,
            edgeDistortion = 0.0,
            randomSeed = 20
        )

        val graphB = Graph(isRoot = true)
        val factoryB = GraphFactory(graphB, configuration2)
        factoryB.exec()

        assertEquals(graphA.getStats(true).toString(), graphB.getStats(true).toString())
        assertFalse(graphA.deepEquals(graphB))
        assertFalse(graphB.deepEquals(graphA))
    }

    @Test
    fun deepCopyDeepEqualsTest0(){
        val configuration = Configuration(
            modelSize = 3000,
            edgesPerNode = 3.0,
            regionProbability = 0.1,
            edgeDistortion = 0.2,
            randomSeed = 10
        )
        val graph = Graph(isRoot = true)
        val factory = GraphFactory(graph, configuration)
        factory.exec()

        val graphCopy = graph.deepCopy()

        //They are not identical references:
        assertNotEquals(graph, graphCopy)

        //The internal deepEquals is true however because of the same structure:
        assertEquals(graph.getStats(true).toString(), graphCopy.getStats(true).toString())
        assertTrue(graph.deepEquals(graphCopy))
        assertTrue(graphCopy.deepEquals(graph))

        //If we manipulate the copy, the structural equality is lost (change happens only in one object)
        graphCopy.nodes.filterIsInstance<Region>().first().graph.nodes.add(SimpleNode("N_FOO", Label.BLUE))
        assertFalse(graph.deepEquals(graphCopy))
    }

    @Test
    fun deepCopyDeepEqualsTest1(){
        val configuration = Configuration(
            modelSize = 3000,
            edgesPerNode = 3.0,
            regionProbability = 0.1,
            edgeDistortion = 0.2,
            randomSeed = 10
        )
        val graph = Graph(isRoot = true)
        val factory = GraphFactory(graph, configuration)
        factory.exec()

        val graphCopy = graph.deepCopy()

        //They are not identical references:
        assertNotEquals(graph, graphCopy)

        //The internal deepEquals is true however because of the same structure:
        assertEquals(graph.getStats(true).toString(), graphCopy.getStats(true).toString())
        assertTrue(graph.deepEquals(graphCopy))
        assertTrue(graphCopy.deepEquals(graph))

        //If we manipulate the copy, the structural equality is lost (change happens only in one object)
        graphCopy.nodes.filterIsInstance<Region>().first().graph.nodes.add(SimpleNode("N_FOO", Label.BLUE))
        assertFalse(graph.deepEquals(graphCopy))
    }

    @Test
    fun deepCopyDeepEqualsTest2(){
        val configuration = Configuration(
            modelSize = 30000,
            edgesPerNode = 3.0,
            allowPartitions = false,
            regionProbability = 0.1,
            edgeDistortion = 0.2,
            randomSeed = 2
        )
        val graph = Graph(isRoot = true)
        val factory = GraphFactory(graph, configuration)
        factory.exec()

        val graphCopy = graph.deepCopy()

        //They are not identical references:
        assertNotEquals(graph, graphCopy)

        //The internal deepEquals is true however because of the same structure:
        assertEquals(graph.getStats(true).toString(), graphCopy.getStats(true).toString())
        assertTrue(graph.deepEquals(graphCopy))
        assertTrue(graphCopy.deepEquals(graph))

        //If we manipulate the copy, the structural equality is lost (change happens only in one object)
        graphCopy.nodes.filterIsInstance<Region>().first().graph.nodes.add(SimpleNode("N_FOO", Label.BLUE))
        assertFalse(graph.deepEquals(graphCopy))
    }

    @Test
    fun deepCopyDeepEqualsTest3(){
        val configuration = Configuration(
            modelSize = 300,
            edgesPerNode = 3.0,
            regionProbability = 0.1,
            edgeDistortion = 0.2,
            randomSeed = 1000
        )
        val graph = Graph(isRoot = true)
        val factory = GraphFactory(graph, configuration)
        factory.exec()

        val graphCopy = graph.deepCopy()

        //They are not identical references:
        assertNotEquals(graph, graphCopy)

        //The internal deepEquals is true however because of the same structure:
        assertEquals(graph.getStats(true).toString(), graphCopy.getStats(true).toString())
        assertTrue(graph.deepEquals(graphCopy))
        assertTrue(graphCopy.deepEquals(graph))

        //If we manipulate the copy, the structural equality is lost (change happens only in one object)
        graphCopy.nodes.filterIsInstance<Region>().first().graph.nodes.add(SimpleNode("N_FOO", Label.BLUE))
        assertFalse(graph.deepEquals(graphCopy))
    }

    @Test
    fun deepCopyDeepEqualsTest4(){
        val configuration = Configuration(
            modelSize = 30700,
            edgesPerNode = 3.0,
            regionProbability = 0.2,
            allowPartitions = false,
            edgeDistortion = 0.2,
            randomSeed = 10635
        )
        val graph = Graph(isRoot = true)
        val factory = GraphFactory(graph, configuration)
        factory.exec()

        val graphCopy = graph.deepCopy()

        //They are not identical references:
        assertNotEquals(graph, graphCopy)

        //The internal deepEquals is true however because of the same structure:
        assertEquals(graph.getStats(true).toString(), graphCopy.getStats(true).toString())
        assertTrue(graph.deepEquals(graphCopy))
        assertTrue(graphCopy.deepEquals(graph))

        //If we manipulate the copy, the structural equality is lost (change happens only in one object)
        graphCopy.nodes.filterIsInstance<Region>().first().graph.nodes.add(SimpleNode("N_FOO", Label.BLUE))
        assertFalse(graph.deepEquals(graphCopy))
    }

}