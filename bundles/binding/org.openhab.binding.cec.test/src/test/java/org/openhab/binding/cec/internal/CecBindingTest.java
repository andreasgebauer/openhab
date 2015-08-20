package org.openhab.binding.cec.internal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Hashtable;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openhab.binding.cec.internal.protocol.CecDatabase;
import org.openhab.binding.cec.internal.protocol.CecDatabaseTest;
import org.openhab.binding.cec.internal.protocol.datatypes.def.MessageType;
import org.openhab.core.events.EventPublisher;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.cm.ConfigurationException;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ FrameworkUtil.class })
public class CecBindingTest {

	@InjectMocks
	private CecBinding testable;

	@Mock
	private EventPublisher eventPublisher;

	@Mock
	private CecDatabase database = mock(CecDatabase.class);

	@BeforeClass
	public static void setup() {
		CecDatabaseTest.setup();
	}

	/**
	 * Tests {@link CecBinding#processLineInput(String)}.
	 */
	@Test
	public void testProcessLineInput() {
		MessageType mock = mock(MessageType.class);
		when(database.getMessage(0x87)).thenReturn(mock);

		testable.processLineInput("TRAFFIC: [             374]	>> 0f:87:00:00:f0");

	}

	public static void main(final String[] args) throws ConfigurationException {
		final CecBinding cecBinding = new CecBinding();
		final Hashtable<String, Object> props = new Hashtable<String, Object>();
		props.put("device", "/dev/ttyACM0");
		cecBinding.updated(props);
		// Thread.sleep(1000L);
		cecBinding.write("20 8f");
		cecBinding.write("21 8f");
		cecBinding.write("22 8f");
	}
}
