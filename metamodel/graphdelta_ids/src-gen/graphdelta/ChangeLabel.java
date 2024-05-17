/**
 */
package graphdelta;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Change Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.ChangeLabel#getNodeName <em>Node Name</em>}</li>
 *   <li>{@link graphdelta.ChangeLabel#getNewLabel <em>New Label</em>}</li>
 *   <li>{@link graphdelta.ChangeLabel#getOldLabel <em>Old Label</em>}</li>
 * </ul>
 *
 * @see graphdelta.GraphdeltaPackage#getChangeLabel()
 * @model
 * @generated
 */
public interface ChangeLabel extends DeltaOperation {
	/**
	 * Returns the value of the '<em><b>Node Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Name</em>' attribute.
	 * @see #setNodeName(String)
	 * @see graphdelta.GraphdeltaPackage#getChangeLabel_NodeName()
	 * @model required="true"
	 * @generated
	 */
	String getNodeName();

	/**
	 * Sets the value of the '{@link graphdelta.ChangeLabel#getNodeName <em>Node Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Name</em>' attribute.
	 * @see #getNodeName()
	 * @generated
	 */
	void setNodeName(String value);

	/**
	 * Returns the value of the '<em><b>New Label</b></em>' attribute.
	 * The literals are from the enumeration {@link graphdelta.Label}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Label</em>' attribute.
	 * @see graphdelta.Label
	 * @see #setNewLabel(Label)
	 * @see graphdelta.GraphdeltaPackage#getChangeLabel_NewLabel()
	 * @model required="true"
	 * @generated
	 */
	Label getNewLabel();

	/**
	 * Sets the value of the '{@link graphdelta.ChangeLabel#getNewLabel <em>New Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Label</em>' attribute.
	 * @see graphdelta.Label
	 * @see #getNewLabel()
	 * @generated
	 */
	void setNewLabel(Label value);

	/**
	 * Returns the value of the '<em><b>Old Label</b></em>' attribute.
	 * The literals are from the enumeration {@link graphdelta.Label}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Old Label</em>' attribute.
	 * @see graphdelta.Label
	 * @see #setOldLabel(Label)
	 * @see graphdelta.GraphdeltaPackage#getChangeLabel_OldLabel()
	 * @model required="true"
	 * @generated
	 */
	Label getOldLabel();

	/**
	 * Sets the value of the '{@link graphdelta.ChangeLabel#getOldLabel <em>Old Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Old Label</em>' attribute.
	 * @see graphdelta.Label
	 * @see #getOldLabel()
	 * @generated
	 */
	void setOldLabel(Label value);

} // ChangeLabel
