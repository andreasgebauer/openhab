/**
 */
package org.openhab.model.persistence.persistence.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openhab.model.persistence.persistence.Filter;
import org.openhab.model.persistence.persistence.PersistenceConfiguration;
import org.openhab.model.persistence.persistence.PersistencePackage;
import org.openhab.model.persistence.persistence.Strategy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openhab.model.persistence.persistence.impl.PersistenceConfigurationImpl#getItems <em>Items</em>}</li>
 *   <li>{@link org.openhab.model.persistence.persistence.impl.PersistenceConfigurationImpl#getAlias <em>Alias</em>}</li>
 *   <li>{@link org.openhab.model.persistence.persistence.impl.PersistenceConfigurationImpl#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link org.openhab.model.persistence.persistence.impl.PersistenceConfigurationImpl#getFilters <em>Filters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PersistenceConfigurationImpl extends MinimalEObjectImpl.Container implements PersistenceConfiguration
{
  /**
   * The cached value of the '{@link #getItems() <em>Items</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getItems()
   * @generated
   * @ordered
   */
  protected EList<EObject> items;

  /**
   * The default value of the '{@link #getAlias() <em>Alias</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlias()
   * @generated
   * @ordered
   */
  protected static final String ALIAS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAlias() <em>Alias</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAlias()
   * @generated
   * @ordered
   */
  protected String alias = ALIAS_EDEFAULT;

  /**
   * The cached value of the '{@link #getStrategies() <em>Strategies</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStrategies()
   * @generated
   * @ordered
   */
  protected EList<Strategy> strategies;

  /**
   * The cached value of the '{@link #getFilters() <em>Filters</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFilters()
   * @generated
   * @ordered
   */
  protected EList<Filter> filters;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PersistenceConfigurationImpl()
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
    return PersistencePackage.Literals.PERSISTENCE_CONFIGURATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getItems()
  {
    if (items == null)
    {
      items = new EObjectContainmentEList<EObject>(EObject.class, this, PersistencePackage.PERSISTENCE_CONFIGURATION__ITEMS);
    }
    return items;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAlias()
  {
    return alias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAlias(String newAlias)
  {
    String oldAlias = alias;
    alias = newAlias;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PersistencePackage.PERSISTENCE_CONFIGURATION__ALIAS, oldAlias, alias));
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
      strategies = new EObjectResolvingEList<Strategy>(Strategy.class, this, PersistencePackage.PERSISTENCE_CONFIGURATION__STRATEGIES);
    }
    return strategies;
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
      filters = new EObjectResolvingEList<Filter>(Filter.class, this, PersistencePackage.PERSISTENCE_CONFIGURATION__FILTERS);
    }
    return filters;
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
      case PersistencePackage.PERSISTENCE_CONFIGURATION__ITEMS:
        return ((InternalEList<?>)getItems()).basicRemove(otherEnd, msgs);
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
      case PersistencePackage.PERSISTENCE_CONFIGURATION__ITEMS:
        return getItems();
      case PersistencePackage.PERSISTENCE_CONFIGURATION__ALIAS:
        return getAlias();
      case PersistencePackage.PERSISTENCE_CONFIGURATION__STRATEGIES:
        return getStrategies();
      case PersistencePackage.PERSISTENCE_CONFIGURATION__FILTERS:
        return getFilters();
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
      case PersistencePackage.PERSISTENCE_CONFIGURATION__ITEMS:
        getItems().clear();
        getItems().addAll((Collection<? extends EObject>)newValue);
        return;
      case PersistencePackage.PERSISTENCE_CONFIGURATION__ALIAS:
        setAlias((String)newValue);
        return;
      case PersistencePackage.PERSISTENCE_CONFIGURATION__STRATEGIES:
        getStrategies().clear();
        getStrategies().addAll((Collection<? extends Strategy>)newValue);
        return;
      case PersistencePackage.PERSISTENCE_CONFIGURATION__FILTERS:
        getFilters().clear();
        getFilters().addAll((Collection<? extends Filter>)newValue);
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
      case PersistencePackage.PERSISTENCE_CONFIGURATION__ITEMS:
        getItems().clear();
        return;
      case PersistencePackage.PERSISTENCE_CONFIGURATION__ALIAS:
        setAlias(ALIAS_EDEFAULT);
        return;
      case PersistencePackage.PERSISTENCE_CONFIGURATION__STRATEGIES:
        getStrategies().clear();
        return;
      case PersistencePackage.PERSISTENCE_CONFIGURATION__FILTERS:
        getFilters().clear();
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
      case PersistencePackage.PERSISTENCE_CONFIGURATION__ITEMS:
        return items != null && !items.isEmpty();
      case PersistencePackage.PERSISTENCE_CONFIGURATION__ALIAS:
        return ALIAS_EDEFAULT == null ? alias != null : !ALIAS_EDEFAULT.equals(alias);
      case PersistencePackage.PERSISTENCE_CONFIGURATION__STRATEGIES:
        return strategies != null && !strategies.isEmpty();
      case PersistencePackage.PERSISTENCE_CONFIGURATION__FILTERS:
        return filters != null && !filters.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (alias: ");
    result.append(alias);
    result.append(')');
    return result.toString();
  }

} //PersistenceConfigurationImpl
