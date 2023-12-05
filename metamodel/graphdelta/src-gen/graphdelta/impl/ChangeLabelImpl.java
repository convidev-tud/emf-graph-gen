/**
 */
package graphdelta.impl;

import graphdelta.ChangeLabel;
import graphdelta.GraphdeltaPackage;
import graphdelta.Label;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Change Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.impl.ChangeLabelImpl#getNodeName <em>Node Name</em>}</li>
 *   <li>{@link graphdelta.impl.ChangeLabelImpl#getNewLabel <em>New Label</em>}</li>
 *   <li>{@link graphdelta.impl.ChangeLabelImpl#getOldLabel <em>Old Label</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ChangeLabelImpl extends DeltaOperationImpl implements ChangeLabel {
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
	 * The default value of the '{@link #getNewLabel() <em>New Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewLabel()
	 * @generated
	 * @ordered
	 */
	protected static final Label NEW_LABEL_EDEFAULT = Label.GREY;

	/**
	 * The cached value of the '{@link #getNewLabel() <em>New Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewLabel()
	 * @generated
	 * @ordered
	 */
	protected Label newLabel = NEW_LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getOldLabel() <em>Old Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOldLabel()
	 * @generated
	 * @ordered
	 */
	protected static final Label OLD_LABEL_EDEFAULT = Label.GREY;

	/**
	 * The cached value of the '{@link #getOldLabel() <em>Old Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOldLabel()
	 * @generated
	 * @ordered
	 */
	protected Label oldLabel = OLD_LABEL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChangeLabelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdeltaPackage.Literals.CHANGE_LABEL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.CHANGE_LABEL__NODE_NAME,
					oldNodeName, nodeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Label getNewLabel() {
		return newLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNewLabel(Label newNewLabel) {
		Label oldNewLabel = newLabel;
		newLabel = newNewLabel == null ? NEW_LABEL_EDEFAULT : newNewLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.CHANGE_LABEL__NEW_LABEL,
					oldNewLabel, newLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Label getOldLabel() {
		return oldLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOldLabel(Label newOldLabel) {
		Label oldOldLabel = oldLabel;
		oldLabel = newOldLabel == null ? OLD_LABEL_EDEFAULT : newOldLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GraphdeltaPackage.CHANGE_LABEL__OLD_LABEL,
					oldOldLabel, oldLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GraphdeltaPackage.CHANGE_LABEL__NODE_NAME:
			return getNodeName();
		case GraphdeltaPackage.CHANGE_LABEL__NEW_LABEL:
			return getNewLabel();
		case GraphdeltaPackage.CHANGE_LABEL__OLD_LABEL:
			return getOldLabel();
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
		case GraphdeltaPackage.CHANGE_LABEL__NODE_NAME:
			setNodeName((String) newValue);
			return;
		case GraphdeltaPackage.CHANGE_LABEL__NEW_LABEL:
			setNewLabel((Label) newValue);
			return;
		case GraphdeltaPackage.CHANGE_LABEL__OLD_LABEL:
			setOldLabel((Label) newValue);
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
		case GraphdeltaPackage.CHANGE_LABEL__NODE_NAME:
			setNodeName(NODE_NAME_EDEFAULT);
			return;
		case GraphdeltaPackage.CHANGE_LABEL__NEW_LABEL:
			setNewLabel(NEW_LABEL_EDEFAULT);
			return;
		case GraphdeltaPackage.CHANGE_LABEL__OLD_LABEL:
			setOldLabel(OLD_LABEL_EDEFAULT);
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
		case GraphdeltaPackage.CHANGE_LABEL__NODE_NAME:
			return NODE_NAME_EDEFAULT == null ? nodeName != null : !NODE_NAME_EDEFAULT.equals(nodeName);
		case GraphdeltaPackage.CHANGE_LABEL__NEW_LABEL:
			return newLabel != NEW_LABEL_EDEFAULT;
		case GraphdeltaPackage.CHANGE_LABEL__OLD_LABEL:
			return oldLabel != OLD_LABEL_EDEFAULT;
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
		result.append(", newLabel: ");
		result.append(newLabel);
		result.append(", oldLabel: ");
		result.append(oldLabel);
		result.append(')');
		return result.toString();
	}

} //ChangeLabelImpl
