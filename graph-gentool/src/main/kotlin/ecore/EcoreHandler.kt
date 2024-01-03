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
package ecore

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.*
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.BasicExtendedMetaData
import org.eclipse.emf.ecore.util.ExtendedMetaData
import org.eclipse.emf.ecore.xmi.XMLResource
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl

class EcoreHandler(metamodel: URI, model: URI, registryExtension: String) {

    private var resourceSet: ResourceSet? = null
    private var metamodelRoot: EPackage? = null
    private var modelResource: Resource? = null

    init {
        Resource.Factory.Registry.INSTANCE.extensionToFactoryMap["ecore"] = EcoreResourceFactoryImpl()
        Resource.Factory.Registry.INSTANCE.extensionToFactoryMap["xmi"] = XMIResourceFactoryImpl()
        Resource.Factory.Registry.INSTANCE.extensionToFactoryMap[registryExtension] = XMIResourceFactoryImpl()

        resourceSet = ResourceSetImpl()
        val extendedMetaData: ExtendedMetaData = BasicExtendedMetaData(resourceSet!!.packageRegistry)
        resourceSet!!.loadOptions[XMLResource.OPTION_EXTENDED_META_DATA] = extendedMetaData

        val metamodelResource = resourceSet!!.getResource(metamodel, true)

        val eObject = metamodelResource!!.contents[0]
        if (eObject is EPackage) {
            metamodelRoot = eObject
            resourceSet!!.packageRegistry[metamodelRoot!!.nsURI] = metamodelRoot!!
        } else {
            throw Exception("Unsupported ECORE specification.")
        }

        modelResource = resourceSet!!.getResource(model, true)
    }

    fun getModelFactory(): EFactory {
        return metamodelRoot!!.eFactoryInstance
    }

    fun getClassMap(): Map<String, EClass> {
        val metaMap: MutableMap<String, EClass> = HashMap()
        for (e in metamodelRoot!!.eClassifiers) {
            if (e is EClass) {
                metaMap[e.getName()] = e
            }
        }
        return metaMap
    }

    fun getEnumMap(): Map<String, EEnum> {
        val enumMap: MutableMap<String, EEnum> = HashMap()
        for (e in metamodelRoot!!.eClassifiers) {
            if (e is EEnum) {
                enumMap[e.getName()] = e
            }
        }
        return enumMap
    }

    fun getModelRoot(): EObject {
        return modelResource!!.contents[0]
    }

    fun saveModel() {
        modelResource!!.save(null)
    }

}