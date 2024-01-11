/**
 */
package graphdelta.impl;

import graphdelta.GraphdeltaPackage;
import graphdelta.MoveEdge;
import graphdelta.MoveNode;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Move Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.impl.MoveNodeImpl#getNodeName <em>Node Name</em>}</li>
 *   <li>{@link graphdelta.impl.MoveNodeImpl#getTargetRegion <em>Target Region</em>}</li>
 *   <li>{@link graphdelta.impl.MoveNodeImpl#getOldRegion <em>Old Region</em>}</li>
 *   <li>{@link graphdelta.impl.MoveNodeImpl#getEdgeImplications <em>Edge Implications</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MoveNodeImpl extends DeltaOperationImpl implements MoveNode {
	/**
	 * The default value of the '{@link #getNodeName() <em>Node Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeName()
	 * @generated
	 * @ordered
	 */
	protected static final String NODE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNodeName() <em>Node Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeName()
	 * @generated
	 * @ordered
	 */
	protected String nodeName = NODE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetRegion() <em>Target Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetRegion()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_REGION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetRegion() <em>Target Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetRegion()
	 * @generated
	 * @ordered
	 */
	protected String targetRegion = TARGET_REGION_EDEFAULT;

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
	 * The cached value of the '{@link #getEdgeImplications() <em>Edge Implications</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdgeImplications()
	 * @generated
	 * @ordered
	 */
	protected EList<MoveEdge> edgeImplications;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MoveNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdeltaPackage.Literals.MOVE_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNodeName(String newNodeName) {
		String oldNodeName = nodeName;
		nodeName = newNodeName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.MOVE_NODE__NODE_NAME, oldNodeName,
					nodeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTargetRegion() {
		return targetRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTargetRegion(String newTargetRegion) {
		String oldTargetRegion = targetRegion;
		targetRegion = newTargetRegion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.MOVE_NODE__TARGET_REGION,
					oldTargetRegion, targetRegion));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.MOVE_NODE__OLD_REGION, oldOldRegion,
					oldRegion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<MoveEdge> getEdgeImplications() {
		if (edgeImplications == null) {
			edgeImplications = new EObjectResolvingEList<MoveEdge>(MoveEdge.class, this,
					GraphdeltaPackage.MOVE_NODE__EDGE_IMPLICATIONS);
		}
		return edgeImplications;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GraphdeltaPackage.MOVE_NODE__NODE_NAME:
			return getNodeName();
		case GraphdeltaPackage.MOVE_NODE__TARGET_REGION:
			return getTargetRegion();
		case GraphdeltaPackage.MOVE_NODE__OLD_REGION:
			return getOldRegion();
		case GraphdeltaPackage.MOVE_NODE__EDGE_IMPLICATIONS:
			return getEdgeImplications();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GraphdeltaPackage.MOVE_NODE__NODE_NAME:
			setNodeName((String) newValue);
			return;
		case GraphdeltaPackage.MOVE_NODE__TARGET_REGION:
			setTargetRegion((String) newValue);
			return;
		case GraphdeltaPackage.MOVE_NODE__OLD_REGION:
			setOldRegion((String) newValue);
			return;
		case GraphdeltaPackage.MOVE_NODE__EDGE_IMPLICATIONS:
			getEdgeImplications().clear();
			getEdgeImplications().addAll((Collection<? extends MoveEdge>) newValue);
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
		case GraphdeltaPackage.MOVE_NODE__NODE_NAME:
			setNodeName(NODE_NAME_EDEFAULT);
			return;
		case GraphdeltaPackage.MOVE_NODE__TARGET_REGION:
			setTargetRegion(TARGET_REGION_EDEFAULT);
			return;
		case GraphdeltaPackage.MOVE_NODE__OLD_REGION:
			setOldRegion(OLD_REGION_EDEFAULT);
			return;
		case GraphdeltaPackage.MOVE_NODE__EDGE_IMPLICATIONS:
			getEdgeImplications().clear();
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
		case GraphdeltaPackage.MOVE_NODE__NODE_NAME:
			return NODE_NAME_EDEFAULT == null ? nodeName != null : !NODE_NAME_EDEFAULT.equals(nodeName);
		case GraphdeltaPackage.MOVE_NODE__TARGET_REGION:
			return TARGET_REGION_EDEFAULT == null ? targetRegion != null : !TARGET_REGION_EDEFAULT.equals(targetRegion);
		case GraphdeltaPackage.MOVE_NODE__OLD_REGION:
			return OLD_REGION_EDEFAULT == null ? oldRegion != null : !OLD_REGION_EDEFAULT.equals(oldRegion);
		case GraphdeltaPackage.MOVE_NODE__EDGE_IMPLICATIONS:
			return edgeImplications != null && !edgeImplications.isEmpty();
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
		result.append(" (nodeName: ");
		result.append(nodeName);
		result.append(", targetRegion: ");
		result.append(targetRegion);
		result.append(", oldRegion: ");
		result.append(oldRegion);
		result.append(')');
		return result.toString();
	}

} //MoveNodeImpl
