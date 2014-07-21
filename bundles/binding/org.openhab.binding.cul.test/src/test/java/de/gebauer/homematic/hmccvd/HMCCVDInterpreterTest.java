package de.gebauer.homematic.hmccvd;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.gebauer.cul.homematic.in.MessageInterpreter;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.Message;

public class HMCCVDInterpreterTest {

    private HMCCVDInterpreter interpreter;

    @Test
    public void testRead() throws Exception {
	this.interpreter = new HMCCVDInterpreter();

	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = interpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA8080209000A04000022"), src, dst);

	assertNotNull(read);
    }

}
