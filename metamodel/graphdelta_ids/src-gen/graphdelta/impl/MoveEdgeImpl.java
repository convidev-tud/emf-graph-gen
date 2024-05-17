/**
 */
package graphdelta.impl;

import graphdelta.GraphdeltaPackage;
import graphdelta.MoveEdge;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Move Edge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.impl.MoveEdgeImpl#getOldRegion <em>Old Region</em>}</li>
 *   <li>{@link graphdelta.impl.MoveEdgeImpl#getNewRegion <em>New Region</em>}</li>
 *   <li>{@link graphdelta.impl.MoveEdgeImpl#getNodeA <em>Node A</em>}</li>
 *   <li>{@link graphdelta.impl.MoveEdgeImpl#getNodeB <em>Node B</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MoveEdgeImpl extends DeltaOperationImpl implements MoveEdge {
	/**
	 * The default value of the '{@link #getOldRegion() <em>Old Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOldRegion()
	 * @generated
	 * @ordered
	 */
	protected static final String OLD_REGION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOldRegion() <em>Old Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOldRegion()
	 * @generated
	 * @ordered
	 */
	protected String oldRegion = OLD_REGION_EDEFAULT;

	/**
	 * The default value of the '{@link #getNewRegion() <em>New Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewRegion()
	 * @generated
	 * @ordered
	 */
	protected static final String NEW_REGION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNewRegion() <em>New Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewRegion()
	 * @generated
	 * @ordered
	 */
	protected String newRegion = NEW_REGION_EDEFAULT;

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
	protected MoveEdgeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdeltaPackage.Literals.MOVE_EDGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOldRegion() {
		return oldRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOldRegion(String newOldRegion) {
		String oldOldRegion = oldRegion;
		oldRegion = newOldRegion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.MOVE_EDGE__OLD_REGION, oldOldRegion,
					oldRegion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNewRegion() {
		return newRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNewRegion(String newNewRegion) {
		String oldNewRegion = newRegion;
		newRegion = newNewRegion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.MOVE_EDGE__NEW_REGION, oldNewRegion,
					newRegion));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.MOVE_EDGE__NODE_A, oldNodeA,
					nodeA));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.MOVE_EDGE__NODE_B, oldNodeB,
					nodeB));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GraphdeltaPackage.MOVE_EDGE__OLD_REGION:
			return getOldRegion();
		case GraphdeltaPackage.MOVE_EDGE__NEW_REGION:
			return getNewRegion();
		case GraphdeltaPackage.MOVE_EDGE__NODE_A:
			return getNodeA();
		case GraphdeltaPackage.MOVE_EDGE__NODE_B:
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
		case GraphdeltaPackage.MOVE_EDGE__OLD_REGION:
			setOldRegion((String) newValue);
			return;
		case GraphdeltaPackage.MOVE_EDGE__NEW_REGION:
			setNewRegion((String) newValue);
			return;
		case GraphdeltaPackage.MOVE_EDGE__NODE_A:
			setNodeA((String) newValue);
			return;
		case GraphdeltaPackage.MOVE_EDGE__NODE_B:
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
		case GraphdeltaPackage.MOVE_EDGE__OLD_REGION:
			setOldRegion(OLD_REGION_EDEFAULT);
			return;
		case GraphdeltaPackage.MOVE_EDGE__NEW_REGION:
			setNewRegion(NEW_REGION_EDEFAULT);
			return;
		case GraphdeltaPackage.MOVE_EDGE__NODE_A:
			setNodeA(NODE_A_EDEFAULT);
			return;
		case GraphdeltaPackage.MOVE_EDGE__NODE_B:
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
		case GraphdeltaPackage.MOVE_EDGE__OLD_REGION:
			return OLD_REGION_EDEFAULT == null ? oldRegion != null : !OLD_REGION_EDEFAULT.equals(oldRegion);
		case GraphdeltaPackage.MOVE_EDGE__NEW_REGION:
			return NEW_REGION_EDEFAULT == null ? newRegion != null : !NEW_REGION_EDEFAULT.equals(newRegion);
		case GraphdeltaPackage.MOVE_EDGE__NODE_A:
			return NODE_A_EDEFAULT == null ? nodeA != null : !NODE_A_EDEFAULT.equals(nodeA);
		case GraphdeltaPackage.MOVE_EDGE__NODE_B:
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
		result.append(" (oldRegion: ");
		result.append(oldRegion);
		result.append(", newRegion: ");
		result.append(newRegion);
		result.append(", nodeA: ");
		result.append(nodeA);
		result.append(", nodeB: ");
		result.append(nodeB);
		result.append(')');
		return result.toString();
	}

} //MoveEdgeImpl
