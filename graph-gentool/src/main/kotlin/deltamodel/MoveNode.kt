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
class MoveNode(/*all*/ id: String,
               val nodeName: String?,
               val nodeID: String?,
               val targetRegionName: String? = "root",
               val targetRegionID: String? = "root",
               val oldRegionName: String? = "root",
               val oldRegionID: String? = "root",
               /*all*/ val edgeImplications: MutableList<MoveEdge> = LinkedList(),
               val serializeWithIDs: Boolean
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
        val idAttribute = operation.eClass().getEStructuralFeature("id")
        operation.eSet(idAttribute, id)

        val edgeImplicationRefs = operation.eClass().getEStructuralFeature("edgeImplications")
        (operation.eGet(edgeImplicationRefs) as java.util.List<Any>).addAll(edgeImplications.map { e -> e.buffer!! })

        if(serializeWithIDs){
            val nodeIDAttribute = operation.eClass().getEStructuralFeature("nodeID")
            val targetRegionIDAttribute = operation.eClass().getEStructuralFeature("targetRegionID")
            val oldRegionIDAttribute = operation.eClass().getEStructuralFeature("oldRegionID")
            operation.eSet(nodeIDAttribute, nodeID)
            operation.eSet(targetRegionIDAttribute, targetRegionID)
            operation.eSet(oldRegionIDAttribute, oldRegionID)
        }else {
            val nodeNameAttribute = operation.eClass().getEStructuralFeature("nodeName")
            val targetRegionAttribute = operation.eClass().getEStructuralFeature("targetRegion")
            val oldRegionAttribute = operation.eClass().getEStructuralFeature("oldRegion")
            operation.eSet(nodeNameAttribute, nodeName)
            operation.eSet(targetRegionAttribute, targetRegionName)
            operation.eSet(oldRegionAttribute, oldRegionName)
        }

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is MoveNode){
            for (moveEdge in edgeImplications) {
                if (!other.edgeImplications.any { it.deepEquals(moveEdge) }) return false
            }
            return if(serializeWithIDs){
                nodeID == other.nodeID && targetRegionID == other.targetRegionID &&
                        oldRegionID == other.oldRegionID
            }else{
                val res = nodeName == other.nodeName && targetRegionName == other.targetRegionName &&
                        oldRegionName == other.oldRegionName
                if(idEquals(other) && !res){
                    throw AssertionError("Incoherent Comparison MoveNode: $this != $other")
                }
                res
            }
        }
        return false
    }

    companion object {

        fun parse(eObject: EObject, serializeWithIDs: Boolean): MoveNode {

            val id = eObject.eGet(eObject.eClass().getEStructuralFeature("id"), true) as String
            val edgeImplications = (eObject.eGet(eObject.eClass().
            getEStructuralFeature("edgeImplications"), true) as List<EObject>).map { e ->
                MoveEdge.parse(e, serializeWithIDs) } as MutableList<MoveEdge>

            var nodeName: String? = null
            var targetRegionName: String? = null
            var oldRegionName: String? = null
            var nodeID: String? = null
            var targetRegionID: String? = null
            var oldRegionID: String? = null

            if(serializeWithIDs){
                nodeID = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeID"), true) as String
                targetRegionID = eObject.eGet(eObject.eClass().getEStructuralFeature("targetRegionID"), true) as String
                oldRegionID = eObject.eGet(eObject.eClass().getEStructuralFeature("oldRegionID"), true) as String
            }else {
                nodeName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeName"), true) as String
                targetRegionName = eObject.eGet(eObject.eClass().getEStructuralFeature("targetRegion"), true) as String
                oldRegionName = eObject.eGet(eObject.eClass().getEStructuralFeature("oldRegion"), true) as String
            }

            return MoveNode(id, nodeName, nodeID, targetRegionName, targetRegionID, oldRegionName, oldRegionID, edgeImplications, serializeWithIDs)
        }
    }
}