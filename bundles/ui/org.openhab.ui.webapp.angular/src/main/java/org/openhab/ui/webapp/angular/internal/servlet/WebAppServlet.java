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
import javax.json.JsonWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.eclipse.emf.common.util.EList;
import org.openhab.core.items.GenericItem;
import org.openhab.core.items.GroupItem;
import org.openhab.core.items.Item;
import org.openhab.core.items.ItemNotFoundException;
import org.openhab.core.items.ItemRegistry;
import org.openhab.model.sitemap.Chart;
import org.openhab.model.sitemap.LinkableWidget;
import org.openhab.model.sitemap.Setpoint;
import org.openhab.model.sitemap.Sitemap;
import org.openhab.model.sitemap.SitemapProvider;
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
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
	LOG.debug("Servlet request received!");

	// read request parameters
	String sitemapName = (String) req.getParameter("sitemap");
	String widgetId = (String) req.getParameter("w");

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
		sitemapBuilder.add("id", label);
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
		    sitemapBuilder.add("id", label);
		    sitemapBuilder.add("children", render(children));
		    writer.writeObject(sitemapBuilder.build());
		}
	    }
	} catch (Exception e) {
	    throw new ServletException(e.getMessage(), e);
	} finally {
	    writer.close();
	}
	res.setContentType("application/xml;charset=UTF-8");
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
	String value = null;
	if (label.indexOf('[') != -1 && label.indexOf(']') != -1) {
	    value = label.substring(label.indexOf('[') + 1, label.indexOf(']'));
	    label = label.substring(0, label.indexOf('[') - 1);
	}
	String icon = itemUIRegistry.getIcon(widget);
	String labelColor = itemUIRegistry.getLabelColor(widget);
	String valueColor = itemUIRegistry.getValueColor(widget);
	String itemName = widget.getItem();

	JsonObjectBuilder widgetBuilder = Json.createObjectBuilder();
	widgetBuilder.add("id", id);
	widgetBuilder.add("type", type);
	widgetBuilder.add("label", label);
	if (value != null) {
	    widgetBuilder.add("value", value);
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

	if (widget instanceof LinkableWidget && !(widget instanceof Text)) {
	    LinkableWidget link = (LinkableWidget) widget;
	    if (!link.getChildren().isEmpty()) {
		widgetBuilder.add("children", render(link.getChildren()));
	    }
	} else if (widget instanceof Chart) {
	    try {
		Item item = this.itemRegistry.getItem(itemName);

		String period = ((Chart) widget).getPeriod();
		widgetBuilder.add("period", periodToTimespan(period));

		JsonArrayBuilder arrBldr = Json.createArrayBuilder();
		if (item instanceof GroupItem) {
		    for (Item chartItem : ((GroupItem) item).getAllMembers()) {
			GenericItem genItem = (GenericItem) chartItem;
			arrBldr.add(genItem.getName());
		    }
		} else {
		    arrBldr.add(item.getName());
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

	    forName = Class.forName("org.openhab.mock.MockItemUIRegistry");
	    ctor = forName.getConstructor();
	    ctor.setAccessible(true);
	    this.itemUIRegistry = (ItemUIRegistry) ctor.newInstance();

	    forName = Class.forName("org.openhab.mock.model.sitemap.SitemapProviderImpl");
	    ctor = forName.getConstructor();
	    ctor.setAccessible(true);
	    this.sitemapProvider = (SitemapProvider) ctor.newInstance();

	    forName = Class.forName("org.openhab.mock.MockItemRegistryImpl");
	    ctor = forName.getConstructor();
	    ctor.setAccessible(true);
	    this.itemRegistry = (ItemRegistry) ctor.newInstance();

	} catch (Exception e) {
	    e.printStackTrace();
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
