/**
 * Copyright 2023 Karl Kegel
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
package graphmodel

import com.sun.org.apache.xpath.internal.operations.Bool
import ecore.EObjectSource
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EFactory
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.impl.EEnumLiteralImpl
import kotlin.random.Random

class SimpleNode(id: String?, name: String, var label: Label, serializeWithIDs: Boolean = false) : Node(id, name, serializeWithIDs), EObjectSource {

    private val description = "SimpleNode"

    override fun deepCopy(): Node {
        return SimpleNode(id, name, label, serializeWithIDs)
    }

    override fun generate(classes: Map<String, EClass>, factory: EFactory, filter: Set<String>,
                          label: EEnum?, nodeType: EEnum?): EObject {
        val node = factory.create(classes[description])
        val nameAttribute = node.eClass().getEStructuralFeature("name")
        val labelAttribute = node.eClass().getEStructuralFeature("label")

        if(serializeWithIDs){
            val idAttribute = node.eClass().getEStructuralFeature("id")
            node.eSet(idAttribute, id)
        }

        node.eSet(nameAttribute, super.name)
        node.eSet(labelAttribute, label!!.getEEnumLiteral(this.label.name))

        super.buffer = node
        return node
    }


    override fun deepEquals(other: Any): Boolean {
        if(other is SimpleNode){
            return name == other.name && label.name == other.label.name
        }
        return false
    }

    companion object {

        fun randomLabel(random: Random): Label {
            val index = random.nextInt(0, 4)
            return Label.entries[index]
        }

        fun construct(predef: EObject, serializeWithIDs: Boolean): SimpleNode {
            val nameAttribute = predef.eClass().getEStructuralFeature("name")
            val labelAttribute = predef.eClass().getEStructuralFeature("label")
            val name = predef.eGet(nameAttribute, true) as String
            val labelIndex = (predef.eGet(labelAttribute, true) as EEnumLiteralImpl).value
            val label = Label.entries[labelIndex]

            var nodeID: String? = null
            if(serializeWithIDs){
                val idAttribute = predef.eClass().getEStructuralFeature("id")
                nodeID = predef.eGet(idAttribute, true) as String
            }

            return SimpleNode(nodeID, name, label, serializeWithIDs)
        }

    }

}