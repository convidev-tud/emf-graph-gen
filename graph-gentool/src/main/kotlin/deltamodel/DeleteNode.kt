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

import graphmodel.Label
import graphmodel.Node
import graphmodel.Region
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.impl.EEnumImpl
import org.eclipse.emf.ecore.impl.EEnumLiteralImpl
import java.util.*

/**
 * Delete a [Node] from the specified [Region].
 * If no Region is specified, it is assumed to be in the Graph root.
 * NodeImplications contains the [DeleteNode] actions that must be executed beforehand to "clean" the node.
 * EdgeImplications contains the [DeleteEdge] actions that must be executed beforehand to "clean" the node.
 */
class DeleteNode(/*all*/        id: String,
                 /*all*/        val nodeName: String,
                 /*no id*/      val nodeID: String?,
                 /*all*/        val label: Label?,
                 /*with id*/    val fromRegionName: String? = "root",
                 /*with id*/    val fromRegionID: String? = "root",
                 /*all*/        val serializeWithIDs: Boolean,
                 /*all*/        val nodeImplications: MutableList<DeleteNode>,
                 /*all*/        val edgeImplications: MutableList<DeleteEdge>) : DeltaOperation(id) {

    val description = "DeleteNode"

    override fun flatten(): List<DeltaOperation> {
        val result: MutableList<DeltaOperation> = LinkedList()
        for (op in edgeImplications){
            result.addAll(op.flatten())
        }
        for (op in nodeImplications){
            result.addAll(op.flatten())
        }
        result.add(this)
        return result
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                          label: EEnum?, nodeType: EEnum?): EObject {

        nodeImplications.forEach{ni -> ni.generate(classes, factory, filter, label, nodeType)}
        edgeImplications.forEach{ei -> ei.generate(classes, factory, filter, label, nodeType)}

        val operation = factory.create(classes[description])

        val nodeNameAttribute = operation.eClass().getEStructuralFeature("nodeName")
        val idAttribute = operation.eClass().getEStructuralFeature("id")
        operation.eSet(nodeNameAttribute, nodeName)
        operation.eSet(idAttribute, id)

        //Because it can be a Region without a label
        if(this.label !== null){
            val labelAttribute = operation.eClass().getEStructuralFeature("label")
            operation.eSet(labelAttribute, label!!.getEEnumLiteral(this.label!!.name))
        }

        if(serializeWithIDs){
            val nodeIDAttribute = operation.eClass().getEStructuralFeature("nodeID")
            val fromRegionIDAttribute = operation.eClass().getEStructuralFeature("fromRegionID")
            operation.eSet(nodeIDAttribute, nodeID)
            operation.eSet(fromRegionIDAttribute, fromRegionID)
        }else{
            val fromRegionNameAttribute = operation.eClass().getEStructuralFeature("fromRegion")
            operation.eSet(fromRegionNameAttribute, fromRegionName)
        }

        val nodeImplicationRefs = operation.eClass().getEStructuralFeature("nodeImplications")
        (operation.eGet(nodeImplicationRefs) as java.util.List<Any>).addAll(nodeImplications.map { e -> e.buffer!! })

        val edgeImplicationRefs = operation.eClass().getEStructuralFeature("edgeImplications")
        (operation.eGet(edgeImplicationRefs) as java.util.List<Any>).addAll(edgeImplications.map { e -> e.buffer!! })

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is DeleteNode){
            for (deleteNode in nodeImplications) {
                if (!other.nodeImplications.any { it.deepEquals(deleteNode) }) return false
            }
            for (deleteEdge in edgeImplications) {
                if (!other.edgeImplications.any { it.deepEquals(deleteEdge) }) return false
            }
            return if(serializeWithIDs) {
                this.nodeName == other.nodeName && this.nodeID == other.nodeID &&
                        this.fromRegionID == other.fromRegionID && this.label == other.label
            }else {
                val res = this.nodeName == other.nodeName && this.fromRegionName == other.fromRegionName &&
                        this.label == other.label
                if(idEquals(other) && !res){
                    throw AssertionError("Incoherent Comparison DeleteNode: $this != $other")
                }
                res
            }
        }
        return false
    }

    companion object {

        fun parse(eObject: EObject, serializeWithIDs: Boolean): DeleteNode {

            val nodeName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeName"), true) as String
            val id = eObject.eGet(eObject.eClass().getEStructuralFeature("id"), true) as String

            val labelAttribute = eObject.eClass().getEStructuralFeature("label")
            val labelProperty = eObject.eGet(labelAttribute, true)
            var label: Label? = null
            if(labelProperty != null) {
                val labelIndex = (labelProperty as EEnumLiteralImpl).value
                label = Label.entries[labelIndex]
            }

            val nodeImplications = (eObject.eGet(eObject.eClass().getEStructuralFeature("nodeImplications")) as List<EObject>)
                .map { e -> parse(e, serializeWithIDs) }.toMutableList()
            val edgeImplications = (eObject.eGet(eObject.eClass().getEStructuralFeature("edgeImplications")) as List<EObject>)
                .map { e -> DeleteEdge.parse(e, serializeWithIDs) }.toMutableList()

            var fromRegionName: String? = null
            var fromRegionID: String? = null
            var nodeID: String? = null

            if(serializeWithIDs){
                fromRegionID = eObject.eGet(eObject.eClass().getEStructuralFeature("fromRegionID"), true) as String
                nodeID = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeID"), true) as String
            }else{
                fromRegionName = eObject.eGet(eObject.eClass().getEStructuralFeature("fromRegion"), true) as String
            }

            return DeleteNode(id, nodeName, nodeID, label, fromRegionName, fromRegionID, serializeWithIDs, nodeImplications, edgeImplications)
        }

    }
}