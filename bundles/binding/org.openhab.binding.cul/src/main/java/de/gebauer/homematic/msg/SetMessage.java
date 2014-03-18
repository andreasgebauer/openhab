package de.gebauer.homematic.msg;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;

/**
 * set desired temperature:
 * 
 * value: x°C -> value=x*2 <br>
 * 0 - 100 °C .5°C step
 * 
 * 
 * @author andi
 */
public class SetMessage extends AbstractMessage {

    private short value;

    public SetMessage(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, short chnl, short value) {
	super(new AbstractMessageParameter(msg, srcDevice, dstDevice, chnl));
	this.value = value;
    }

    @Override
    public String getPayload() {
	return String.format("%02X%02X%02X", 0x02, channel, value);
    }

    @Override
    public MessageType getType() {
	return MessageType.COMMAND;
    }

    @Override
    public String toString() {
	return "SetMessage [chnl=" + channel + ", value=" + value + ", msg=" + msg + "]";
    }

    @Override
    public boolean needsAck() {
	return true;
    }

}
