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

import ecore.DeepComparable
import ecore.EObjectSource
import ecore.IDComparable
import graphmodel.Graph
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import util.IndexedComparable
import java.util.*

class DeltaSequence(
    val deltaOperations: MutableList<DeltaOperation> = LinkedList(),
    predef: EObject? = null
) : EObjectSource, IndexedComparable(), DeepComparable, IDComparable {

    private var buffer = predef
    private val description = "DeltaSequence"

    init {
        if (deltaOperations.isEmpty() && predef != null){
            val operationsComposition = predef.eClass().getEStructuralFeature("deltaOperations")
            val eDeltaOperations = predef.eGet(operationsComposition) as java.util.List<EObject>
            for (eDeltaOperation in eDeltaOperations){

                val name = eDeltaOperation.eClass().name
                var op: DeltaOperation? = null

                when (name) {
                    "AddNode" -> {
                        op = AddNode.parse(eDeltaOperation)
                    }
                    "AddEdge" -> {
                        op = AddEdge.parse(eDeltaOperation)
                    }
                    "ChangeLabel" -> {
                        op = ChangeLabel.parse(eDeltaOperation)
                    }
                    "MoveEdge" -> {
                        op = MoveEdge.parse(eDeltaOperation)
                    }
                    "DeleteNode" -> {
                        op = DeleteNode.parse(eDeltaOperation)
                    }
                    "DeleteEdge" -> {
                        op = DeleteEdge.parse(eDeltaOperation)
                    }
                    "MoveNode" -> {
                        op = MoveNode.parse(eDeltaOperation)
                    }
                }

                if (op != null) deltaOperations.add(op)
            }
        }
    }

    fun getLength(): Int = deltaOperations.size

    fun getAtomicLength(): Int {
        var size = 0
        for (dop in deltaOperations){
            size += dop.getAtomicLength()
        }
        return size
    }

    fun pushOperation(deltaOperation: DeltaOperation){
        deltaOperations.add(deltaOperation)
    }

    fun pushOperations(deltaOperations: List<DeltaOperation>){
        this.deltaOperations.addAll(deltaOperations)
    }

    fun operations(): List<DeltaOperation> {
        return deltaOperations.toList()
    }

    override fun generate(
        classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
        label: EEnum?, nodeType: EEnum?
    ): EObject {

        val deltaSequence = buffer ?: factory.create(classes[description])

        //recursively generate delta operations to EObjects
        deltaOperations.forEach { op -> op.generate(classes, factory, filter, label, nodeType) }

        //(old) val flatOperationSequence: List<DeltaOperation> = deltaOperations.flatMap { op -> op.flatten() }
        //because of the newly introduced composition, we need no flattening.
        //All recursive operations are contained in their parent operations, they form a true tree.
        val flatEObjectSequence: List<EObject> = deltaOperations.map { op -> op.buffer!! }

        val operationsComposition = deltaSequence.eClass().getEStructuralFeature("deltaOperations")
        (deltaSequence.eGet(operationsComposition) as java.util.List<EObject>).addAll(flatEObjectSequence)

        buffer = deltaSequence
        return deltaSequence
    }

    override fun deepEquals(other: Any): Boolean {
        if(other !is DeltaSequence) return false
        if(deltaOperations.size != other.deltaOperations.size) return false
        for(deltaOperation in deltaOperations){
            if(!other.deltaOperations.any { it.deepEquals(deltaOperation) }) return false
        }
        return true
    }

    override fun idEquals(other: Any): Boolean {
        if(other !is DeltaSequence) return false
        if(deltaOperations.size != other.deltaOperations.size) return false
        for(deltaOperation in deltaOperations){
            if(!other.deltaOperations.any { it.idEquals(deltaOperation) }) return false
        }
        return true
    }

}
