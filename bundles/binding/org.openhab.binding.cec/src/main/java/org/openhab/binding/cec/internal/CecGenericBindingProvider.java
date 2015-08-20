package org.openhab.binding.cec.internal;

import org.openhab.core.binding.BindingConfig;
import org.openhab.model.item.binding.BindingConfigParseException;
import org.openhab.core.items.Item;
import org.openhab.binding.cec.CecBindingProvider;
import org.openhab.model.item.binding.AbstractGenericBindingProvider;

public class CecGenericBindingProvider extends AbstractGenericBindingProvider implements CecBindingProvider
{
	public String getBindingType() {
		return "cec";
	}

	public void validateItemType(final Item item, final String bindingConfig) throws BindingConfigParseException {
	}

	public void processBindingConfiguration(final String context, final Item item, final String bindingConfig) throws BindingConfigParseException {
		super.processBindingConfiguration(context, item, bindingConfig);
		final CecBindingConfig cecBindingConfig = new CecBindingConfig();
		cecBindingConfig.item = item.getName();
		final String[] configTokens = bindingConfig.trim().split(":");
		String[] array;
		for (int length = (array = configTokens).length, i = 0; i < length; ++i) {
			final String configString = array[i];
			final String[] keyValue = configString.split("=");
			final String key = keyValue[0];
			final String value = keyValue[1];
			if (key.equals("device")) {
				cecBindingConfig.address = value;
			}
			else if (key.equals("property")) {
				cecBindingConfig.property = value;
			}
		}
		this.addBindingConfig(item, (BindingConfig) cecBindingConfig);
	}

	public CecBindingConfig getBindingFor(final String item) {
		return (CecBindingConfig) this.bindingConfigs.get(item);
	}
}
