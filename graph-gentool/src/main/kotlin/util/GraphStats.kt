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

import graphmodel.Edge
import graphmodel.Region
import graphmodel.SimpleNode

data class GraphStats(
    val allSimpleNodes: MutableSet<SimpleNode>,
    val allRegions: MutableSet<Region>,
    val allEdges: MutableSet<Edge>
){

    override fun toString(): String {
        val simpleNodeSize = allSimpleNodes.size
        val regionsSize = allRegions.size
        val edgeSize = allEdges.size
        return "GraphStats: " +
                "SimpleNodes: $simpleNodeSize | Regions: $regionsSize | Edges: $edgeSize " +
                "|| NODES: ${simpleNodeSize + regionsSize} || SUM: ${simpleNodeSize + regionsSize + edgeSize}"
    }

}
