/**
 * Copyright 2024 Karl Kegel
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
import util.Configuration
import util.GraphStats
import util.Stage
import kotlin.random.Random

class GraphProcessor(private val graph: Graph,
                     private val conf: Configuration) {

    private val random: Random = Random(conf.randomSeed)
    private val rootStats: GraphStats = graph.getStats(true)

    //TODO config file with edit weights / probabilities
    private val changeOperationWeights: Map<String, Double> = mapOf(
        Pair("ADD_SIMPLE", 0.18),
        Pair("ADD_REGION", 0.02),
        Pair("DELETE_NODE", 0.2),
        Pair("MOVE_NODE", 0.2),
        Pair("CHANGE_LABEL", 0.2),
        Pair("ADD_EDGE", 0.1),
        Pair("DELETE_EDGE", 0.1)
    )

    private lateinit var stage: Stage

    init {
        assert(conf.branchEditLength > 0)
        assert(conf.branchEditFocus in 0.0..1.0)
    }

    fun exec(){
        /**
         * Viable operations are:
         *  - Add Node
         *      - Simple Node
         *      - Region
         *  - Delete Node
         *  - Move Node
         *  - Change Label
         *  - Add Edge
         *  - Delete Edge
         */
        val editLength = 0

        while (editLength < conf.branchEditLength){

            //TODO

            break
        }
    }

    fun executeOnStageWithImpact(operation: String): Int {
        //global stage variable as graph copy
        //return graph copy and int
        return 0
    }

    fun applyStage(stage: Stage) {

    }

    fun clearStage() {

    }

}