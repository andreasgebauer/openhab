package de.gebauer.homematic.hmccvd;

import java.lang.reflect.Method;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.device.AbstractDevice;

public class ValveDevice extends AbstractDevice implements Valve {

    public ValveDevice(String name, String id, DeviceInfo info) {
	super(name, id, info);
    }

    @Override
    public Method[] getCommands() {
	return Valve.class.getDeclaredMethods();
    }

    @Override
    public DeviceMessageInterpreter getInterpreter() {
	return new HMCCVDInterpreter();
    }

    @Override
    public void setTo(AbstractDevice src, short valvePos) {
	addToSendQueue(new ClimateCommand(new RawMessage(), src, this, 0, valvePos));
    }

}
