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
class AddEdge(/*all*/       id: String,
              /*with id*/   val nodeAName: String?,
              /*no id*/     val nodeAID: String?,
              /*with id*/   val nodeBName: String?,
              /*no id*/     val nodeBID: String?,
              /*with id*/   val toRegionName: String? = "root",
              /*no id*/     val toRegionID: String? = "root",
              /*with id*/   val edgeID: String?,
              /*all*/       val serializeWithIDs: Boolean) : DeltaOperation(id) {

    private val description = "AddEdge"

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
            val toRegionIDAttribute = operation.eClass().getEStructuralFeature("toRegionID")
            val edgeIDAttribute = operation.eClass().getEStructuralFeature("edgeID")
            operation.eSet(nodeAIDAttribute, nodeAID)
            operation.eSet(nodeBIDAttribute, nodeBID)
            operation.eSet(toRegionIDAttribute, toRegionID)
            operation.eSet(edgeIDAttribute, edgeID)
        }else{
            val nodeAAttribute = operation.eClass().getEStructuralFeature("nodeA")
            val nodeBAttribute = operation.eClass().getEStructuralFeature("nodeB")
            val toRegionAttribute = operation.eClass().getEStructuralFeature("toRegion")
            operation.eSet(nodeAAttribute, nodeAName)
            operation.eSet(nodeBAttribute, nodeBName)
            operation.eSet(toRegionAttribute, toRegionName)
        }

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is AddEdge){
            return if(serializeWithIDs){
                this.nodeAID == other.nodeAID && this.nodeBID == other.nodeBID &&
                        this.toRegionID == other.toRegionID && this.edgeID == other.edgeID
            }else{
                val res = this.nodeAName == other.nodeAName && this.nodeBName == other.nodeBName &&
                        this.toRegionName == other.toRegionName
                if(idEquals(other) && !res){
                    throw AssertionError("Incoherent Comparison AddEdge: $this != $other")
                }
                res
            }
        }
        return false
    }

    companion object {

        fun parse(eObject: EObject, serializeWithIDs: Boolean): AddEdge {
            val idAttribute = eObject.eClass().getEStructuralFeature("id")
            val id = eObject.eGet(idAttribute, true) as String

            var nodeAName: String? = null
            var nodeBName: String? = null
            var nodeAID: String? = null
            var nodeBID: String? = null
            var toRegionName: String? = null
            var toRegionID: String? = null
            var edgeID: String? = null

            if(serializeWithIDs){
                nodeAID = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeAID")) as String
                nodeBID = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeBID")) as String
                toRegionID = eObject.eGet(eObject.eClass().getEStructuralFeature("toRegionID")) as String
                edgeID = eObject.eGet(eObject.eClass().getEStructuralFeature("edgeID")) as String
            }else{
                nodeAName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeA")) as String
                nodeBName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeB")) as String
                toRegionName = eObject.eGet(eObject.eClass().getEStructuralFeature("toRegion")) as String
            }

            return AddEdge(id, nodeAName, nodeAID, nodeBName, nodeBID, toRegionName, toRegionID, edgeID, serializeWithIDs)
        }
    }

}