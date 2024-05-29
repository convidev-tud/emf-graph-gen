/**
 */
package labelgraph;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link labelgraph.SimpleNode#getLabel <em>Label</em>}</li>
 * </ul>
 *
 * @see labelgraph.LabelgraphPackage#getSimpleNode()
 * @model
 * @generated
 */
public interface SimpleNode extends Node {
	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * The default value is <code>"GREY"</code>.
	 * The literals are from the enumeration {@link labelgraph.Label}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see labelgraph.Label
	 * @see #setLabel(Label)
	 * @see labelgraph.LabelgraphPackage#getSimpleNode_Label()
	 * @model default="GREY" required="true"
	 * @generated
	 */
	Label getLabel();

	/**
	 * Sets the value of the '{@link labelgraph.SimpleNode#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see labelgraph.Label
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(Label value);

} // SimpleNode
