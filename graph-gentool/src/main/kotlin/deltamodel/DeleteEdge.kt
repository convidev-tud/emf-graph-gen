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

import graphmodel.Edge
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject

/**
 * Delete an [Edge].
 * In case the Edge occurs multiple times, only one occurrence is deleted.
 */
class DeleteEdge(val edge: Edge) : DeltaOperation() {

    private val description = "DeleteEdge"

    override fun flatten(): List<DeltaOperation> {
        return listOf(this)
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                          label: EEnum?, nodeType: EEnum?): EObject {
        val operation = factory.create(classes[description])
        val nodeAAttribute = operation.eClass().getEStructuralFeature("nodeA")
        val nodeBAttribute = operation.eClass().getEStructuralFeature("nodeB")

        operation.eSet(nodeAAttribute, edge.a.name)
        operation.eSet(nodeBAttribute, edge.b.name)

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is DeleteEdge){
            return edge.deepEquals(other.edge)
        }
        return false
    }
}