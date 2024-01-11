/**
 */
package graphdelta.impl;

import graphdelta.DeleteEdge;
import graphdelta.DeleteNode;
import graphdelta.GraphdeltaPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Delete Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.impl.DeleteNodeImpl#getNodeImplications <em>Node Implications</em>}</li>
 *   <li>{@link graphdelta.impl.DeleteNodeImpl#getEdgeImplications <em>Edge Implications</em>}</li>
 *   <li>{@link graphdelta.impl.DeleteNodeImpl#getNodeName <em>Node Name</em>}</li>
 *   <li>{@link graphdelta.impl.DeleteNodeImpl#getFromRegion <em>From Region</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeleteNodeImpl extends DeltaOperationImpl implements DeleteNode {
	/**
	 * The cached value of the '{@link #getNodeImplications() <em>Node Implications</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodeImplications()
	 * @generated
	 * @ordered
	 */
	protected EList<DeleteNode> nodeImplications;

	/**
	 * The cached value of the '{@link #getEdgeImplications() <em>Edge Implications</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEdgeImplications()
	 * @generated
	 * @ordered
	 */
	protected EList<DeleteEdge> edgeImplications;

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
	 * The default value of the '{@link #getFromRegion() <em>From Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromRegion()
	 * @generated
	 * @ordered
	 */
	protected static final String FROM_REGION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFromRegion() <em>From Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromRegion()
	 * @generated
	 * @ordered
	 */
	protected String fromRegion = FROM_REGION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeleteNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdeltaPackage.Literals.DELETE_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DeleteNode> getNodeImplications() {
		if (nodeImplications == null) {
			nodeImplications = new EObjectResolvingEList<DeleteNode>(DeleteNode.class, this,
					GraphdeltaPackage.DELETE_NODE__NODE_IMPLICATIONS);
		}
		return nodeImplications;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DeleteEdge> getEdgeImplications() {
		if (edgeImplications == null) {
			edgeImplications = new EObjectResolvingEList<DeleteEdge>(DeleteEdge.class, this,
					GraphdeltaPackage.DELETE_NODE__EDGE_IMPLICATIONS);
		}
		return edgeImplications;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.DELETE_NODE__NODE_NAME, oldNodeName,
					nodeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFromRegion() {
		return fromRegion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFromRegion(String newFromRegion) {
		String oldFromRegion = fromRegion;
		fromRegion = newFromRegion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.DELETE_NODE__FROM_REGION,
					oldFromRegion, fromRegion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GraphdeltaPackage.DELETE_NODE__NODE_IMPLICATIONS:
			return getNodeImplications();
		case GraphdeltaPackage.DELETE_NODE__EDGE_IMPLICATIONS:
			return getEdgeImplications();
		case GraphdeltaPackage.DELETE_NODE__NODE_NAME:
			return getNodeName();
		case GraphdeltaPackage.DELETE_NODE__FROM_REGION:
			return getFromRegion();
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
		case GraphdeltaPackage.DELETE_NODE__NODE_IMPLICATIONS:
			getNodeImplications().clear();
			getNodeImplications().addAll((Collection<? extends DeleteNode>) newValue);
			return;
		case GraphdeltaPackage.DELETE_NODE__EDGE_IMPLICATIONS:
			getEdgeImplications().clear();
			getEdgeImplications().addAll((Collection<? extends DeleteEdge>) newValue);
			return;
		case GraphdeltaPackage.DELETE_NODE__NODE_NAME:
			setNodeName((String) newValue);
			return;
		case GraphdeltaPackage.DELETE_NODE__FROM_REGION:
			setFromRegion((String) newValue);
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
		case GraphdeltaPackage.DELETE_NODE__NODE_IMPLICATIONS:
			getNodeImplications().clear();
			return;
		case GraphdeltaPackage.DELETE_NODE__EDGE_IMPLICATIONS:
			getEdgeImplications().clear();
			return;
		case GraphdeltaPackage.DELETE_NODE__NODE_NAME:
			setNodeName(NODE_NAME_EDEFAULT);
			return;
		case GraphdeltaPackage.DELETE_NODE__FROM_REGION:
			setFromRegion(FROM_REGION_EDEFAULT);
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
		case GraphdeltaPackage.DELETE_NODE__NODE_IMPLICATIONS:
			return nodeImplications != null && !nodeImplications.isEmpty();
		case GraphdeltaPackage.DELETE_NODE__EDGE_IMPLICATIONS:
			return edgeImplications != null && !edgeImplications.isEmpty();
		case GraphdeltaPackage.DELETE_NODE__NODE_NAME:
			return NODE_NAME_EDEFAULT == null ? nodeName != null : !NODE_NAME_EDEFAULT.equals(nodeName);
		case GraphdeltaPackage.DELETE_NODE__FROM_REGION:
			return FROM_REGION_EDEFAULT == null ? fromRegion != null : !FROM_REGION_EDEFAULT.equals(fromRegion);
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
		result.append(", fromRegion: ");
		result.append(fromRegion);
		result.append(')');
		return result.toString();
	}

} //DeleteNodeImpl
