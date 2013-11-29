package de.gebauer.homematic;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.device.HomeMaticDeviceType;
import de.gebauer.cul.homematic.device.Model;
import de.gebauer.cul.homematic.in.MessageInterpreter;

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
    public int peerChannelA;
    public int peerChannelB;

    public DeviceInfo(final String version, final Model mdl, final String serNo, final int peerChannelA,
	    final int peerChannelB) {
	assert version != null;
	assert mdl != null;
	this.version = version;
	this.mdl = mdl;
	this.serNo = serNo;
	this.peerChannelA = peerChannelA;
	this.peerChannelB = peerChannelB;
    }

    @Override
    public String toString() {
	return "DeviceInfo [version=" + this.version + ", mdl=" + this.mdl.getName() + ", serNo=" + this.serNo
		+ ", peerChannelA="
		+ this.peerChannelA + ", peerChannelB=" + this.peerChannelB + "]";
    }

    public static DeviceInfo parse(final String pl) {
	final String version = pl.substring(0, 2);
	final Model mdl = Model.forId(MessageInterpreter.toInt(pl, 2, 4));
	if (mdl == null) {
	    LOG.error("No model defined for " + mdl);
	}
	// TODO is this the same as the MessageType?
	final String subTypeStr = pl.substring(26, 28);
	final HomeMaticDeviceType subtype = HomeMaticDeviceType.valueOfID(subTypeStr);
	if (subtype == null) {
	    LOG.error("No device type defined for " + subTypeStr);
	}
	final String serNo = pl.substring(6, 26);
	final int peerChannelA = MessageInterpreter.toInt(pl, 28, 2);
	final int peerChannelB = MessageInterpreter.toInt(pl, 30, 2);

	return new DeviceInfo(version, mdl, serNo, peerChannelA, peerChannelB);
    }
}