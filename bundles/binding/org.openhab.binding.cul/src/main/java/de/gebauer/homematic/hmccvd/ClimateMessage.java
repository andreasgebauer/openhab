package de.gebauer.homematic.hmccvd;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.msg.AbstractMessageWithoutChannel;
import de.gebauer.homematic.msg.MessageType;

/**
 * flag: A1
 * 
 * Nach 3 Minuten reagiert das Gerät nicht mehr egal in welchen Zeitabständen man Kommandos abschickt.
 * 
 * @author andi
 * 
 */
public class ClimateMessage extends AbstractMessageWithoutChannel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final int command;
    private final int valvePos;

    public ClimateMessage(final RawMessage msg, final AbstractDevice srcDevice, final AbstractDevice receiver, final int valvePosition, int rssi) {
	super(msg, srcDevice, receiver, rssi);
	this.command = 0;
	this.valvePos = valvePosition;
    }

    public ClimateMessage(final RawMessage msg, final AbstractDevice srcDevice, final AbstractDevice receiver, final int valvePosition) {
	super(msg, srcDevice, receiver, -1);
	this.command = 0;
	this.valvePos = valvePosition;
    }

    public int getCommand() {
	return this.command;
    }

    public int getValvePos() {
	return this.valvePos;
    }

    @Override
    public MessageType getType() {
	return MessageType.THERMOSTAT;
    }

    @Override
    public String getPayload() {
	return String.format("%02X%02X", this.command, this.valvePos);
    }

    @Override
    public String toString() {
	return "Climate " + super.sendString() + " [command=" + this.command + ", valvePos=" + this.valvePos + "]";
    }

}
