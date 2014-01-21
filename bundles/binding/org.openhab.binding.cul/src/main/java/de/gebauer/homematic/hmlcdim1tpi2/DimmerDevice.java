package de.gebauer.homematic.hmlcdim1tpi2;

import java.lang.reflect.Method;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.command.SimpleCommand;
import de.gebauer.homematic.device.AbstractDevice;

public class DimmerDevice extends AbstractDevice implements Dimmer {

    private static DeviceMessageInterpreter interpreter = new HMLCDIM1TPI2Interpreter();

    public DimmerDevice(String name, String id, DeviceInfo info) {
	super(name, id, info);
    }

    @Override
    public Method[] getCommands() {
	return Dimmer.class.getDeclaredMethods();
    }

    @Override
    public DeviceMessageInterpreter getInterpreter() {
	return interpreter;
    }

    @Override
    public boolean off(AbstractDevice src) {
	addToSendQueue(new SimpleCommand(new DimMessage(src, this, false)));
	return true;
    }

    @Override
    public boolean on(AbstractDevice src) {
	addToSendQueue(new SimpleCommand(new DimMessage(src, this, true)));
	return true;
    }

    @Override
    public boolean dim(AbstractDevice src, short value) {
	addToSendQueue(new SimpleCommand(new DimMessage(src, this, value)));
	return true;
    }

}
