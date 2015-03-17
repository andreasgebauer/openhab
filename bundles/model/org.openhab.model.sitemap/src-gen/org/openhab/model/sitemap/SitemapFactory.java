/**
 */
package org.openhab.model.sitemap;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openhab.model.sitemap.SitemapPackage
 * @generated
 */
public interface SitemapFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SitemapFactory eINSTANCE = org.openhab.model.sitemap.impl.SitemapFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model</em>'.
   * @generated
   */
  SitemapModel createSitemapModel();

  /**
   * Returns a new object of class '<em>Sitemap</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Sitemap</em>'.
   * @generated
   */
  Sitemap createSitemap();

  /**
   * Returns a new object of class '<em>Widget</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Widget</em>'.
   * @generated
   */
  Widget createWidget();

  /**
   * Returns a new object of class '<em>Non Linkable Widget</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Non Linkable Widget</em>'.
   * @generated
   */
  NonLinkableWidget createNonLinkableWidget();

  /**
   * Returns a new object of class '<em>Linkable Widget</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Linkable Widget</em>'.
   * @generated
   */
  LinkableWidget createLinkableWidget();

  /**
   * Returns a new object of class '<em>Frame</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Frame</em>'.
   * @generated
   */
  Frame createFrame();

  /**
   * Returns a new object of class '<em>Text</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Text</em>'.
   * @generated
   */
  Text createText();

  /**
   * Returns a new object of class '<em>Group</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Group</em>'.
   * @generated
   */
  Group createGroup();

  /**
   * Returns a new object of class '<em>Image</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Image</em>'.
   * @generated
   */
  Image createImage();

  /**
   * Returns a new object of class '<em>Video</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Video</em>'.
   * @generated
   */
  Video createVideo();

  /**
   * Returns a new object of class '<em>Chart</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Chart</em>'.
   * @generated
   */
  Chart createChart();

  /**
   * Returns a new object of class '<em>Webview</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Webview</em>'.
   * @generated
   */
  Webview createWebview();

  /**
   * Returns a new object of class '<em>Switch</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Switch</em>'.
   * @generated
   */
  Switch createSwitch();

  /**
   * Returns a new object of class '<em>Slider</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Slider</em>'.
   * @generated
   */
  Slider createSlider();

  /**
   * Returns a new object of class '<em>Selection</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Selection</em>'.
   * @generated
   */
  Selection createSelection();

  /**
   * Returns a new object of class '<em>List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>List</em>'.
   * @generated
   */
  List createList();

  /**
   * Returns a new object of class '<em>Setpoint</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Setpoint</em>'.
   * @generated
   */
  Setpoint createSetpoint();

  /**
   * Returns a new object of class '<em>Colorpicker</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Colorpicker</em>'.
   * @generated
   */
  Colorpicker createColorpicker();

  /**
   * Returns a new object of class '<em>Mapping</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mapping</em>'.
   * @generated
   */
  Mapping createMapping();

  /**
   * Returns a new object of class '<em>Visibility Rule</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Visibility Rule</em>'.
   * @generated
   */
  VisibilityRule createVisibilityRule();

  /**
   * Returns a new object of class '<em>Color Array</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Color Array</em>'.
   * @generated
   */
  ColorArray createColorArray();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  SitemapPackage getSitemapPackage();

} //SitemapFactory
