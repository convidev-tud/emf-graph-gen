/**
 */
package graphdelta;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Move Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.MoveEdge#getOldRegion <em>Old Region</em>}</li>
 *   <li>{@link graphdelta.MoveEdge#getNewRegion <em>New Region</em>}</li>
 *   <li>{@link graphdelta.MoveEdge#getNodeA <em>Node A</em>}</li>
 *   <li>{@link graphdelta.MoveEdge#getNodeB <em>Node B</em>}</li>
 * </ul>
 *
 * @see graphdelta.GraphdeltaPackage#getMoveEdge()
 * @model
 * @generated
 */
public interface MoveEdge extends DeltaOperation {
	/**
	 * Returns the value of the '<em><b>Old Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Old Region</em>' attribute.
	 * @see #setOldRegion(String)
	 * @see graphdelta.GraphdeltaPackage#getMoveEdge_OldRegion()
	 * @model required="true"
	 * @generated
	 */
	String getOldRegion();

	/**
	 * Sets the value of the '{@link graphdelta.MoveEdge#getOldRegion <em>Old Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Old Region</em>' attribute.
	 * @see #getOldRegion()
	 * @generated
	 */
	void setOldRegion(String value);

	/**
	 * Returns the value of the '<em><b>New Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Region</em>' attribute.
	 * @see #setNewRegion(String)
	 * @see graphdelta.GraphdeltaPackage#getMoveEdge_NewRegion()
	 * @model required="true"
	 * @generated
	 */
	String getNewRegion();

	/**
	 * Sets the value of the '{@link graphdelta.MoveEdge#getNewRegion <em>New Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Region</em>' attribute.
	 * @see #getNewRegion()
	 * @generated
	 */
	void setNewRegion(String value);

	/**
	 * Returns the value of the '<em><b>Node A</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node A</em>' attribute.
	 * @see #setNodeA(String)
	 * @see graphdelta.GraphdeltaPackage#getMoveEdge_NodeA()
	 * @model required="true"
	 * @generated
	 */
	String getNodeA();

	/**
	 * Sets the value of the '{@link graphdelta.MoveEdge#getNodeA <em>Node A</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node A</em>' attribute.
	 * @see #getNodeA()
	 * @generated
	 */
	void setNodeA(String value);

	/**
	 * Returns the value of the '<em><b>Node B</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node B</em>' attribute.
	 * @see #setNodeB(String)
	 * @see graphdelta.GraphdeltaPackage#getMoveEdge_NodeB()
	 * @model required="true"
	 * @generated
	 */
	String getNodeB();

	/**
	 * Sets the value of the '{@link graphdelta.MoveEdge#getNodeB <em>Node B</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node B</em>' attribute.
	 * @see #getNodeB()
	 * @generated
	 */
	void setNodeB(String value);

} // MoveEdge
