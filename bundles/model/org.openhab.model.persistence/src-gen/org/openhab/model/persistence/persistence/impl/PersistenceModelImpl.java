/**
 */
package org.openhab.model.persistence.persistence.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openhab.model.persistence.persistence.Filter;
import org.openhab.model.persistence.persistence.PersistenceConfiguration;
import org.openhab.model.persistence.persistence.PersistenceModel;
import org.openhab.model.persistence.persistence.PersistencePackage;
import org.openhab.model.persistence.persistence.Strategy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openhab.model.persistence.persistence.impl.PersistenceModelImpl#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link org.openhab.model.persistence.persistence.impl.PersistenceModelImpl#getDefaults <em>Defaults</em>}</li>
 *   <li>{@link org.openhab.model.persistence.persistence.impl.PersistenceModelImpl#getFilters <em>Filters</em>}</li>
 *   <li>{@link org.openhab.model.persistence.persistence.impl.PersistenceModelImpl#getConfigs <em>Configs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PersistenceModelImpl extends MinimalEObjectImpl.Container implements PersistenceModel
{
  /**
   * The cached value of the '{@link #getStrategies() <em>Strategies</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStrategies()
   * @generated
   * @ordered
   */
  protected EList<Strategy> strategies;

  /**
   * The cached value of the '{@link #getDefaults() <em>Defaults</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefaults()
   * @generated
   * @ordered
   */
  protected EList<Strategy> defaults;

  /**
   * The cached value of the '{@link #getFilters() <em>Filters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFilters()
   * @generated
   * @ordered
   */
  protected EList<Filter> filters;

  /**
   * The cached value of the '{@link #getConfigs() <em>Configs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConfigs()
   * @generated
   * @ordered
   */
  protected EList<PersistenceConfiguration> configs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PersistenceModelImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return PersistencePackage.Literals.PERSISTENCE_MODEL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Strategy> getStrategies()
  {
    if (strategies == null)
    {
      strategies = new EObjectContainmentEList<Strategy>(Strategy.class, this, PersistencePackage.PERSISTENCE_MODEL__STRATEGIES);
    }
    return strategies;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Strategy> getDefaults()
  {
    if (defaults == null)
    {
      defaults = new EObjectResolvingEList<Strategy>(Strategy.class, this, PersistencePackage.PERSISTENCE_MODEL__DEFAULTS);
    }
    return defaults;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Filter> getFilters()
  {
    if (filters == null)
    {
      filters = new EObjectContainmentEList<Filter>(Filter.class, this, PersistencePackage.PERSISTENCE_MODEL__FILTERS);
    }
    return filters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PersistenceConfiguration> getConfigs()
  {
    if (configs == null)
    {
      configs = new EObjectContainmentEList<PersistenceConfiguration>(PersistenceConfiguration.class, this, PersistencePackage.PERSISTENCE_MODEL__CONFIGS);
    }
    return configs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case PersistencePackage.PERSISTENCE_MODEL__STRATEGIES:
        return ((InternalEList<?>)getStrategies()).basicRemove(otherEnd, msgs);
      case PersistencePackage.PERSISTENCE_MODEL__FILTERS:
        return ((InternalEList<?>)getFilters()).basicRemove(otherEnd, msgs);
      case PersistencePackage.PERSISTENCE_MODEL__CONFIGS:
        return ((InternalEList<?>)getConfigs()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case PersistencePackage.PERSISTENCE_MODEL__STRATEGIES:
        return getStrategies();
      case PersistencePackage.PERSISTENCE_MODEL__DEFAULTS:
        return getDefaults();
      case PersistencePackage.PERSISTENCE_MODEL__FILTERS:
        return getFilters();
      case PersistencePackage.PERSISTENCE_MODEL__CONFIGS:
        return getConfigs();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case PersistencePackage.PERSISTENCE_MODEL__STRATEGIES:
        getStrategies().clear();
        getStrategies().addAll((Collection<? extends Strategy>)newValue);
        return;
      case PersistencePackage.PERSISTENCE_MODEL__DEFAULTS:
        getDefaults().clear();
        getDefaults().addAll((Collection<? extends Strategy>)newValue);
        return;
      case PersistencePackage.PERSISTENCE_MODEL__FILTERS:
        getFilters().clear();
        getFilters().addAll((Collection<? extends Filter>)newValue);
        return;
      case PersistencePackage.PERSISTENCE_MODEL__CONFIGS:
        getConfigs().clear();
        getConfigs().addAll((Collection<? extends PersistenceConfiguration>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case PersistencePackage.PERSISTENCE_MODEL__STRATEGIES:
        getStrategies().clear();
        return;
      case PersistencePackage.PERSISTENCE_MODEL__DEFAULTS:
        getDefaults().clear();
        return;
      case PersistencePackage.PERSISTENCE_MODEL__FILTERS:
        getFilters().clear();
        return;
      case PersistencePackage.PERSISTENCE_MODEL__CONFIGS:
        getConfigs().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case PersistencePackage.PERSISTENCE_MODEL__STRATEGIES:
        return strategies != null && !strategies.isEmpty();
      case PersistencePackage.PERSISTENCE_MODEL__DEFAULTS:
        return defaults != null && !defaults.isEmpty();
      case PersistencePackage.PERSISTENCE_MODEL__FILTERS:
        return filters != null && !filters.isEmpty();
      case PersistencePackage.PERSISTENCE_MODEL__CONFIGS:
        return configs != null && !configs.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //PersistenceModelImpl