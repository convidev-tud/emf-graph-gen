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

/**
 * Delete a [Node] from the specified [Region].
 * If no Region is specified, it is assumed to be in the Graph root.
 * NodeImplications contains the [DeleteNode] actions that must be executed beforehand to "clean" the node.
 * EdgeImplications contains the [DeleteEdge] actions that must be executed beforehand to "clean" the node.
 */
class DeleteNode(val node: Node,
                 val nodeImplications: MutableList<DeleteNode>,
                 val edgeImplications: MutableList<DeleteEdge>,
                 val region: Region? = null) : DeltaOperation() {

    val description = "DeleteNode"

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                          label: EEnum?, nodeType: EEnum?): EObject {
        val operation = factory.create(classes[description])
        val nodeNameAttribute = operation.eClass().getEStructuralFeature("nodeName")
        val fromRegionAttribute = operation.eClass().getEStructuralFeature("fromRegion")

        val target = region?.name ?: ""

        operation.eSet(nodeNameAttribute, node.name)
        operation.eSet(fromRegionAttribute, target)

        val nodeImplicationRefs = operation.eClass().getEStructuralFeature("nodeImplications")
        (operation.eGet(nodeImplicationRefs) as java.util.List<Any>).addAll(nodeImplications.map { e -> e.buffer!! })

        val edgeImplicationRefs = operation.eClass().getEStructuralFeature("edgeImplications")
        (operation.eGet(edgeImplicationRefs) as java.util.List<Any>).addAll(edgeImplications.map { e -> e.buffer!! })

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is DeleteNode){
            return node.deepEquals(other.node) && region == other.region
        }
        return false
    }
}