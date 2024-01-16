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
package util

data class Configuration(

    var randomSeed: Int = 0,

    val outputPath: String = "./",

    /**
     * The size of the model as the sum of nodes and edges.
     * Depending on how many nodes are regions and the ratio of nodes and edges, the actual file size can vary.
     * Due to rounding errors, this number can vary +/- 1 element
     * Default is 1024
     */
    val modelSize: Int = 1024,

    /**
     * The ratio of edges and nodes, here SimpleNodes and Regions are handled the same.
     * A ratio of 1.0 say there are as many edges as nodes (consequently, a node is on average connected by two edges).
     * A higher ratio increases the number of edges (and decreases the number of nodes, because modelSize is fixed).
     * If allowPartitions is false, the ratio must be at least 0.5 and may be ignored to fulfill the connections.
     * Double edges can occur, the probability increases if partitions are allowed and a high edge-ratio is chosen
     * because edges are evenly distributed.
     */
    val edgesPerNode: Double = 1.0,

    /**
     * The probability that an edge leaves the region.
     * This probability is ignored as along as partitions must be closed
     */
    val edgeDistortion: Double = 0.0,

    /**
     * The probability that a Node becomes a Region (it becomes a SimpleNode otherwise)
     */
    val regionProbability: Double = 0.0,

    /**
     * Allow partitions to exist within a single region, i.e., two or more sub-graphs not connected to each other.
     * The partition-removing edges are always in between nodes of the same region.
     * A single node in a (sub-)graph of a region is not a partition
     */
    val allowPartitions: Boolean = false,

    /**
     * The number of branches (variants) to create from the final base model
     */
    val branchNumber: Int = 8,

    /**
     * The number of additional edit operation performed on each branch.
     */
    val branchEditLength: Int = 0,

    /**
     * Probability factor 0..1 that the next edit operation happens in the same region as the previous.
     * A value of 0.0 results in an even distribution over all regions.
     */
    val branchEditFocus: Double = 0.0,

    /**
     * Toggle, how the edit length is counted. true for atomic counting and false for accumulative counting
     * If true, the resulting edit sequence will have exactly the same size as specified by the branch edit length.
     * If false, the number of explicit (high-level) edits is counted (although writing the atomic edits to the
     * edit sequence). For example, let there be a region R containing 3 nodes and 2 edges. If delete R is the edit.
     * If R gets deleted, its composite contents must be deleted as well. The result are 6 atomic edits which are
     * added to the edit sequence (one explicit edit and 5 implicit edits). If atomic counting is used, the counter
     * increments by 6. If no atomic counting is used, the counter increments by 1.
     */
    val atomicCounting: Boolean = false

){

    init {
        assert(modelSize > 0)
        assert(edgesPerNode >= 0)
        assert(edgeDistortion in 0.0..1.0)
        assert(regionProbability in 0.0..1.0)
        assert(branchNumber >= 0)
        assert(branchEditLength >= 0)
        assert(branchEditFocus in 0.0..1.0)
    }
}
