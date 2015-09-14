/**
 */
package org.openhab.model.sitemap.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.openhab.model.sitemap.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SitemapFactoryImpl extends EFactoryImpl implements SitemapFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SitemapFactory init()
  {
    try
    {
      SitemapFactory theSitemapFactory = (SitemapFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.openhab.org/model/Sitemap"); 
      if (theSitemapFactory != null)
      {
        return theSitemapFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new SitemapFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SitemapFactoryImpl()
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
      case SitemapPackage.SITEMAP_MODEL: return createSitemapModel();
      case SitemapPackage.SITEMAP: return createSitemap();
      case SitemapPackage.WIDGET: return createWidget();
      case SitemapPackage.NON_LINKABLE_WIDGET: return createNonLinkableWidget();
      case SitemapPackage.LINKABLE_WIDGET: return createLinkableWidget();
      case SitemapPackage.FRAME: return createFrame();
      case SitemapPackage.TEXT: return createText();
      case SitemapPackage.GROUP: return createGroup();
      case SitemapPackage.IMAGE: return createImage();
      case SitemapPackage.VIDEO: return createVideo();
      case SitemapPackage.CHART: return createChart();
      case SitemapPackage.WEBVIEW: return createWebview();
      case SitemapPackage.MAPVIEW: return createMapview();
      case SitemapPackage.SWITCH: return createSwitch();
      case SitemapPackage.SLIDER: return createSlider();
      case SitemapPackage.SELECTION: return createSelection();
      case SitemapPackage.LIST: return createList();
      case SitemapPackage.SETPOINT: return createSetpoint();
      case SitemapPackage.COLORPICKER: return createColorpicker();
      case SitemapPackage.MAPPING: return createMapping();
      case SitemapPackage.VISIBILITY_RULE: return createVisibilityRule();
      case SitemapPackage.COLOR_ARRAY: return createColorArray();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SitemapModel createSitemapModel()
  {
    SitemapModelImpl sitemapModel = new SitemapModelImpl();
    return sitemapModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Sitemap createSitemap()
  {
    SitemapImpl sitemap = new SitemapImpl();
    return sitemap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Widget createWidget()
  {
    WidgetImpl widget = new WidgetImpl();
    return widget;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NonLinkableWidget createNonLinkableWidget()
  {
    NonLinkableWidgetImpl nonLinkableWidget = new NonLinkableWidgetImpl();
    return nonLinkableWidget;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LinkableWidget createLinkableWidget()
  {
    LinkableWidgetImpl linkableWidget = new LinkableWidgetImpl();
    return linkableWidget;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Frame createFrame()
  {
    FrameImpl frame = new FrameImpl();
    return frame;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Text createText()
  {
    TextImpl text = new TextImpl();
    return text;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Group createGroup()
  {
    GroupImpl group = new GroupImpl();
    return group;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Image createImage()
  {
    ImageImpl image = new ImageImpl();
    return image;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Video createVideo()
  {
    VideoImpl video = new VideoImpl();
    return video;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Chart createChart()
  {
    ChartImpl chart = new ChartImpl();
    return chart;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Webview createWebview()
  {
    WebviewImpl webview = new WebviewImpl();
    return webview;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mapview createMapview()
  {
    MapviewImpl mapview = new MapviewImpl();
    return mapview;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Switch createSwitch()
  {
    SwitchImpl switch_ = new SwitchImpl();
    return switch_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Slider createSlider()
  {
    SliderImpl slider = new SliderImpl();
    return slider;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Selection createSelection()
  {
    SelectionImpl selection = new SelectionImpl();
    return selection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List createList()
  {
    ListImpl list = new ListImpl();
    return list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Setpoint createSetpoint()
  {
    SetpointImpl setpoint = new SetpointImpl();
    return setpoint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Colorpicker createColorpicker()
  {
    ColorpickerImpl colorpicker = new ColorpickerImpl();
    return colorpicker;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mapping createMapping()
  {
    MappingImpl mapping = new MappingImpl();
    return mapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VisibilityRule createVisibilityRule()
  {
    VisibilityRuleImpl visibilityRule = new VisibilityRuleImpl();
    return visibilityRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColorArray createColorArray()
  {
    ColorArrayImpl colorArray = new ColorArrayImpl();
    return colorArray;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SitemapPackage getSitemapPackage()
  {
    return (SitemapPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static SitemapPackage getPackage()
  {
    return SitemapPackage.eINSTANCE;
  }

} //SitemapFactoryImpl