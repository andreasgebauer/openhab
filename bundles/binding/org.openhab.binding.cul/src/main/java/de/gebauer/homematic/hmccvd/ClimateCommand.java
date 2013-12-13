package de.gebauer.homematic.hmccvd;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.AbstractCommand;
import de.gebauer.homematic.MessageType;
import de.gebauer.homematic.device.AbstractDevice;

public class ClimateCommand extends AbstractCommand {

    private final int command;
    private final int valvePos;

    public ClimateCommand(RawMessage msg, AbstractDevice srcDevice, AbstractDevice receiver, int subtype, int vp) {
	super(msg, srcDevice, receiver);
	command = subtype;
	valvePos = vp;
    }

    public int getCommand() {
	return command;
    }

    public int getValvePos() {
	return valvePos;
    }

    public MessageType getType() {
	return MessageType.THERMOSTAT;
    }

    @Override
    public String toString() {
	return "ClimateEvent [command=" + command + ", valvePos=" + valvePos + ", raw=" + super.msg + "]";
    }

    @Override
    public String getPayload() {
	throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public boolean needsAck() {
	return true;
    }

}
