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

/**
 * Change the [Label] value of an existing [Node].
 * The uniqueness of the Label value must not be violated.
 */
class ChangeLabel(val node: Node,
                  val newLabel: Label,
                  val oldLabel: Label) : DeltaOperation() {

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

        operation.eSet(nodeNameAttribute, node.name)
        operation.eSet(newLabelAttribute, label!!.getEEnumLiteral(this.newLabel.name))
        operation.eSet(oldLabelAttribute, label.getEEnumLiteral(this.oldLabel.name))

        this.buffer = operation
        return operation
    }

    override fun deepEquals(other: Any): Boolean {
        if(other is ChangeLabel){
            return node.deepEquals(other.node) && newLabel == other.newLabel && oldLabel == other.oldLabel
        }
        return false
    }
}