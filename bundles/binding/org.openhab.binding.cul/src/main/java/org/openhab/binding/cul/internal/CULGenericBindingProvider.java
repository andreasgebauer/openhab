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

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.openhab.binding.cul.CULBindingProvider;
import org.openhab.binding.cul.internal.binding.AbstractCulBindingConfig;
import org.openhab.binding.cul.internal.binding.FHTBindingConfig;
import org.openhab.core.items.Item;
import org.openhab.model.item.binding.AbstractGenericBindingProvider;
import org.openhab.model.item.binding.BindingConfigParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is responsible for parsing the binding configuration.
 * 
 * @author Till Klocke
 * @since 1.2.0
 */
public class CULGenericBindingProvider extends AbstractGenericBindingProvider
		implements CULBindingProvider {

	private final static Logger log = LoggerFactory
			.getLogger(CULGenericBindingProvider.class);

	/**
	 * A map from the item name to our binding config
	 */
	private HashMap<String, AbstractCulBindingConfig> itemNameToConfig = new LinkedHashMap<String, AbstractCulBindingConfig>();

	/**
	 * A map from an address to a read only binding config, so we can have a
	 * read only and a writeable binding config per device address
	 */
	private Map<String, AbstractCulBindingConfig> addressToReadOnlyConfig = new LinkedHashMap<String, AbstractCulBindingConfig>();

	private Map<String, AbstractCulBindingConfig> addressToWriteableConfig = new LinkedHashMap<String, AbstractCulBindingConfig>();

	private Set<FHTBindingConfig> fhtBindings = new HashSet<FHTBindingConfig>();

	/**
	 * {@inheritDoc}
	 */
	public String getBindingType() {
		return "cul";
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void validateItemType(Item item, String bindingConfig)
			throws BindingConfigParseException {
		AbstractCulBindingConfig
				.getBindingConfigFromString(item, bindingConfig);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processBindingConfiguration(String context, Item item,
			String bindingConfig) throws BindingConfigParseException {
		super.processBindingConfiguration(context, item, bindingConfig);
		log.debug("Parsing CUL binding config: " + bindingConfig);
		AbstractCulBindingConfig config = AbstractCulBindingConfig
				.getBindingConfigFromString(item, bindingConfig);
		if (config != null) {
			addBindingConfig(item, config);
		}
	}

	private void addBindingConfig(Item item, AbstractCulBindingConfig config) {
		itemNameToConfig.put(item.getName(), config);
		if (config.isWriteable()) {
			addressToWriteableConfig.put(config.getAddress(), config);
		} else {
			addressToReadOnlyConfig.put(config.getAddress(), config);
		}
		if (config.isWriteable() && config instanceof FHTBindingConfig
				&& config.getAddress().length() == 4) {
			// We have pretty certain a binding for a FHT80b here
			fhtBindings.add((FHTBindingConfig) config);
		}
		super.addBindingConfig(item, config);
	}

	@Override
	public AbstractCulBindingConfig getBindingConfigForItem(String itemName) {
		return itemNameToConfig.get(itemName);
	}

	@Override
	public AbstractCulBindingConfig getReadOnlyBindingConfigForAddress(
			String address) {
		return addressToReadOnlyConfig.get(address);
	}

	@Override
	public AbstractCulBindingConfig getWriteableBindingConfigForAddress(
			String address) {
		return addressToWriteableConfig.get(address);
	}

	@Override
	public Set<FHTBindingConfig> getFHT80bBindings() {
		return Collections.unmodifiableSet(fhtBindings);
	}

}
