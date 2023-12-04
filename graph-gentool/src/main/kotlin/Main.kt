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
import meta.Configuration
import model.Edge
import model.Graph
import model.Node
import org.eclipse.emf.common.util.URI
import picocli.CommandLine
import java.io.File
import java.util.*
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@CommandLine.Command(name = "graph-gentool", mixinStandardHelpOptions = true, version = ["v.0.1"])
class Checksum : Callable<Int> {

    private val defaultConfiguration = Configuration()

    @CommandLine.Parameters(index = "0", description = ["output directory"])
    lateinit var output: File

    @CommandLine.Option(
        names = ["-s", "--model_size"],
        description = ["Sum of nodes and edges in the generated base model (INT)."]
    )
    var modelSize: Int = defaultConfiguration.modelSize

    @CommandLine.Option(
        names = ["-en", "--edges_per_node"],
        description = ["Number of edge elements per node element (DOUBLE)." +
                "This value influences edge_distortion and is influenced by allow_partitions."]
    )
    var edgesPerNode: Double = defaultConfiguration.edgesPerNode

    @CommandLine.Option(
        names = ["-ed", "--edge_distortion"],
        description = ["Probability 0...1 that an edge crosses region boundaries (DOUBLE)." +
                "This value is influenced by allow_partitions."]
    )
    var edgeDistortion: Double = defaultConfiguration.edgeDistortion

    @CommandLine.Option(
        names = ["-r", "--region_probability"],
        description = ["Probability 0...1 that a node is a region (DOUBLE)."]
    )
    var regionProbability: Double = defaultConfiguration.regionProbability

    @CommandLine.Option(
        names = ["-p", "--allow_partitions"],
        description = ["Allow (true) or forbid (false) the graph to have unconnected parts (BOOL)." +
                "This value influences edges_per_node."]
    )
    var allowPartitions: Boolean = defaultConfiguration.allowPartitions

    /**
     * TODO the features below
     */

    @CommandLine.Option(
        names = ["-bn", "--branch_number"],
        description = ["The number of branches (variants) to create from the final base model (INT)."]
    )
    var branchNumber: Int = defaultConfiguration.branchNumber

    @CommandLine.Option(
        names = ["-bel", "--branch_edit_length"],
        description = ["The number of additional edit operation performed on each branch (INT)."]
    )
    var branchEditLength: Int = defaultConfiguration.branchEditLength

    @CommandLine.Option(
        names = ["-bef", "--branch_edit_focus"],
        description = ["Probability factor 0..1 that the next edit operation happens in the same region as the previous." +
                "A value of 0.0 results in a evenly distribution over all regions."]
    )
    var branchEditFocus: Double = defaultConfiguration.branchEditFocus

    override fun call(): Int {
        runWithConfig(
            Configuration(
                output.path,
                modelSize,
                edgesPerNode,
                edgeDistortion,
                regionProbability,
                allowPartitions,
                branchNumber,
                branchEditLength,
                branchEditFocus
            )
        )
        return 0
    }
}

fun main(args: Array<String>) {
    exitProcess(CommandLine(Checksum()).execute(*args))
}

fun runWithConfig(configuration: Configuration) {
    val metamodelPath: String = object {}.javaClass.getResource("labelgraph.ecore")!!.path
    val metamodelURI = URI.createFileURI(metamodelPath)
    val ecoreHandler = EcoreHandler(metamodelURI, createOutputBaseModelFile(configuration))

    val factory = ecoreHandler.getModelFactory()
    val classMap = ecoreHandler.getClassMap()
    val label = ecoreHandler.getEnumMap()["Label"]!!
    val graphRoot = ecoreHandler.getModelRoot()
    val graph = Graph(LinkedList<Node>(), LinkedList<Edge>(), graphRoot)
    val originGraphFactory = GraphFactory(graph, configuration)

    val startTimeGenerate = System.currentTimeMillis()
    originGraphFactory.exec()
    val endTimeGenerate = System.currentTimeMillis()
    println("Generation Time: ${endTimeGenerate - startTimeGenerate} ms")

    println("Postprocessing ECORE models...")
    val startTimeWrite = System.currentTimeMillis()
    graph.generate(classMap, label, factory, setOf("Node"))
    graph.generate(classMap, label, factory, setOf("Edge"))
    ecoreHandler.saveModel()
    val endTimeWrite = System.currentTimeMillis()
    println("Postprocessing Time: ${endTimeWrite - startTimeWrite} ms")

    println("Creating Branches...")
    //TODO
}

fun createOutputBaseModelFile(configuration: Configuration): URI {
    val modelTemplate = File(object {}.javaClass.getResource("template.labelgraph")!!.toURI())
    return URI.createFileURI(modelTemplate.copyTo(
        File(configuration.outputPath + "out/base.labelgraph"),
        overwrite = true
    ).path)
}

fun createOutputBranchModelFiles(configuration: Configuration): List<URI> {
    //TODO
    return LinkedList()
}