package de.gebauer.homematic.hmlcsw1pbufm;

import java.lang.reflect.Method;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.command.SimpleCommand;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.CommandMessage;
import de.gebauer.homematic.msg.MessageFlag;

public class SwitchDevice extends AbstractDevice implements Switch {

    private static DeviceMessageInterpreter interpreter = new HMLCSW1PBUFMInterpreter();

    public SwitchDevice(String name, String id, DeviceInfo info) {
	super(name, id, info);
    }

    @Override
    public Method[] getCommands() {
	return Switch.class.getDeclaredMethods();
    }

    @Override
    public boolean off(AbstractDevice src) {
	// CUL_HM_PushCmdStack($hash,'++'.$flag.'11'.$id.$dst.'02'.$chn.'000000');
	RawMessageBuilder msgBldr = new RawMessageBuilder();
	msgBldr.setMsgFlag(MessageFlag.VAL_A0);
	addToSendQueue(new SimpleCommand(new CommandMessage(msgBldr.build(), src, this, (short) 1, "0201000000")));
	return true;
    }

    @Override
    public boolean on(AbstractDevice src) {
	// CUL_HM_PushCmdStack($hash,'++'.$flag.'11'.$id.$dst.'02'.$chn.'C80000');

	// 0E03A011 13C86D 209DA1 0201C80000
	// 0C01A011 13C86D 209DA1 0201C8
	// 0C02A011 13C86D 209DA1 C80000

	RawMessageBuilder msgBldr = new RawMessageBuilder();
	msgBldr.setMsgFlag(MessageFlag.VAL_A0);
	addToSendQueue(new SimpleCommand(new CommandMessage(msgBldr.build(), src, this, (short) 1, "0201C80000")));
	return true;
    }

    @Override
    public DeviceMessageInterpreter getInterpreter() {
	return interpreter;
    }

}
