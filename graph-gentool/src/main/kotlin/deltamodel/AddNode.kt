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

/**
 * Add a [Node] ([SimpleNode] or [Region]) to a specified Region.
 * If the specified region is null, it is added to the root [Graph] directly.
 */
class AddNode(val node: Node, private val nodeType: NodeType, val region: Region? = null) : DeltaOperation() {

    private val description = "AddNode"

    override fun flatten(): List<DeltaOperation> {
        return listOf(this)
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                          label: EEnum?, nodeType: EEnum?): EObject {
        val operation = factory.create(classes[description])
        val nodeNameAttribute = operation.eClass().getEStructuralFeature("nodeName")
        val nodeTypeAttribute = operation.eClass().getEStructuralFeature("nodeType")
        val nodeRegionAttribute = operation.eClass().getEStructuralFeature("nodeRegion")

        operation.eSet(nodeNameAttribute, node.name)
        operation.eSet(nodeTypeAttribute, nodeType!!.getEEnumLiteral(this.nodeType.name))
        operation.eSet(nodeRegionAttribute, region?.name ?: "")

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is AddNode){
            return node.deepEquals(other.node) && region?.name == other.region?.name
        }
        return false
    }
}