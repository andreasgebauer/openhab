/**
 */
package org.openhab.model.persistence.persistence.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.openhab.model.persistence.persistence.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.openhab.model.persistence.persistence.PersistencePackage
 * @generated
 */
public class PersistenceAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static PersistencePackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersistenceAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = PersistencePackage.eINSTANCE;
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
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PersistenceSwitch<Adapter> modelSwitch =
    new PersistenceSwitch<Adapter>()
    {
      @Override
      public Adapter casePersistenceModel(PersistenceModel object)
      {
        return createPersistenceModelAdapter();
      }
      @Override
      public Adapter caseStrategy(Strategy object)
      {
        return createStrategyAdapter();
      }
      @Override
      public Adapter caseFilter(Filter object)
      {
        return createFilterAdapter();
      }
      @Override
      public Adapter caseFilterDetails(FilterDetails object)
      {
        return createFilterDetailsAdapter();
      }
      @Override
      public Adapter caseThresholdFilter(ThresholdFilter object)
      {
        return createThresholdFilterAdapter();
      }
      @Override
      public Adapter caseTimeFilter(TimeFilter object)
      {
        return createTimeFilterAdapter();
      }
      @Override
      public Adapter casePersistenceConfiguration(PersistenceConfiguration object)
      {
        return createPersistenceConfigurationAdapter();
      }
      @Override
      public Adapter caseAllConfig(AllConfig object)
      {
        return createAllConfigAdapter();
      }
      @Override
      public Adapter caseItemConfig(ItemConfig object)
      {
        return createItemConfigAdapter();
      }
      @Override
      public Adapter caseGroupConfig(GroupConfig object)
      {
        return createGroupConfigAdapter();
      }
      @Override
      public Adapter caseCronStrategy(CronStrategy object)
      {
        return createCronStrategyAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
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
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.openhab.model.persistence.persistence.PersistenceModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openhab.model.persistence.persistence.PersistenceModel
   * @generated
   */
  public Adapter createPersistenceModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openhab.model.persistence.persistence.Strategy <em>Strategy</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openhab.model.persistence.persistence.Strategy
   * @generated
   */
  public Adapter createStrategyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openhab.model.persistence.persistence.Filter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openhab.model.persistence.persistence.Filter
   * @generated
   */
  public Adapter createFilterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openhab.model.persistence.persistence.FilterDetails <em>Filter Details</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openhab.model.persistence.persistence.FilterDetails
   * @generated
   */
  public Adapter createFilterDetailsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openhab.model.persistence.persistence.ThresholdFilter <em>Threshold Filter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openhab.model.persistence.persistence.ThresholdFilter
   * @generated
   */
  public Adapter createThresholdFilterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openhab.model.persistence.persistence.TimeFilter <em>Time Filter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openhab.model.persistence.persistence.TimeFilter
   * @generated
   */
  public Adapter createTimeFilterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openhab.model.persistence.persistence.PersistenceConfiguration <em>Configuration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openhab.model.persistence.persistence.PersistenceConfiguration
   * @generated
   */
  public Adapter createPersistenceConfigurationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openhab.model.persistence.persistence.AllConfig <em>All Config</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openhab.model.persistence.persistence.AllConfig
   * @generated
   */
  public Adapter createAllConfigAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openhab.model.persistence.persistence.ItemConfig <em>Item Config</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openhab.model.persistence.persistence.ItemConfig
   * @generated
   */
  public Adapter createItemConfigAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openhab.model.persistence.persistence.GroupConfig <em>Group Config</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openhab.model.persistence.persistence.GroupConfig
   * @generated
   */
  public Adapter createGroupConfigAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.openhab.model.persistence.persistence.CronStrategy <em>Cron Strategy</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.openhab.model.persistence.persistence.CronStrategy
   * @generated
   */
  public Adapter createCronStrategyAdapter()
  {
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
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //PersistenceAdapterFactory
