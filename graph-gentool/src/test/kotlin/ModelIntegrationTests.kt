
import meta.Configuration
import model.Graph
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

class ModelIntegrationTests {

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
        val graphA = Graph()
        val factoryA = GraphFactory(graphA, configuration, randomSeed)
        factoryA.exec()

        val graphB = Graph()
        val factoryB = GraphFactory(graphB, configuration, randomSeed)
        factoryB.exec()

        assertEquals(graphA.getStats(true).toString(), graphB.getStats(true).toString())
        assertTrue(graphA.deepEquals(graphB))
        assertTrue(graphB.deepEquals(graphA))
    }

    @Test
    fun modelDeepNotEqualsTest(){

        val configuration = Configuration(
            modelSize = 3000,
            edgesPerNode = 3.0,
            regionProbability = 0.1,
            edgeDistortion = 0.0
        )
        val graphA = Graph()
        val factoryA = GraphFactory(graphA, configuration, 10)
        factoryA.exec()

        val graphB = Graph()
        val factoryB = GraphFactory(graphB, configuration,20)
        factoryB.exec()

        assertEquals(graphA.getStats(true).toString(), graphB.getStats(true).toString())
        assertFalse(graphA.deepEquals(graphB))
        assertFalse(graphB.deepEquals(graphA))
    }

}