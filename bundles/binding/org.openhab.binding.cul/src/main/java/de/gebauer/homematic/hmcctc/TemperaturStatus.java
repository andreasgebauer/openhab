package de.gebauer.homematic.hmcctc;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.InfoActuatorStatus;
import de.gebauer.homematic.Message;
import de.gebauer.homematic.StatusChangeEvent.ChannelStatus;
import de.gebauer.homematic.device.AbstractDevice;

public class TemperaturStatus extends InfoActuatorStatus implements Message {

    private double desiredTemp;

    public TemperaturStatus(RawMessage msg, AbstractDevice src, AbstractDevice dst, double desiredTemp, ChannelStatus chStatus) {
	super(msg, src, dst, (short) 0x02, chStatus);
	this.desiredTemp = desiredTemp;
    }

}
