/**
 */
package graphdelta.impl;

import graphdelta.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GraphdeltaFactoryImpl extends EFactoryImpl implements GraphdeltaFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GraphdeltaFactory init() {
		try {
			GraphdeltaFactory theGraphdeltaFactory = (GraphdeltaFactory) EPackage.Registry.INSTANCE
					.getEFactory(GraphdeltaPackage.eNS_URI);
			if (theGraphdeltaFactory != null) {
				return theGraphdeltaFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GraphdeltaFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphdeltaFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case GraphdeltaPackage.DELTA_SEQUENCE:
			return createDeltaSequence();
		case GraphdeltaPackage.ADD_NODE:
			return createAddNode();
		case GraphdeltaPackage.DELETE_NODE:
			return createDeleteNode();
		case GraphdeltaPackage.ADD_EDGE:
			return createAddEdge();
		case GraphdeltaPackage.DELETE_EDGE:
			return createDeleteEdge();
		case GraphdeltaPackage.MOVE_NODE:
			return createMoveNode();
		case GraphdeltaPackage.CHANGE_LABEL:
			return createChangeLabel();
		case GraphdeltaPackage.MOVE_EDGE:
			return createMoveEdge();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case GraphdeltaPackage.NODE_TYPE:
			return createNodeTypeFromString(eDataType, initialValue);
		case GraphdeltaPackage.LABEL:
			return createLabelFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case GraphdeltaPackage.NODE_TYPE:
			return convertNodeTypeToString(eDataType, instanceValue);
		case GraphdeltaPackage.LABEL:
			return convertLabelToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeltaSequence createDeltaSequence() {
		DeltaSequenceImpl deltaSequence = new DeltaSequenceImpl();
		return deltaSequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AddNode createAddNode() {
		AddNodeImpl addNode = new AddNodeImpl();
		return addNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeleteNode createDeleteNode() {
		DeleteNodeImpl deleteNode = new DeleteNodeImpl();
		return deleteNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AddEdge createAddEdge() {
		AddEdgeImpl addEdge = new AddEdgeImpl();
		return addEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DeleteEdge createDeleteEdge() {
		DeleteEdgeImpl deleteEdge = new DeleteEdgeImpl();
		return deleteEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MoveNode createMoveNode() {
		MoveNodeImpl moveNode = new MoveNodeImpl();
		return moveNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ChangeLabel createChangeLabel() {
		ChangeLabelImpl changeLabel = new ChangeLabelImpl();
		return changeLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MoveEdge createMoveEdge() {
		MoveEdgeImpl moveEdge = new MoveEdgeImpl();
		return moveEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeType createNodeTypeFromString(EDataType eDataType, String initialValue) {
		NodeType result = NodeType.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNodeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Label createLabelFromString(EDataType eDataType, String initialValue) {
		Label result = Label.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLabelToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GraphdeltaPackage getGraphdeltaPackage() {
		return (GraphdeltaPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static GraphdeltaPackage getPackage() {
		return GraphdeltaPackage.eINSTANCE;
	}

} //GraphdeltaFactoryImpl
