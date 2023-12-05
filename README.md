# graph-gentool

A generator for large models of hierarchical colored labeled graphs using the EMF. 

**Structure**

* Generator
  * Gradle Setup
  * Arguments
  * Output Structure
* Graph Metamodel
* Delta Metamodel 

## Generator

The graph generator is a Kotlin JVM application based on a Gradle build setup.
The sources are located under ``graph-gentool/``. The project is compilable and usable as executable JAR.

However, the most simple way to run the application is by using the gradle run command directly. We explain this in the next section.

### Gradle Setup

The following Gradle setup is the most easy way to use the graph-gentool. The setup was tested under following system conditions. Other setups may work as well.

* Gradle        8.4
* JVM           OpenJDK 21
* OS            Mac OS X 14.0 aarch64 (should have no influence)

Navigate in the project root directory (where the ``build.gradle`` file is located). 
To run the application, execute:

```
gradle run --args="./output_directory ...Arguments" 
```
For example ``gradle run --args="./out -s 512 -r 0.1 -p"``. You find the catalogue of possible arguments in the next section.

### Arguments

Short | Argument (--arg)| Default | Description
--- | --- | --- | ---
-- | *output directory*  | ``"./"`` |                Explicit first parameter. An existing or not existing directory Path in the form "a/b/c". If not existing, it will be created. If the directory contains a generated model set, same called files are overwritten.
``-s``|  ``model_size``     | ``1024`` |      Sum of nodes and edges in the generated base model (INT)
``-n``| ``edges_per_node``  | ``1.0`` |        Number of edge elements per node element (DOUBLE). This value influences edge_distortion and is influenced by allow_partitions.
``-d``| ``edge_distortion``   | ``0.0`` |      Probability 0...1 that an edge crosses region boundaries (DOUBLE). This value is influenced by allow_partitions.
``-r``| ``region_probability`` | ``0.0`` |   Probability 0...1 that a node is a region (DOUBLE).
``-p``| ``allow_partitions``  | ``unset`` |    Allow (set) or forbid (unset) the graph to have unconnected parts (BOOL). This value influences edges_per_node.
``-c``| ``branch_number``    | ``0`` |     (:ambulance: TODO) The number of branches (variants) to create from the final base model (INT).
``-l``| ``branch_edit_length`` | ``0`` |   (:ambulance: TODO) The number of additional edit operation performed on each branch (INT).
``-f``| ``branch_edit_focus``  | ``0.0`` |   (:ambulance: TODO) Probability factor 0..1 that the next edit operation happens in the same region as the previous. A value of 0.0 results in an evenly distribution over all regions.

### Output Structure

The specified output directory is populated as follows:

* model.labelgraph
* model.graphdelta
* b_[0...branch_number-1]
  * model.labelgraph
  * model.graphdelta

The toplevel model is the base model (root graph) and the delta sequence to create it.
The branch-level models contain the edited branches (variants). The branch-level delta sequence contains the edit steps from the base to the final model.

## Graph Metamodel

The graph-gentool generates graphs following a custom metamodel. The Ecore representation is shown in the figure. The Ecore files are located under ``metamodel/labelgraph``.

The graph has the following properties:
* A Graph contains a set of Nodes and a set of Edges.
* Each Node has a name.
* An Edge connects two Nodes. The Edge has no semantics. However, the connected Nodes are ordered. 
* Each Node is either a SimpleNode or a Region.
* A SimpleNode has a label (color) out of a finite set of labels. The concrete Metamodel knows four different labels (colors).
* A Region is a Node that contains a Graph. We call it a Sub-Graph.
* An Edge can connect Nodes out of different Sub-Graphs (Cross-Hierarchy). We call such an Edge *distorted*. An Edge is posessed by the (Sub-)Graph where its first Node is located in.

![](doc/../doc/graph_metamodel_screenshot.png)

## Delta Metamodel

:ambulance: TODO