"""
Copyright 2023 Karl Kegel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
"""

from pyecore.resources import ResourceSet, URI
from pyecore.utils import DynamicEPackage
from pyecore.resources.xmi import XMIResource

if __name__ == '__main__':
    rset = ResourceSet()
    resource = rset.get_resource(URI('./resources/labelgraph.ecore'))
    mm_root = resource.contents[0]
    rset.metamodel_registry[mm_root.nsURI] = mm_root
    # At this point, the .ecore is loaded in the 'rset' as a metamodel

    Metamodel = DynamicEPackage(mm_root)

    root = Metamodel.Graph()

    n1 = Metamodel.SimpleNode(name='N1')
    n1.label = "BLUE"
    root.nodes.extend([n1])

    n2 = Metamodel.SimpleNode(name="N2")
    n2.label = "ORANGE"
    root.nodes.extend([n2])

    edge = Metamodel.Edge()
    edge.nodes.extend([n1, n2])
    root.edges.extend([edge])

    rset_out = ResourceSet()
    resource_out = rset_out.create_resource(URI('./out.labelgraph'))
    resource_out.append(root)
    resource_out.save()
