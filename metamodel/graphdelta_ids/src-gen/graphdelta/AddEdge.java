/**
 */
package graphdelta;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Add Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.AddEdge#getNodeA <em>Node A</em>}</li>
 *   <li>{@link graphdelta.AddEdge#getNodeB <em>Node B</em>}</li>
 * </ul>
 *
 * @see graphdelta.GraphdeltaPackage#getAddEdge()
 * @model
 * @generated
 */
public interface AddEdge extends DeltaOperation {
	/**
	 * Returns the value of the '<em><b>Node A</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node A</em>' attribute.
	 * @see #setNodeA(String)
	 * @see graphdelta.GraphdeltaPackage#getAddEdge_NodeA()
	 * @model required="true"
	 * @generated
	 */
	String getNodeA();

	/**
	 * Sets the value of the '{@link graphdelta.AddEdge#getNodeA <em>Node A</em>}' attribute.
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
	 * @see graphdelta.GraphdeltaPackage#getAddEdge_NodeB()
	 * @model required="true"
	 * @generated
	 */
	String getNodeB();

	/**
	 * Sets the value of the '{@link graphdelta.AddEdge#getNodeB <em>Node B</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node B</em>' attribute.
	 * @see #getNodeB()
	 * @generated
	 */
	void setNodeB(String value);

} // AddEdge
