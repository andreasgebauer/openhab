package de.gebauer.homematic.hmlcsw1pbufm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.gebauer.cul.homematic.in.MessageInterpreter;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.Message;

public class HMLCSW1PBUFMInterpreterTest {

    private HMLCSW1PBUFMInterpreter interpreter;

    @Before
    public void setUp() {
	this.interpreter = new HMLCSW1PBUFMInterpreter();
    }

    // A1D13A410209DA113C86D060100A8967FECAB3030313735100101297E713E1C
    // [209DA1->13C86D #13; len=1D, flag=VAL_A4, type=SWITCH, p=060100A8967FECAB3030313735100101297E713E1C]
    // what's that?
    @Test
    public void testReadSwitchStateMessageOffPaired() {

	AbstractDevice src = null;
	AbstractDevice dst = null;

	Message read = interpreter.read(MessageInterpreter.getRawMessage("A1D13A410209DA113C86D060100A8967FECAB3030313735100101297E713E1C"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof SwitchStateMessage);
	SwitchStateMessage ssm = (SwitchStateMessage) read;
	assertEquals(false, ssm.getState().isOn());
    }

    // A0E07A410209DA113C86D0601C8004A24
    // SwitchStateMessage [msg=[209DA1->13C86D #0C; len=0E, flag=VAL_A4, type=SWITCH, p=0601C8004A24]]
    // what's that?
    @Test
    public void testReadSwitchStateMessageOnPaired() {

	AbstractDevice src = null;
	AbstractDevice dst = null;

	Message read = interpreter.read(MessageInterpreter.getRawMessage("A0E07A410209DA113C86D0601C8004A24"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof SwitchStateMessage);
	SwitchStateMessage ssm = (SwitchStateMessage) read;
	assertEquals(true, ssm.getState().isOn());
    }

}
