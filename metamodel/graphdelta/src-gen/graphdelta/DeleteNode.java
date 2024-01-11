/**
 */
package graphdelta;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Delete Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.DeleteNode#getNodeImplications <em>Node Implications</em>}</li>
 *   <li>{@link graphdelta.DeleteNode#getEdgeImplications <em>Edge Implications</em>}</li>
 *   <li>{@link graphdelta.DeleteNode#getNodeName <em>Node Name</em>}</li>
 *   <li>{@link graphdelta.DeleteNode#getFromRegion <em>From Region</em>}</li>
 * </ul>
 *
 * @see graphdelta.GraphdeltaPackage#getDeleteNode()
 * @model
 * @generated
 */
public interface DeleteNode extends DeltaOperation {
	/**
	 * Returns the value of the '<em><b>Node Implications</b></em>' reference list.
	 * The list contents are of type {@link graphdelta.DeleteNode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Implications</em>' reference list.
	 * @see graphdelta.GraphdeltaPackage#getDeleteNode_NodeImplications()
	 * @model
	 * @generated
	 */
	EList<DeleteNode> getNodeImplications();

	/**
	 * Returns the value of the '<em><b>Edge Implications</b></em>' reference list.
	 * The list contents are of type {@link graphdelta.DeleteEdge}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge Implications</em>' reference list.
	 * @see graphdelta.GraphdeltaPackage#getDeleteNode_EdgeImplications()
	 * @model
	 * @generated
	 */
	EList<DeleteEdge> getEdgeImplications();

	/**
	 * Returns the value of the '<em><b>Node Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Name</em>' attribute.
	 * @see #setNodeName(String)
	 * @see graphdelta.GraphdeltaPackage#getDeleteNode_NodeName()
	 * @model required="true"
	 * @generated
	 */
	String getNodeName();

	/**
	 * Sets the value of the '{@link graphdelta.DeleteNode#getNodeName <em>Node Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Name</em>' attribute.
	 * @see #getNodeName()
	 * @generated
	 */
	void setNodeName(String value);

	/**
	 * Returns the value of the '<em><b>From Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From Region</em>' attribute.
	 * @see #setFromRegion(String)
	 * @see graphdelta.GraphdeltaPackage#getDeleteNode_FromRegion()
	 * @model required="true"
	 * @generated
	 */
	String getFromRegion();

	/**
	 * Sets the value of the '{@link graphdelta.DeleteNode#getFromRegion <em>From Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Region</em>' attribute.
	 * @see #getFromRegion()
	 * @generated
	 */
	void setFromRegion(String value);

} // DeleteNode
