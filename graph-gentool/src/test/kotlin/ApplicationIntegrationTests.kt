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

import ecore.EcoreHandler
import graphmodel.Edge
import graphmodel.Graph
import graphmodel.Node
import org.apache.commons.io.FileUtils
import org.eclipse.emf.common.util.URI
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.Configuration
import java.io.File
import java.util.*

class ApplicationIntegrationTests {

    private var output: String? = null

    @AfterEach
    fun cleanTestFiles() {
        try {
            FileUtils.forceDelete(File("./out"))
        } catch (e: Exception) {
            println("WARNING: No test cleanup required")
        }
    }

    @Test
    fun writeAndReadGraphEqualsTest() {

        val configuration = Configuration(
            modelSize = 3000,
            edgesPerNode = 3.0,
            regionProbability = 0.2,
            branchNumber = 0,
            branchEditLength = 0,
            edgeDistortion = 0.1,
            outputPath = "./out/test"
        )

        val outputGraph = runWithConfig(configuration).baseGraph

        //there should be a non-empty file:
        val expectedOutputURI = URI.createFileURI(File(configuration.outputPath + "/base.labelgraph").absolutePath)

        val metamodelPath: String = object {}.javaClass.getResource("labelgraph.ecore")!!.path
        val metamodelURI = URI.createFileURI(metamodelPath)
        val ecoreHandler = EcoreHandler(metamodelURI, expectedOutputURI, "labelgraph")

        val graphRoot = ecoreHandler.getModelRoot()
        val inputGraph = Graph(LinkedList<Node>(), LinkedList<Edge>(), graphRoot, isRoot = true)

        Assertions.assertEquals(outputGraph.getStats(true).toString(), inputGraph.getStats(true).toString())
        Assertions.assertTrue(outputGraph.deepEquals(inputGraph))
    }

    @Test
    fun writeAndReadDeltaSequenceEqualsTest() {
        //TODO
        Assertions.assertTrue(true)
    }

    @Test
    fun simpleWorkflowExecutionTest() {

        for (i in 0..100) {

            val configuration = Configuration(
                modelSize = 100,
                edgesPerNode = 3.0,
                regionProbability = 0.2,
                allowPartitions = false,
                edgeDistortion = 0.1,
                outputPath = "./out/test",
                branchNumber = 10,
                branchEditLength = 20,
                branchEditFocus = 0.8,
                atomicCounting = true,
                randomSeed = i
            )

            val outputEnv = runWithConfig(configuration)

            Assertions.assertEquals(10, outputEnv.branchDeltas.size)
            Assertions.assertEquals(10, outputEnv.branchGraphs.size)

            for (deltaSequence in outputEnv.branchDeltas) {
                Assertions.assertEquals(20, deltaSequence.getAtomicLength())
            }
        }

    }

    @Test
    fun largeModelWorkflowExecutionTest() {

        val configuration = Configuration(
            modelSize = 5000,
            edgesPerNode = 3.0,
            regionProbability = 0.2,
            allowPartitions = false,
            edgeDistortion = 0.1,
            outputPath = "./out/test",
            branchNumber = 10,
            branchEditLength = 100,
            branchEditFocus = 0.8,
            atomicCounting = true,
            randomSeed = 100
        )

        val outputEnv = runWithConfig(configuration)

        Assertions.assertEquals(10, outputEnv.branchDeltas.size)
        Assertions.assertEquals(10, outputEnv.branchGraphs.size)

        for (deltaSequence in outputEnv.branchDeltas) {
            Assertions.assertEquals(100, deltaSequence.getAtomicLength())
        }
    }

}