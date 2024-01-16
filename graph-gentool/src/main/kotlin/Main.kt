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
import deltamodel.DeltaSequence
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
                "A value of 0.0 results in an even distribution over all regions."]
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
    var atomicCounting: Boolean = false

    override fun call(): Int {
        runWithConfig(
            Configuration(
                randomSeed = 0, //TODO add random seed as optional CLI argument
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

fun runWithConfig(configuration: Configuration): Environment {
    val graphMetamodelPath: String = object {}.javaClass.getResource("labelgraph.ecore")!!.path
    val graphMetamodelURI = URI.createFileURI(graphMetamodelPath)
    val baseModel = createOutputBaseModelFile(configuration)
    val ecoreHandler = EcoreHandler(graphMetamodelURI, baseModel, "labelgraph")

    println("Generating Base Graph ECORE models...")

    val factory = ecoreHandler.getModelFactory()
    val classMap = ecoreHandler.getClassMap()
    val label = ecoreHandler.getEnumMap()["Label"]!!
    val graphRoot = ecoreHandler.getModelRoot()
    val graph = Graph(LinkedList<Node>(), LinkedList<Edge>(), graphRoot)
    val originGraphFactory = GraphFactory(graph, configuration)

    val startTimeGenerate = System.currentTimeMillis()
    originGraphFactory.exec()

    graph.generate(classMap, factory, setOf("Node"), label)
    graph.generate(classMap, factory, setOf("Edge"), label)
    ecoreHandler.saveModel()

    val endTimeGenerate = System.currentTimeMillis()
    println("Generation Time: ${endTimeGenerate - startTimeGenerate} ms")

    val environment = Environment(graph, LinkedList(), LinkedList())

    if (configuration.branchNumber > 0) {
        println("Creating Branches...")
        val deltaMetamodelPath: String = object {}.javaClass.getResource("graphdelta.ecore")!!.path
        val deltaMetamodelURI = URI.createFileURI(deltaMetamodelPath)
        val branches = createOutputBranchModelFiles(configuration, File(baseModel.toFileString()))
        processBranches(branches, configuration, graphMetamodelURI, deltaMetamodelURI, environment)
    }

    return environment
}

fun processBranches(branches: List<Branch>, configuration: Configuration, graphMetamodelURI: URI,
                    deltaMetamodelURI: URI, environment: Environment) {

    for ((branchIndex, branch) in branches.withIndex()){

        val graphEcoreHandler = EcoreHandler(metamodel = graphMetamodelURI, model = branch.modelURI, "labelgraph")

        val graphClassMap = graphEcoreHandler.getClassMap()
        val graphLabels = graphEcoreHandler.getEnumMap()["Label"]!!
        val graphRoot = graphEcoreHandler.getModelRoot()
        val graph = Graph(LinkedList<Node>(), LinkedList<Edge>(), graphRoot, isRoot = true)

        val deltaEcoreHandler = EcoreHandler(metamodel = deltaMetamodelURI, model = branch.deltaURI, "graphdelta")

        val deltaClassMap = deltaEcoreHandler.getClassMap()
        val deltaLabels = deltaEcoreHandler.getEnumMap()["Label"]!!
        val deltaNodeTypes = deltaEcoreHandler.getEnumMap()["NodeType"]!!
        val deltaRoot = deltaEcoreHandler.getModelRoot()

        println("Generating Edit Sequence Branch $branchIndex...")

        val startTimeProcessing = System.currentTimeMillis()
        val graphProcessor = GraphProcessor(graph, configuration)
        val finalStage = graphProcessor.exec()

        val graphFactory = graphEcoreHandler.getModelFactory()
        graph.apply(finalStage.graph)
        graph.generate(graphClassMap, graphFactory, setOf("Node"), graphLabels)
        graph.generate(graphClassMap, graphFactory, setOf("Edge"), graphLabels)
        environment.branchGraphs.add(graph)

        val deltaSequence = DeltaSequence(finalStage.deltaSequence.deltaOperations, deltaRoot)
        deltaSequence.generate(deltaClassMap, deltaEcoreHandler.getModelFactory(), TreeSet(), deltaLabels, deltaNodeTypes)
        environment.branchDeltas.add(deltaSequence)

        graphEcoreHandler.saveModel()
        deltaEcoreHandler.saveModel()

        val endTimeProcessing = System.currentTimeMillis()
        println("Processing Time: ${endTimeProcessing - startTimeProcessing} ms")

        configuration.randomSeed++
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