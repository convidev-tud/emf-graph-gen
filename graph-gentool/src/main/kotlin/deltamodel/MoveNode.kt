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
 * Move a [Node] from one [Region] to another one.
 * If a Region is null, the graph root is used instead.
 * EdgeImplications are the movement of Edges to be located in the same Graph as their start Node.
 * <strong>This operation must assure that regions do not have cycles in their composition structure!</strong>
 */
class MoveNode(id: String,
               val nodeName: String,
               val targetRegionName: String = "",
               val oldRegionName: String = "",
               val edgeImplications: MutableList<MoveEdge> = LinkedList()
    ) : DeltaOperation(id) {

    private val description = "MoveNode"

    override fun flatten(): List<DeltaOperation> {
        val result: MutableList<DeltaOperation> = LinkedList()
        for (op in edgeImplications){
            result.addAll(op.flatten())
        }
        result.add(this)
        return result
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                          label: EEnum?, nodeType: EEnum?): EObject {

        edgeImplications.forEach{ei -> ei.generate(classes, factory, filter, label, nodeType)}

        val operation = factory.create(classes[description])
        val nodeNameAttribute = operation.eClass().getEStructuralFeature("nodeName")
        val targetRegionAttribute = operation.eClass().getEStructuralFeature("targetRegion")
        val oldRegionAttribute = operation.eClass().getEStructuralFeature("oldRegion")
        val idAttribute = operation.eClass().getEStructuralFeature("id")

        operation.eSet(nodeNameAttribute, nodeName)
        operation.eSet(targetRegionAttribute, targetRegionName)
        operation.eSet(oldRegionAttribute, oldRegionName)
        operation.eSet(idAttribute, id)

        val edgeImplicationRefs = operation.eClass().getEStructuralFeature("edgeImplications")
        (operation.eGet(edgeImplicationRefs) as java.util.List<Any>).addAll(edgeImplications.map { e -> e.buffer!! })

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is MoveNode){
            for (moveEdge in edgeImplications) {
                if (!other.edgeImplications.any { it.deepEquals(moveEdge) }) return false
            }
            return other.nodeName == nodeName && other.targetRegionName == targetRegionName &&
                    other.oldRegionName == oldRegionName
        }
        return false
    }

    companion object {

        fun parse(eObject: EObject): MoveNode {
            val nodeName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeName"), true) as String
            val id = eObject.eGet(eObject.eClass().getEStructuralFeature("id"), true) as String
            val targetRegionName = eObject.eGet(eObject.eClass().getEStructuralFeature("targetRegion"), true) as String
            val oldRegionName = eObject.eGet(eObject.eClass().getEStructuralFeature("oldRegion"), true) as String

            val edgeImplications = (eObject.eGet(eObject.eClass().
            getEStructuralFeature("edgeImplications"), true) as List<EObject>).map { e ->
                MoveEdge.parse(e) } as MutableList<MoveEdge>

            return MoveNode(id, nodeName, targetRegionName, oldRegionName, edgeImplications)
        }
    }
}