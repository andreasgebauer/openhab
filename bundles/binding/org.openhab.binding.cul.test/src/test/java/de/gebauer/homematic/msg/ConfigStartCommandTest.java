package de.gebauer.homematic.msg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.DeviceFactory;

public class ConfigStartCommandTest {

    @Test
    public void testGetPayload() throws Exception {

	final short channel = 0x02;
	final String peerAddress = "111111";
	final short peerChannel = 0xA1;
	final short paramList = 0x05;

	final AbstractDevice srcDevice = new DeviceFactory().createDevice(null, null, new DeviceInfo(null, null, null));
	final AbstractDevice dstDevice = new DeviceFactory().createDevice(null, null, new DeviceInfo(null, null, null));
	final AbstractMessageParameter msgParam = new AbstractMessageParameter(null, srcDevice, dstDevice, channel);
	final String payload = new ConfigStartMessage(msgParam, peerAddress, peerChannel, paramList).getPayload();

	assertEquals("0205111111A105", payload);
    }
}
