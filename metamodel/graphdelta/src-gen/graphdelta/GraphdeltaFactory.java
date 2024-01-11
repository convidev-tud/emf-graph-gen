/**
 */
package graphdelta;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see graphdelta.GraphdeltaPackage
 * @generated
 */
public interface GraphdeltaFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	GraphdeltaFactory eINSTANCE = graphdelta.impl.GraphdeltaFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Delta Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Delta Sequence</em>'.
	 * @generated
	 */
	DeltaSequence createDeltaSequence();

	/**
	 * Returns a new object of class '<em>Add Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Node</em>'.
	 * @generated
	 */
	AddNode createAddNode();

	/**
	 * Returns a new object of class '<em>Delete Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Delete Node</em>'.
	 * @generated
	 */
	DeleteNode createDeleteNode();

	/**
	 * Returns a new object of class '<em>Add Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Edge</em>'.
	 * @generated
	 */
	AddEdge createAddEdge();

	/**
	 * Returns a new object of class '<em>Delete Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Delete Edge</em>'.
	 * @generated
	 */
	DeleteEdge createDeleteEdge();

	/**
	 * Returns a new object of class '<em>Move Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Move Node</em>'.
	 * @generated
	 */
	MoveNode createMoveNode();

	/**
	 * Returns a new object of class '<em>Change Label</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Change Label</em>'.
	 * @generated
	 */
	ChangeLabel createChangeLabel();

	/**
	 * Returns a new object of class '<em>Move Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Move Edge</em>'.
	 * @generated
	 */
	MoveEdge createMoveEdge();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	GraphdeltaPackage getGraphdeltaPackage();

} //GraphdeltaFactory
