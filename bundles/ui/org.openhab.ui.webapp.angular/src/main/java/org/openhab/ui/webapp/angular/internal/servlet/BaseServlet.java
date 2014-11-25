package org.openhab.ui.webapp.angular.internal.servlet;

import java.util.Hashtable;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.openhab.core.items.ItemRegistry;
import org.openhab.io.net.http.SecureHttpContext;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseServlet implements Servlet {

    protected static final String WEBAPP_ALIAS = "/angular";
    private static final Logger logger = LoggerFactory.getLogger(BaseServlet.class);

    protected HttpService httpService;
    protected ItemRegistry itemRegistry;

    private ServletConfig config;

    public void setItemRegistry(ItemRegistry itemRegistry) {
	this.itemRegistry = itemRegistry;
    }

    public void unsetItemRegistry(ItemRegistry itemRegistry) {
	this.itemRegistry = null;
    }

    public void setHttpService(HttpService httpService) {
	this.httpService = httpService;
    }

    public void unsetHttpService(HttpService httpService) {
	this.httpService = null;
    }

    protected void activate() {
	try {
	    String mapping = WEBAPP_ALIAS + "/" + getServletName();
	    logger.debug("Starting up " + this.getClass().getSimpleName() + " at " + mapping);

	    Hashtable<String, String> props = new Hashtable<String, String>();
	    httpService.registerServlet(mapping, this, props, createHttpContext());

	} catch (NamespaceException e) {
	    logger.error("Error during servlet startup", e);
	} catch (ServletException e) {
	    logger.error("Error during servlet startup", e);
	}
    }

    protected abstract String getServletName();

    protected void deactivate() {
	httpService.unregister(WEBAPP_ALIAS + getServletName());
    }

    /**
     * Creates a {@link SecureHttpContext} which handles the security for this Servlet
     * 
     * @return a {@link SecureHttpContext}
     */
    protected HttpContext createHttpContext() {
	HttpContext defaultHttpContext = httpService.createDefaultHttpContext();
	return new SecureHttpContext(defaultHttpContext, "openHAB.org");
    }

    @Override
    public void init(final ServletConfig config) throws ServletException {
	this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
	return this.config;
    }

}