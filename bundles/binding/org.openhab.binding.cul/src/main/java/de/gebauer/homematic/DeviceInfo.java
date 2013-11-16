package de.gebauer.homematic;

import java.io.Serializable;

import de.gebauer.cul.homematic.device.Model;
import de.gebauer.cul.homematic.in.MessageInterpreter;

public class DeviceInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * firmware version
     */
    public String version;
    /**
     * model type
     */
    public Model mdl;
    public String serNo;
    /**
     * model class
     */
    public HomeMaticDeviceType subtype;
    public int peerChannelA;
    public int peerChannelB;

    public DeviceInfo(String version, Model mdl, String serNo, HomeMaticDeviceType subtype, int peerChannelA,
	    int peerChannelB) {
	assert version != null;
	assert mdl != null;
	this.version = version;
	this.mdl = mdl;
	this.serNo = serNo;
	this.subtype = subtype;
	this.peerChannelA = peerChannelA;
	this.peerChannelB = peerChannelB;
    }

    @Override
    public String toString() {
	return "DeviceInfo [version=" + version + ", mdl=" + mdl.getName() + ", serNo=" + serNo + ", subtype="
		+ subtype + ", peerChannelA=" + peerChannelA + ", peerChannelB=" + peerChannelB + "]";
    }

    public static DeviceInfo parse(final String pl) {
	String version = pl.substring(0, 2);
	Model mdl = Model.forId(MessageInterpreter.toInt(pl, 2, 4));
	String serNo = pl.substring(6, 26);
	HomeMaticDeviceType subtype = HomeMaticDeviceType.valueOfID(pl.substring(26, 28));
	int peerChannelA = MessageInterpreter.toInt(pl, 28, 2);
	int peerChannelB = MessageInterpreter.toInt(pl, 30, 2);

	return new DeviceInfo(version, mdl, serNo, subtype, peerChannelA, peerChannelB);
    }
}