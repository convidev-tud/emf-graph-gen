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
import meta.GraphStats
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject

class Region(name: String, val graph: Graph) : Node(name), EObjectSource {

    private val description = "Region"

    fun getStats(recursive: Boolean): GraphStats {
        return graph.getStats(recursive)
    }

    override fun generate(classes: Map<String, EClass>, label: EEnum, factory: EFactory, filter: Set<String>): EObject {
        if(filter.contains("Node")) {
            val region = factory.create(classes[description])
            super.buffer = region
            val nameAttribute = region.eClass().getEStructuralFeature("name")
            val graph = region.eClass().getEStructuralFeature("graph")

            region.eSet(nameAttribute, super.name)
            region.eSet(graph, this.graph.generate(classes, label, factory, filter))
        }else{
            this.graph.generate(classes, label, factory, filter)
        }

        return super.buffer!!
    }

}