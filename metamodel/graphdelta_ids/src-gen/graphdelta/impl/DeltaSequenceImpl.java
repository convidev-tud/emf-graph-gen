/**
 */
package graphdelta.impl;

import graphdelta.DeltaOperation;
import graphdelta.DeltaSequence;
import graphdelta.GraphdeltaPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Delta Sequence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.impl.DeltaSequenceImpl#getDeltaOperations <em>Delta Operations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeltaSequenceImpl extends MinimalEObjectImpl.Container implements DeltaSequence {
	/**
	 * The cached value of the '{@link #getDeltaOperations() <em>Delta Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeltaOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<DeltaOperation> deltaOperations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeltaSequenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GraphdeltaPackage.Literals.DELTA_SEQUENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<DeltaOperation> getDeltaOperations() {
		if (deltaOperations == null) {
			deltaOperations = new EObjectContainmentEList<DeltaOperation>(DeltaOperation.class, this,
					GraphdeltaPackage.DELTA_SEQUENCE__DELTA_OPERATIONS);
		}
		return deltaOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GraphdeltaPackage.DELTA_SEQUENCE__DELTA_OPERATIONS:
			return ((InternalEList<?>) getDeltaOperations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GraphdeltaPackage.DELTA_SEQUENCE__DELTA_OPERATIONS:
			return getDeltaOperations();
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
		case GraphdeltaPackage.DELTA_SEQUENCE__DELTA_OPERATIONS:
			getDeltaOperations().clear();
			getDeltaOperations().addAll((Collection<? extends DeltaOperation>) newValue);
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
		case GraphdeltaPackage.DELTA_SEQUENCE__DELTA_OPERATIONS:
			getDeltaOperations().clear();
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
		case GraphdeltaPackage.DELTA_SEQUENCE__DELTA_OPERATIONS:
			return deltaOperations != null && !deltaOperations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DeltaSequenceImpl
