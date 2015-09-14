package org.openhab.ui.webapp.angular.internal.servlet;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import org.eclipse.emf.ecore.EObject;
import org.openhab.core.items.GenericItem;
import org.openhab.core.items.GroupItem;
import org.openhab.core.items.Item;
import org.openhab.core.items.ItemNotFoundException;
import org.openhab.core.library.items.RollershutterItem;
import org.openhab.model.sitemap.Chart;
import org.openhab.model.sitemap.Frame;
import org.openhab.model.sitemap.LinkableWidget;
import org.openhab.model.sitemap.Mapping;
import org.openhab.model.sitemap.Setpoint;
import org.openhab.model.sitemap.Sitemap;
import org.openhab.model.sitemap.SitemapProvider;
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
	/* RegEx to extract and parse a function String <code>'\[(.*?)\((.*)\):(.*)\]'</code> */
	protected static final Pattern EXTRACT_TRANSFORMFUNCTION_PATTERN = Pattern.compile(".*?\\[(.*?)\\((.*)\\):(.*)\\]");
	protected static final Pattern EXTRACT_LABEL_PATTERN = Pattern.compile("(.*?)\\s*\\[(.*?)\\]");

	protected SitemapProvider sitemapProvider;
	private ItemUIRegistry itemUIRegistry;

	public void setItemUIRegistry(ItemUIRegistry itemUIRegistry) {
		this.itemUIRegistry = itemUIRegistry;
	}

	public void unsetItemUIRegistry(ItemUIRegistry itemUIRegistry) {
		this.itemUIRegistry = null;
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
				sitemapBuilder.add("name", sitemapName);
				sitemapBuilder.add("children", render(sitemap.getChildren()));
				writer.writeObject(sitemapBuilder.build());

			} else if (!widgetId.equals("Colorpicker")) {
				// we are on some subpage, so we have to render the children of the widget that has been selected
				Widget w = itemUIRegistry.getWidget(sitemap, widgetId);
				if (w != null) {
					if (!(w instanceof LinkableWidget)) {
						throw new RenderException("Widget '" + w + "' can not have any content");
					}
					EList<Widget> children = itemUIRegistry.getChildren((LinkableWidget) w);
					String label = itemUIRegistry.getLabel(w);
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

				return itemUIRegistry.getWidgetId(parent);

				// String widgetId = getParentWidgetId((LinkableWidget) widget, w);
				// if (widgetId != null) {
				// return widgetId;
				// }
			}
		}

		return null;
	}

	private JsonArrayBuilder render(EList<Widget> children) throws ItemNotFoundException {
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		for (Iterator<Widget> iterator = children.iterator(); iterator.hasNext();) {
			Widget widget = iterator.next();
			arrayBuilder.add(render(widget));
		}
		return arrayBuilder;
	}

	private JsonObjectBuilder render(Widget widget) throws ItemNotFoundException {

		String simpleName = widget.getClass().getSimpleName();
		
		String type = simpleName.substring(0, simpleName.length() - "Impl".length()).toLowerCase();

		if (widget instanceof Text && !((Text) widget).getChildren().isEmpty()) {
			// render a link here
			type += "_link";
		}

		String id = itemUIRegistry.getWidgetId(widget);
		String labelPattern = widget.getLabel();
		if (labelPattern == null) {
			labelPattern = itemUIRegistry.getLabel(widget.getItem());
		}

		String label = itemUIRegistry.getLabel(widget);
		if (label != null) {
			Matcher matcher = EXTRACT_LABEL_PATTERN.matcher(label);
			if (matcher.matches()) {
				label = matcher.group(1);
			}
		}

		String itemName = widget.getItem();
		String icon = null; // itemUIRegistry.getIcon(widget);
		String labelColor = itemUIRegistry.getLabelColor(widget);
		String valueColor = itemUIRegistry.getValueColor(widget);

		Item item = null;

		try {
			item = itemRegistry.getItem(itemName);
		} catch (ItemNotFoundException e) {
			// do nothing
		}

		JSONDataBuilder dataBuilder = JSONDataBuilder.createStateMessage(id, label, icon);

		dataBuilder.add("type", type);

		if (item != null) {
			// String itemType = item.getClass().getSimpleName();
			// widgetBuilder.add("type", itemType.substring(0, itemType.length() - 4).toLowerCase());
			dataBuilder.addValue(new Date(), item.getState());
		}
		if (labelPattern != null) {
			dataBuilder.add("labelPattern", labelPattern);
		}
		if (null != labelColor) {
			dataBuilder.add("labelColor", labelColor);
		}
		if (null != valueColor) {
			dataBuilder.add("valueColor", valueColor);
		}
		if (itemName != null) {
			dataBuilder.add("item", itemName);
		}
		
		if (labelPattern != null) {
			Matcher matcher = EXTRACT_TRANSFORMFUNCTION_PATTERN.matcher(labelPattern);
			if (matcher.matches()) {
				String transformType = matcher.group(1);
				String pattern = matcher.group(2);
				String valuePattern = matcher.group(3);

				JsonObjectBuilder transform = Json.createObjectBuilder();
				transform.add("type", transformType);
				transform.add("param", pattern);
				transform.add("valuePattern", valuePattern);

				dataBuilder.add("transform", transform.build());
			}
		}

		if (widget instanceof LinkableWidget) {
			LinkableWidget link = (LinkableWidget) widget;
			if (!(link instanceof Text) && !link.getChildren().isEmpty()) {
				dataBuilder.add("children", render(link.getChildren()));
			}
		}

		//
		if (widget instanceof Chart) {
			String period = ((Chart) widget).getPeriod();
			dataBuilder.add("period", periodToTimespan(period));

			JsonArrayBuilder arrBldr = Json.createArrayBuilder();

			if (item instanceof GroupItem) {
				List<Item> allMembers = ((GroupItem) item).getAllMembers();
				for (Item chartItem : allMembers) {
					GenericItem genItem = (GenericItem) chartItem;
					arrBldr.add(genItem.getName());
				}
			} else {
				arrBldr.add(item.getName());
			}

			dataBuilder.add("items", arrBldr);

		} else if (widget instanceof Setpoint) {
			Setpoint setPoint = ((Setpoint) widget);
			BigDecimal maxValue = setPoint.getMaxValue();
			BigDecimal minValue = setPoint.getMinValue();
			BigDecimal step = setPoint.getStep();

			dataBuilder.add("min", minValue);
			dataBuilder.add("max", maxValue);
			dataBuilder.add("step", step);

		} else if (widget instanceof Switch) {
			Switch sw = (Switch) widget;
			EList<Mapping> mappings = sw.getMappings();

			String subType;
			if (mappings.size() == 0) {
				if (widget instanceof RollershutterItem) {
					subType = "rollerblind";
				} else if (widget instanceof GroupItem
						&& ((GroupItem) widget).getBaseItem() instanceof RollershutterItem) {
					subType = "rollerblind";
				} else {
					subType = "switch";
				}
			} else {
				subType = "buttons";
			}

			dataBuilder.add("type", subType);

			if (!mappings.isEmpty()) {
				JsonArrayBuilder arr = Json.createArrayBuilder();
				for (Mapping mapping : mappings) {
					JsonObjectBuilder mappingJson = Json.createObjectBuilder();
					mappingJson.add("label", mapping.getLabel());
					mappingJson.add("cmd", mapping.getCmd());
					arr.add(mappingJson);
				}
				dataBuilder.add("mappings", arr);
			}
		}

		return dataBuilder.builder();
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
