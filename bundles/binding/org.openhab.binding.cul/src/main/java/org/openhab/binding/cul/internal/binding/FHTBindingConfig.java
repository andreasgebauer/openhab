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
package org.openhab.binding.cul.internal.binding;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.openhab.binding.cul.FHTMode;
import org.openhab.core.items.Item;
import org.openhab.core.library.items.ContactItem;
import org.openhab.core.library.items.NumberItem;
import org.openhab.core.library.items.StringItem;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.StringType;
import org.openhab.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tobiaswegner.communication.cul4java.CULInterface;
import de.tobiaswegner.communication.cul4java.impl.FHTHandler;

public class FHTBindingConfig extends AbstractCulBindingConfig {

	private final static Logger log = LoggerFactory
			.getLogger(FHTBindingConfig.class);

	private final static Set<Class<? extends Item>> allowedItemTypes;
	private final static Set<Class<? extends Command>> allowedCommands;
	static {
		Set<Class<? extends Item>> tempItemSet = new HashSet<Class<? extends Item>>(
				3);
		tempItemSet.add(NumberItem.class);
		tempItemSet.add(ContactItem.class);
		tempItemSet.add(StringItem.class);
		allowedItemTypes = Collections.unmodifiableSet(tempItemSet);

		Set<Class<? extends Command>> tempCommandSet = new HashSet<Class<? extends Command>>(
				2);
		tempCommandSet.add(DecimalType.class);
		tempCommandSet.add(StringType.class);
		allowedCommands = Collections.unmodifiableSet(tempCommandSet);
	}

	private boolean isWriteable;

	public FHTBindingConfig(Item item, String houseCode) {
		super(item, houseCode);
	}

	@Override
	public Set<Class<? extends Item>> getAllowedItemTypes() {
		return allowedItemTypes;
	}

	public String getHouseCode() {
		return houseCode;
	}

	@Override
	public String getAddress() {
		return houseCode;
	}

	@Override
	public boolean isCommandAllowed(Command command) {
		if (isWriteable) {
			return allowedCommands.contains(command.getClass());
		}
		return false;
	}

	@Override
	public void executeCommand(CULInterface cul, Command command) {
		if (isCommandAllowed(command)) {
			FHTHandler handler = cul.getHandlerForType('T');
			if (command instanceof DecimalType) {
				double desiredTemperature = ((DecimalType) command)
						.doubleValue();
				handler.setDesiredTemperature(getAddress(), desiredTemperature);
			} else if (command instanceof StringType) {
				StringType type = (StringType) command;
				log.debug("Trying to FHT mode for string " + type.format("%s"));
				FHTMode mode = FHTMode.valueOf(type.format("%s"));
				if (mode == null) {
					log.warn("The string " + type.format("%S")
							+ " doesn't seem to be a avlid FHT mode");
				}
				handler.setFHTMode(getAddress(), mode);

			}
		}

	}

	public void updateTime(CULInterface cul, Date currentDate) {
		FHTHandler handler = cul.getHandlerForType('T');
		handler.setDateTime(getAddress(), currentDate);
	}

	@Override
	public boolean isWriteable() {
		return isWriteable;
	}

	public void setWriteable(boolean writeable) {
		isWriteable = writeable;
	}

}
