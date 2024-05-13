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
import graphmodel.Region
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject

/**
 * Move an [Edge] from one [Region] to another.
 * This action must not be executed out of context of a [MoveNode] operation.
 * This action exists to model and represent the movement of edges after a node was moved ensuring that edges
 * are located in the same region as their first node.
 */
class MoveEdge(id: String,
               val nodeAName: String,
               val nodeBName: String,
               val newRegionName: String = "",
               val oldRegionName: String = "") : DeltaOperation(id) {

    val description = "MoveEdge"

    override fun flatten(): List<DeltaOperation> {
        return listOf(this)
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                          label: EEnum?, nodeType: EEnum?): EObject {
        val operation = factory.create(classes[description])
        val nodeAAttribute = operation.eClass().getEStructuralFeature("nodeA")
        val nodeBAttribute = operation.eClass().getEStructuralFeature("nodeB")
        val idAttribute = operation.eClass().getEStructuralFeature("id")

        operation.eSet(nodeAAttribute, nodeAName)
        operation.eSet(nodeBAttribute, nodeBName)
        operation.eSet(idAttribute, id)

        val newRegAttribute = operation.eClass().getEStructuralFeature("newRegion")
        val oldRegAttribute = operation.eClass().getEStructuralFeature("oldRegion")

        operation.eSet(oldRegAttribute, oldRegionName)
        operation.eSet(newRegAttribute, newRegionName)

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is MoveEdge){
            return nodeAName == other.nodeAName && nodeBName == other.nodeBName &&
                    newRegionName == other.newRegionName && oldRegionName == other.oldRegionName
        }
        return false
    }

    companion object {

        fun parse(eObject: EObject): MoveEdge {
            val nodeA = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeA")) as String
            val nodeB = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeB")) as String
            val id = eObject.eGet(eObject.eClass().getEStructuralFeature("id")) as String
            val newRegion = eObject.eGet(eObject.eClass().getEStructuralFeature("newRegion")) as String
            val oldRegion = eObject.eGet(eObject.eClass().getEStructuralFeature("oldRegion")) as String

            return MoveEdge(id, nodeA, nodeB, newRegion, oldRegion)
        }

    }

}