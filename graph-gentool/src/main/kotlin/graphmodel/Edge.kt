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
package graphmodel

import ecore.DeepComparable
import ecore.EObjectSource
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject

class Edge(
    val a: Node,
    val b: Node
) : EObjectSource, DeepComparable {

    private val description = "Edge"

    fun deepCopy(allNodes: Collection<Node>): Edge {
        val symmetricA = allNodes.find { n -> n.name == a.name }!!
        val symmetricB = allNodes.find { n -> n.name == b.name }!!
        return Edge(symmetricA, symmetricB)
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                                   label: EEnum?, nodeType: EEnum?): EObject {
        val edge = factory.create(classes[description])
        val nodesReferences = edge.eClass().getEStructuralFeature("nodes")
        (edge.eGet(nodesReferences) as java.util.List<Any>).addAll(listOf(a.buffer!!, b.buffer!!))
        return edge
    }

    /**
     * Compares two edges.
     * Two edges are considered equal if their nodes have the same names and are in the same order
     */
    override fun deepEquals(other: Any): Boolean {
        if(other is Edge){
            return a.name == other.a.name && b.name == other.b.name
        }
        return false
    }

}
