package org.openhab.binding.cul.internal.binding;

import org.openhab.core.binding.BindingConfig;
import org.openhab.core.items.Item;

public class HomeMaticBindingConfig implements BindingConfig {

    private String id;
    private String channel;
    private String parameter;
    private String name;
    private Item item;

    public HomeMaticBindingConfig(Item item, String config) {
	this.item = item;
	String[] configItems = config.split(", ");

	for (String string : configItems) {
	    String[] split = string.split("=");
	    String key = split[0];
	    String val = split[1];
	    if (key.equals("id")) {
		this.id = val;
	    } else if (key.equals("channel")) {
		this.channel = val;
	    } else if (key.equals("parameter")) {
		this.parameter = val;
	    } else if (key.equals("name")) {
		this.name = val;
	    }
	}

    }

    public String getAddress() {
	return this.name + ":" + parameter;
    }

    public String getId() {
	return this.id;
    }

    public Item getItem() {
	return this.item;
    }

    public String getParameter() {
	return this.parameter;
    }

    public boolean isWriteable() {
	return true;
    }

    public static HomeMaticBindingConfig getBindingConfigFromString(Item item2, String bindingConfig) {
	return new HomeMaticBindingConfig(item2, bindingConfig);
    }

}
