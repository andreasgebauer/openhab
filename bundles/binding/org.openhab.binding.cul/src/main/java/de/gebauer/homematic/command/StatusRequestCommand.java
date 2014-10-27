package de.gebauer.homematic.command;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.Config1Message;

// [13C86D->209DA1 #0A; len=0B, flag=VAL_A0, type=CONFIG, p=010E1D]
public class StatusRequestCommand extends AbstractCCUCommand {

    public StatusRequestCommand(VirtualCCU ccu, AbstractDevice dst) {
	super(ccu, dst);

	RawMessage msg = new RawMessageBuilder().build();
	AbstractMessageParameter msgParam = new AbstractMessageParameter(msg, ccu, dst, null);
	add(new Config1Message(msgParam));
    }
}
