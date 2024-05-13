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
class AddNode(id: String, val nodeName: String, private val nodeType: NodeType, val regionName: String = "") : DeltaOperation(id) {

    private val description = "AddNode"

    override fun flatten(): List<DeltaOperation> {
        return listOf(this)
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                          label: EEnum?, nodeType: EEnum?): EObject {
        val operation = factory.create(classes[description])
        val nodeNameAttribute = operation.eClass().getEStructuralFeature("nodeName")
        val nodeTypeAttribute = operation.eClass().getEStructuralFeature("nodeType")
        val nodeRegionAttribute = operation.eClass().getEStructuralFeature("toRegion")
        val idAttribute = operation.eClass().getEStructuralFeature("id")

        operation.eSet(nodeNameAttribute, nodeName)
        operation.eSet(nodeTypeAttribute, nodeType!!.getEEnumLiteral(this.nodeType.name))
        operation.eSet(nodeRegionAttribute, regionName)
        operation.eSet(idAttribute, super.id)

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is AddNode){
            return other.nodeName == nodeName && other.nodeType == nodeType && other.regionName == regionName
        }
        return false
    }

    companion object {

        fun parse(eObject: EObject): AddNode {
            val nodeName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeName"), true) as String
            val id = eObject.eGet(eObject.eClass().getEStructuralFeature("id"), true) as String
            val typeIndex = (eObject.eGet(eObject.eClass().getEStructuralFeature("nodeType"), true) as EEnumLiteralImpl).value
            val nodeType = NodeType.entries[typeIndex]
            val regionName = eObject.eGet(eObject.eClass().getEStructuralFeature("toRegion"), true) as String

            return AddNode(id, nodeName, nodeType, regionName)
        }

    }
}