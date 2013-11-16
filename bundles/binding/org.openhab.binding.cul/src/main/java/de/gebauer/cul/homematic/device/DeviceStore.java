package de.gebauer.cul.homematic.device;

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

    private Map<String, Device> deviceMap = new HashMap<String, Device>();

    public Device get(String deviceHexString) {
	if (this.deviceMap.containsKey(deviceHexString)) {
	    return this.deviceMap.get(deviceHexString);
	}
	return null;
    }

    public void add(String src, Device device) {
	this.deviceMap.put(src, device);
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	for (Entry<String, Device> entry : deviceMap.entrySet()) {
	    sb.append(entry.getKey() + ": " + entry.getValue() + "\r\n");
	}

	return sb.toString();
    }

    public Collection<Device> getDevices() {
	return deviceMap.values();
    }
}
