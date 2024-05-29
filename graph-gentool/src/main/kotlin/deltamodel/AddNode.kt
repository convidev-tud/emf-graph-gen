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

import graphmodel.Graph
import graphmodel.Node
import graphmodel.Region
import graphmodel.SimpleNode
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.impl.EEnumLiteralImpl

/**
 * Add a [Node] ([SimpleNode] or [Region]) to a specified Region.
 * If the specified region is null, it is added to the root [Graph] directly.
 */
class AddNode(/*all*/       operationID: String,
              /*no id*/     val nodeName: String,
              /*with id*/   val nodeID: String?,
              /*all*/       private val nodeType: NodeType,
              /*no id*/     val toRegionName: String? = "root",
              /*with id*/   val toRegionID: String? = "root",
              /*all*/       val serializeWithIDs: Boolean) : DeltaOperation(operationID) {

    private val description = "AddNode"

    override fun flatten(): List<DeltaOperation> {
        return listOf(this)
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                          label: EEnum?, nodeType: EEnum?): EObject {
        val operation = factory.create(classes[description])

        val nodeNameAttribute = operation.eClass().getEStructuralFeature("nodeName")
        val nodeTypeAttribute = operation.eClass().getEStructuralFeature("nodeType")
        val idAttribute = operation.eClass().getEStructuralFeature("id")
        operation.eSet(nodeNameAttribute, nodeName)
        operation.eSet(nodeTypeAttribute, nodeType!!.getEEnumLiteral(this.nodeType.name))
        operation.eSet(idAttribute, super.id)

        if(serializeWithIDs){
            val nodeIDAttribute = operation.eClass().getEStructuralFeature("nodeID")
            val toRegionIDAttribute = operation.eClass().getEStructuralFeature("toRegionID")
            operation.eSet(nodeIDAttribute, nodeID)
            operation.eSet(toRegionIDAttribute, toRegionID)
        }else{
            val toRegionNameAttribute = operation.eClass().getEStructuralFeature("toRegion")
            operation.eSet(toRegionNameAttribute, toRegionName)
        }

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is AddNode){
            return if (serializeWithIDs){
               nodeName == other.nodeName && nodeType == other.nodeType &&
                        toRegionID == other.toRegionID && nodeID == other.nodeID
            }else{
                val res = nodeName == other.nodeName && nodeType == other.nodeType && toRegionName == other.toRegionName
                if(idEquals(other) && !res){
                    throw AssertionError("Incoherent Comparison AddNode: $this != $other")
                }
                res
            }
        }
        return false
    }

    companion object {

        fun parse(eObject: EObject, serializeWithIDs: Boolean): AddNode {
            val nodeName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeName"), true) as String
            val id = eObject.eGet(eObject.eClass().getEStructuralFeature("id"), true) as String
            val typeIndex = (eObject.eGet(eObject.eClass().getEStructuralFeature("nodeType"), true) as EEnumLiteralImpl).value
            val nodeType = NodeType.entries[typeIndex]

            var toRegionName: String? = null
            var toRegionID: String? = null
            var nodeID: String? = null

            if(serializeWithIDs){
                toRegionID = eObject.eGet(eObject.eClass().getEStructuralFeature("toRegionID"), true) as String
                nodeID = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeID"), true) as String
            }else{
                toRegionName = eObject.eGet(eObject.eClass().getEStructuralFeature("toRegion"), true) as String
            }

            return AddNode(id, nodeName, nodeID, nodeType, toRegionName, toRegionID, serializeWithIDs)
        }

    }
}