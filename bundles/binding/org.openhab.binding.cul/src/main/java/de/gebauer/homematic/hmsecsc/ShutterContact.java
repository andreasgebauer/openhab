package de.gebauer.homematic.hmsecsc;

import java.lang.reflect.Method;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.device.AbstractDevice;

public class ShutterContact extends AbstractDevice {

    public ShutterContact(String name, String id, DeviceInfo info) {
	super(name, id, info);
    }

    @Override
    public Method[] getCommands() {
	return null;
    }

    @Override
    public DeviceMessageInterpreter getInterpreter() {
	return new HMSECSCInterpreter();
    }

}
