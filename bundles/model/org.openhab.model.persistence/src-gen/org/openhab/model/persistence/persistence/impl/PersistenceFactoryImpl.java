/**
 */
package org.openhab.model.persistence.persistence.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.openhab.model.persistence.persistence.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PersistenceFactoryImpl extends EFactoryImpl implements PersistenceFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static PersistenceFactory init()
  {
    try
    {
      PersistenceFactory thePersistenceFactory = (PersistenceFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openhab.org/model/persistence/Persistence"); 
      if (thePersistenceFactory != null)
      {
        return thePersistenceFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new PersistenceFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersistenceFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case PersistencePackage.PERSISTENCE_MODEL: return createPersistenceModel();
      case PersistencePackage.STRATEGY: return createStrategy();
      case PersistencePackage.FILTER: return createFilter();
      case PersistencePackage.FILTER_DETAILS: return createFilterDetails();
      case PersistencePackage.THRESHOLD_FILTER: return createThresholdFilter();
      case PersistencePackage.TIME_FILTER: return createTimeFilter();
      case PersistencePackage.PERSISTENCE_CONFIGURATION: return createPersistenceConfiguration();
      case PersistencePackage.ALL_CONFIG: return createAllConfig();
      case PersistencePackage.ITEM_CONFIG: return createItemConfig();
      case PersistencePackage.GROUP_CONFIG: return createGroupConfig();
      case PersistencePackage.CRON_STRATEGY: return createCronStrategy();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersistenceModel createPersistenceModel()
  {
    PersistenceModelImpl persistenceModel = new PersistenceModelImpl();
    return persistenceModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Strategy createStrategy()
  {
    StrategyImpl strategy = new StrategyImpl();
    return strategy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Filter createFilter()
  {
    FilterImpl filter = new FilterImpl();
    return filter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FilterDetails createFilterDetails()
  {
    FilterDetailsImpl filterDetails = new FilterDetailsImpl();
    return filterDetails;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ThresholdFilter createThresholdFilter()
  {
    ThresholdFilterImpl thresholdFilter = new ThresholdFilterImpl();
    return thresholdFilter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TimeFilter createTimeFilter()
  {
    TimeFilterImpl timeFilter = new TimeFilterImpl();
    return timeFilter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersistenceConfiguration createPersistenceConfiguration()
  {
    PersistenceConfigurationImpl persistenceConfiguration = new PersistenceConfigurationImpl();
    return persistenceConfiguration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AllConfig createAllConfig()
  {
    AllConfigImpl allConfig = new AllConfigImpl();
    return allConfig;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ItemConfig createItemConfig()
  {
    ItemConfigImpl itemConfig = new ItemConfigImpl();
    return itemConfig;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupConfig createGroupConfig()
  {
    GroupConfigImpl groupConfig = new GroupConfigImpl();
    return groupConfig;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CronStrategy createCronStrategy()
  {
    CronStrategyImpl cronStrategy = new CronStrategyImpl();
    return cronStrategy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersistencePackage getPersistencePackage()
  {
    return (PersistencePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static PersistencePackage getPackage()
  {
    return PersistencePackage.eINSTANCE;
  }

} //PersistenceFactoryImpl
