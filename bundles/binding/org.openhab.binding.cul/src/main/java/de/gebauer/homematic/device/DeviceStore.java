package de.gebauer.homematic.device;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DeviceStore implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Map<String, AbstractDevice> deviceMap = new HashMap<String, AbstractDevice>();

    public AbstractDevice get(String deviceHexString) {
	if (this.deviceMap.containsKey(deviceHexString)) {
	    return this.deviceMap.get(deviceHexString);
	}
	return null;
    }

    public void add(String src, AbstractDevice device) {
	this.deviceMap.put(src, device);
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (Entry<String, AbstractDevice> entry : deviceMap.entrySet()) {
	    sb.append(entry.getKey() + ": " + entry.getValue() + "\r\n");
	}

	return sb.toString();
    }

    public Collection<AbstractDevice> getDevices() {
	return deviceMap.values();
    }

    public void clear() {
	this.deviceMap.clear();
    }
}