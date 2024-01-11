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

package deltamodel

import ecore.EObjectSource
import ecore.DeepComparable
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import java.util.*

class DeltaSequence(
    val deltaOperations: MutableList<DeltaOperation> = LinkedList(),
    private val predef: EObject? = null
): EObjectSource, DeepComparable {

    override fun generate(classes: Map<String, EClass>, label: EEnum, factory: EFactory, filter: Set<String>): EObject {
        TODO("Not yet implemented")
        /*
        val graph = buffer ?: factory.create(classes[description])
        buffer = graph
        val nodesComposition = graph.eClass().getEStructuralFeature("nodes")
        val edgesComposition = graph.eClass().getEStructuralFeature("edges")
        if(filter.contains("Node")){
            graph.eSet(nodesComposition, LinkedList<Any>())
            val eSimpleNodes = nodes.filterIsInstance<SimpleNode>().map { n -> n.generate(classes, label, factory, filter) }
            val eRegions = nodes.filterIsInstance<Region>().map { n -> n.generate(classes, label, factory, filter) }
            val eNodes = LinkedList<EObject>()
            eNodes.addAll(eSimpleNodes)
            eNodes.addAll(eRegions)
            (graph.eGet(nodesComposition) as java.util.List<EObject>).addAll(eNodes)
        }
        if(filter.contains("Edge")){
            graph.eSet(edgesComposition, LinkedList<Any>())
            val eEdges = edges.map { edge -> edge.generate(classes, label, factory, filter) }
            (graph.eGet(edgesComposition) as java.util.List<EObject>).addAll(eEdges)
            nodes.filterIsInstance<Region>().forEach{ r -> r.generate(classes, label, factory, filter)}
        }
        return graph
         */
    }

    override fun deepEquals(other: Any): Boolean {
        TODO("Not yet implemented")
    }
}