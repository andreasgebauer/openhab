package de.gebauer.homematic.device;

import static junit.framework.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Test;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.homematic.DeviceInfo;

public class HMCycleCalculatorTest {

    private static final class Device extends AbstractDevice {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Device(String name) {
	    super(name, null, new DeviceInfo("", Model.HMCCTC, ""));
	}

	@Override
	public DeviceMessageInterpreter getInterpreter() {
	    return null;
	}

	@Override
	public Method[] getCommands() {
	    return null;
	}
    }

    HMCycleCalculator hmCycle;
    private AbstractDevice device;

    @Test
    public void testGetNextCycle206185() throws Exception {
	device = new Device("1C475A");
	hmCycle = new HMCycleCalculator(device.getName());

	check(0, 122750);
	check(1, 172500);
	check(2, 158000);
	check(3, 143500);
	check(4, 129250);
	check(5, 178750);
	check(6, 164250);
	check(7, 149750);
	check(8, 135500);

	check(0x10, 148000);

	check(0x18, 160750);

	check(0x1C, 167000);
	check(0x1D, 152500);
	check(0x1E, 138250);

	check(0x20, 173250);
	check(0x30, 134500);

	check(0x80, 132750);

	check(0x80, 132750);

	check(0xFE, 171250);
	check(0xFF, 156750);
    }

    @Test
    public void testGetNextCycle1C4E7F() throws Exception {
	device = new Device("1C4E7F");
	hmCycle = new HMCycleCalculator(device.getName());

	check(0, 182750);
	check(1, 168250);
	check(2, 154000);
	check(3, 139500);
	check(4, 125000);
	check(5, 174750);
	check(6, 160250);
	check(7, 145750);
	check(8, 131500);
	check(16, 144000);
	check(20, 150250);
	check(22, 121500);
	check(23, 171000);
	check(24, 156500);
	check(54, 172000);

	check(57, 128750);

	check(160, 179000);

	check(176, 140250);

	check(184, 152750);

	check(187, 173500);

	check(189, 144750);

	// 190
	check(190, 130250);
	check(0xBF, 180000);
	check(192, 165500);
	check(0xC1, 151000);
	check(0xC2, 136500);
	check(0xC3, 122250);
	check(0xC4, 171750);
	check(197, 157250);
	check(198, 143000);
	check(199, 128500);

	// 200
	check(200, 178000);
	check(202, 149250);

	// 208
	check(0xD0, 126750);
	check(0xD1, 176250);
	// 210
	check(0xD2, 161750);
	check(0xD3, 147500);
	check(0xD4, 133000);
	check(0xD5, 182500);
	check(0xD6, 168250);
	check(0xD7, 153750);
	check(0xD8, 139250);
	check(0xD9, 125000);
	check(0xDA, 174500);
	check(0xDB, 160000);
	// 220
	check(0xDC, 145500);
	check(0xDD, 131250);
	check(0xDE, 180750);
	check(0xDF, 166250);
	check(0xE0, 152000);
	check(0xE1, 137500);
	check(0xE2, 123000);
	check(0xE3, 172750);
	check(0xE4, 158250);
	check(0xE5, 143750);
	// 230
	check(0xE6, 129500);
	check(0xE7, 179000);
	check(0xE8, 164500);
	check(0xE9, 150000);
	check(0xEA, 135750);
	check(0xEB, 121250);
	check(0xEC, 170750);
	check(0xED, 156500);
	check(0xEE, 142000);
	check(0xEF, 127500);

	check(0xF0, 177250);
	check(0xF1, 162750);
	check(0xF2, 148250);
	check(0xF3, 134000);
	check(0xF4, 183500);
	check(0xF5, 169000);
	check(0xF6, 154500);
	check(0xF7, 140250);
	check(0xF8, 125750);
	check(0xF9, 175250);

	check(0xFA, 161000);
	check(0xFB, 146500);
	check(0xFC, 132000);
	check(0xFD, 181750);
	check(0xFE, 167250);
    }

    private void check(int msgCnt, int expected) {
	assertEquals("0x" + String.format("%02X", msgCnt) + " " + msgCnt, expected, hmCycle.getIncrease(msgCnt));
    }
}
