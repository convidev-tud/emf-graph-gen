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
package model

import ecore.EObjectSource
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject

class Edge(
    val a: Node,
    val b: Node
) : EObjectSource {

    private val description = "Edge"

    override fun generate(classes: Map<String, EClass>, label: EEnum, factory: EFactory, filter: Set<String>): EObject {
        val edge = factory.create(classes[description])
        val nodesComposition = edge.eClass().getEStructuralFeature("nodes")
        (edge.eGet(nodesComposition) as java.util.List<Any>).addAll(listOf(a.buffer!!, b.buffer!!))
        return edge
    }

}