/**
 */
package org.openhab.model.sitemap.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.openhab.model.sitemap.Chart;
import org.openhab.model.sitemap.ColorArray;
import org.openhab.model.sitemap.Colorpicker;
import org.openhab.model.sitemap.Frame;
import org.openhab.model.sitemap.Group;
import org.openhab.model.sitemap.Image;
import org.openhab.model.sitemap.LinkableWidget;
import org.openhab.model.sitemap.List;
import org.openhab.model.sitemap.Mapping;
import org.openhab.model.sitemap.Mapview;
import org.openhab.model.sitemap.NonLinkableWidget;
import org.openhab.model.sitemap.Selection;
import org.openhab.model.sitemap.Setpoint;
import org.openhab.model.sitemap.Sitemap;
import org.openhab.model.sitemap.SitemapFactory;
import org.openhab.model.sitemap.SitemapModel;
import org.openhab.model.sitemap.SitemapPackage;
import org.openhab.model.sitemap.Slider;
import org.openhab.model.sitemap.Switch;
import org.openhab.model.sitemap.Text;
import org.openhab.model.sitemap.Video;
import org.openhab.model.sitemap.VisibilityRule;
import org.openhab.model.sitemap.Webview;
import org.openhab.model.sitemap.Widget;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SitemapPackageImpl extends EPackageImpl implements SitemapPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sitemapModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sitemapEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass widgetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nonLinkableWidgetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass linkableWidgetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass frameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass textEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass groupEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass imageEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass videoEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass chartEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass webviewEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mapviewEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass switchEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sliderEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass selectionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass listEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass setpointEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass colorpickerEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mappingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass visibilityRuleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass colorArrayEClass = null;

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
   * @see org.openhab.model.sitemap.SitemapPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private SitemapPackageImpl()
  {
    super(eNS_URI, SitemapFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link SitemapPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static SitemapPackage init()
  {
    if (isInited) return (SitemapPackage)EPackage.Registry.INSTANCE.getEPackage(SitemapPackage.eNS_URI);

    // Obtain or create and register package
    SitemapPackageImpl theSitemapPackage = (SitemapPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SitemapPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SitemapPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theSitemapPackage.createPackageContents();

    // Initialize created meta-data
    theSitemapPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theSitemapPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(SitemapPackage.eNS_URI, theSitemapPackage);
    return theSitemapPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSitemapModel()
  {
    return sitemapModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSitemap()
  {
    return sitemapEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSitemap_Name()
  {
    return (EAttribute)sitemapEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSitemap_Label()
  {
    return (EAttribute)sitemapEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSitemap_Icon()
  {
    return (EAttribute)sitemapEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSitemap_Children()
  {
    return (EReference)sitemapEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWidget()
  {
    return widgetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWidget_Item()
  {
    return (EAttribute)widgetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWidget_Label()
  {
    return (EAttribute)widgetEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWidget_Icon()
  {
    return (EAttribute)widgetEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getWidget_LabelColor()
  {
    return (EReference)widgetEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getWidget_ValueColor()
  {
    return (EReference)widgetEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getWidget_Visibility()
  {
    return (EReference)widgetEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNonLinkableWidget()
  {
    return nonLinkableWidgetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLinkableWidget()
  {
    return linkableWidgetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLinkableWidget_Children()
  {
    return (EReference)linkableWidgetEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFrame()
  {
    return frameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getText()
  {
    return textEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGroup()
  {
    return groupEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImage()
  {
    return imageEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImage_Url()
  {
    return (EAttribute)imageEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImage_Refresh()
  {
    return (EAttribute)imageEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImage_IconColor()
  {
    return (EReference)imageEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVideo()
  {
    return videoEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVideo_Url()
  {
    return (EAttribute)videoEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVideo_Encoding()
  {
    return (EAttribute)videoEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getChart()
  {
    return chartEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getChart_Service()
  {
    return (EAttribute)chartEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getChart_Refresh()
  {
    return (EAttribute)chartEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getChart_Period()
  {
    return (EAttribute)chartEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWebview()
  {
    return webviewEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWebview_Height()
  {
    return (EAttribute)webviewEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getWebview_Url()
  {
    return (EAttribute)webviewEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMapview()
  {
    return mapviewEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMapview_Height()
  {
    return (EAttribute)mapviewEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSwitch()
  {
    return switchEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSwitch_Mappings()
  {
    return (EReference)switchEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSlider()
  {
    return sliderEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSlider_Frequency()
  {
    return (EAttribute)sliderEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSlider_SwitchEnabled()
  {
    return (EAttribute)sliderEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSelection()
  {
    return selectionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSelection_Mappings()
  {
    return (EReference)selectionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getList()
  {
    return listEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getList_Separator()
  {
    return (EAttribute)listEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSetpoint()
  {
    return setpointEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSetpoint_MinValue()
  {
    return (EAttribute)setpointEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSetpoint_MaxValue()
  {
    return (EAttribute)setpointEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSetpoint_Step()
  {
    return (EAttribute)setpointEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getColorpicker()
  {
    return colorpickerEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getColorpicker_Frequency()
  {
    return (EAttribute)colorpickerEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMapping()
  {
    return mappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMapping_Cmd()
  {
    return (EAttribute)mappingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMapping_Label()
  {
    return (EAttribute)mappingEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVisibilityRule()
  {
    return visibilityRuleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVisibilityRule_Item()
  {
    return (EAttribute)visibilityRuleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVisibilityRule_Condition()
  {
    return (EAttribute)visibilityRuleEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVisibilityRule_Sign()
  {
    return (EAttribute)visibilityRuleEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVisibilityRule_State()
  {
    return (EAttribute)visibilityRuleEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getColorArray()
  {
    return colorArrayEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getColorArray_Item()
  {
    return (EAttribute)colorArrayEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getColorArray_Condition()
  {
    return (EAttribute)colorArrayEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getColorArray_Sign()
  {
    return (EAttribute)colorArrayEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getColorArray_State()
  {
    return (EAttribute)colorArrayEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getColorArray_Arg()
  {
    return (EAttribute)colorArrayEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SitemapFactory getSitemapFactory()
  {
    return (SitemapFactory)getEFactoryInstance();
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
    sitemapModelEClass = createEClass(SITEMAP_MODEL);

    sitemapEClass = createEClass(SITEMAP);
    createEAttribute(sitemapEClass, SITEMAP__NAME);
    createEAttribute(sitemapEClass, SITEMAP__LABEL);
    createEAttribute(sitemapEClass, SITEMAP__ICON);
    createEReference(sitemapEClass, SITEMAP__CHILDREN);

    widgetEClass = createEClass(WIDGET);
    createEAttribute(widgetEClass, WIDGET__ITEM);
    createEAttribute(widgetEClass, WIDGET__LABEL);
    createEAttribute(widgetEClass, WIDGET__ICON);
    createEReference(widgetEClass, WIDGET__LABEL_COLOR);
    createEReference(widgetEClass, WIDGET__VALUE_COLOR);
    createEReference(widgetEClass, WIDGET__VISIBILITY);

    nonLinkableWidgetEClass = createEClass(NON_LINKABLE_WIDGET);

    linkableWidgetEClass = createEClass(LINKABLE_WIDGET);
    createEReference(linkableWidgetEClass, LINKABLE_WIDGET__CHILDREN);

    frameEClass = createEClass(FRAME);

    textEClass = createEClass(TEXT);

    groupEClass = createEClass(GROUP);

    imageEClass = createEClass(IMAGE);
    createEAttribute(imageEClass, IMAGE__URL);
    createEAttribute(imageEClass, IMAGE__REFRESH);
    createEReference(imageEClass, IMAGE__ICON_COLOR);

    videoEClass = createEClass(VIDEO);
    createEAttribute(videoEClass, VIDEO__URL);
    createEAttribute(videoEClass, VIDEO__ENCODING);

    chartEClass = createEClass(CHART);
    createEAttribute(chartEClass, CHART__SERVICE);
    createEAttribute(chartEClass, CHART__REFRESH);
    createEAttribute(chartEClass, CHART__PERIOD);

    webviewEClass = createEClass(WEBVIEW);
    createEAttribute(webviewEClass, WEBVIEW__HEIGHT);
    createEAttribute(webviewEClass, WEBVIEW__URL);

    mapviewEClass = createEClass(MAPVIEW);
    createEAttribute(mapviewEClass, MAPVIEW__HEIGHT);

    switchEClass = createEClass(SWITCH);
    createEReference(switchEClass, SWITCH__MAPPINGS);

    sliderEClass = createEClass(SLIDER);
    createEAttribute(sliderEClass, SLIDER__FREQUENCY);
    createEAttribute(sliderEClass, SLIDER__SWITCH_ENABLED);

    selectionEClass = createEClass(SELECTION);
    createEReference(selectionEClass, SELECTION__MAPPINGS);

    listEClass = createEClass(LIST);
    createEAttribute(listEClass, LIST__SEPARATOR);

    setpointEClass = createEClass(SETPOINT);
    createEAttribute(setpointEClass, SETPOINT__MIN_VALUE);
    createEAttribute(setpointEClass, SETPOINT__MAX_VALUE);
    createEAttribute(setpointEClass, SETPOINT__STEP);

    colorpickerEClass = createEClass(COLORPICKER);
    createEAttribute(colorpickerEClass, COLORPICKER__FREQUENCY);

    mappingEClass = createEClass(MAPPING);
    createEAttribute(mappingEClass, MAPPING__CMD);
    createEAttribute(mappingEClass, MAPPING__LABEL);

    visibilityRuleEClass = createEClass(VISIBILITY_RULE);
    createEAttribute(visibilityRuleEClass, VISIBILITY_RULE__ITEM);
    createEAttribute(visibilityRuleEClass, VISIBILITY_RULE__CONDITION);
    createEAttribute(visibilityRuleEClass, VISIBILITY_RULE__SIGN);
    createEAttribute(visibilityRuleEClass, VISIBILITY_RULE__STATE);

    colorArrayEClass = createEClass(COLOR_ARRAY);
    createEAttribute(colorArrayEClass, COLOR_ARRAY__ITEM);
    createEAttribute(colorArrayEClass, COLOR_ARRAY__CONDITION);
    createEAttribute(colorArrayEClass, COLOR_ARRAY__SIGN);
    createEAttribute(colorArrayEClass, COLOR_ARRAY__STATE);
    createEAttribute(colorArrayEClass, COLOR_ARRAY__ARG);
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
    sitemapEClass.getESuperTypes().add(this.getSitemapModel());
    nonLinkableWidgetEClass.getESuperTypes().add(this.getWidget());
    linkableWidgetEClass.getESuperTypes().add(this.getWidget());
    frameEClass.getESuperTypes().add(this.getLinkableWidget());
    textEClass.getESuperTypes().add(this.getLinkableWidget());
    groupEClass.getESuperTypes().add(this.getLinkableWidget());
    imageEClass.getESuperTypes().add(this.getLinkableWidget());
    videoEClass.getESuperTypes().add(this.getNonLinkableWidget());
    chartEClass.getESuperTypes().add(this.getNonLinkableWidget());
    webviewEClass.getESuperTypes().add(this.getNonLinkableWidget());
    mapviewEClass.getESuperTypes().add(this.getNonLinkableWidget());
    switchEClass.getESuperTypes().add(this.getNonLinkableWidget());
    sliderEClass.getESuperTypes().add(this.getNonLinkableWidget());
    selectionEClass.getESuperTypes().add(this.getNonLinkableWidget());
    listEClass.getESuperTypes().add(this.getNonLinkableWidget());
    setpointEClass.getESuperTypes().add(this.getNonLinkableWidget());
    colorpickerEClass.getESuperTypes().add(this.getNonLinkableWidget());

    // Initialize classes and features; add operations and parameters
    initEClass(sitemapModelEClass, SitemapModel.class, "SitemapModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(sitemapEClass, Sitemap.class, "Sitemap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSitemap_Name(), ecorePackage.getEString(), "name", null, 0, 1, Sitemap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSitemap_Label(), ecorePackage.getEString(), "label", null, 0, 1, Sitemap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSitemap_Icon(), ecorePackage.getEString(), "icon", null, 0, 1, Sitemap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSitemap_Children(), this.getWidget(), null, "children", null, 0, -1, Sitemap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(widgetEClass, Widget.class, "Widget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getWidget_Item(), ecorePackage.getEString(), "item", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getWidget_Label(), ecorePackage.getEString(), "label", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getWidget_Icon(), ecorePackage.getEString(), "icon", null, 0, 1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWidget_LabelColor(), this.getColorArray(), null, "LabelColor", null, 0, -1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWidget_ValueColor(), this.getColorArray(), null, "ValueColor", null, 0, -1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWidget_Visibility(), this.getVisibilityRule(), null, "Visibility", null, 0, -1, Widget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nonLinkableWidgetEClass, NonLinkableWidget.class, "NonLinkableWidget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(linkableWidgetEClass, LinkableWidget.class, "LinkableWidget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getLinkableWidget_Children(), this.getWidget(), null, "children", null, 0, -1, LinkableWidget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(frameEClass, Frame.class, "Frame", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(textEClass, Text.class, "Text", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(groupEClass, Group.class, "Group", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(imageEClass, Image.class, "Image", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getImage_Url(), ecorePackage.getEString(), "url", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getImage_Refresh(), ecorePackage.getEInt(), "refresh", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getImage_IconColor(), this.getColorArray(), null, "IconColor", null, 0, -1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(videoEClass, Video.class, "Video", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getVideo_Url(), ecorePackage.getEString(), "url", null, 0, 1, Video.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getVideo_Encoding(), ecorePackage.getEString(), "encoding", null, 0, 1, Video.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(chartEClass, Chart.class, "Chart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getChart_Service(), ecorePackage.getEString(), "service", null, 0, 1, Chart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getChart_Refresh(), ecorePackage.getEInt(), "refresh", null, 0, 1, Chart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getChart_Period(), ecorePackage.getEString(), "period", null, 0, 1, Chart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(webviewEClass, Webview.class, "Webview", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getWebview_Height(), ecorePackage.getEInt(), "height", null, 0, 1, Webview.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getWebview_Url(), ecorePackage.getEString(), "url", null, 0, 1, Webview.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(mapviewEClass, Mapview.class, "Mapview", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMapview_Height(), ecorePackage.getEInt(), "height", null, 0, 1, Mapview.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(switchEClass, Switch.class, "Switch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSwitch_Mappings(), this.getMapping(), null, "mappings", null, 0, -1, Switch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(sliderEClass, Slider.class, "Slider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSlider_Frequency(), ecorePackage.getEInt(), "frequency", null, 0, 1, Slider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSlider_SwitchEnabled(), ecorePackage.getEBoolean(), "switchEnabled", null, 0, 1, Slider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(selectionEClass, Selection.class, "Selection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSelection_Mappings(), this.getMapping(), null, "mappings", null, 0, -1, Selection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(listEClass, List.class, "List", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getList_Separator(), ecorePackage.getEString(), "separator", null, 0, 1, List.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(setpointEClass, Setpoint.class, "Setpoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSetpoint_MinValue(), ecorePackage.getEBigDecimal(), "minValue", null, 0, 1, Setpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSetpoint_MaxValue(), ecorePackage.getEBigDecimal(), "maxValue", null, 0, 1, Setpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSetpoint_Step(), ecorePackage.getEBigDecimal(), "step", null, 0, 1, Setpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(colorpickerEClass, Colorpicker.class, "Colorpicker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getColorpicker_Frequency(), ecorePackage.getEInt(), "frequency", null, 0, 1, Colorpicker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(mappingEClass, Mapping.class, "Mapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMapping_Cmd(), ecorePackage.getEString(), "cmd", null, 0, 1, Mapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMapping_Label(), ecorePackage.getEString(), "label", null, 0, 1, Mapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(visibilityRuleEClass, VisibilityRule.class, "VisibilityRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getVisibilityRule_Item(), ecorePackage.getEString(), "item", null, 0, 1, VisibilityRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getVisibilityRule_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, VisibilityRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getVisibilityRule_Sign(), ecorePackage.getEString(), "sign", null, 0, 1, VisibilityRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getVisibilityRule_State(), ecorePackage.getEString(), "state", null, 0, 1, VisibilityRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(colorArrayEClass, ColorArray.class, "ColorArray", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getColorArray_Item(), ecorePackage.getEString(), "item", null, 0, 1, ColorArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getColorArray_Condition(), ecorePackage.getEString(), "condition", null, 0, 1, ColorArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getColorArray_Sign(), ecorePackage.getEString(), "sign", null, 0, 1, ColorArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getColorArray_State(), ecorePackage.getEString(), "state", null, 0, 1, ColorArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getColorArray_Arg(), ecorePackage.getEString(), "arg", null, 0, 1, ColorArray.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //SitemapPackageImpl