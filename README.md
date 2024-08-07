# graph-gentool

A generator for large models of hierarchical colored labeled graphs using the EMF. 
The generator is able to incrementally generate models, model variants, model evolutions and respective change sequences (deltas). The generator supports model generation with and without EcoreIDs.

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

The most simple way to run the application is by using the gradle run command directly as explained in the following section.

### Gradle Setup

The following Gradle setup is the most easy way to use the graph-gentool. The setup was tested under following system conditions. Other setups may work as well.

* Gradle        8.4
* JVM           OpenJDK 21
* OS            Mac OS X 14.0 aarch64 (should have no influence) AND Ubuntu 21.04

Navigate in the project root directory (where the ``build.gradle`` file is located).
To run the application, execute:

```
gradle run --args="./output_directory ...Arguments" 
```
For example:
```
 gradle run --args="./out -c 1 -d 0.1 -e -f 0.8 -n 3 -r 0.2 -s 10000 -l 100"
```

:ambulance: As of right now, executing the compiled JAR does occasionally no work out of the box. Please use the Gradle setup in this case. 

You find the catalogue of possible arguments in the next section.

### Arguments

Short | Argument (--arg)| Default | Description
--- | --- | --- | ---
-- | *output directory*  | ``"./"`` |                Explicit first parameter. An existing or not existing directory Path in the form "a/b/c". If not existing, it will be created. If the directory contains a generated model set, files are overwritten in case of equal names.
``-i`` | ``--with_eids``| ``false`` | Flag to select the metamodel (graph + deltas) either without (false) or with (true) unique UUIDS. See sections *Graph-* and *Delta Metamodel* for more information.
``-s``|  ``model_size``     | ``1024`` |      Sum of nodes and edges in the generated base model (INT)
``-n``| ``edges_per_node``  | ``1.0`` |        Number of edge elements per node element (DOUBLE). This value influences edge_distortion and is influenced by allow_partitions.
``-d``| ``edge_distortion``   | ``0.0`` |      Probability 0...1 that an edge crosses region boundaries (DOUBLE). This value is influenced by allow_partitions.
``-r``| ``region_probability`` | ``0.0`` |   Probability 0...1 that a node is a region (DOUBLE).
``-p``| ``allow_partitions``  | ``unset`` |    Allow (set) or forbid (unset) the graph to have unconnected parts within a region and regions (1) (BOOL). This value influences edges_per_node.
``-c``| ``branch_number``    | ``0`` |     The number of branches (variants) to create from the final base model (INT).
``-l``| ``branch_edit_length`` | ``0`` |   The number of additional edit operation performed on each branch (INT).
``-f``| ``branch_edit_focus``  | ``0.0`` |   Probability factor 0..1 that the next edit operation happens in the same region as the previous. A value of 0.0 results in an evenly distribution over all regions.
``-e`` | ``atomic_counting`` | ``false`` | Toggle, how the edit length is counted. ``true`` for atomic counting and ``false`` for accumulative counting. If ``true``, the resulting edit sequence will have exactly the same size as specified by the branch edit length. If ``false``, the number of explicit (high-level) edits is counted (although writing the atomic edits to the edit sequence). For example, let there be a region R containing 3 nodes and 2 edges. If ``delete R`` is the edit.  If R gets deleted, its composite contents must be deleted as well. The result are 6 atomic edits which are added to the edit sequence (one explicit edit and 5 implicit edits). If atomic counting is used, the counter increments by 6. If no atomic counting is used, the counter increments by 1 (BOOL).
``-x`` | ``--stepwise_export`` | ``false``| Toggle if the processor exports the variants only once or after each step. The base model generation is not influenced by this toggle. If set to true, a model variant with an edit length of 10 leads to 10 exported models. This operation is not compatible with atomic counting (BOOL).
``-u`` | ``--random_seed``| ``0`` | Random seed for the strict deterministic random generation algorithm (INT).
``-o`` | ``--edit_probabilities`` | ``"15:5:5:5:25:25:20"`` | Edit probabilities (int) seperated by ':'. The order is ADD_SIMPLE, ADD_REGION, DELETE_NODE, MOVE_NODE, CHANGE_LABEL, ADD_EDGE, DELETE_EDGE. The sum of all probabilities must be 100. "

**(1) Connectedness** 

In general, a graph has partitions if two or more subgraphs without connecting edges can be identified. In the case of the graph-gentool, we use stricter definition of connectedness to improve computation complexity: Edges between nodes of different regions are not traversed to check for partitions. Consequently, a graph has no partitions if the each sub-graph has no partition ignoring incoming and outgoing edges AND the set of regions form a composite structure (true by construction).

Example: The first figure shows a graph (as it could be generated by this tool) with has partitions according to our definition. All nodes in Region A (A, B, Region B, Region C) are connected. However, nodes E and F have no connection within Region C. Therefore, the they are considered partitions.

In the second figure, E and F are connected within Region C. Therefore, the graph has no partitions.


|   |   |
|---|---|
| ![](doc/../doc/example_graph_with_partition.png) | ![](doc/../doc/example_graph_without_partition.png) |

### Output Structure

The specified output directory is populated as follows:

* model.labelgraph
* model.graphdelta
* b_[0...branch_number-1]
  * model_[0...edit_steps-1].labelgraph
  * model_[0...edit_steps-1].graphdelta

The toplevel model is the base model (root graph) and the delta sequence to create it.
The branch-level models contain the edited branches (variants). The branch-level delta sequence contains the edit steps from the base to the final model.

**You find generated example data in the ``example`` folder of this repository.**

### Runtime Properties

:ambulance: The current implementation of the generator is not optimized for performance yet.

> This section is NOT based on scientific measurements but simple test-runs with time tracking only.

Generating a graph base model has a sub-quadratic average complecity (estimated / not prooven yet). The perceived generation speed is fast for smaller and mid-sized models. Generation and serialization of models less thann 100.000 elements (n/e = 1/3) takes less than one second. For models with 1.000.000 elements (n/e = 1/3) it takes around 60 seconds. However, the generation of edit sequences can easily run into very long (not terminating) execution times. From first observations, generation long edit sequences for small base models is fast. Generation small edit sequences for large base models is slow. The edit algoritm is non-deteministic but assures a non-zero probability to terminate.

The following table shows execution times for selected model sizes. Tested on a MacBook with an Apple M2 processor. The follwong configuration is used. The runs are **without stepwise export and without e-ids enabled**. Both features slow the generator down significantly.

```
modelSize = X,
branchEditLength = Y,
edgesPerNode = 3.0,
regionProbability = 0.2,
allowPartitions = false,
edgeDistortion = 0.1,
branchNumber = 1,
branchEditFocus = 0.8,
atomicCounting = true,
randomSeed = 1
```

Change operations are choosen with the following percentages.

```
"ADD_SIMPLE" --> 18%
"ADD_REGION" --> 2%
"DELETE_NODE" --> 20%
"MOVE_NODE" --> 20%
"CHANGE_LABEL" --> 20%
"ADD_EDGE" --> 10%
"DELETE_EDGE" --> 10%
```

Executed via gradle (example call from the project root).

```
 gradle run --args="./out -c 1 -d 0.1 -e -f 0.8 -n 3 -r 0.2 -s 10000 -l 100"
```

| Base Model Size | Edit Sequence Length | Time Base Model Generation | Time Edit Generation |
| ---       | ---  | ---    | ---     |
| 1.000     | 10   | 0.067s |  0.4s   |
| 1.000     | 100  | 0.065s |  0.8s   |
| 1.000     | 200  | 0.065s |  0.9s   |
| 10.000    | 100  | 0.165s |  3.6s   |
| 10.000    | 500  | 0.168s |  11.6s   |
| 10.000    | 1000 | 0.176s |  25.2s   |
| 10.000    | 1500 | 0.176s |  37.0s   |
| 10.000    | 2000 | 0.172s |  43.4s   |
| 20.000    | 100  | 0.241s |  18.4s   |
| 30.000    | 100  | 0.315s |  38.2s   |
| 50.000    | 100  | 0.488s |  100.0s  |
| 100.000   | 10   | 1.1s   |  82.0s   |
| 400.000 |    | 11.3s   |     |
| 700.000 |    | 26.6s   |     |
| 1.000.000 |    | 55.4s   |     |

![](doc/../doc/gentool_performance_charts.png)

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

**The graph metamodel exists in two variants: with and without EcoreIDs. You can select the metamodel via the input arguments.**

:zap: **The according delta metamodel (with- or without) IDs is selected accordingly!**

Graph metamodel without IDs:
![](doc/../doc/model_graph_noids.png)

---

Graph metamodel with IDs:
![](doc/../doc/model_graph_ids.png)

**Implementation Details**

The implemented construction algorithm has certain consequences on the graph structure:
* Starting from the root, the regions are initially organized to form a tree.
  * Edge additions (having more edges as required) may lead to cyclic connected regions within the level of a common parent region.
  * If the edge distortion probability is 0.0, regions do not have cross-hierarchy cycles.
  * SimpleNodes are distributed evenly over all regions
  * Regions are distributed according to the region distribution algorithm

**Region Distribution**

1. Define the two sets *Distributed* and *NotDistributed*
2. Construct all regions and add them to the *NotDistributed* set.
3. Pick one Region as the root Region; Link it as the graph root; Move it from *NotDistributed* to *Distributed*
4. While *NotDistributed* is not empty:
   1. Take and remove a Region from *NotDistributed*
   2. Link it as child of a random Region from *Distributed*
   3. Add it to *Distributed*

This leads to a tree composite hierarchy. Regions closer to the root contain (have a higher probability of) more direct child regions as Regions farer away from the root.

## Delta Metamodel

Delta metamodel without IDs:
![](doc/../doc/model_delta_noids.png)

---

Delta metamodel with IDs:
![](doc/../doc/model_delta_ids.png)
