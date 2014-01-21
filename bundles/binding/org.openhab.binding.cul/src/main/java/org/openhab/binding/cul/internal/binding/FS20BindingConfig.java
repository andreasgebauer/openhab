package org.openhab.binding.cul.internal.binding;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.openhab.core.items.Item;
import org.openhab.core.library.items.DimmerItem;
import org.openhab.core.library.items.SwitchItem;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.library.types.UpDownType;
import org.openhab.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tobiaswegner.communication.cul4java.CULInterface;
import de.tobiaswegner.communication.cul4java.FS20Command;
import de.tobiaswegner.communication.cul4java.impl.FS20Handler;

public class FS20BindingConfig extends AbstractCulBindingConfig {

	private final static Logger log = LoggerFactory
			.getLogger(FS20BindingConfig.class);

	private final static Set<Class<? extends Command>> allowedCommands;
	private final static Set<Class<? extends Item>> allowedItemTypes;
	static {
		Set<Class<? extends Command>> tempCommandSet = new HashSet<Class<? extends Command>>(
				3);
		tempCommandSet.add(UpDownType.class);
		tempCommandSet.add(OnOffType.class);
		tempCommandSet.add(PercentType.class);

		allowedCommands = Collections.unmodifiableSet(tempCommandSet);

		Set<Class<? extends Item>> tempItemSet = new HashSet<Class<? extends Item>>(
				2);
		tempItemSet.add(DimmerItem.class);
		tempItemSet.add(SwitchItem.class);

		allowedItemTypes = Collections.unmodifiableSet(tempItemSet);
	}

	private String address;
	private boolean isWriteable;

	public FS20BindingConfig(Item item, String houseCode) {
		super(item, houseCode.substring(0, 4));
		address = houseCode.substring(4);
	}

	@Override
	public String getAddress() {
		return houseCode + address;
	}

	@Override
	public Set<Class<? extends Item>> getAllowedItemTypes() {
		return allowedItemTypes;
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
		FS20Handler handler = cul.getHandlerForType('F');
		FS20Command fs20Command = FS20CommandHelper
				.convertHABCommandToFS20Command(command);
		if (fs20Command != null && fs20Command != FS20Command.UNKOWN) {
			handler.send(houseCode, address, fs20Command);
		} else {
			log.warn("Trying to send invalid command of type "
					+ command.getClass().getSimpleName());
		}
		return false;

	}

	@Override
	public boolean isWriteable() {
		return isWriteable;
	}

	public void setWriteable(boolean writeable) {
		isWriteable = writeable;
	}

}
