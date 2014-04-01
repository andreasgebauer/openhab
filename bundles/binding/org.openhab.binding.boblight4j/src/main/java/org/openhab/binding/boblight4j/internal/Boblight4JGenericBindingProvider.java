package org.openhab.binding.boblight4j.internal;

import java.util.HashMap;
import java.util.Map;

import org.openhab.binding.boblight4j.Boblight4JBindingProvider;
import org.openhab.binding.boblight4j.internal.binding.Boblight4JBindingConfig;
import org.openhab.core.binding.BindingConfig;
import org.openhab.core.items.Item;
import org.openhab.model.item.binding.AbstractGenericBindingProvider;
import org.openhab.model.item.binding.BindingConfigParseException;

public class Boblight4JGenericBindingProvider extends AbstractGenericBindingProvider implements Boblight4JBindingProvider {

    private Map<String, Boblight4JBindingConfig> configs = new HashMap<String, Boblight4JBindingConfig>();

    @Override
    public String getBindingType() {
	return "boblight4j";
    }

    @Override
    public void validateItemType(Item item, String bindingConfig) throws BindingConfigParseException {

    }

    @Override
    public void processBindingConfiguration(String context, Item item, String bindingConfig) throws BindingConfigParseException {
	super.processBindingConfiguration(context, item, bindingConfig);
	Boblight4JBindingConfig config = Boblight4JBindingConfig.getBindingConfigFromString(item, bindingConfig);
	if (config != null) {
	    addBindingConfig(item, config);
	}
    }

    @Override
    protected void addBindingConfig(Item item, BindingConfig config) {
	super.addBindingConfig(item, config);

	Boblight4JBindingConfig bindingConfig = (Boblight4JBindingConfig) config;
	this.configs.put(item.getName(), bindingConfig);
    }

    protected Boblight4JBindingConfig getConfig(String itemName){
	return this.configs.get(itemName);
    };
}
