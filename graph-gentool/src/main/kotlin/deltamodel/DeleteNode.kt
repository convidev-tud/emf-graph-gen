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

import graphmodel.Node
import graphmodel.Region
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import java.util.*

/**
 * Delete a [Node] from the specified [Region].
 * If no Region is specified, it is assumed to be in the Graph root.
 * NodeImplications contains the [DeleteNode] actions that must be executed beforehand to "clean" the node.
 * EdgeImplications contains the [DeleteEdge] actions that must be executed beforehand to "clean" the node.
 */
class DeleteNode(id: String,
                 val nodeName: String,
                 val nodeImplications: MutableList<DeleteNode>,
                 val edgeImplications: MutableList<DeleteEdge>,
                 val regionName: String = "") : DeltaOperation(id) {

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
        val fromRegionAttribute = operation.eClass().getEStructuralFeature("fromRegion")
        val idAttribute = operation.eClass().getEStructuralFeature("id")

        operation.eSet(nodeNameAttribute, nodeName)
        operation.eSet(fromRegionAttribute, regionName)
        operation.eSet(idAttribute, id)

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
            return nodeName == other.nodeName && regionName == other.regionName
        }
        return false
    }

    companion object {

        fun parse(eObject: EObject): DeleteNode {
            val nodeName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeName"), true) as String
            val id = eObject.eGet(eObject.eClass().getEStructuralFeature("id"), true) as String
            val regionName = eObject.eGet(eObject.eClass().getEStructuralFeature("fromRegion"), true) as String

            val nodeImplications = (eObject.eGet(eObject.eClass().getEStructuralFeature("nodeImplications")) as List<EObject>)
                .map { e -> parse(e) }.toMutableList()
            val edgeImplications = (eObject.eGet(eObject.eClass().getEStructuralFeature("edgeImplications")) as List<EObject>)
                .map { e -> DeleteEdge.parse(e) }.toMutableList()

            return DeleteNode(id, nodeName, nodeImplications, edgeImplications, regionName)
        }

    }
}