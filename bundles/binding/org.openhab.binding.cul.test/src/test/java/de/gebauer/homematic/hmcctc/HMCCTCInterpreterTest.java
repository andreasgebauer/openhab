package de.gebauer.homematic.hmcctc;

import static org.junit.Assert.*;

import org.junit.Test;

import de.gebauer.cul.homematic.in.MessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.hmcctc.HMCCTCInterpreter;
import de.gebauer.homematic.msg.Message;

public class HMCCTCInterpreterTest {

    private HMCCTCInterpreter hmcctcInterpreter;

    @Test
    public void testRead() throws Exception {
	hmcctcInterpreter = new HMCCTCInterpreter();

	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = hmcctcInterpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA8080209000A040000"), src, dst);

	assertNotNull(read);
    }

}
