package de.gebauer.homematic.hmcctc;

import static org.junit.Assert.*;

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
    public void testRead() throws Exception {
	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA8080209000A040000"), src, dst);

	assertNotNull(read);
    }

    @Test
    public void testReadModeAuto() throws Exception {
	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA8080402000000000501090000"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof BasicTCInfoMessage);
	BasicTCInfoMessage basicTcInfo = (BasicTCInfoMessage) read;

	assertEquals(ControlMode.AUTO, basicTcInfo.getControlMode());
    }
    
    @Test
    public void testReadModeAuto2() throws Exception {
	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA80806021E00000000"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof BasicTCInfoMessage);
	BasicTCInfoMessage basicTcInfo = (BasicTCInfoMessage) read;

	assertEquals(ControlMode.AUTO, basicTcInfo.getControlMode());
    }

}
