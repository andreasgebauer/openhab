package de.gebauer.homematic.msg;

import de.gebauer.homematic.DeviceState;

/**
 * Config message with sub type 0x01<br>
 * 
 * @author andreas
 *
 */
public class Config1Message extends AbstractConfigMessage {

    private DeviceState devData;

    public Config1Message(AbstractMessageParameter msgParam) {
	super(msgParam);
    }

    public Config1Message(AbstractMessageParameter msgParam, DeviceState devData) {
	super(msgParam);
	this.devData = devData;
    }

    @Override
    public short getSubType() {
	return 0x01;
    }

}
