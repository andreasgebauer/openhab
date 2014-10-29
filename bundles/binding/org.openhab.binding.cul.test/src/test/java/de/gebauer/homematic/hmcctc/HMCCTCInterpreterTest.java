package de.gebauer.homematic.hmcctc;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import de.gebauer.cul.homematic.in.MessageInterpreter;
import de.gebauer.homematic.WeekDay;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.Message;

public class HMCCTCInterpreterTest {

    private HMCCTCInterpreter hmcctcInterpreter;

    @Before
    public void setUp() {
	hmcctcInterpreter = new HMCCTCInterpreter();
    }

    @Test
    public void testSetModeManual2() throws Exception {
	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA80806021D00000000"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof TemperatureSetMessage);
	TemperatureSetMessage message = (TemperatureSetMessage) read;
	assertEquals(new BigDecimal("14.5"), message.getDesiredTemp());
    }

    @Test
    public void testSetWOT6C() throws Exception {
	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA80804032190C50105050C0000"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof WindowOpenTemperatureSetMessage);
	WindowOpenTemperatureSetMessage wotMsg = (WindowOpenTemperatureSetMessage) read;

	assertEquals(new BigDecimal(6), wotMsg.getTemperature());
    }

    @Test
    public void testSetTemperatureTo23() {
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A10B5A4101EA80813C86D06022E0000000044"), null, null);

	assertNotNull(read);
	assertTrue(read instanceof TemperatureSetMessage);
	TemperatureSetMessage tsm = (TemperatureSetMessage) read;

	BigDecimal desiredTemp = tsm.getDesiredTemp();
	assertEquals(23, desiredTemp.doubleValue(), 0);

    }

    // cent
    // [1EA808->13C86C #93; len=14, flag=A4, type=SWITCH, p=0402000000000501110000]
    // [13C86C->1EA808 #93; len=0A, flag=80, type=ACK, p=00]
    @Test
    public void testSetModeCentral() throws Exception {
	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA8080402000000000501110000"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof BasicTCInfoMessage);
	BasicTCInfoMessage basicTcInfo = (BasicTCInfoMessage) read;

	assertEquals(ControlMode.CENTRAL, basicTcInfo.getTcData().controlMode);
    }

    @Test
    public void testSetModeManual1() throws Exception {
	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA808040200000000050101000022"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof BasicTCInfoMessage);
	BasicTCInfoMessage message = (BasicTCInfoMessage) read;
	assertEquals(ControlMode.MANUAL, message.getTcData().controlMode);
    }

    @Test
    public void testReadModeAuto() throws Exception {
	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA808040200000000050109000022"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof BasicTCInfoMessage);
	BasicTCInfoMessage basicTcInfo = (BasicTCInfoMessage) read;

	assertEquals(ControlMode.AUTO, basicTcInfo.getTcData().controlMode);
    }

    @Test
    public void testReadSetControlMode() {
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A1492A4101EA80813C86D0402000000000501090000EA"), null, null);

	assertNotNull(read);
	assertTrue(read instanceof BasicTCInfoMessage);
	BasicTCInfoMessage basicTcInfo = (BasicTCInfoMessage) read;

	assertEquals(ControlMode.AUTO, basicTcInfo.getTcData().controlMode);
	assertEquals(DisplayMode.TEMPERATURE_AND_HUMDITY, basicTcInfo.getTcData().displayMode);
	assertEquals(DisplayTemp.ACTUAL, basicTcInfo.getTcData().displayTemp);
	assertEquals(TemperatureUnit.CELSIUS, basicTcInfo.getTcData().displayTempUnit);
	assertEquals(WeekDay.SATURDAY, basicTcInfo.getTcData().decalcDay);
    }

    @Test
    public void testReadSetControlModeAUTO() {

	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A16A4A4101EA80813C86D04020000000005012E0860000051"), null, null);

	assertNotNull(read);
	assertTrue(read instanceof BasicTCInfoMessage);
	BasicTCInfoMessage basicTcInfo = (BasicTCInfoMessage) read;

	assertEquals(ControlMode.AUTO, basicTcInfo.getTcData().controlMode);
	assertEquals(DisplayMode.TEMPERATURE, basicTcInfo.getTcData().displayMode);
	assertEquals(DisplayTemp.SETPOINT, basicTcInfo.getTcData().displayTemp);
	assertEquals(TemperatureUnit.FAHRENHEIT, basicTcInfo.getTcData().displayTempUnit);
	assertEquals(WeekDay.SUNDAY, basicTcInfo.getTcData().decalcDay);
	assertEquals(new HMCCTCInterpreter.Time(12, 00), basicTcInfo.getTcData().decalcTime);
    }
}
