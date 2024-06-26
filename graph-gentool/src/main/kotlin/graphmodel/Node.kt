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

import ecore.DeepComparable
import ecore.IDComparable
import org.eclipse.emf.ecore.EObject
import util.IndexedComparable

abstract class Node(val id: String?, val name: String, val serializeWithIDs: Boolean = false) : DeepComparable, IDComparable, IndexedComparable() {

    var buffer: EObject? = null

    abstract fun deepCopy(): Node

    override fun idEquals(other: Any): Boolean {
        if(!serializeWithIDs) return false
        return other is Node && other.serializeWithIDs && id == other.id
    }

}