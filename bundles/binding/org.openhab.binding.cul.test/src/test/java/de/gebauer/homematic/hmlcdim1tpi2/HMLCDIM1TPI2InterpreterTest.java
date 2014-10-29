package de.gebauer.homematic.hmlcdim1tpi2;

import org.junit.Before;
import org.junit.Test;

import de.gebauer.cul.homematic.in.MessageInterpreter;
import de.gebauer.homematic.msg.Message;

public class HMLCDIM1TPI2InterpreterTest {

    private HMLCDIM1TPI2Interpreter interpreter;

    @Before
    public void setUp() throws Exception {
	this.interpreter = new HMLCDIM1TPI2Interpreter();
    }

    @Test
    public void testRead() throws Exception {
	Message read = this.interpreter.read(MessageInterpreter.getRawMessage("A0E01800220E91613C86D01010000221C"), null, null);

	
    }

}
