package de.gebauer.homematic;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.MessageInterpreter;
import de.gebauer.homematic.device.HomeMaticDeviceType;
import de.gebauer.homematic.device.Model;

/**
 * 28 chars = 14 bytes.<br>
 * vvmmmmsssssssssssssssssssstt <br>
 * version (1 byte) model (2 bytes) serial (10 bytes) type (1 byte)
 * 
 * @author andi
 * 
 */
public class DeviceInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(DeviceInfo.class);

    /**
     * firmware version
     */
    public String version;
    /**
     * model type
     */
    public Model mdl;
    public String serNo;

    public DeviceInfo(final String version, final Model mdl, final String serNo) {
	assert version != null;
	assert mdl != null;
	this.version = version;
	this.mdl = mdl;
	this.serNo = serNo;
    }

    @Override
    public String toString() {
	return "DeviceInfo [version=" + this.version + ", mdl=" + this.mdl.getName() + ", serNo=" + this.serNo + "]";
    }

    public static DeviceInfo parse(final String pl) {
	final String version = pl.substring(0, 2);
	final Model mdl = Model.forId(MessageInterpreter.toInt(pl, 2, 4));
	if (mdl == null) {
	    LOG.error("No model defined for " + mdl);
	}
	final String serNo = pl.substring(6, 26);
	// TODO is this the same as the MessageType?
	// 21 00 39 00 00 00 00 00 58 00 02 00 24
	final String subTypeStr;
	if (pl.length() >= 28) {
	    subTypeStr = pl.substring(26, 28);
	} else {
	    subTypeStr = pl.substring(16, 18);
	}
	final HomeMaticDeviceType subtype = HomeMaticDeviceType.valueOfID(subTypeStr);
	if (subtype == null) {
	    LOG.error("No device type defined for " + subTypeStr);
	}
	return new DeviceInfo(version, mdl, serNo);
    }
}