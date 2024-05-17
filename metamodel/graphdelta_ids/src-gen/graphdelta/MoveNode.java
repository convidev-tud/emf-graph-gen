/**
 */
package graphdelta;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Move Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link graphdelta.MoveNode#getNodeName <em>Node Name</em>}</li>
 *   <li>{@link graphdelta.MoveNode#getTargetRegion <em>Target Region</em>}</li>
 *   <li>{@link graphdelta.MoveNode#getOldRegion <em>Old Region</em>}</li>
 *   <li>{@link graphdelta.MoveNode#getEdgeImplications <em>Edge Implications</em>}</li>
 * </ul>
 *
 * @see graphdelta.GraphdeltaPackage#getMoveNode()
 * @model
 * @generated
 */
public interface MoveNode extends DeltaOperation {
	/**
	 * Returns the value of the '<em><b>Node Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Name</em>' attribute.
	 * @see #setNodeName(String)
	 * @see graphdelta.GraphdeltaPackage#getMoveNode_NodeName()
	 * @model required="true"
	 * @generated
	 */
	String getNodeName();

	/**
	 * Sets the value of the '{@link graphdelta.MoveNode#getNodeName <em>Node Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Name</em>' attribute.
	 * @see #getNodeName()
	 * @generated
	 */
	void setNodeName(String value);

	/**
	 * Returns the value of the '<em><b>Target Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Region</em>' attribute.
	 * @see #setTargetRegion(String)
	 * @see graphdelta.GraphdeltaPackage#getMoveNode_TargetRegion()
	 * @model required="true"
	 * @generated
	 */
	String getTargetRegion();

	/**
	 * Sets the value of the '{@link graphdelta.MoveNode#getTargetRegion <em>Target Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Region</em>' attribute.
	 * @see #getTargetRegion()
	 * @generated
	 */
	void setTargetRegion(String value);

	/**
	 * Returns the value of the '<em><b>Old Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Old Region</em>' attribute.
	 * @see #setOldRegion(String)
	 * @see graphdelta.GraphdeltaPackage#getMoveNode_OldRegion()
	 * @model required="true"
	 * @generated
	 */
	String getOldRegion();

	/**
	 * Sets the value of the '{@link graphdelta.MoveNode#getOldRegion <em>Old Region</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Old Region</em>' attribute.
	 * @see #getOldRegion()
	 * @generated
	 */
	void setOldRegion(String value);

	/**
	 * Returns the value of the '<em><b>Edge Implications</b></em>' containment reference list.
	 * The list contents are of type {@link graphdelta.MoveEdge}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge Implications</em>' containment reference list.
	 * @see graphdelta.GraphdeltaPackage#getMoveNode_EdgeImplications()
	 * @model containment="true"
	 * @generated
	 */
	EList<MoveEdge> getEdgeImplications();

} // MoveNode
