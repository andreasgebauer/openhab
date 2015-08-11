/**
 */
package org.openhab.model.persistence.persistence.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.openhab.model.persistence.persistence.AllConfig;
import org.openhab.model.persistence.persistence.CronStrategy;
import org.openhab.model.persistence.persistence.Filter;
import org.openhab.model.persistence.persistence.FilterDetails;
import org.openhab.model.persistence.persistence.GroupConfig;
import org.openhab.model.persistence.persistence.ItemConfig;
import org.openhab.model.persistence.persistence.PersistenceConfiguration;
import org.openhab.model.persistence.persistence.PersistenceFactory;
import org.openhab.model.persistence.persistence.PersistenceModel;
import org.openhab.model.persistence.persistence.PersistencePackage;
import org.openhab.model.persistence.persistence.Strategy;
import org.openhab.model.persistence.persistence.ThresholdFilter;
import org.openhab.model.persistence.persistence.TimeFilter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PersistencePackageImpl extends EPackageImpl implements PersistencePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass persistenceModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass strategyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass filterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass filterDetailsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass thresholdFilterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass timeFilterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass persistenceConfigurationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass allConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass itemConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass groupConfigEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass cronStrategyEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.openhab.model.persistence.persistence.PersistencePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private PersistencePackageImpl()
  {
    super(eNS_URI, PersistenceFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link PersistencePackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static PersistencePackage init()
  {
    if (isInited) return (PersistencePackage)EPackage.Registry.INSTANCE.getEPackage(PersistencePackage.eNS_URI);

    // Obtain or create and register package
    PersistencePackageImpl thePersistencePackage = (PersistencePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PersistencePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PersistencePackageImpl());

    isInited = true;

    // Create package meta-data objects
    thePersistencePackage.createPackageContents();

    // Initialize created meta-data
    thePersistencePackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    thePersistencePackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(PersistencePackage.eNS_URI, thePersistencePackage);
    return thePersistencePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPersistenceModel()
  {
    return persistenceModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPersistenceModel_Strategies()
  {
    return (EReference)persistenceModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPersistenceModel_Defaults()
  {
    return (EReference)persistenceModelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPersistenceModel_Filters()
  {
    return (EReference)persistenceModelEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPersistenceModel_Configs()
  {
    return (EReference)persistenceModelEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStrategy()
  {
    return strategyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getStrategy_Name()
  {
    return (EAttribute)strategyEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFilter()
  {
    return filterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFilter_Name()
  {
    return (EAttribute)filterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFilter_Definition()
  {
    return (EReference)filterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFilterDetails()
  {
    return filterDetailsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getThresholdFilter()
  {
    return thresholdFilterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getThresholdFilter_Value()
  {
    return (EAttribute)thresholdFilterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getThresholdFilter_Percent()
  {
    return (EAttribute)thresholdFilterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTimeFilter()
  {
    return timeFilterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTimeFilter_Value()
  {
    return (EAttribute)timeFilterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTimeFilter_Unit()
  {
    return (EAttribute)timeFilterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPersistenceConfiguration()
  {
    return persistenceConfigurationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPersistenceConfiguration_Items()
  {
    return (EReference)persistenceConfigurationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPersistenceConfiguration_Alias()
  {
    return (EAttribute)persistenceConfigurationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPersistenceConfiguration_Strategies()
  {
    return (EReference)persistenceConfigurationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPersistenceConfiguration_Filters()
  {
    return (EReference)persistenceConfigurationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAllConfig()
  {
    return allConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getItemConfig()
  {
    return itemConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getItemConfig_Item()
  {
    return (EAttribute)itemConfigEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGroupConfig()
  {
    return groupConfigEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGroupConfig_Group()
  {
    return (EAttribute)groupConfigEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCronStrategy()
  {
    return cronStrategyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCronStrategy_CronExpression()
  {
    return (EAttribute)cronStrategyEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersistenceFactory getPersistenceFactory()
  {
    return (PersistenceFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    persistenceModelEClass = createEClass(PERSISTENCE_MODEL);
    createEReference(persistenceModelEClass, PERSISTENCE_MODEL__STRATEGIES);
    createEReference(persistenceModelEClass, PERSISTENCE_MODEL__DEFAULTS);
    createEReference(persistenceModelEClass, PERSISTENCE_MODEL__FILTERS);
    createEReference(persistenceModelEClass, PERSISTENCE_MODEL__CONFIGS);

    strategyEClass = createEClass(STRATEGY);
    createEAttribute(strategyEClass, STRATEGY__NAME);

    filterEClass = createEClass(FILTER);
    createEAttribute(filterEClass, FILTER__NAME);
    createEReference(filterEClass, FILTER__DEFINITION);

    filterDetailsEClass = createEClass(FILTER_DETAILS);

    thresholdFilterEClass = createEClass(THRESHOLD_FILTER);
    createEAttribute(thresholdFilterEClass, THRESHOLD_FILTER__VALUE);
    createEAttribute(thresholdFilterEClass, THRESHOLD_FILTER__PERCENT);

    timeFilterEClass = createEClass(TIME_FILTER);
    createEAttribute(timeFilterEClass, TIME_FILTER__VALUE);
    createEAttribute(timeFilterEClass, TIME_FILTER__UNIT);

    persistenceConfigurationEClass = createEClass(PERSISTENCE_CONFIGURATION);
    createEReference(persistenceConfigurationEClass, PERSISTENCE_CONFIGURATION__ITEMS);
    createEAttribute(persistenceConfigurationEClass, PERSISTENCE_CONFIGURATION__ALIAS);
    createEReference(persistenceConfigurationEClass, PERSISTENCE_CONFIGURATION__STRATEGIES);
    createEReference(persistenceConfigurationEClass, PERSISTENCE_CONFIGURATION__FILTERS);

    allConfigEClass = createEClass(ALL_CONFIG);

    itemConfigEClass = createEClass(ITEM_CONFIG);
    createEAttribute(itemConfigEClass, ITEM_CONFIG__ITEM);

    groupConfigEClass = createEClass(GROUP_CONFIG);
    createEAttribute(groupConfigEClass, GROUP_CONFIG__GROUP);

    cronStrategyEClass = createEClass(CRON_STRATEGY);
    createEAttribute(cronStrategyEClass, CRON_STRATEGY__CRON_EXPRESSION);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    thresholdFilterEClass.getESuperTypes().add(this.getFilterDetails());
    timeFilterEClass.getESuperTypes().add(this.getFilterDetails());
    cronStrategyEClass.getESuperTypes().add(this.getStrategy());

    // Initialize classes and features; add operations and parameters
    initEClass(persistenceModelEClass, PersistenceModel.class, "PersistenceModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPersistenceModel_Strategies(), this.getStrategy(), null, "strategies", null, 0, -1, PersistenceModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPersistenceModel_Defaults(), this.getStrategy(), null, "defaults", null, 0, -1, PersistenceModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPersistenceModel_Filters(), this.getFilter(), null, "filters", null, 0, -1, PersistenceModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPersistenceModel_Configs(), this.getPersistenceConfiguration(), null, "configs", null, 0, -1, PersistenceModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(strategyEClass, Strategy.class, "Strategy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getStrategy_Name(), ecorePackage.getEString(), "name", null, 0, 1, Strategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(filterEClass, Filter.class, "Filter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFilter_Name(), ecorePackage.getEString(), "name", null, 0, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFilter_Definition(), this.getFilterDetails(), null, "definition", null, 0, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(filterDetailsEClass, FilterDetails.class, "FilterDetails", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(thresholdFilterEClass, ThresholdFilter.class, "ThresholdFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getThresholdFilter_Value(), ecorePackage.getEBigDecimal(), "value", null, 0, 1, ThresholdFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getThresholdFilter_Percent(), ecorePackage.getEBoolean(), "percent", null, 0, 1, ThresholdFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(timeFilterEClass, TimeFilter.class, "TimeFilter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTimeFilter_Value(), ecorePackage.getEInt(), "value", null, 0, 1, TimeFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTimeFilter_Unit(), ecorePackage.getEString(), "unit", null, 0, 1, TimeFilter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(persistenceConfigurationEClass, PersistenceConfiguration.class, "PersistenceConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPersistenceConfiguration_Items(), ecorePackage.getEObject(), null, "items", null, 0, -1, PersistenceConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPersistenceConfiguration_Alias(), ecorePackage.getEString(), "alias", null, 0, 1, PersistenceConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPersistenceConfiguration_Strategies(), this.getStrategy(), null, "strategies", null, 0, -1, PersistenceConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPersistenceConfiguration_Filters(), this.getFilter(), null, "filters", null, 0, -1, PersistenceConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(allConfigEClass, AllConfig.class, "AllConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(itemConfigEClass, ItemConfig.class, "ItemConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getItemConfig_Item(), ecorePackage.getEString(), "item", null, 0, 1, ItemConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(groupConfigEClass, GroupConfig.class, "GroupConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGroupConfig_Group(), ecorePackage.getEString(), "group", null, 0, 1, GroupConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(cronStrategyEClass, CronStrategy.class, "CronStrategy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCronStrategy_CronExpression(), ecorePackage.getEString(), "cronExpression", null, 0, 1, CronStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //PersistencePackageImpl