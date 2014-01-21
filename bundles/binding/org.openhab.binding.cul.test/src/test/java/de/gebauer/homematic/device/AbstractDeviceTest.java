package de.gebauer.homematic.device;

import static junit.framework.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

public class AbstractDeviceTest {

    @Test
    public void testGetNextCycle() throws Exception {
	check(0, 152750);
	check(1, 182750);
	check(2, 168250);
    }

    private void check(int messageCount, int expected) {
	Calendar now = Calendar.getInstance();
	HMCycle hmCycle = new HMCycle(now, null);
	Calendar nextCycle = hmCycle.getNextCycle(messageCount);
	assertEquals(expected, nextCycle.getTimeInMillis() - now.getTimeInMillis());
    }
}
