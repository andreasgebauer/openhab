package org.openhab.binding.boblight4j.internal;

import org.openhab.binding.boblight4j.Boblight4JBindingProvider;
import org.openhab.binding.boblight4j.internal.binding.Boblight4JBindingConfig;
import org.openhab.core.items.Item;
import org.openhab.model.item.binding.AbstractGenericBindingProvider;
import org.openhab.model.item.binding.BindingConfigParseException;

public class Boblight4JGenericBindingProvider extends AbstractGenericBindingProvider implements Boblight4JBindingProvider {

    @Override
    public String getBindingType() {
	return "boblight4j";
    }

    @Override
    public void validateItemType(Item item, String bindingConfig) throws BindingConfigParseException {

    }

    @Override
    public void processBindingConfiguration(String context, Item item, String bindingConfig) throws BindingConfigParseException {
	Boblight4JBindingConfig config = new Boblight4JBindingConfig();
	BindingConfigParser parser = new BindingConfigParser();
	parser.parse(bindingConfig, config);
	addBindingConfig(item, config);
    }
}
