/**
 */
package graphdelta.impl;

import graphdelta.AddEdge;
import graphdelta.AddNode;
import graphdelta.ChangeLabel;
import graphdelta.DeleteEdge;
import graphdelta.DeleteNode;
import graphdelta.DeltaOperation;
import graphdelta.DeltaSequence;
import graphdelta.GraphdeltaFactory;
import graphdelta.GraphdeltaPackage;
import graphdelta.Label;
import graphdelta.MoveEdge;
import graphdelta.MoveNode;
import graphdelta.NodeType;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GraphdeltaPackageImpl extends EPackageImpl implements GraphdeltaPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deltaSequenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deltaOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deleteNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deleteEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moveNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass changeLabelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moveEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum nodeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum labelEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see graphdelta.GraphdeltaPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private GraphdeltaPackageImpl() {
		super(eNS_URI, GraphdeltaFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link GraphdeltaPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static GraphdeltaPackage init() {
		if (isInited)
			return (GraphdeltaPackage) EPackage.Registry.INSTANCE.getEPackage(GraphdeltaPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredGraphdeltaPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		GraphdeltaPackageImpl theGraphdeltaPackage = registeredGraphdeltaPackage instanceof GraphdeltaPackageImpl
				? (GraphdeltaPackageImpl) registeredGraphdeltaPackage
				: new GraphdeltaPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theGraphdeltaPackage.createPackageContents();

		// Initialize created meta-data
		theGraphdeltaPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theGraphdeltaPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GraphdeltaPackage.eNS_URI, theGraphdeltaPackage);
		return theGraphdeltaPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDeltaSequence() {
		return deltaSequenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeltaSequence_DeltaOperations() {
		return (EReference) deltaSequenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDeltaOperation() {
		return deltaOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeltaOperation_Id() {
		return (EAttribute) deltaOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAddNode() {
		return addNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAddNode_NodeName() {
		return (EAttribute) addNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAddNode_NodeType() {
		return (EAttribute) addNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAddNode_ToRegion() {
		return (EAttribute) addNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDeleteNode() {
		return deleteNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeleteNode_NodeImplications() {
		return (EReference) deleteNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDeleteNode_EdgeImplications() {
		return (EReference) deleteNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeleteNode_NodeName() {
		return (EAttribute) deleteNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeleteNode_FromRegion() {
		return (EAttribute) deleteNodeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAddEdge() {
		return addEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAddEdge_NodeA() {
		return (EAttribute) addEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAddEdge_NodeB() {
		return (EAttribute) addEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDeleteEdge() {
		return deleteEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeleteEdge_NodeA() {
		return (EAttribute) deleteEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDeleteEdge_NodeB() {
		return (EAttribute) deleteEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMoveNode() {
		return moveNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMoveNode_NodeName() {
		return (EAttribute) moveNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMoveNode_TargetRegion() {
		return (EAttribute) moveNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMoveNode_OldRegion() {
		return (EAttribute) moveNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMoveNode_EdgeImplications() {
		return (EReference) moveNodeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getChangeLabel() {
		return changeLabelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getChangeLabel_NodeName() {
		return (EAttribute) changeLabelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getChangeLabel_NewLabel() {
		return (EAttribute) changeLabelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getChangeLabel_OldLabel() {
		return (EAttribute) changeLabelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMoveEdge() {
		return moveEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMoveEdge_OldRegion() {
		return (EAttribute) moveEdgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMoveEdge_NewRegion() {
		return (EAttribute) moveEdgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMoveEdge_NodeA() {
		return (EAttribute) moveEdgeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMoveEdge_NodeB() {
		return (EAttribute) moveEdgeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getNodeType() {
		return nodeTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getLabel() {
		return labelEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GraphdeltaFactory getGraphdeltaFactory() {
		return (GraphdeltaFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		deltaSequenceEClass = createEClass(DELTA_SEQUENCE);
		createEReference(deltaSequenceEClass, DELTA_SEQUENCE__DELTA_OPERATIONS);

		deltaOperationEClass = createEClass(DELTA_OPERATION);
		createEAttribute(deltaOperationEClass, DELTA_OPERATION__ID);

		addNodeEClass = createEClass(ADD_NODE);
		createEAttribute(addNodeEClass, ADD_NODE__NODE_NAME);
		createEAttribute(addNodeEClass, ADD_NODE__NODE_TYPE);
		createEAttribute(addNodeEClass, ADD_NODE__TO_REGION);

		deleteNodeEClass = createEClass(DELETE_NODE);
		createEReference(deleteNodeEClass, DELETE_NODE__NODE_IMPLICATIONS);
		createEReference(deleteNodeEClass, DELETE_NODE__EDGE_IMPLICATIONS);
		createEAttribute(deleteNodeEClass, DELETE_NODE__NODE_NAME);
		createEAttribute(deleteNodeEClass, DELETE_NODE__FROM_REGION);

		addEdgeEClass = createEClass(ADD_EDGE);
		createEAttribute(addEdgeEClass, ADD_EDGE__NODE_A);
		createEAttribute(addEdgeEClass, ADD_EDGE__NODE_B);

		deleteEdgeEClass = createEClass(DELETE_EDGE);
		createEAttribute(deleteEdgeEClass, DELETE_EDGE__NODE_A);
		createEAttribute(deleteEdgeEClass, DELETE_EDGE__NODE_B);

		moveNodeEClass = createEClass(MOVE_NODE);
		createEAttribute(moveNodeEClass, MOVE_NODE__NODE_NAME);
		createEAttribute(moveNodeEClass, MOVE_NODE__TARGET_REGION);
		createEAttribute(moveNodeEClass, MOVE_NODE__OLD_REGION);
		createEReference(moveNodeEClass, MOVE_NODE__EDGE_IMPLICATIONS);

		changeLabelEClass = createEClass(CHANGE_LABEL);
		createEAttribute(changeLabelEClass, CHANGE_LABEL__NODE_NAME);
		createEAttribute(changeLabelEClass, CHANGE_LABEL__NEW_LABEL);
		createEAttribute(changeLabelEClass, CHANGE_LABEL__OLD_LABEL);

		moveEdgeEClass = createEClass(MOVE_EDGE);
		createEAttribute(moveEdgeEClass, MOVE_EDGE__OLD_REGION);
		createEAttribute(moveEdgeEClass, MOVE_EDGE__NEW_REGION);
		createEAttribute(moveEdgeEClass, MOVE_EDGE__NODE_A);
		createEAttribute(moveEdgeEClass, MOVE_EDGE__NODE_B);

		// Create enums
		nodeTypeEEnum = createEEnum(NODE_TYPE);
		labelEEnum = createEEnum(LABEL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		addNodeEClass.getESuperTypes().add(this.getDeltaOperation());
		deleteNodeEClass.getESuperTypes().add(this.getDeltaOperation());
		addEdgeEClass.getESuperTypes().add(this.getDeltaOperation());
		deleteEdgeEClass.getESuperTypes().add(this.getDeltaOperation());
		moveNodeEClass.getESuperTypes().add(this.getDeltaOperation());
		changeLabelEClass.getESuperTypes().add(this.getDeltaOperation());
		moveEdgeEClass.getESuperTypes().add(this.getDeltaOperation());

		// Initialize classes, features, and operations; add parameters
		initEClass(deltaSequenceEClass, DeltaSequence.class, "DeltaSequence", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDeltaSequence_DeltaOperations(), this.getDeltaOperation(), null, "deltaOperations", null, 0,
				-1, DeltaSequence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deltaOperationEClass, DeltaOperation.class, "DeltaOperation", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeltaOperation_Id(), ecorePackage.getEString(), "id", null, 1, 1, DeltaOperation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(addNodeEClass, AddNode.class, "AddNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAddNode_NodeName(), ecorePackage.getEString(), "nodeName", null, 1, 1, AddNode.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAddNode_NodeType(), this.getNodeType(), "nodeType", "SIMPLE", 1, 1, AddNode.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAddNode_ToRegion(), ecorePackage.getEString(), "toRegion", null, 1, 1, AddNode.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deleteNodeEClass, DeleteNode.class, "DeleteNode", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDeleteNode_NodeImplications(), this.getDeleteNode(), null, "nodeImplications", null, 0, -1,
				DeleteNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDeleteNode_EdgeImplications(), this.getDeleteEdge(), null, "edgeImplications", null, 0, -1,
				DeleteNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeleteNode_NodeName(), ecorePackage.getEString(), "nodeName", null, 1, 1, DeleteNode.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeleteNode_FromRegion(), ecorePackage.getEString(), "fromRegion", null, 1, 1,
				DeleteNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(addEdgeEClass, AddEdge.class, "AddEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAddEdge_NodeA(), ecorePackage.getEString(), "nodeA", null, 1, 1, AddEdge.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAddEdge_NodeB(), ecorePackage.getEString(), "nodeB", null, 1, 1, AddEdge.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deleteEdgeEClass, DeleteEdge.class, "DeleteEdge", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDeleteEdge_NodeA(), ecorePackage.getEString(), "nodeA", null, 1, 1, DeleteEdge.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDeleteEdge_NodeB(), ecorePackage.getEString(), "nodeB", null, 1, 1, DeleteEdge.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(moveNodeEClass, MoveNode.class, "MoveNode", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMoveNode_NodeName(), ecorePackage.getEString(), "nodeName", null, 1, 1, MoveNode.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMoveNode_TargetRegion(), ecorePackage.getEString(), "targetRegion", null, 1, 1,
				MoveNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMoveNode_OldRegion(), ecorePackage.getEString(), "oldRegion", null, 1, 1, MoveNode.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMoveNode_EdgeImplications(), this.getMoveEdge(), null, "edgeImplications", null, 0, -1,
				MoveNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(changeLabelEClass, ChangeLabel.class, "ChangeLabel", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getChangeLabel_NodeName(), ecorePackage.getEString(), "nodeName", null, 1, 1, ChangeLabel.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChangeLabel_NewLabel(), this.getLabel(), "newLabel", null, 1, 1, ChangeLabel.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChangeLabel_OldLabel(), this.getLabel(), "oldLabel", null, 1, 1, ChangeLabel.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(moveEdgeEClass, MoveEdge.class, "MoveEdge", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMoveEdge_OldRegion(), ecorePackage.getEString(), "oldRegion", null, 1, 1, MoveEdge.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMoveEdge_NewRegion(), ecorePackage.getEString(), "newRegion", null, 1, 1, MoveEdge.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMoveEdge_NodeA(), ecorePackage.getEString(), "nodeA", null, 1, 1, MoveEdge.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMoveEdge_NodeB(), ecorePackage.getEString(), "nodeB", null, 1, 1, MoveEdge.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(nodeTypeEEnum, NodeType.class, "NodeType");
		addEEnumLiteral(nodeTypeEEnum, NodeType.SIMPLE);
		addEEnumLiteral(nodeTypeEEnum, NodeType.REGION);

		initEEnum(labelEEnum, Label.class, "Label");
		addEEnumLiteral(labelEEnum, Label.GREY);
		addEEnumLiteral(labelEEnum, Label.GREEN);
		addEEnumLiteral(labelEEnum, Label.ORANGE);
		addEEnumLiteral(labelEEnum, Label.BLUE);

		// Create resource
		createResource(eNS_URI);
	}

} //GraphdeltaPackageImpl
