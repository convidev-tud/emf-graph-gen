/**
 */
package graphdelta.impl;

import graphdelta.AddEdge;
import graphdelta.GraphdeltaPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Add Edge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.impl.AddEdgeImpl#getNodeA <em>Node A</em>}</li>
 *   <li>{@link graphdelta.impl.AddEdgeImpl#getNodeB <em>Node B</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AddEdgeImpl extends DeltaOperationImpl implements AddEdge {
	/**
	 * The default value of the '{@link #getNodeA() <em>Node A</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeA()
	 * @generated
	 * @ordered
	 */
	protected static final String NODE_A_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNodeA() <em>Node A</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeA()
	 * @generated
	 * @ordered
	 */
	protected String nodeA = NODE_A_EDEFAULT;

	/**
	 * The default value of the '{@link #getNodeB() <em>Node B</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeB()
	 * @generated
	 * @ordered
	 */
	protected static final String NODE_B_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNodeB() <em>Node B</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeB()
	 * @generated
	 * @ordered
	 */
	protected String nodeB = NODE_B_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AddEdgeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdeltaPackage.Literals.ADD_EDGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNodeA() {
		return nodeA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNodeA(String newNodeA) {
		String oldNodeA = nodeA;
		nodeA = newNodeA;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.ADD_EDGE__NODE_A, oldNodeA, nodeA));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNodeB() {
		return nodeB;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNodeB(String newNodeB) {
		String oldNodeB = nodeB;
		nodeB = newNodeB;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.ADD_EDGE__NODE_B, oldNodeB, nodeB));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GraphdeltaPackage.ADD_EDGE__NODE_A:
			return getNodeA();
		case GraphdeltaPackage.ADD_EDGE__NODE_B:
			return getNodeB();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GraphdeltaPackage.ADD_EDGE__NODE_A:
			setNodeA((String) newValue);
			return;
		case GraphdeltaPackage.ADD_EDGE__NODE_B:
			setNodeB((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case GraphdeltaPackage.ADD_EDGE__NODE_A:
			setNodeA(NODE_A_EDEFAULT);
			return;
		case GraphdeltaPackage.ADD_EDGE__NODE_B:
			setNodeB(NODE_B_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case GraphdeltaPackage.ADD_EDGE__NODE_A:
			return NODE_A_EDEFAULT == null ? nodeA != null : !NODE_A_EDEFAULT.equals(nodeA);
		case GraphdeltaPackage.ADD_EDGE__NODE_B:
			return NODE_B_EDEFAULT == null ? nodeB != null : !NODE_B_EDEFAULT.equals(nodeB);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (nodeA: ");
		result.append(nodeA);
		result.append(", nodeB: ");
		result.append(nodeB);
		result.append(')');
		return result.toString();
	}

} //AddEdgeImpl
