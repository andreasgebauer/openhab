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

import java.util.Set;

import org.openhab.core.binding.BindingConfig;
import org.openhab.core.items.Item;
import org.openhab.core.types.Command;
import org.openhab.model.item.binding.BindingConfigParseException;

import de.tobiaswegner.communication.cul4java.CULInterface;

public abstract class AbstractCulBindingConfig implements BindingConfig {

    protected String houseCode;
    protected Item item;

    public AbstractCulBindingConfig(Item item, String houseCode) {
	this.houseCode = houseCode;
	this.item = item;
    }

    public String getHouseCode() {
	return houseCode;
    }

    public void setHouseCode(String houseCode) {
	this.houseCode = houseCode;
    }

    public Item getItem() {
	return item;
    }

    public abstract String getAddress();

    public abstract Set<Class<? extends Item>> getAllowedItemTypes();

    public abstract boolean isCommandAllowed(Command command);

    public abstract boolean executeCommand(CULInterface cul, Command command);

    public abstract boolean isWriteable();

    /**
     * Config in in the style of <T|F|E><R|W>HHHH(SS)
     * 
     * @param config
     * @return
     * @throws BindingConfigParseException
     */
    @SuppressWarnings("unchecked")
    public static <B extends AbstractCulBindingConfig> B getBindingConfigFromString(
	    Item item, String config) throws BindingConfigParseException {
	char type = config.charAt(0);
	switch (type) {
	case 'T':
	    return (B) parseFHTBindingConfig(item, config);
	case 'F':
	    return (B) parseFS20BindingConfig(item, config);
	case 'i':
	    return (B) new IntertechnoBindingConfig(item, config.substring(1));
	case 'A':
	    return (B) new HomeMaticBindingConfig(item, config.substring(1));
	default:
	    throw new BindingConfigParseException(
		    "Unknown culbinding config type " + type);
	}
    }

    private static FS20BindingConfig parseFS20BindingConfig(Item item,
	    String config) throws BindingConfigParseException {
	FS20BindingConfig fs20Config = new FS20BindingConfig(item,
		config.substring(2));
	fs20Config.setWriteable(isWriteableConfig(config));

	return fs20Config;
    }

    private static boolean isWriteableConfig(String config)
	    throws BindingConfigParseException {
	char writeableIndicator = config.charAt(1);
	switch (writeableIndicator) {
	case 'W':
	    return true;
	case 'R':
	    return false;
	default:
	    throw new BindingConfigParseException(
		    "Unknown writable indicator "
			    + writeableIndicator
			    + ". This indicator can only be R for read onyl or W for writeable");
	}
    }

    private static FHTBindingConfig parseFHTBindingConfig(Item item,
	    String config) throws BindingConfigParseException {
	FHTBindingConfig fhtConfig = new FHTBindingConfig(item,
		config.substring(2));
	if (!fhtConfig.getAllowedItemTypes().contains(item.getClass())) {
	    throw new BindingConfigParseException("The item class "
		    + item.getClass().getSimpleName()
		    + " is not allowed for the config of type "
		    + FHTBindingConfig.class.getSimpleName());
	}
	fhtConfig.setWriteable(isWriteableConfig(config));
	return fhtConfig;
    }

}
