package org.openhab.binding.boblight4j.internal.binding;

import org.openhab.core.binding.BindingConfig;
import org.openhab.core.items.Item;

public class Boblight4JBindingConfig implements BindingConfig {

    private final Item item;
    private final String bindingConfig;

    public Boblight4JBindingConfig(Item item, String bindingConfig) {
	this.item = item;
	this.bindingConfig = bindingConfig;
    }

    public static Boblight4JBindingConfig getBindingConfigFromString(Item item, String bindingConfig) {
	return new Boblight4JBindingConfig(item, bindingConfig);
    }

    public String getBindingConfig() {
	return bindingConfig;
    }

}
