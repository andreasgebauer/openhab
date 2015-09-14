/**
 */
package org.openhab.model.rule.rules.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.xbase.XBlockExpression;

import org.openhab.model.rule.rules.EventTrigger;
import org.openhab.model.rule.rules.Rule;
import org.openhab.model.rule.rules.RulesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openhab.model.rule.rules.impl.RuleImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.openhab.model.rule.rules.impl.RuleImpl#getEventtrigger <em>Eventtrigger</em>}</li>
 *   <li>{@link org.openhab.model.rule.rules.impl.RuleImpl#getScript <em>Script</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RuleImpl extends MinimalEObjectImpl.Container implements Rule
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getEventtrigger() <em>Eventtrigger</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEventtrigger()
   * @generated
   * @ordered
   */
  protected EList<EventTrigger> eventtrigger;

  /**
   * The cached value of the '{@link #getScript() <em>Script</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScript()
   * @generated
   * @ordered
   */
  protected XBlockExpression script;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RuleImpl()
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
    return RulesPackage.Literals.RULE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RulesPackage.RULE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EventTrigger> getEventtrigger()
  {
    if (eventtrigger == null)
    {
      eventtrigger = new EObjectContainmentEList<EventTrigger>(EventTrigger.class, this, RulesPackage.RULE__EVENTTRIGGER);
    }
    return eventtrigger;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XBlockExpression getScript()
  {
    return script;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetScript(XBlockExpression newScript, NotificationChain msgs)
  {
    XBlockExpression oldScript = script;
    script = newScript;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RulesPackage.RULE__SCRIPT, oldScript, newScript);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setScript(XBlockExpression newScript)
  {
    if (newScript != script)
    {
      NotificationChain msgs = null;
      if (script != null)
        msgs = ((InternalEObject)script).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RulesPackage.RULE__SCRIPT, null, msgs);
      if (newScript != null)
        msgs = ((InternalEObject)newScript).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RulesPackage.RULE__SCRIPT, null, msgs);
      msgs = basicSetScript(newScript, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RulesPackage.RULE__SCRIPT, newScript, newScript));
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
      case RulesPackage.RULE__EVENTTRIGGER:
        return ((InternalEList<?>)getEventtrigger()).basicRemove(otherEnd, msgs);
      case RulesPackage.RULE__SCRIPT:
        return basicSetScript(null, msgs);
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
      case RulesPackage.RULE__NAME:
        return getName();
      case RulesPackage.RULE__EVENTTRIGGER:
        return getEventtrigger();
      case RulesPackage.RULE__SCRIPT:
        return getScript();
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
      case RulesPackage.RULE__NAME:
        setName((String)newValue);
        return;
      case RulesPackage.RULE__EVENTTRIGGER:
        getEventtrigger().clear();
        getEventtrigger().addAll((Collection<? extends EventTrigger>)newValue);
        return;
      case RulesPackage.RULE__SCRIPT:
        setScript((XBlockExpression)newValue);
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
      case RulesPackage.RULE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case RulesPackage.RULE__EVENTTRIGGER:
        getEventtrigger().clear();
        return;
      case RulesPackage.RULE__SCRIPT:
        setScript((XBlockExpression)null);
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
      case RulesPackage.RULE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case RulesPackage.RULE__EVENTTRIGGER:
        return eventtrigger != null && !eventtrigger.isEmpty();
      case RulesPackage.RULE__SCRIPT:
        return script != null;
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
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //RuleImpl