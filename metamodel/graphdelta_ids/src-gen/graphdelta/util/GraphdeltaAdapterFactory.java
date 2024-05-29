/**
 */
package graphdelta.util;

import graphdelta.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see graphdelta.GraphdeltaPackage
 * @generated
 */
public class GraphdeltaAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static GraphdeltaPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphdeltaAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = GraphdeltaPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphdeltaSwitch<Adapter> modelSwitch = new GraphdeltaSwitch<Adapter>() {
		@Override
		public Adapter caseDeltaSequence(DeltaSequence object) {
			return createDeltaSequenceAdapter();
		}

		@Override
		public Adapter caseDeltaOperation(DeltaOperation object) {
			return createDeltaOperationAdapter();
		}

		@Override
		public Adapter caseAddNode(AddNode object) {
			return createAddNodeAdapter();
		}

		@Override
		public Adapter caseDeleteNode(DeleteNode object) {
			return createDeleteNodeAdapter();
		}

		@Override
		public Adapter caseAddEdge(AddEdge object) {
			return createAddEdgeAdapter();
		}

		@Override
		public Adapter caseDeleteEdge(DeleteEdge object) {
			return createDeleteEdgeAdapter();
		}

		@Override
		public Adapter caseMoveNode(MoveNode object) {
			return createMoveNodeAdapter();
		}

		@Override
		public Adapter caseChangeLabel(ChangeLabel object) {
			return createChangeLabelAdapter();
		}

		@Override
		public Adapter caseMoveEdge(MoveEdge object) {
			return createMoveEdgeAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link graphdelta.DeltaSequence <em>Delta Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see graphdelta.DeltaSequence
	 * @generated
	 */
	public Adapter createDeltaSequenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link graphdelta.DeltaOperation <em>Delta Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see graphdelta.DeltaOperation
	 * @generated
	 */
	public Adapter createDeltaOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link graphdelta.AddNode <em>Add Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see graphdelta.AddNode
	 * @generated
	 */
	public Adapter createAddNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link graphdelta.DeleteNode <em>Delete Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see graphdelta.DeleteNode
	 * @generated
	 */
	public Adapter createDeleteNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link graphdelta.AddEdge <em>Add Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see graphdelta.AddEdge
	 * @generated
	 */
	public Adapter createAddEdgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link graphdelta.DeleteEdge <em>Delete Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see graphdelta.DeleteEdge
	 * @generated
	 */
	public Adapter createDeleteEdgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link graphdelta.MoveNode <em>Move Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see graphdelta.MoveNode
	 * @generated
	 */
	public Adapter createMoveNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link graphdelta.ChangeLabel <em>Change Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see graphdelta.ChangeLabel
	 * @generated
	 */
	public Adapter createChangeLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link graphdelta.MoveEdge <em>Move Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see graphdelta.MoveEdge
	 * @generated
	 */
	public Adapter createMoveEdgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //GraphdeltaAdapterFactory
