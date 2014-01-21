package org.openhab.binding.cul.internal.binding;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.openhab.core.items.Item;
import org.openhab.core.library.items.SwitchItem;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.types.Command;
import org.openhab.model.item.binding.BindingConfigParseException;

import de.tobiaswegner.communication.cul4java.CULInterface;

public class IntertechnoBindingConfig extends AbstractCulBindingConfig {

	private final static Set<Class<? extends Item>> allowedItems;
	private final static Set<Class<? extends Command>> allowedCommands;
	static {
		Set<Class<? extends Item>> tempItemSet = new HashSet<Class<? extends Item>>(
				1);
		tempItemSet.add(SwitchItem.class);
		allowedItems = Collections.unmodifiableSet(tempItemSet);

		Set<Class<? extends Command>> tempCommandSet = new HashSet<Class<? extends Command>>(
				1);
		tempCommandSet.add(OnOffType.class);
		allowedCommands = Collections.unmodifiableSet(tempCommandSet);
	}

	private String groupCode;

	public IntertechnoBindingConfig(Item item, String houseCode)
			throws BindingConfigParseException {
		super(item, houseCode);
		char type = houseCode.charAt(1);
		switch (type) {
		case 'C':
			parseClassicIntertechnoConfig(houseCode.substring(2));
			break;

		case 'F':
			parseFLSConfig(houseCode.substring(2));
			break;
		default:
			throw new BindingConfigParseException(
					"Unknown type of Intertechno address");
		}
	}

	private void parseClassicIntertechnoConfig(String houseCode)
			throws BindingConfigParseException {
		char groupAddress = houseCode.charAt(0);
		this.houseCode = IntertechnoAddressHelper
				.getClassicIntertechnoGroupAddress(groupAddress);
		try {
			int subAddress = Integer.parseInt(houseCode.substring(1));
			this.groupCode = IntertechnoAddressHelper
					.getClassicIntertechnoSubAddress(subAddress);
		} catch (NumberFormatException e) {
			throw new BindingConfigParseException(
					houseCode
							+ " doesn't seem to be a valid config for an Intertechno device");
		}
	}

	private void parseFLSConfig(String houseCode)
			throws BindingConfigParseException {
		if (houseCode.length() == 2) {
			this.houseCode = calculate4SettingHousecode(houseCode.substring(0,
					1));
			groupCode = calculate4SettingGroupcode(Integer.parseInt(houseCode
					.substring(1)));
		} else if (houseCode.length() == 3) {
			this.houseCode = calculate4SettingHousecode(houseCode.substring(0,
					2));
			groupCode = calculate4SettingGroupcode(Integer.parseInt(houseCode
					.substring(2)));
		} else {
			throw new BindingConfigParseException("Unparseable config "
					+ houseCode);
		}
	}

	@Override
	public String getAddress() {
		return houseCode + groupCode;
	}

	@Override
	public Set<Class<? extends Item>> getAllowedItemTypes() {
		return allowedItems;
	}

	@Override
	public boolean isCommandAllowed(Command command) {
		return allowedCommands.contains(command.getClass());
	}

	@Override
	public boolean executeCommand(CULInterface cul, Command command) throws IOException {
		if (!isCommandAllowed(command)) {
			return false;
		}
		OnOffType onOff = (OnOffType) command;
		String sendString = null;
		switch (onOff) {
		case ON:
			sendString = "is" + getAddress() + "00FF";
			break;
		case OFF:
			sendString = "is" + getAddress() + "00F0";
			break;
		}
		cul.sendRAW(sendString);
		return false;
	}

	@Override
	public boolean isWriteable() {
		return true;
	}

	private String calculate4SettingHousecode(String romanNumber)
			throws BindingConfigParseException {
		if ("I".equalsIgnoreCase(romanNumber)) {
			return "0FFF";
		} else if ("II".equalsIgnoreCase(romanNumber)) {
			return "F0FF";
		} else if ("III".equalsIgnoreCase(romanNumber)) {
			return "FF0F";
		} else if ("IV".equalsIgnoreCase(romanNumber)) {
			return "FFF0";
		} else {
			throw new BindingConfigParseException(
					"Unknown roman number given: " + romanNumber);
		}
	}

	private String calculate4SettingGroupcode(int number)
			throws BindingConfigParseException {
		switch (number) {
		case 1:
			return "0FFF";
		case 2:
			return "F0FF";
		case 3:
			return "FF0F";
		case 4:
			return "FFF0";
		default:
			throw new BindingConfigParseException(
					"Unknown group address given " + number);
		}
	}

}
