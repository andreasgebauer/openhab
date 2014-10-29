package de.gebauer.homematic.hmccvd;

import java.math.BigDecimal;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.AbstractMessage;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.MessageType;

/**
 * flag: A1
 * 
 * Nach 3 Minuten reagiert das Gerät nicht mehr egal in welchen Zeitabständen man Kommandos abschickt.
 * 
 * @author andi
 * 
 */
public class ClimateMessage extends AbstractMessage {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final int command;
    private final int valvePos;

    public ClimateMessage(final RawMessage msg, final AbstractDevice srcDevice, final AbstractDevice receiver, final int valvePosition, BigDecimal rssi) {
	super(new AbstractMessageParameter(msg, srcDevice, receiver, null, rssi));
	this.command = 0;
	this.valvePos = valvePosition;
    }

    public ClimateMessage(final RawMessage msg, final AbstractDevice srcDevice, final AbstractDevice receiver, final int valvePosition) {
	super(new AbstractMessageParameter(msg, srcDevice, receiver, null, null));
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

    @Override
    public boolean needsAck() {
	return true;
    }

}
