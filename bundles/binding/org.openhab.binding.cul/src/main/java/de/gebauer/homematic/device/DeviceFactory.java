package de.gebauer.homematic.device;

import java.lang.reflect.Method;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.hmcctc.ThermoControlDevice;
import de.gebauer.homematic.hmccvd.ValveDevice;
import de.gebauer.homematic.hmlcdim1tpi2.DimmerDevice;
import de.gebauer.homematic.hmsecsc.ShutterContactDevice;

public class DeviceFactory {

    public AbstractDevice createDevice(String name, String src, DeviceInfo deviceInfo) {
	if (deviceInfo == null) {
	    throw new IllegalArgumentException();
	}

	if (deviceInfo.mdl == Model.HMCCTC) {
	    return new ThermoControlDevice(name, src, deviceInfo);
	} else if (deviceInfo.mdl == Model.HMLCDIM1TPI2) {
	    return new DimmerDevice(name, src, deviceInfo);
	} else if (deviceInfo.mdl == Model.HMSECSC) {
	    return new ShutterContactDevice(name, src, deviceInfo);
	} else if (deviceInfo.mdl == Model.HMCCVD) {
	    return new ValveDevice(name, src, deviceInfo);
	}
	
	return new AbstractDevice(name, src, deviceInfo) {

	    private Object object;

	    @Override
	    public Method[] getCommands() {
		return null;
	    }

	    @Override
	    public DeviceMessageInterpreter getInterpreter() {
		return null;
	    }

	    @Override
	    public void setPeerList(Object object) {
		this.object = object;
	    }

	    @Override
	    public void setRegL(short list, String peerId, Object object) {
		
	    }

	};
    }

}
