package de.gebauer.homematic;

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
public class SetCommand extends AbstractMessage {

    private short value;

    public SetCommand(RawMessage msg, AbstractDevice srcDevice, AbstractDevice dstDevice, short chnl, short value) {
	super(msg, srcDevice, dstDevice, chnl);
	this.value = value;
    }

    @Override
    public MessageType getType() {
	return MessageType.COMMAND;
    }

    @Override
    public String toString() {
	return "SetCommand [chnl=" + channel + ", value=" + value + ", msg=" + msg + "]";
    }

    @Override
    public String getPayload() {
	return String.format("02%02X%02X", channel, value);
    }

    @Override
    public boolean needsAck() {
	return true;
    }
}
