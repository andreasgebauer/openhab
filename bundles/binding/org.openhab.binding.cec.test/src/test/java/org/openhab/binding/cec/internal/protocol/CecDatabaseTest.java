package org.openhab.binding.cec.internal.protocol;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openhab.binding.cec.internal.protocol.datatypes.def.MessageType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ FrameworkUtil.class })
public class CecDatabaseTest {

	@BeforeClass
	public static void setup() {
		Bundle bundle = mock(Bundle.class);
		mockStatic(FrameworkUtil.class);
		when(FrameworkUtil.getBundle(CecDatabase.class)).thenReturn(bundle);
		when(bundle.getEntry(Matchers.anyString())).thenAnswer(new Answer<URL>() {
			@Override
			public URL answer(InvocationOnMock invocation) throws Throwable {
				return CecDatabase.class.getResource("/" + invocation.getArguments()[0]);
			}
		});
	}

	@Test
	public void testLoad() {
		CecDatabase cecDatabase = new CecDatabase();

		MessageType message = cecDatabase.getMessage(0x46);

		assertEquals("Give OSD Name", message.getName());
	}

	// @Test
	// public void testWrite() {
	// XStream xstream = new XStream(new StaxDriver());
	// xstream.registerConverter(new HexToIntegerConverter());
	// xstream.alias("cec-config", CecConfig.class);
	// xstream.processAnnotations(MessageType.class);
	// xstream.processAnnotations(PhysicalAddress.class);
	// xstream.processAnnotations(DeviceType.class);
	//
	// CecConfig config = new CecConfig();
	// config.getMessages().add(new MessageType(23, "Category", "name", null));
	//
	// String xml = xstream.toXML(config);
	//
	// System.out.println(xml);
	// }
}
