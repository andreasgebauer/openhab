package de.gebauer.homematic.msg;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.Model;

public class DeviceInfoEventTest {

    private DeviceInfoEvent event;

    @Before
    public void setup() {

	final RawMessage msg = new RawMessage();
	final AbstractDevice srcDevice = this.newDevice("name", "id", "21", Model.HMCCVD, "4A455130333133333732");
	final AbstractDevice dst = this.newDevice("name2", "id2", "21", Model.HMCCVD, "4A455130333133333732");
	final short pChA = 0;
	final short pChB = 0;
	final String cmd = "cmd";
	this.event = new DeviceInfoEvent(msg, srcDevice, dst, srcDevice.getInfo(), pChA, pChB, cmd);
    }

    private AbstractDevice newDevice(final String name, final String id, final String version, final Model mdl, final String serNo) {
	DeviceInfo deviceInfo = new DeviceInfo(version, mdl, serNo);
	return new AbstractDevice(name, id, deviceInfo) {

	    @Override
	    public Method[] getCommands() {
		return null;
	    }

	    @Override
	    public DeviceMessageInterpreter getInterpreter() {
		return null;
	    }
	};
    }

    @Test
    public void testGetPayload() throws Exception {
	final String payload = this.event.getPayload();

	assertEquals("21003A4A455130333133333732580000cmd", payload);
    }

    @Test
    public void testGetType() throws Exception {
	MessageType expected = MessageType.UNKNOWN;
	MessageType actual = this.event.getType();
	assertTrue(null, expected == actual);
    }

    @Test
    public void testGetInfo() throws Exception {

    }

    @Test
    public void testNeedsAck() throws Exception {
	assertTrue(this.event.needsAck());
    }

}
