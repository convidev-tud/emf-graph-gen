/**
 */
package graphdelta;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Delta Sequence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.DeltaSequence#getDeltaOperations <em>Delta Operations</em>}</li>
 * </ul>
 *
 * @see graphdelta.GraphdeltaPackage#getDeltaSequence()
 * @model
 * @generated
 */
public interface DeltaSequence extends EObject {
	/**
	 * Returns the value of the '<em><b>Delta Operations</b></em>' containment reference list.
	 * The list contents are of type {@link graphdelta.DeltaOperation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delta Operations</em>' containment reference list.
	 * @see graphdelta.GraphdeltaPackage#getDeltaSequence_DeltaOperations()
	 * @model containment="true"
	 * @generated
	 */
	EList<DeltaOperation> getDeltaOperations();

} // DeltaSequence
