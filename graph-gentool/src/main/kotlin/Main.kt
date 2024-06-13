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
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EObject
import picocli.CommandLine
import util.Branch
import util.Configuration
import util.Stage
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
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

    @CommandLine.Option(
        names = ["-x", "--stepwise_export"],
        description = ["Toggle if the processor the variants only once or after each step. " +
                "The base model generation is not influenced by this toggle. " +
                "If set to true, a model variant with an edit length of 10 leads to 10 exported models. " +
                "This operation is not compatible with atomic counting. " +
                "This operation is not compatible with atomic counting. "]
    )
    var stepwiseExport: Boolean = false

    @CommandLine.Option(
        names = ["-i", "--with_eids"],
        description = ["Toggle if the generated models should contain EIDs (Boolean)."]
    )
    var withEIDs: Boolean = false

    @CommandLine.Option(
        names = ["-u", "--random_seed"],
        description = ["Random seed for the strict deterministic random generation algorithms"]
    )
    var userRandomSeed: Int = 0

    @CommandLine.Option(
        names = ["-o", "--edit_probabilities"],
        description = ["Edit probabilities (int) seperated by ':'. " +
                "The order is ADD_SIMPLE, ADD_REGION, DELETE_NODE, MOVE_NODE, CHANGE_LABEL, ADD_EDGE, DELETE_EDGE. " +
                "The sum of all probabilities must be 100. " +
                "Default: 15:5:5:5:25:25:20"]
    )
    var editProbabilities: String = "15:5:5:5:25:25:20"

    override fun call(): Int {
        runWithConfig(
            Configuration(
                randomSeed = userRandomSeed,
                outputPath = output.path,
                modelSize,
                edgesPerNode,
                edgeDistortion,
                regionProbability,
                allowPartitions,
                branchNumber,
                branchEditLength,
                branchEditFocus,
                atomicCounting,
                stepwiseExport,
                withEIDs = withEIDs,
                editProbabilities = editProbabilities
            )
        )
        return 0
    }
}

fun main(args: Array<String>) {
    exitProcess(CommandLine(Checksum()).execute(*args))
}

fun runWithConfig(configuration: Configuration): Environment {

    val graphMetaName = if(configuration.withEIDs) "idlabelgraph" else "labelgraph"
    val deltaMetaName = if(configuration.withEIDs) "idgraphdelta" else "graphdelta"

    val graphMetamodelPath: String = createTempMetamodelFile(graphMetaName).toString()
    val graphMetamodelURI = URI.createFileURI(graphMetamodelPath)
    val baseModel = createOutputBaseModelFile(configuration)
    val ecoreHandler = EcoreHandler(graphMetamodelURI, baseModel, "labelgraph")

    println("Generating Base Graph ECORE models...")

    val factory = ecoreHandler.getModelFactory()
    val classMap = ecoreHandler.getClassMap()
    val label = ecoreHandler.getEnumMap()["Label"]!!
    val graphRoot = ecoreHandler.getModelRoot()
    val graph = Graph("root", LinkedList<Node>(), LinkedList<Edge>(), graphRoot, serializeWithIDs = configuration.withEIDs)
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
        val deltaMetamodelPath: String = createTempMetamodelFile(deltaMetaName).toString()
        val deltaMetamodelURI = URI.createFileURI(deltaMetamodelPath)
        val branches = createOutputBranchModelFiles(configuration, File(baseModel.toFileString()))
        processBranches(branches, configuration, graphMetamodelURI, deltaMetamodelURI, environment)
    }

    return environment
}

fun processBranches(
    branches: List<Branch>, configuration: Configuration, graphMetamodelURI: URI,
    deltaMetamodelURI: URI, environment: Environment
) {

    val editProbabilities = configuration.editProbabilities.split(":").map { it.toInt() }
    val changeOperationWeights: List<Pair<String, Int>> = listOf(
        Pair("ADD_SIMPLE", editProbabilities[0]),
        Pair("ADD_REGION", editProbabilities[1]),
        Pair("DELETE_NODE", editProbabilities[2]),
        Pair("MOVE_NODE", editProbabilities[3]),
        Pair("CHANGE_LABEL", editProbabilities[4]),
        Pair("ADD_EDGE", editProbabilities[5]),
        Pair("DELETE_EDGE", editProbabilities[6])
    )

    for ((branchIndex, branch) in branches.withIndex()) {

        val graphEcoreHandlerSet: MutableList<EcoreHandler> = LinkedList()
        val deltaEcoreHandlerSet: MutableList<EcoreHandler> = LinkedList()

        environment.branchGraphs.add(mutableListOf())
        environment.branchDeltas.add(mutableListOf())

        var graphRoot: EObject?
        var graph: Graph? = null

        for((versionIndex, branchVersion) in branch.modelURIs.withIndex()){

            val graphEcoreHandler = EcoreHandler(
                metamodel = graphMetamodelURI, model = branchVersion, "labelgraph")
            val deltaEcoreHandler = EcoreHandler(
                metamodel = deltaMetamodelURI, model = branch.deltaURIs[versionIndex], "graphdelta")

            if(versionIndex == 0) {
                graphRoot = graphEcoreHandler.getModelRoot()
                //Graph ID is set in the constructor
                graph = Graph(null, LinkedList<Node>(), LinkedList<Edge>(), graphRoot, isRoot = true, serializeWithIDs = configuration.withEIDs)
            }
            graphEcoreHandlerSet.add(graphEcoreHandler)
            deltaEcoreHandlerSet.add(deltaEcoreHandler)
        }

        println("Generating Edit Sequence Branch $branchIndex...")

        val startTimeProcessing = System.currentTimeMillis()
        val graphProcessor = GraphProcessor(graph!!, configuration, changeOperationWeights)

        var iterationCounter: Int = 0
        var deltaIterationCounter: Int = 0

        graphProcessor.exec(
            { stage: Stage ->
                persistGraph(graphEcoreHandlerSet[iterationCounter], stage, environment)
                iterationCounter++
            },
            { stage: Stage ->
                persistDeltas(deltaEcoreHandlerSet[deltaIterationCounter], stage, environment)
                deltaIterationCounter++
            })

        val endTimeProcessing = System.currentTimeMillis()
        println("Processing Time: ${endTimeProcessing - startTimeProcessing} ms")
        configuration.randomSeed++
    }
}

fun persistGraph(
    graphEcoreHandler: EcoreHandler, stage: Stage, environment: Environment
) {
    val graphClassMap = graphEcoreHandler.getClassMap()
    val graphLabels = graphEcoreHandler.getEnumMap()["Label"]!!
    val graphFactory = graphEcoreHandler.getModelFactory()
    val graphRoot = graphEcoreHandler.getModelRoot()
    val rootGraph = Graph(stage.graph.id, LinkedList<Node>(), LinkedList<Edge>(), graphRoot,
        isRoot = true, serializeWithIDs = stage.graph.serializeWithIDs)
    rootGraph.apply(stage.graph)
    rootGraph.generate(graphClassMap, graphFactory, setOf("Node"), graphLabels)
    rootGraph.generate(graphClassMap, graphFactory, setOf("Edge"), graphLabels)

    environment.branchGraphs[environment.branchGraphs.size-1].add(rootGraph)
    graphEcoreHandler.saveModel()
}

fun persistDeltas(
    deltaEcoreHandler: EcoreHandler, stage: Stage, environment: Environment
) {
    val deltaClassMap = deltaEcoreHandler.getClassMap()
    val deltaLabels = deltaEcoreHandler.getEnumMap()["Label"]!!
    val deltaNodeTypes = deltaEcoreHandler.getEnumMap()["NodeType"]!!
    val deltaRoot = deltaEcoreHandler.getModelRoot()
    val rootSequence = DeltaSequence(stage.deltaSequence.deltaOperations, deltaRoot, stage.deltaSequence.serializeWithIDs)

    rootSequence.generate(deltaClassMap, deltaEcoreHandler.getModelFactory(), TreeSet(), deltaLabels, deltaNodeTypes)
    environment.branchDeltas[environment.branchDeltas.size-1].add(rootSequence)
    deltaEcoreHandler.saveModel()
}

fun createOutputBaseModelFile(configuration: Configuration): URI {

    val templateName = if(configuration.withEIDs) "template_withid" else "template_noid"

    val basePath = Paths.get(configuration.outputPath, "base.labelgraph")
    Files.createDirectories(basePath.parent)

    object {}.javaClass.getResourceAsStream("$templateName.labelgraph").use { input ->
        if (input != null)
            BufferedReader(InputStreamReader(input)).use { reader ->
                Files.newBufferedWriter(basePath).use { writer ->
                    reader.copyTo(writer)
                    writer.flush()
                }
            }
    }

    return URI.createFileURI(basePath.toString())
}

fun createOutputBranchModelFiles(configuration: Configuration, baseModel: File): List<Branch> {
    val results: MutableList<Branch> = LinkedList()

    var content: List<String> = LinkedList()
    object {}.javaClass.getResourceAsStream("template_universal.graphdelta").use { input ->
        if (input != null)
            BufferedReader(InputStreamReader(input)).use { reader ->
                content = reader.readLines()
            }
    }

    for(i: Int in 0..< configuration.branchNumber) {
        val dir = configuration.outputPath + "/b_" + i.toString()

        val modelURIs: MutableList<URI> = LinkedList()
        val deltaURIs: MutableList<URI> = LinkedList()

        val numberVersions = if (!configuration.stepwiseExport) {
            1
        } else {
            configuration.branchEditLength
        }
        for (v: Int in 0..<numberVersions) {

            val deltaPath = Paths.get(dir, "model_$v.graphdelta")
            Files.createDirectories(deltaPath.parent)
            Files.write(deltaPath, content)
            val deltaURI = URI.createFileURI(deltaPath.toString())
            deltaURIs.add(deltaURI)

            val modelURI = URI.createFileURI(
                baseModel.copyTo(
                    File("$dir/model_$v.labelgraph"),
                    overwrite = true
                ).path
            )
            modelURIs.add(modelURI)

        }
        results.add(Branch(modelURIs, deltaURIs))
    }


    return results
}

fun createTempMetamodelFile(title: String): Path {
    val tempPath = Files.createTempFile(title, ".ecore")
    Runtime.getRuntime().addShutdownHook(Thread() {
        run() {
            Files.delete(tempPath)
        }
    })

    object {}.javaClass.getResourceAsStream("$title.ecore").use { input ->
        if (input != null)
            BufferedReader(InputStreamReader(input)).use { reader ->
                Files.newBufferedWriter(tempPath).use { writer ->
                    reader.copyTo(writer)
                    writer.flush()
                }
            }
    }

    return tempPath
}