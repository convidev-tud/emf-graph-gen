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
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.impl.EEnumLiteralImpl

/**
 * Change the [Label] value of an existing [Node].
 * The uniqueness of the Label value must not be violated.
 */
class ChangeLabel(id: String,
                  val nodeName: String,
                  val newLabel: Label,
                  val oldLabel: Label) : DeltaOperation(id) {

    private val description = "ChangeLabel"

    override fun flatten(): List<DeltaOperation> {
        return listOf(this)
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                          label: EEnum?, nodeType: EEnum?): EObject {
        val operation = factory.create(classes[description])
        val nodeNameAttribute = operation.eClass().getEStructuralFeature("nodeName")
        val newLabelAttribute = operation.eClass().getEStructuralFeature("newLabel")
        val oldLabelAttribute = operation.eClass().getEStructuralFeature("oldLabel")
        val idAttribute = operation.eClass().getEStructuralFeature("id")

        operation.eSet(nodeNameAttribute, nodeName)
        operation.eSet(idAttribute, id)
        operation.eSet(newLabelAttribute, label!!.getEEnumLiteral(this.newLabel.name))
        operation.eSet(oldLabelAttribute, label.getEEnumLiteral(this.oldLabel.name))

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is ChangeLabel){
            return nodeName == other.nodeName && newLabel == other.newLabel && oldLabel == other.oldLabel
        }
        return false
    }

    companion object {

        fun parse(eObject: EObject): ChangeLabel {
            val nodeName = eObject.eGet(eObject.eClass().getEStructuralFeature("nodeName")) as String
            val newLabel = Label.entries[(eObject.eGet(eObject.eClass().getEStructuralFeature("newLabel")) as EEnumLiteralImpl).value]
            val oldLabel = Label.entries[(eObject.eGet(eObject.eClass().getEStructuralFeature("oldLabel")) as EEnumLiteralImpl).value]
            val id = eObject.eGet(eObject.eClass().getEStructuralFeature("id")) as String

            return ChangeLabel(id, nodeName, newLabel, oldLabel)
        }

    }

}