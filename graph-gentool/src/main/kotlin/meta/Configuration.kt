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
package meta

data class Configuration(

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


    //TODO
    val branchNumber: Int = 0,

    //TODO
    val branchEditLength: Int = 0,

    //TODO
    val branchEditFocus: Double = 0.0

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
