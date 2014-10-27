package org.openhab.binding.cul.internal;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.DeviceStore;
import de.gebauer.homematic.device.Model;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.ConfigRegisterReadMessage;
import de.gebauer.homematic.msg.ParamResponseMessage;

public class CULBindingTest {

    private HomematicCULBinding binding;

    @Before
    public void setup() throws NoSuchFieldException, SecurityException {
	binding = new HomematicCULBinding();
	binding.activate();

	// binding.getClass().getDeclaredField("messageParser").set(binding, value);;
	DeviceStore dvcStore = new DeviceStore();
	binding.dvcStore = dvcStore;
    }

    @Test
    public void testReceivedMessage() throws Exception {
	AbstractDevice ccu = null;

	Short channel = 1;
	AbstractDevice src = new AbstractDevice("", null, new DeviceInfo(null, Model.HMCCTC, null)) {

	    @Override
	    public DeviceMessageInterpreter getInterpreter() {
		return null;
	    }

	    @Override
	    public Method[] getCommands() {
		return null;
	    }
	};
	RawMessage msg = new RawMessageBuilder().setMsgCount("00").build();
	AbstractMessageParameter msgParam = new AbstractMessageParameter(msg, ccu, src, channel);
	src.messageSent(new ConfigRegisterReadMessage(msgParam, null, (short) -1, (short) -1));

	ParamResponseMessage paramResponseMessage = new ParamResponseMessage(msg, src, ccu, null, " 00:00");

	binding.dataReceived("A02010101010101010101");

    }
}
