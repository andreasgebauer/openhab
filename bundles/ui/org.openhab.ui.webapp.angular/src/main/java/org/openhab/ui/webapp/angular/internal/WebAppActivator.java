package org.openhab.ui.webapp.angular.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class WebAppActivator implements BundleActivator {

    private static final Logger LOG = LoggerFactory.getLogger(WebAppActivator.class);
    private static BundleContext context;

    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start(BundleContext bc) throws Exception {
	context = bc;
	LOG.info("starting AngularJS Webapp");
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop(BundleContext bc) throws Exception {
	context = null;
	LOG.info("stopping AngularJS Webapp");
    }

    public static BundleContext getContext() {
	return context;
    }
}
