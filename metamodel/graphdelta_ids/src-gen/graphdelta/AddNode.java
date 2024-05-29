/**
 */
package graphdelta;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Add Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.AddNode#getNodeName <em>Node Name</em>}</li>
 *   <li>{@link graphdelta.AddNode#getNodeType <em>Node Type</em>}</li>
 *   <li>{@link graphdelta.AddNode#getToRegion <em>To Region</em>}</li>
 * </ul>
 *
 * @see graphdelta.GraphdeltaPackage#getAddNode()
 * @model
 * @generated
 */
public interface AddNode extends DeltaOperation {
	/**
	 * Returns the value of the '<em><b>Node Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Name</em>' attribute.
	 * @see #setNodeName(String)
	 * @see graphdelta.GraphdeltaPackage#getAddNode_NodeName()
	 * @model required="true"
	 * @generated
	 */
	String getNodeName();

	/**
	 * Sets the value of the '{@link graphdelta.AddNode#getNodeName <em>Node Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Name</em>' attribute.
	 * @see #getNodeName()
	 * @generated
	 */
	void setNodeName(String value);

	/**
	 * Returns the value of the '<em><b>Node Type</b></em>' attribute.
	 * The default value is <code>"SIMPLE"</code>.
	 * The literals are from the enumeration {@link graphdelta.NodeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Type</em>' attribute.
	 * @see graphdelta.NodeType
	 * @see #setNodeType(NodeType)
	 * @see graphdelta.GraphdeltaPackage#getAddNode_NodeType()
	 * @model default="SIMPLE" required="true"
	 * @generated
	 */
	NodeType getNodeType();

	/**
	 * Sets the value of the '{@link graphdelta.AddNode#getNodeType <em>Node Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Type</em>' attribute.
	 * @see graphdelta.NodeType
	 * @see #getNodeType()
	 * @generated
	 */
	void setNodeType(NodeType value);

	/**
	 * Returns the value of the '<em><b>To Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Region</em>' attribute.
	 * @see #setToRegion(String)
	 * @see graphdelta.GraphdeltaPackage#getAddNode_ToRegion()
	 * @model required="true"
	 * @generated
	 */
	String getToRegion();

	/**
	 * Sets the value of the '{@link graphdelta.AddNode#getToRegion <em>To Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Region</em>' attribute.
	 * @see #getToRegion()
	 * @generated
	 */
	void setToRegion(String value);

} // AddNode
