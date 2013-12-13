package de.gebauer.homematic.hmlcdim1tpi2;

import java.lang.reflect.Method;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.device.AbstractDevice;

public class Dimmer extends AbstractDevice implements DimDevice {

    private DeviceMessageInterpreter interpreter = new HMLCDIM1TPI2Interpreter();

    public Dimmer(String name, String id, DeviceInfo info) {
	super(name, id, info);
    }

    @Override
    public Method[] getCommands() {
	return DimDevice.class.getDeclaredMethods();
    }

    @Override
    public DeviceMessageInterpreter getInterpreter() {
	return this.interpreter;
    }

    @Override
    public boolean off(AbstractDevice src) {
	addToSendQueue(new DimCommand(src, this, false));
	return true;
    }

    @Override
    public boolean on(AbstractDevice src) {
	addToSendQueue(new DimCommand(src, this, true));
	return true;
    }

    @Override
    public boolean dim(AbstractDevice src, short value) {
	addToSendQueue(new DimCommand(src, this, value));
	return true;
    }

}
