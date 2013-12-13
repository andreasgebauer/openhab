package org.openhab.binding.cul.internal.binding;

import java.util.Set;

import org.openhab.core.items.Item;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.IncreaseDecreaseType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;
import org.openhab.core.types.UnDefType;

import de.gebauer.communication.cul4java.impl.HMHandler;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.DeviceStore;
import de.gebauer.homematic.device.HomeMaticDeviceType;
import de.gebauer.homematic.hmcctc.ControlMode;
import de.gebauer.homematic.hmcctc.ThermoControlDevice;
import de.gebauer.homematic.hmlcdim1tpi2.DimCommand;
import de.tobiaswegner.communication.cul4java.CULInterface;

public class HomeMaticBindingConfig extends AbstractCulBindingConfig {

    private String id;
    private String channel;
    private String parameter;
    private String name;

    public HomeMaticBindingConfig(Item item, String config) {
	super(item, null);

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

    @Override
    public String getAddress() {
	return this.name + ":" + parameter;
    }

    @Override
    public Set<Class<? extends Item>> getAllowedItemTypes() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean isCommandAllowed(Command command) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean executeCommand(CULInterface cul, Command command) {
	HMHandler hmHandler = cul.getHandlerForType('A');
	DeviceStore store = hmHandler.getDeviceStore();
	AbstractDevice destination = store.get(this.id);
	State state = this.item.getState();

	if (destination != null && destination.getInfo().mdl.getDeviceType() == HomeMaticDeviceType.DIMMER) {
	    DimCommand event = null;
	    if (command instanceof PercentType) {
		int intValue = ((PercentType) command).intValue();
		event = new DimCommand(hmHandler.getCCU(), destination, intValue);
	    } else if (command instanceof OnOffType) {
		switch ((OnOffType) command) {
		case ON:
		    event = new DimCommand(hmHandler.getCCU(), destination, true);
		    break;
		case OFF:
		    event = new DimCommand(hmHandler.getCCU(), destination, false);
		    break;
		default:
		    break;
		}
	    } else if (command instanceof IncreaseDecreaseType) {
		if (state instanceof UnDefType) {
		    state = PercentType.ZERO;
		}
		PercentType stateAs = (PercentType) item.getStateAs(PercentType.class);
		// TODO increase based on current value
		switch ((IncreaseDecreaseType) command) {
		case INCREASE:
		    event = new DimCommand(hmHandler.getCCU(), destination, stateAs.intValue() + 5);
		    break;
		case DECREASE:
		    event = new DimCommand(hmHandler.getCCU(), destination, stateAs.intValue() - 5);
		    break;
		default:
		    break;
		}
	    }
	    if (event != null) {
		destination.addToSendQueue(event);
		// handlerForType.getMessageSender().send(event);
	    }
	    return true;
	} else if (destination instanceof ThermoControlDevice) {
	    if (command instanceof DecimalType) {
		int intValue = ((DecimalType) command).intValue();
		ControlMode valueOf = ControlMode.valueOf(intValue);
		((ThermoControlDevice) destination).controlMode(hmHandler.getCCU(), valueOf);
		return false;
	    }
	}
	return false;
    }

    @Override
    public boolean isWriteable() {
	return true;
    }

    public String getId() {
	return this.id;
    }

}
