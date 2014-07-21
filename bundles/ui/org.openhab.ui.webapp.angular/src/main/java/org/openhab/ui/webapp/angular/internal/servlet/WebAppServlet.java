package org.openhab.ui.webapp.angular.internal.servlet;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.openhab.core.items.GenericItem;
import org.openhab.core.items.GroupItem;
import org.openhab.core.items.Item;
import org.openhab.core.items.ItemNotFoundException;
import org.openhab.core.library.items.RollershutterItem;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.types.State;
import org.openhab.model.sitemap.AbstractChart;
import org.openhab.model.sitemap.Chart;
import org.openhab.model.sitemap.Frame;
import org.openhab.model.sitemap.LinkableWidget;
import org.openhab.model.sitemap.Mapping;
import org.openhab.model.sitemap.Setpoint;
import org.openhab.model.sitemap.Sitemap;
import org.openhab.model.sitemap.SitemapProvider;
import org.openhab.model.sitemap.SmartChart;
import org.openhab.model.sitemap.Switch;
import org.openhab.model.sitemap.Text;
import org.openhab.model.sitemap.Widget;
import org.openhab.ui.items.ItemUIRegistry;
import org.openhab.ui.webapp.angular.internal.WebAppActivator;
import org.openhab.ui.webapp.angular.internal.render.RenderException;
import org.osgi.framework.BundleContext;
import org.osgi.service.http.NamespaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebAppServlet extends BaseServlet {

    private static final Logger LOG = LoggerFactory.getLogger(WebAppServlet.class);

    protected SitemapProvider sitemapProvider;
    private ItemUIRegistry itemUIRegistry;

    public void setItemUIRegistry(ItemUIRegistry itemUIRegistry) {
	this.itemUIRegistry = itemUIRegistry;
    }

    public void unsetItemUIRegistry(ItemUIRegistry itemUIRegistry) {
	this.itemUIRegistry = null;
    }

    public ItemUIRegistry getItemUIRegistry() {
	return itemUIRegistry;
    }

    public void setSitemapProvider(final SitemapProvider sitemapProvider) {
	this.sitemapProvider = sitemapProvider;
    }

    public void unsetSitemapProvider(final SitemapProvider sitemapProvider) {
	this.sitemapProvider = null;
    }

    @Override
    protected void activate() {
	super.activate();
	try {
	    httpService.registerResources(WEBAPP_ALIAS, "web", null);
	} catch (NamespaceException e) {
	    LOG.error("", e);
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	LOG.debug("Servlet request received!");

	// read request parameters
	String sitemapName = req.getParameter("sitemap");
	String widgetId = req.getParameter("w");

	// if there are no parameters, display the "default" sitemap
	if (sitemapName == null)
	    sitemapName = "default";

	JsonWriter writer = Json.createWriter(res.getOutputStream());

	Sitemap sitemap = sitemapProvider.getSitemap(sitemapName);
	try {
	    if (sitemap == null) {
		throw new RenderException("Sitemap '" + sitemapName + "' could not be found");
	    }
	    LOG.debug("reading sitemap {}", sitemap.getName());
	    if (widgetId == null || widgetId.isEmpty() || widgetId.equals("Home")) {
		// we are at the homepage, so we render the children of the sitemap root node
		String label = sitemap.getLabel() != null ? sitemap.getLabel() : sitemapName;

		JsonObjectBuilder sitemapBuilder = Json.createObjectBuilder();
		// sitemapBuilder.add("id", label);
		sitemapBuilder.add("label", label);
		sitemapBuilder.add("children", render(sitemap.getChildren()));
		writer.writeObject(sitemapBuilder.build());

	    } else if (!widgetId.equals("Colorpicker")) {
		// we are on some subpage, so we have to render the children of the widget that has been selected
		Widget w = getItemUIRegistry().getWidget(sitemap, widgetId);
		if (w != null) {
		    if (!(w instanceof LinkableWidget)) {
			throw new RenderException("Widget '" + w + "' can not have any content");
		    }
		    EList<Widget> children = getItemUIRegistry().getChildren((LinkableWidget) w);
		    String label = getItemUIRegistry().getLabel(w);
		    if (label == null)
			label = "undefined";

		    JsonObjectBuilder sitemapBuilder = Json.createObjectBuilder();
		    String id = itemUIRegistry.getWidgetId(w);
		    String parentWidgetId = getParentWidgetId(sitemap, w);
		    sitemapBuilder.add("id", id);
		    sitemapBuilder.add("parentId", parentWidgetId);
		    sitemapBuilder.add("label", label);
		    sitemapBuilder.add("children", render(children));
		    writer.writeObject(sitemapBuilder.build());
		}
	    }
	} catch (Exception e) {
	    throw new ServletException(e.getMessage(), e);
	}

	writer.close();
	res.setContentType("application/xml;charset=UTF-8");
    }

    private String getParentWidgetId(Sitemap sitemap, Widget w) {
	EList<Widget> children = sitemap.getChildren();

	for (Widget widget : children) {
	    if (widget.equals(w)) {
		return "";
	    } else if (widget instanceof LinkableWidget) {

		Widget parent = null;
		Widget current = w;
		while (parent == null) {
		    EObject eContainer = current.eContainer();
		    if (eContainer instanceof Widget) {
			if (eContainer instanceof Frame) {
			    current = (Widget) eContainer;
			} else {
			    parent = (Widget) eContainer;
			}
		    } else if (eContainer instanceof Sitemap) {
			return "";
		    }
		}

		return getItemUIRegistry().getWidgetId(parent);

		// String widgetId = getParentWidgetId((LinkableWidget) widget, w);
		// if (widgetId != null) {
		// return widgetId;
		// }
	    }
	}

	return null;
    }

    private JsonArrayBuilder render(EList<Widget> children) {
	JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
	for (Iterator<Widget> iterator = children.iterator(); iterator.hasNext();) {
	    Widget widget = iterator.next();
	    arrayBuilder.add(render(widget));
	}
	return arrayBuilder;
    }

    private JsonObjectBuilder render(Widget widget) {

	String simpleName = widget.getClass().getSimpleName();
	String type = simpleName.substring(0, simpleName.length() - "Impl".length()).toLowerCase();

	if (widget instanceof Text && !((Text) widget).getChildren().isEmpty()) {
	    // render a link here
	    type += "_link";
	}

	String id = itemUIRegistry.getWidgetId(widget);
	String label = itemUIRegistry.getLabel(widget);
	JsonValue valueJson = null;
	if (label.indexOf('[') != -1 && label.indexOf(']') != -1) {
	    final String value = label.substring(label.indexOf('[') + 1, label.indexOf(']'));
	    label = label.substring(0, label.indexOf('[') - 1);
	    valueJson = new JsonString() {

		@Override
		public ValueType getValueType() {
		    return ValueType.STRING;
		}

		@Override
		public String getString() {
		    return value;
		}

		@Override
		public CharSequence getChars() {
		    return value;
		}

	    };
	} else {
	    State state = itemUIRegistry.getState(widget);
	    if (state instanceof OnOffType) {
		valueJson = ((OnOffType) state) == OnOffType.ON ? JsonValue.TRUE : JsonValue.FALSE;
	    }
	}
	String icon = itemUIRegistry.getIcon(widget);
	String labelColor = itemUIRegistry.getLabelColor(widget);
	String valueColor = itemUIRegistry.getValueColor(widget);
	String itemName = widget.getItem();

	JsonObjectBuilder widgetBuilder = Json.createObjectBuilder();
	widgetBuilder.add("id", id);
	widgetBuilder.add("type", type);
	if (label != null) {
	    widgetBuilder.add("label", label);
	}
	if (valueJson != null) {
	    widgetBuilder.add("value", valueJson);
	}
	if (icon != null) {
	    widgetBuilder.add("icon", icon);
	}
	if (null != labelColor) {
	    widgetBuilder.add("labelColor", labelColor);
	}
	if (null != valueColor) {
	    widgetBuilder.add("valueColor", valueColor);
	}
	if (itemName != null) {
	    widgetBuilder.add("item", itemName);
	}

	if (widget instanceof LinkableWidget) {
	    LinkableWidget link = (LinkableWidget) widget;
	    if (!(link instanceof Text) && !link.getChildren().isEmpty()) {
		widgetBuilder.add("children", render(link.getChildren()));
	    }
	}

	//
	if (widget instanceof AbstractChart) {
	    try {
		String period = ((AbstractChart) widget).getPeriod();
		widgetBuilder.add("period", periodToTimespan(period));

		JsonArrayBuilder arrBldr = Json.createArrayBuilder();

		if (widget instanceof Chart || widget instanceof SmartChart) {
		    Item item = this.itemRegistry.getItem(itemName);

		    if (item instanceof GroupItem) {
			for (Item chartItem : ((GroupItem) item).getAllMembers()) {
			    GenericItem genItem = (GenericItem) chartItem;
			    arrBldr.add(genItem.getName());
			}
		    } else {
			arrBldr.add(item.getName());
		    }
		}  
		if (widget instanceof SmartChart) {
		    EList<String> items = ((SmartChart) widget).getItems();
		    for (String string : items) {
			Item item = itemRegistry.getItem(string);

			if (item instanceof GroupItem) {
			    for (Item chartItem : ((GroupItem) item).getAllMembers()) {
				GenericItem genItem = (GenericItem) chartItem;
				arrBldr.add(genItem.getName());
			    }
			} else {
			    arrBldr.add(item.getName());
			}
		    }
		}

		widgetBuilder.add("items", arrBldr);

	    } catch (ItemNotFoundException e) {
		// ignore

	    }
	} else if (widget instanceof Setpoint) {
	    Setpoint setPoint = ((Setpoint) widget);
	    BigDecimal maxValue = setPoint.getMaxValue();
	    BigDecimal minValue = setPoint.getMinValue();
	    BigDecimal step = setPoint.getStep();

	    widgetBuilder.add("max", maxValue);

	} else if (widget instanceof Switch) {
	    Switch sw = (Switch) widget;
	    EList<Mapping> mappings = sw.getMappings();

	    String subType;
	    if (mappings.size() == 0) {
		if (widget instanceof RollershutterItem) {
		    subType = "rollerblind";
		} else if (widget instanceof GroupItem && ((GroupItem) widget).getBaseItem() instanceof RollershutterItem) {
		    subType = "rollerblind";
		} else {
		    subType = "switch";
		}
	    } else {
		subType = "buttons";
	    }

	    widgetBuilder.add("type", subType);

	    if (!mappings.isEmpty()) {
		JsonArrayBuilder arr = Json.createArrayBuilder();
		for (Mapping mapping : mappings) {
		    JsonObjectBuilder mappingJson = Json.createObjectBuilder();
		    mappingJson.add("label", mapping.getLabel());
		    mappingJson.add("cmd", mapping.getCmd());
		    arr.add(mappingJson);
		}
		widgetBuilder.add("mappings", arr);
	    }
	}

	return widgetBuilder;
    }

    private Long periodToTimespan(String period) {

	Pattern pattern = Pattern.compile("(\\d*)([A-Za-z])");
	Matcher matcher = pattern.matcher(period);

	if (matcher.matches()) {
	    String amountStr = matcher.group(1);
	    Long amount = amountStr.length() > 0 ? Long.parseLong(amountStr) : 1;
	    char unit = matcher.group(2).charAt(0);

	    switch (unit) {
	    case 'M':
		return (long) (1000L * 60L * 60L * 24L * 30L * amount);
	    case 'W':
		return (long) (1000L * 60L * 60L * 24L * 7L * amount);
	    case 'D':
		return (long) (1000L * 60L * 60L * 24L * amount);
	    case 'h':
		return (long) (1000L * 60L * 60L * amount);
	    case 'm':
		return (long) (1000L * 60L * amount);
	    }
	}

	return 0L;
    }

    @Override
    public String getServletInfo() {
	return "Web Servlet";
    }

    @Override
    public void init(final ServletConfig config) throws ServletException {
	super.init(config);

	try {
	    Class<?> forName = Class.forName("org.openhab.mock.MockBundleContext");
	    Constructor<?> ctor = forName.getConstructor(ServletConfig.class);
	    ctor.setAccessible(true);
	    Object newInstance = ctor.newInstance(config);
	    new WebAppActivator().start((BundleContext) newInstance);
	} catch (Exception e) {
	    // nothing to do
	}
    }

    @Override
    public void destroy() {
    }

    @Override
    protected String getServletName() {
	return "sitemap";
    }

}
