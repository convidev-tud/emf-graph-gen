/**
 */
package graphdelta;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see graphdelta.GraphdeltaFactory
 * @model kind="package"
 * @generated
 */
public interface GraphdeltaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "graphdelta";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://st.tud.de/graphdelta";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "graphdelta";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GraphdeltaPackage eINSTANCE = graphdelta.impl.GraphdeltaPackageImpl.init();

	/**
	 * The meta object id for the '{@link graphdelta.impl.DeltaSequenceImpl <em>Delta Sequence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see graphdelta.impl.DeltaSequenceImpl
	 * @see graphdelta.impl.GraphdeltaPackageImpl#getDeltaSequence()
	 * @generated
	 */
	int DELTA_SEQUENCE = 0;

	/**
	 * The feature id for the '<em><b>Delta Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELTA_SEQUENCE__DELTA_OPERATIONS = 0;

	/**
	 * The number of structural features of the '<em>Delta Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELTA_SEQUENCE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Delta Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELTA_SEQUENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link graphdelta.impl.DeltaOperationImpl <em>Delta Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see graphdelta.impl.DeltaOperationImpl
	 * @see graphdelta.impl.GraphdeltaPackageImpl#getDeltaOperation()
	 * @generated
	 */
	int DELTA_OPERATION = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELTA_OPERATION__ID = 0;

	/**
	 * The number of structural features of the '<em>Delta Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELTA_OPERATION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Delta Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELTA_OPERATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link graphdelta.impl.AddNodeImpl <em>Add Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see graphdelta.impl.AddNodeImpl
	 * @see graphdelta.impl.GraphdeltaPackageImpl#getAddNode()
	 * @generated
	 */
	int ADD_NODE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_NODE__ID = DELTA_OPERATION__ID;

	/**
	 * The feature id for the '<em><b>Node Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_NODE__NODE_NAME = DELTA_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Node Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_NODE__NODE_TYPE = DELTA_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>To Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_NODE__TO_REGION = DELTA_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Add Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_NODE_FEATURE_COUNT = DELTA_OPERATION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Add Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_NODE_OPERATION_COUNT = DELTA_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link graphdelta.impl.DeleteNodeImpl <em>Delete Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see graphdelta.impl.DeleteNodeImpl
	 * @see graphdelta.impl.GraphdeltaPackageImpl#getDeleteNode()
	 * @generated
	 */
	int DELETE_NODE = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_NODE__ID = DELTA_OPERATION__ID;

	/**
	 * The feature id for the '<em><b>Node Implications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_NODE__NODE_IMPLICATIONS = DELTA_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Edge Implications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_NODE__EDGE_IMPLICATIONS = DELTA_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Node Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_NODE__NODE_NAME = DELTA_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>From Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_NODE__FROM_REGION = DELTA_OPERATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Delete Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_NODE_FEATURE_COUNT = DELTA_OPERATION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Delete Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_NODE_OPERATION_COUNT = DELTA_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link graphdelta.impl.AddEdgeImpl <em>Add Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see graphdelta.impl.AddEdgeImpl
	 * @see graphdelta.impl.GraphdeltaPackageImpl#getAddEdge()
	 * @generated
	 */
	int ADD_EDGE = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EDGE__ID = DELTA_OPERATION__ID;

	/**
	 * The feature id for the '<em><b>Node A</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EDGE__NODE_A = DELTA_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Node B</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EDGE__NODE_B = DELTA_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Add Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EDGE_FEATURE_COUNT = DELTA_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Add Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EDGE_OPERATION_COUNT = DELTA_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link graphdelta.impl.DeleteEdgeImpl <em>Delete Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see graphdelta.impl.DeleteEdgeImpl
	 * @see graphdelta.impl.GraphdeltaPackageImpl#getDeleteEdge()
	 * @generated
	 */
	int DELETE_EDGE = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_EDGE__ID = DELTA_OPERATION__ID;

	/**
	 * The feature id for the '<em><b>Node A</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_EDGE__NODE_A = DELTA_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Node B</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_EDGE__NODE_B = DELTA_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Delete Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_EDGE_FEATURE_COUNT = DELTA_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Delete Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELETE_EDGE_OPERATION_COUNT = DELTA_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link graphdelta.impl.MoveNodeImpl <em>Move Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see graphdelta.impl.MoveNodeImpl
	 * @see graphdelta.impl.GraphdeltaPackageImpl#getMoveNode()
	 * @generated
	 */
	int MOVE_NODE = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_NODE__ID = DELTA_OPERATION__ID;

	/**
	 * The feature id for the '<em><b>Node Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_NODE__NODE_NAME = DELTA_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_NODE__TARGET_REGION = DELTA_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Old Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_NODE__OLD_REGION = DELTA_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Edge Implications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_NODE__EDGE_IMPLICATIONS = DELTA_OPERATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Move Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_NODE_FEATURE_COUNT = DELTA_OPERATION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Move Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_NODE_OPERATION_COUNT = DELTA_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link graphdelta.impl.ChangeLabelImpl <em>Change Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see graphdelta.impl.ChangeLabelImpl
	 * @see graphdelta.impl.GraphdeltaPackageImpl#getChangeLabel()
	 * @generated
	 */
	int CHANGE_LABEL = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_LABEL__ID = DELTA_OPERATION__ID;

	/**
	 * The feature id for the '<em><b>Node Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_LABEL__NODE_NAME = DELTA_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>New Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_LABEL__NEW_LABEL = DELTA_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Old Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_LABEL__OLD_LABEL = DELTA_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Change Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_LABEL_FEATURE_COUNT = DELTA_OPERATION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Change Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_LABEL_OPERATION_COUNT = DELTA_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link graphdelta.impl.MoveEdgeImpl <em>Move Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see graphdelta.impl.MoveEdgeImpl
	 * @see graphdelta.impl.GraphdeltaPackageImpl#getMoveEdge()
	 * @generated
	 */
	int MOVE_EDGE = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_EDGE__ID = DELTA_OPERATION__ID;

	/**
	 * The feature id for the '<em><b>Old Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_EDGE__OLD_REGION = DELTA_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>New Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_EDGE__NEW_REGION = DELTA_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Node A</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_EDGE__NODE_A = DELTA_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Node B</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_EDGE__NODE_B = DELTA_OPERATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Move Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_EDGE_FEATURE_COUNT = DELTA_OPERATION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Move Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOVE_EDGE_OPERATION_COUNT = DELTA_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link graphdelta.NodeType <em>Node Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see graphdelta.NodeType
	 * @see graphdelta.impl.GraphdeltaPackageImpl#getNodeType()
	 * @generated
	 */
	int NODE_TYPE = 9;

	/**
	 * The meta object id for the '{@link graphdelta.Label <em>Label</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see graphdelta.Label
	 * @see graphdelta.impl.GraphdeltaPackageImpl#getLabel()
	 * @generated
	 */
	int LABEL = 10;

	/**
	 * Returns the meta object for class '{@link graphdelta.DeltaSequence <em>Delta Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delta Sequence</em>'.
	 * @see graphdelta.DeltaSequence
	 * @generated
	 */
	EClass getDeltaSequence();

	/**
	 * Returns the meta object for the containment reference list '{@link graphdelta.DeltaSequence#getDeltaOperations <em>Delta Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Delta Operations</em>'.
	 * @see graphdelta.DeltaSequence#getDeltaOperations()
	 * @see #getDeltaSequence()
	 * @generated
	 */
	EReference getDeltaSequence_DeltaOperations();

	/**
	 * Returns the meta object for class '{@link graphdelta.DeltaOperation <em>Delta Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delta Operation</em>'.
	 * @see graphdelta.DeltaOperation
	 * @generated
	 */
	EClass getDeltaOperation();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.DeltaOperation#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see graphdelta.DeltaOperation#getId()
	 * @see #getDeltaOperation()
	 * @generated
	 */
	EAttribute getDeltaOperation_Id();

	/**
	 * Returns the meta object for class '{@link graphdelta.AddNode <em>Add Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Node</em>'.
	 * @see graphdelta.AddNode
	 * @generated
	 */
	EClass getAddNode();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.AddNode#getNodeName <em>Node Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Name</em>'.
	 * @see graphdelta.AddNode#getNodeName()
	 * @see #getAddNode()
	 * @generated
	 */
	EAttribute getAddNode_NodeName();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.AddNode#getNodeType <em>Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Type</em>'.
	 * @see graphdelta.AddNode#getNodeType()
	 * @see #getAddNode()
	 * @generated
	 */
	EAttribute getAddNode_NodeType();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.AddNode#getToRegion <em>To Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To Region</em>'.
	 * @see graphdelta.AddNode#getToRegion()
	 * @see #getAddNode()
	 * @generated
	 */
	EAttribute getAddNode_ToRegion();

	/**
	 * Returns the meta object for class '{@link graphdelta.DeleteNode <em>Delete Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delete Node</em>'.
	 * @see graphdelta.DeleteNode
	 * @generated
	 */
	EClass getDeleteNode();

	/**
	 * Returns the meta object for the containment reference list '{@link graphdelta.DeleteNode#getNodeImplications <em>Node Implications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Node Implications</em>'.
	 * @see graphdelta.DeleteNode#getNodeImplications()
	 * @see #getDeleteNode()
	 * @generated
	 */
	EReference getDeleteNode_NodeImplications();

	/**
	 * Returns the meta object for the containment reference list '{@link graphdelta.DeleteNode#getEdgeImplications <em>Edge Implications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Edge Implications</em>'.
	 * @see graphdelta.DeleteNode#getEdgeImplications()
	 * @see #getDeleteNode()
	 * @generated
	 */
	EReference getDeleteNode_EdgeImplications();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.DeleteNode#getNodeName <em>Node Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Name</em>'.
	 * @see graphdelta.DeleteNode#getNodeName()
	 * @see #getDeleteNode()
	 * @generated
	 */
	EAttribute getDeleteNode_NodeName();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.DeleteNode#getFromRegion <em>From Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From Region</em>'.
	 * @see graphdelta.DeleteNode#getFromRegion()
	 * @see #getDeleteNode()
	 * @generated
	 */
	EAttribute getDeleteNode_FromRegion();

	/**
	 * Returns the meta object for class '{@link graphdelta.AddEdge <em>Add Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Edge</em>'.
	 * @see graphdelta.AddEdge
	 * @generated
	 */
	EClass getAddEdge();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.AddEdge#getNodeA <em>Node A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node A</em>'.
	 * @see graphdelta.AddEdge#getNodeA()
	 * @see #getAddEdge()
	 * @generated
	 */
	EAttribute getAddEdge_NodeA();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.AddEdge#getNodeB <em>Node B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node B</em>'.
	 * @see graphdelta.AddEdge#getNodeB()
	 * @see #getAddEdge()
	 * @generated
	 */
	EAttribute getAddEdge_NodeB();

	/**
	 * Returns the meta object for class '{@link graphdelta.DeleteEdge <em>Delete Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delete Edge</em>'.
	 * @see graphdelta.DeleteEdge
	 * @generated
	 */
	EClass getDeleteEdge();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.DeleteEdge#getNodeA <em>Node A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node A</em>'.
	 * @see graphdelta.DeleteEdge#getNodeA()
	 * @see #getDeleteEdge()
	 * @generated
	 */
	EAttribute getDeleteEdge_NodeA();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.DeleteEdge#getNodeB <em>Node B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node B</em>'.
	 * @see graphdelta.DeleteEdge#getNodeB()
	 * @see #getDeleteEdge()
	 * @generated
	 */
	EAttribute getDeleteEdge_NodeB();

	/**
	 * Returns the meta object for class '{@link graphdelta.MoveNode <em>Move Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Move Node</em>'.
	 * @see graphdelta.MoveNode
	 * @generated
	 */
	EClass getMoveNode();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.MoveNode#getNodeName <em>Node Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Name</em>'.
	 * @see graphdelta.MoveNode#getNodeName()
	 * @see #getMoveNode()
	 * @generated
	 */
	EAttribute getMoveNode_NodeName();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.MoveNode#getTargetRegion <em>Target Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Region</em>'.
	 * @see graphdelta.MoveNode#getTargetRegion()
	 * @see #getMoveNode()
	 * @generated
	 */
	EAttribute getMoveNode_TargetRegion();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.MoveNode#getOldRegion <em>Old Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Old Region</em>'.
	 * @see graphdelta.MoveNode#getOldRegion()
	 * @see #getMoveNode()
	 * @generated
	 */
	EAttribute getMoveNode_OldRegion();

	/**
	 * Returns the meta object for the containment reference list '{@link graphdelta.MoveNode#getEdgeImplications <em>Edge Implications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Edge Implications</em>'.
	 * @see graphdelta.MoveNode#getEdgeImplications()
	 * @see #getMoveNode()
	 * @generated
	 */
	EReference getMoveNode_EdgeImplications();

	/**
	 * Returns the meta object for class '{@link graphdelta.ChangeLabel <em>Change Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Change Label</em>'.
	 * @see graphdelta.ChangeLabel
	 * @generated
	 */
	EClass getChangeLabel();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.ChangeLabel#getNodeName <em>Node Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Name</em>'.
	 * @see graphdelta.ChangeLabel#getNodeName()
	 * @see #getChangeLabel()
	 * @generated
	 */
	EAttribute getChangeLabel_NodeName();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.ChangeLabel#getNewLabel <em>New Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Label</em>'.
	 * @see graphdelta.ChangeLabel#getNewLabel()
	 * @see #getChangeLabel()
	 * @generated
	 */
	EAttribute getChangeLabel_NewLabel();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.ChangeLabel#getOldLabel <em>Old Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Old Label</em>'.
	 * @see graphdelta.ChangeLabel#getOldLabel()
	 * @see #getChangeLabel()
	 * @generated
	 */
	EAttribute getChangeLabel_OldLabel();

	/**
	 * Returns the meta object for class '{@link graphdelta.MoveEdge <em>Move Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Move Edge</em>'.
	 * @see graphdelta.MoveEdge
	 * @generated
	 */
	EClass getMoveEdge();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.MoveEdge#getOldRegion <em>Old Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Old Region</em>'.
	 * @see graphdelta.MoveEdge#getOldRegion()
	 * @see #getMoveEdge()
	 * @generated
	 */
	EAttribute getMoveEdge_OldRegion();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.MoveEdge#getNewRegion <em>New Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Region</em>'.
	 * @see graphdelta.MoveEdge#getNewRegion()
	 * @see #getMoveEdge()
	 * @generated
	 */
	EAttribute getMoveEdge_NewRegion();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.MoveEdge#getNodeA <em>Node A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node A</em>'.
	 * @see graphdelta.MoveEdge#getNodeA()
	 * @see #getMoveEdge()
	 * @generated
	 */
	EAttribute getMoveEdge_NodeA();

	/**
	 * Returns the meta object for the attribute '{@link graphdelta.MoveEdge#getNodeB <em>Node B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node B</em>'.
	 * @see graphdelta.MoveEdge#getNodeB()
	 * @see #getMoveEdge()
	 * @generated
	 */
	EAttribute getMoveEdge_NodeB();

	/**
	 * Returns the meta object for enum '{@link graphdelta.NodeType <em>Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Node Type</em>'.
	 * @see graphdelta.NodeType
	 * @generated
	 */
	EEnum getNodeType();

	/**
	 * Returns the meta object for enum '{@link graphdelta.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Label</em>'.
	 * @see graphdelta.Label
	 * @generated
	 */
	EEnum getLabel();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GraphdeltaFactory getGraphdeltaFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link graphdelta.impl.DeltaSequenceImpl <em>Delta Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see graphdelta.impl.DeltaSequenceImpl
		 * @see graphdelta.impl.GraphdeltaPackageImpl#getDeltaSequence()
		 * @generated
		 */
		EClass DELTA_SEQUENCE = eINSTANCE.getDeltaSequence();

		/**
		 * The meta object literal for the '<em><b>Delta Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DELTA_SEQUENCE__DELTA_OPERATIONS = eINSTANCE.getDeltaSequence_DeltaOperations();

		/**
		 * The meta object literal for the '{@link graphdelta.impl.DeltaOperationImpl <em>Delta Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see graphdelta.impl.DeltaOperationImpl
		 * @see graphdelta.impl.GraphdeltaPackageImpl#getDeltaOperation()
		 * @generated
		 */
		EClass DELTA_OPERATION = eINSTANCE.getDeltaOperation();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELTA_OPERATION__ID = eINSTANCE.getDeltaOperation_Id();

		/**
		 * The meta object literal for the '{@link graphdelta.impl.AddNodeImpl <em>Add Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see graphdelta.impl.AddNodeImpl
		 * @see graphdelta.impl.GraphdeltaPackageImpl#getAddNode()
		 * @generated
		 */
		EClass ADD_NODE = eINSTANCE.getAddNode();

		/**
		 * The meta object literal for the '<em><b>Node Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_NODE__NODE_NAME = eINSTANCE.getAddNode_NodeName();

		/**
		 * The meta object literal for the '<em><b>Node Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_NODE__NODE_TYPE = eINSTANCE.getAddNode_NodeType();

		/**
		 * The meta object literal for the '<em><b>To Region</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_NODE__TO_REGION = eINSTANCE.getAddNode_ToRegion();

		/**
		 * The meta object literal for the '{@link graphdelta.impl.DeleteNodeImpl <em>Delete Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see graphdelta.impl.DeleteNodeImpl
		 * @see graphdelta.impl.GraphdeltaPackageImpl#getDeleteNode()
		 * @generated
		 */
		EClass DELETE_NODE = eINSTANCE.getDeleteNode();

		/**
		 * The meta object literal for the '<em><b>Node Implications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DELETE_NODE__NODE_IMPLICATIONS = eINSTANCE.getDeleteNode_NodeImplications();

		/**
		 * The meta object literal for the '<em><b>Edge Implications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DELETE_NODE__EDGE_IMPLICATIONS = eINSTANCE.getDeleteNode_EdgeImplications();

		/**
		 * The meta object literal for the '<em><b>Node Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELETE_NODE__NODE_NAME = eINSTANCE.getDeleteNode_NodeName();

		/**
		 * The meta object literal for the '<em><b>From Region</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELETE_NODE__FROM_REGION = eINSTANCE.getDeleteNode_FromRegion();

		/**
		 * The meta object literal for the '{@link graphdelta.impl.AddEdgeImpl <em>Add Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see graphdelta.impl.AddEdgeImpl
		 * @see graphdelta.impl.GraphdeltaPackageImpl#getAddEdge()
		 * @generated
		 */
		EClass ADD_EDGE = eINSTANCE.getAddEdge();

		/**
		 * The meta object literal for the '<em><b>Node A</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_EDGE__NODE_A = eINSTANCE.getAddEdge_NodeA();

		/**
		 * The meta object literal for the '<em><b>Node B</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_EDGE__NODE_B = eINSTANCE.getAddEdge_NodeB();

		/**
		 * The meta object literal for the '{@link graphdelta.impl.DeleteEdgeImpl <em>Delete Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see graphdelta.impl.DeleteEdgeImpl
		 * @see graphdelta.impl.GraphdeltaPackageImpl#getDeleteEdge()
		 * @generated
		 */
		EClass DELETE_EDGE = eINSTANCE.getDeleteEdge();

		/**
		 * The meta object literal for the '<em><b>Node A</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELETE_EDGE__NODE_A = eINSTANCE.getDeleteEdge_NodeA();

		/**
		 * The meta object literal for the '<em><b>Node B</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELETE_EDGE__NODE_B = eINSTANCE.getDeleteEdge_NodeB();

		/**
		 * The meta object literal for the '{@link graphdelta.impl.MoveNodeImpl <em>Move Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see graphdelta.impl.MoveNodeImpl
		 * @see graphdelta.impl.GraphdeltaPackageImpl#getMoveNode()
		 * @generated
		 */
		EClass MOVE_NODE = eINSTANCE.getMoveNode();

		/**
		 * The meta object literal for the '<em><b>Node Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOVE_NODE__NODE_NAME = eINSTANCE.getMoveNode_NodeName();

		/**
		 * The meta object literal for the '<em><b>Target Region</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOVE_NODE__TARGET_REGION = eINSTANCE.getMoveNode_TargetRegion();

		/**
		 * The meta object literal for the '<em><b>Old Region</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOVE_NODE__OLD_REGION = eINSTANCE.getMoveNode_OldRegion();

		/**
		 * The meta object literal for the '<em><b>Edge Implications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MOVE_NODE__EDGE_IMPLICATIONS = eINSTANCE.getMoveNode_EdgeImplications();

		/**
		 * The meta object literal for the '{@link graphdelta.impl.ChangeLabelImpl <em>Change Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see graphdelta.impl.ChangeLabelImpl
		 * @see graphdelta.impl.GraphdeltaPackageImpl#getChangeLabel()
		 * @generated
		 */
		EClass CHANGE_LABEL = eINSTANCE.getChangeLabel();

		/**
		 * The meta object literal for the '<em><b>Node Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE_LABEL__NODE_NAME = eINSTANCE.getChangeLabel_NodeName();

		/**
		 * The meta object literal for the '<em><b>New Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE_LABEL__NEW_LABEL = eINSTANCE.getChangeLabel_NewLabel();

		/**
		 * The meta object literal for the '<em><b>Old Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE_LABEL__OLD_LABEL = eINSTANCE.getChangeLabel_OldLabel();

		/**
		 * The meta object literal for the '{@link graphdelta.impl.MoveEdgeImpl <em>Move Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see graphdelta.impl.MoveEdgeImpl
		 * @see graphdelta.impl.GraphdeltaPackageImpl#getMoveEdge()
		 * @generated
		 */
		EClass MOVE_EDGE = eINSTANCE.getMoveEdge();

		/**
		 * The meta object literal for the '<em><b>Old Region</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOVE_EDGE__OLD_REGION = eINSTANCE.getMoveEdge_OldRegion();

		/**
		 * The meta object literal for the '<em><b>New Region</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOVE_EDGE__NEW_REGION = eINSTANCE.getMoveEdge_NewRegion();

		/**
		 * The meta object literal for the '<em><b>Node A</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOVE_EDGE__NODE_A = eINSTANCE.getMoveEdge_NodeA();

		/**
		 * The meta object literal for the '<em><b>Node B</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOVE_EDGE__NODE_B = eINSTANCE.getMoveEdge_NodeB();

		/**
		 * The meta object literal for the '{@link graphdelta.NodeType <em>Node Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see graphdelta.NodeType
		 * @see graphdelta.impl.GraphdeltaPackageImpl#getNodeType()
		 * @generated
		 */
		EEnum NODE_TYPE = eINSTANCE.getNodeType();

		/**
		 * The meta object literal for the '{@link graphdelta.Label <em>Label</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see graphdelta.Label
		 * @see graphdelta.impl.GraphdeltaPackageImpl#getLabel()
		 * @generated
		 */
		EEnum LABEL = eINSTANCE.getLabel();

	}

} //GraphdeltaPackage
