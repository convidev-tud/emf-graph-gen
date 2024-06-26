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
import org.eclipse.emf.ecore.EObject
import util.IndexedComparable
import java.util.*

abstract class DeltaOperation(val id: String) : EObjectSource, DeepComparable, IDComparable, IndexedComparable() {

    /**
     * Set the EObject after it was generated for later use
     */
    var buffer: EObject? = null

    abstract fun flatten(): List<DeltaOperation>

    fun getAtomicLength() = flatten().size

    override fun idEquals(other: Any): Boolean {
        if(other is DeltaOperation){
            return id == other.id
        }
        return false
    }

    companion object {

        fun generateId(): String {
            return UUID.randomUUID().toString()
        }

    }

}