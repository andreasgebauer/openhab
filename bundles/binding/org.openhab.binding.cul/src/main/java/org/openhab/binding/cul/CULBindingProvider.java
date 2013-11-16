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
package org.openhab.binding.cul;

import java.util.Set;

import org.openhab.binding.cul.internal.binding.AbstractCulBindingConfig;
import org.openhab.binding.cul.internal.binding.FHTBindingConfig;
import org.openhab.core.binding.BindingProvider;

/**
 * Provides the configured bindings for the CUL binding
 * 
 * @author Till Klocke
 * @since 1.2.0
 */
public interface CULBindingProvider extends BindingProvider {

	/**
	 * Retrieve a binding for the given item name
	 * 
	 * @param itemName
	 * @return {@link AbstractCulBindingConfig} or null if no binding for this
	 *         item can be found
	 */
	public AbstractCulBindingConfig getBindingConfigForItem(String itemName);

	/**
	 * Retrieve a read only binding (such as a window contact, measured
	 * temperature) for the given device address
	 * 
	 * @param address
	 * @return {@link AbstractCulBindingConfig} or null if no binding can be
	 *         found
	 */
	public AbstractCulBindingConfig getReadOnlyBindingConfigForAddress(
			String address);

	/**
	 * Retrieve a writable binding config for the given address (such as a
	 * switch or a desired tempreature)
	 * 
	 * @param address
	 * @return {@link AbstractCulBindingConfig} or null if no binding can be
	 *         found
	 */
	public AbstractCulBindingConfig getWriteableBindingConfigForAddress(
			String address);
	
	public Set<FHTBindingConfig> getFHT80bBindings();

}
