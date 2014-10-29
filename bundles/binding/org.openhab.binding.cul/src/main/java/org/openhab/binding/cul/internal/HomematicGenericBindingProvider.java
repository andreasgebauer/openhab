/**
 * openHAB, the open Home Automation Bus.
 * Copyright (C) 2010-2013, openHAB.org <admin@openhab.org>
 *
 * See the contributors.txt file in the distribution for a
 * full listing of individual contributors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Additional permission under GNU GPL version 3 section 7
 *
 * If you modify this Program, or any covered work, by linking or
 * combining it with Eclipse (or a modified version of that library),
 * containing parts covered by the terms of the Eclipse Public License
 * (EPL), the licensors of this Program grant you additional permission
 * to convey the resulting work.
 */
package org.openhab.binding.cul.internal;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openhab.binding.cul.HomematicCULBindingProvider;
import org.openhab.binding.cul.internal.binding.HomeMaticBindingConfig;
import org.openhab.core.items.Item;
import org.openhab.model.item.binding.AbstractGenericBindingProvider;
import org.openhab.model.item.binding.BindingConfigParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is responsible for parsing the binding configuration.
 * 
 * @author Andreas Gebauer
 * @since 1.2.0
 */
public class HomematicGenericBindingProvider extends AbstractGenericBindingProvider implements HomematicCULBindingProvider {

    private final static Logger log = LoggerFactory.getLogger(HomematicGenericBindingProvider.class);

    private Map<String, HomeMaticBindingConfig> addressToConfig = new LinkedHashMap<String, HomeMaticBindingConfig>();

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBindingType() {
	return "culhm";
    }

    /**
     * @{inheritDoc
     */
    @Override
    public void validateItemType(Item item, String bindingConfig) throws BindingConfigParseException {
	HomeMaticBindingConfig.getBindingConfigFromString(item, bindingConfig);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processBindingConfiguration(String context, Item item, String bindingConfig) throws BindingConfigParseException {
	super.processBindingConfiguration(context, item, bindingConfig);
	log.debug("Parsing CUL binding config: " + bindingConfig);
	HomeMaticBindingConfig config = HomeMaticBindingConfig.getBindingConfigFromString(item, bindingConfig);
	if (config != null) {
	    super.addBindingConfig(item, config);

	    this.addressToConfig.put(config.getAddress(), config);
	}
    }

    @Override
    public HomeMaticBindingConfig getBindingConfigForItem(String itemName) {
	return (HomeMaticBindingConfig) bindingConfigs.get(itemName);
    }

    @Override
    public HomeMaticBindingConfig getBindingConfigForAddress(String itemName, String parameter) {
	return addressToConfig.get(itemName + ":" + parameter);
    }

}
