package org.openhab.binding.cec.internal.protocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openhab.binding.cec.internal.device.Vendor;
import org.openhab.binding.cec.internal.protocol.data.FeatureOpcode;
import org.openhab.binding.cec.internal.protocol.data.PhysicalAddress;
import org.openhab.binding.cec.internal.protocol.data.PowerStatus;
import org.osgi.framework.FrameworkUtil;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ FrameworkUtil.class })
public class MessageFactoryTest {

	private MessageFactory messageParser;
	private CecDatabase cecDatabase;

	@Before
	public void setup() {
		CecDatabaseTest.setup();

		cecDatabase = new CecDatabase();
		messageParser = new MessageFactory(cecDatabase);
	}

	@Test
	public void testParse_SetMenuLanguage() {
		Message message = messageParser.parse(0x32, "676572");

		assertNotNull(message);
		assertEquals("ger", message.getPayload().getValue());
	}

	@Test
	public void testParse_DeviceVendorID() {
		Message message = messageParser.parse(0x87, "0000f0");

		assertNotNull(message);
		assertEquals(Vendor.SAMSUNG, message.getPayload().getValue());
	}

	@Test
	public void testParse_GiveDevicePowerStatus() {
		Message message = messageParser.parse(0x8F, null);

		assertNotNull(message);
	}

	@Test
	public void testParse_ReportPowerStatus() {
		Message message = messageParser.parse(0x90, "01");

		assertNotNull(message);
		assertEquals(PowerStatus.STANDBY, message.getPayload().getValue());
	}

	@Test
	public void testParse_FeatureAbort() {
		Message message = messageParser.parse(0x00, "8C00");

		Payload featureOpcode = message.getPayload("featureOpcode");

		assertNotNull(message);
		assertEquals(FeatureOpcode.GIVE_DEVICE_VENDOR_ID, featureOpcode.getValue());
	}

	@Test
	public void testParse_CECVersion() {
		Message message = messageParser.parse(0x9e, "05");

		Payload featureOpcode = message.getPayload("cecVersion");

		assertNotNull(message);
		assertEquals(5, featureOpcode.getValue());
	}

	@Test
	public void testParse_ReportPhysicalAddress() {
		Message message = messageParser.parse(0x84, "100004");

		Payload physicalAddress = message.getPayload("physicalAddress");

		assertNotNull(message);
		assertEquals(new PhysicalAddress(new int[] { 1, 0, 0, 0 }), physicalAddress.getValue());
	}

}
