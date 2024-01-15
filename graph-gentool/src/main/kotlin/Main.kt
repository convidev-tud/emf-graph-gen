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
import org.eclipse.emf.common.util.URI
import picocli.CommandLine
import util.Branch
import util.Configuration
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
        names = ["-n", "--edges_per_node"],
        description = ["Number of edge elements per node element (DOUBLE)." +
                "This value influences edge_distortion and is influenced by allow_partitions."]
    )
    var edgesPerNode: Double = defaultConfiguration.edgesPerNode

    @CommandLine.Option(
        names = ["-d", "--edge_distortion"],
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
        names = ["-c", "--branch_number"],
        description = ["The number of branches (variants) to create from the final base model (INT)."]
    )
    var branchNumber: Int = defaultConfiguration.branchNumber

    @CommandLine.Option(
        names = ["-l", "--branch_edit_length"],
        description = ["The number of additional edit operation performed on each branch (INT)."]
    )
    var branchEditLength: Int = defaultConfiguration.branchEditLength

    @CommandLine.Option(
        names = ["-f", "--branch_edit_focus"],
        description = ["Probability factor 0..1 that the next edit operation happens in the same region as the previous." +
                "A value of 0.0 results in a evenly distribution over all regions."]
    )
    var branchEditFocus: Double = defaultConfiguration.branchEditFocus

    @CommandLine.Option(
        names = ["-e", "--atomic_counting"],
        description = ["Toggle, how the edit length is counted. true for atomic counting and false for accumulative counting. " +
                "If true, the resulting edit sequence will have exactly the same size as specified by the branch edit length. " +
                "If false, the number of explicit (high-level) edits is counted (although writing the atomic edits to the " +
                "edit sequence). For example, let there be a region R containing 3 nodes and 2 edges. If delete R is the edit. " +
                "If R gets deleted, its composite contents must be deleted as well. The result are 6 atomic edits which are " +
                "added to the edit sequence (one explicit edit and 5 implicit edits). If atomic counting is used, the counter " +
                "increments by 6. If no atomic counting is used, the counter increments by 1."]
    )
    val atomicCounting: Boolean = false

    override fun call(): Int {
        runWithConfig(
            Configuration(
                randomSeed = 0,
                outputPath = output.path,
                modelSize,
                edgesPerNode,
                edgeDistortion,
                regionProbability,
                allowPartitions,
                branchNumber,
                branchEditLength,
                branchEditFocus,
                atomicCounting
            )
        )
        return 0
    }
}

fun main(args: Array<String>) {
    exitProcess(CommandLine(Checksum()).execute(*args))
}

fun runWithConfig(configuration: Configuration): Graph {
    val metamodelPath: String = object {}.javaClass.getResource("labelgraph.ecore")!!.path
    val metamodelURI = URI.createFileURI(metamodelPath)
    val baseModel = createOutputBaseModelFile(configuration)
    val ecoreHandler = EcoreHandler(metamodelURI, baseModel, "labelgraph")

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
    graph.generate(classMap, factory, setOf("Node"), label)
    graph.generate(classMap, factory, setOf("Edge"), label)
    ecoreHandler.saveModel()
    val endTimeWrite = System.currentTimeMillis()
    println("Postprocessing Time: ${endTimeWrite - startTimeWrite} ms")

    if (configuration.branchNumber > 0) {
        println("Creating Branches...")
        val branches = createOutputBranchModelFiles(configuration, File(baseModel.toFileString()))
    }

    return graph
}

fun processBranches(branches: List<Branch>, configuration: Configuration, graphMetamodelURI: URI, deltaMetamodelURI: URI): Unit {

    for (branch in branches){
        val graphEcoreHandler = EcoreHandler(graphMetamodelURI, branch.modelURI, "labelgraph")
        //TODO check if Graph class can decode predefined input

        val graphClassMap = graphEcoreHandler.getClassMap()
        val graphLabels = graphEcoreHandler.getEnumMap()["Label"]!!
        val graphRoot = graphEcoreHandler.getModelRoot()
        val graph = Graph(LinkedList<Node>(), LinkedList<Edge>(), graphRoot)

        val deltaEcoreHandler = EcoreHandler(deltaMetamodelURI, branch.deltaURI, "graphdelta")

        //TODO delta mappings

        val deltaClassMap = graphEcoreHandler.getClassMap()
        val deltaLabels = graphEcoreHandler.getEnumMap()["Label"]!!
        val deltaRoot = graphEcoreHandler.getModelRoot()
        val deltaSequence = Graph(LinkedList<Node>(), LinkedList<Edge>(), graphRoot)

        //val processor = GraphProcessor()
    }

}

fun createOutputBaseModelFile(configuration: Configuration): URI {
    val modelTemplate = File(object {}.javaClass.getResource("template.labelgraph")!!.toURI())
    return URI.createFileURI(modelTemplate.copyTo(
        File(configuration.outputPath + "/base.labelgraph"),
        overwrite = true
    ).path)
}

fun createOutputBranchModelFiles(configuration: Configuration, baseModel: File): List<Branch> {

    val deltaTemplate = File(object {}.javaClass.getResource("template.graphdelta")!!.toURI())
    val results: MutableList<Branch> = LinkedList()

    for(i: Int in 0..< configuration.branchNumber){
        val dir = configuration.outputPath + "/b_" + i.toString()
        val deltaURI = URI.createFileURI(deltaTemplate.copyTo(
            File("$dir/model.graphdelta"),
            overwrite = true
        ).path)
        val modelURI = URI.createFileURI(baseModel.copyTo(
            File("$dir/model.labelgraph"),
            overwrite = true
        ).path)
        results.add(Branch(modelURI, deltaURI))
    }
    return results
}