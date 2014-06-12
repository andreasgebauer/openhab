package de.gebauer.homematic.hmcctc;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import de.gebauer.cul.homematic.in.MessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.hmcctc.HMCCTCInterpreter;
import de.gebauer.homematic.msg.Message;

public class HMCCTCInterpreterTest {

    private HMCCTCInterpreter hmcctcInterpreter;

    @Before
    public void setUp() {
	hmcctcInterpreter = new HMCCTCInterpreter();
    }

    @Test
    public void testSetModeManual1() throws Exception {
	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA808040200000000050101000022"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof BasicTCInfoMessage);
	BasicTCInfoMessage message = (BasicTCInfoMessage) read;
	assertEquals(ControlMode.MANUAL, message.getControlMode());
    }

    @Test
    public void testSetModeManual2() throws Exception {
	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA80806021D00000000"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof TemperaturSetMessage);
	TemperaturSetMessage message = (TemperaturSetMessage) read;
	assertEquals(new BigDecimal("14.5"), message.getDesiredTemp());
    }

    @Test
    public void testReadModeAuto() throws Exception {
	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA808040200000000050109000022"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof BasicTCInfoMessage);
	BasicTCInfoMessage basicTcInfo = (BasicTCInfoMessage) read;

	assertEquals(ControlMode.AUTO, basicTcInfo.getControlMode());
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

	assertEquals(ControlMode.CENTRAL, basicTcInfo.getControlMode());

    }

}
