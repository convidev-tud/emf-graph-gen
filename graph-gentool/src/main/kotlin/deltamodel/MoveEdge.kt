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
class MoveEdge(/*all*/ id: String,
               val nodeAName: String?,
               val nodeAID: String?,
               val nodeBName: String?,
               val nodeBID: String?,
               val edgeID: String?,
               val newRegionName: String? = "root",
               val newRegionID: String? = "root",
               val oldRegionName: String? = "root",
               val oldRegionID: String? = "root",
               val serializeWithIDs: Boolean) : DeltaOperation(id) {

    val description = "MoveEdge"

    override fun flatten(): List<DeltaOperation> {
        return listOf(this)
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                          label: EEnum?, nodeType: EEnum?): EObject {
        val operation = factory.create(classes[description])

        val idAttribute = operation.eClass().getEStructuralFeature("id")
        operation.eSet(idAttribute, id)

        if(serializeWithIDs){
            val nodeAIDAttribute = operation.eClass().getEStructuralFeature("nodeAID")
            val nodeBIDAttribute = operation.eClass().getEStructuralFeature("nodeBID")
            val newRegionIDAttribute = operation.eClass().getEStructuralFeature("newRegionID")
            val oldRegionIDAttribute = operation.eClass().getEStructuralFeature("oldRegionID")
            val edgeIDAttribute = operation.eClass().getEStructuralFeature("edgeID")
            operation.eSet(nodeAIDAttribute, nodeAID)
            operation.eSet(nodeBIDAttribute, nodeBID)
            operation.eSet(newRegionIDAttribute, newRegionID)
            operation.eSet(oldRegionIDAttribute, oldRegionID)
            operation.eSet(edgeIDAttribute, edgeID)
        } else {
            val nodeANameAttribute = operation.eClass().getEStructuralFeature("nodeA")
            val nodeBNameAttribute = operation.eClass().getEStructuralFeature("nodeB")
            val newRegionNameAttribute = operation.eClass().getEStructuralFeature("newRegion")
            val oldRegionNameAttribute = operation.eClass().getEStructuralFeature("oldRegion")
            operation.eSet(nodeANameAttribute, nodeAName)
            operation.eSet(nodeBNameAttribute, nodeBName)
            operation.eSet(newRegionNameAttribute, newRegionName)
            operation.eSet(oldRegionNameAttribute, oldRegionName)
        }

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is MoveEdge){
            return if(serializeWithIDs){
                nodeAID == other.nodeAID && nodeBID == other.nodeBID &&
                        newRegionID == other.newRegionID && oldRegionID == other.oldRegionID &&
                        edgeID == other.edgeID
            }else{
                val res = nodeAName == other.nodeAName && nodeBName == other.nodeBName &&
                        newRegionName == other.newRegionName && oldRegionName == other.oldRegionName
                if(idEquals(other) && !res){
                    throw AssertionError("Incoherent Comparison MoveEdge: $this != $other")
                }
                res
            }
        }
        return false
    }

    companion object {

        fun parse(eObject: EObject, serializeWithIDs: Boolean): MoveEdge {

            val id = eObject.eGet(eObject.eClass().getEStructuralFeature("id")) as String

            var nodeAName: String? = null
            var nodeBName: String? = null
            var nodeAID: String? = null
            var nodeBID: String? = null
            var edgeID: String? = null
            var newRegionName: String? = null
            var newRegionID: String? = null
            var oldRegionName: String? = null
            var oldRegionID: String? = null

            if(serializeWithIDs){
                nodeAID = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeAID")) as String
                nodeBID = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeBID")) as String
                newRegionID = eObject.eGet(eObject.eClass().getEStructuralFeature("newRegionID")) as String
                oldRegionID = eObject.eGet(eObject.eClass().getEStructuralFeature("oldRegionID")) as String
                edgeID = eObject.eGet(eObject.eClass().getEStructuralFeature("edgeID")) as String
            }else{
                nodeAName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeA")) as String
                nodeBName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeB")) as String
                newRegionName = eObject.eGet(eObject.eClass().getEStructuralFeature("newRegion")) as String
                oldRegionName = eObject.eGet(eObject.eClass().getEStructuralFeature("oldRegion")) as String
            }

            return MoveEdge(id, nodeAName, nodeAID, nodeBName, nodeBID, edgeID, newRegionName, newRegionID,
                oldRegionName, oldRegionID, serializeWithIDs)
        }

    }

}