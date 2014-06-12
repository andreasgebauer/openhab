package org.openhab.binding.boblight4j.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Boblight4JBundleActivator implements BundleActivator {

    private static Logger logger = LoggerFactory.getLogger(Boblight4JBundleActivator.class);

    private static BundleContext context;

    @Override
    public void start(BundleContext ctx) throws Exception {
	context = ctx;
	logger.info("Boblight4JBundle started");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	context = null;
	logger.info("Boblight4JBundle stopped");
    }

}
