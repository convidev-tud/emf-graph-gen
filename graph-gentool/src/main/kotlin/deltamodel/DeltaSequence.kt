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

import ecore.EObjectSource
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import util.IndexedComparable
import java.util.*

class DeltaSequence(
    val deltaOperations: MutableList<DeltaOperation> = LinkedList(),
    predef: EObject? = null
) : EObjectSource, IndexedComparable() {

    private var buffer = predef
    private val description = "DeltaSequence"

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

        val flatOperationSequence: List<DeltaOperation> = deltaOperations.flatMap { op -> op.flatten() }
        val flatEObjectSequence: List<EObject> = flatOperationSequence.map { op -> op.buffer!! }

        val operationsComposition = deltaSequence.eClass().getEStructuralFeature("deltaOperations")
        (deltaSequence.eGet(operationsComposition) as java.util.List<EObject>).addAll(flatEObjectSequence)

        buffer = deltaSequence
        return deltaSequence
    }

}
