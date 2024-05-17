/**
 */
package graphdelta.util;

import graphdelta.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see graphdelta.GraphdeltaPackage
 * @generated
 */
public class GraphdeltaSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GraphdeltaPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphdeltaSwitch() {
		if (modelPackage == null) {
			modelPackage = GraphdeltaPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case GraphdeltaPackage.DELTA_SEQUENCE: {
			DeltaSequence deltaSequence = (DeltaSequence) theEObject;
			T result = caseDeltaSequence(deltaSequence);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GraphdeltaPackage.DELTA_OPERATION: {
			DeltaOperation deltaOperation = (DeltaOperation) theEObject;
			T result = caseDeltaOperation(deltaOperation);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GraphdeltaPackage.ADD_NODE: {
			AddNode addNode = (AddNode) theEObject;
			T result = caseAddNode(addNode);
			if (result == null)
				result = caseDeltaOperation(addNode);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GraphdeltaPackage.DELETE_NODE: {
			DeleteNode deleteNode = (DeleteNode) theEObject;
			T result = caseDeleteNode(deleteNode);
			if (result == null)
				result = caseDeltaOperation(deleteNode);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GraphdeltaPackage.ADD_EDGE: {
			AddEdge addEdge = (AddEdge) theEObject;
			T result = caseAddEdge(addEdge);
			if (result == null)
				result = caseDeltaOperation(addEdge);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GraphdeltaPackage.DELETE_EDGE: {
			DeleteEdge deleteEdge = (DeleteEdge) theEObject;
			T result = caseDeleteEdge(deleteEdge);
			if (result == null)
				result = caseDeltaOperation(deleteEdge);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GraphdeltaPackage.MOVE_NODE: {
			MoveNode moveNode = (MoveNode) theEObject;
			T result = caseMoveNode(moveNode);
			if (result == null)
				result = caseDeltaOperation(moveNode);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GraphdeltaPackage.CHANGE_LABEL: {
			ChangeLabel changeLabel = (ChangeLabel) theEObject;
			T result = caseChangeLabel(changeLabel);
			if (result == null)
				result = caseDeltaOperation(changeLabel);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case GraphdeltaPackage.MOVE_EDGE: {
			MoveEdge moveEdge = (MoveEdge) theEObject;
			T result = caseMoveEdge(moveEdge);
			if (result == null)
				result = caseDeltaOperation(moveEdge);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Delta Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Delta Sequence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeltaSequence(DeltaSequence object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Delta Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Delta Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeltaOperation(DeltaOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddNode(AddNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Delete Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Delete Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeleteNode(DeleteNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddEdge(AddEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Delete Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Delete Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeleteEdge(DeleteEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Move Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Move Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMoveNode(MoveNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Change Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Change Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseChangeLabel(ChangeLabel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Move Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Move Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMoveEdge(MoveEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //GraphdeltaSwitch
