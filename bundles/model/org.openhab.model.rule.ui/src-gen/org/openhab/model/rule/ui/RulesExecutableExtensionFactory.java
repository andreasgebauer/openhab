/*
 * generated by Xtext
 */
package org.openhab.model.rule.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import org.openhab.model.rule.ui.internal.RulesActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class RulesExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return RulesActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return RulesActivator.getInstance().getInjector(RulesActivator.ORG_OPENHAB_MODEL_RULE_RULES);
	}
	
}
