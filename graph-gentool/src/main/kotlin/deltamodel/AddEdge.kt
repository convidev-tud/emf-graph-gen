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
import graphmodel.Graph
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject

/**
 * Add a new [Edge].
 * The Edge is added to the (sub-)graph where the first Node is located.
 */
class AddEdge(id: String, val nodeAName: String, val nodeBName: String) : DeltaOperation(id) {

    private val description = "AddEdge"

    override fun flatten(): List<DeltaOperation> {
        return listOf(this)
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                          label: EEnum?, nodeType: EEnum?): EObject {
        val operation = factory.create(classes[description])
        val nodeAAttribute = operation.eClass().getEStructuralFeature("nodeA")
        val nodeBAttribute = operation.eClass().getEStructuralFeature("nodeB")
        val idAttribute = operation.eClass().getEStructuralFeature("id")

        operation.eSet(idAttribute, id)
        operation.eSet(nodeAAttribute, nodeAName)
        operation.eSet(nodeBAttribute, nodeBName)

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is AddEdge){
            return nodeAName == other.nodeAName && nodeBName == other.nodeBName
        }
        return false
    }

    companion object {

        fun parse(eObject: EObject): AddEdge {
            val idAttribute = eObject.eClass().getEStructuralFeature("id")
            val id = eObject.eGet(idAttribute, true) as String
            val nodeAName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeA")) as String
            val nodeBName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeB")) as String
            return AddEdge(id, nodeAName, nodeBName)
        }
    }

}