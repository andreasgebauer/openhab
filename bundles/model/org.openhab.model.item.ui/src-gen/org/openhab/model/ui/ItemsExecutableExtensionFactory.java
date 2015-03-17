/*
 * generated by Xtext
 */
package org.openhab.model.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import org.openhab.model.ui.internal.ItemsActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class ItemsExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return ItemsActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return ItemsActivator.getInstance().getInjector(ItemsActivator.ORG_OPENHAB_MODEL_ITEMS);
	}
	
}
