package de.gebauer.cul.homematic.in;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.DeviceStore;
import de.gebauer.homematic.device.Model;
import de.gebauer.homematic.hmcctc.ThermoControlDevice;
import de.gebauer.homematic.hmccvd.ValveDevice;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.ConfigRegisterReadMessage;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.ParamResponseMessage;

public class MessageInterpreterTest {

    @Test
    public void testParseParamResponseMessage() {
	final short channel = 0x02;
	final String peerAddress = "111111";
	final short peerChannel = 0xA1;
	final short paramList = 0x05;

	DeviceStore deviceStore = new DeviceStore();
	AbstractDevice vd = new ValveDevice("1C4E7F", "1C4E7F", new DeviceInfo("10", Model.HMCCVD, "1C4E7F"));
	AbstractDevice tc = new ThermoControlDevice("1EA808", "1EA808", new DeviceInfo("10", Model.HMCCVD, "1EA808"));

	AbstractMessageParameter msgParam = new AbstractMessageParameter(null, tc, vd, channel);
	ConfigRegisterReadMessage regReadCmd = new ConfigRegisterReadMessage(msgParam, peerAddress, peerChannel, paramList);
	vd.messageReceived(regReadCmd);
	tc.messageSent(regReadCmd);

	deviceStore.add("1C4E7F", vd);
	deviceStore.add("1EA808", tc);
	Message msg = new MessageInterpreter(deviceStore).parse("A107980101C4E7F1EA8080209000A040000");

	assertNotNull(msg);
	assertTrue(msg instanceof ParamResponseMessage);
	ParamResponseMessage paramRspMsg = (ParamResponseMessage) msg;
	assertEquals(" 09:00 0A:04 00:00", paramRspMsg.getData());
    }
    
}
