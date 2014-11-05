package de.gebauer.homematic.hmccvd;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.gebauer.cul.homematic.in.MessageInterpreter;
import de.gebauer.homematic.DeviceState;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.msg.AckStatusEvent;
import de.gebauer.homematic.msg.Message;

public class HMCCVDInterpreterTest {

    private HMCCVDInterpreter interpreter;

    @Before
    public void setUp() {
	this.interpreter = new HMCCVDInterpreter();
    }

    @Test
    @Ignore
    public void testRead() throws Exception {

	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = interpreter.read(MessageInterpreter.getRawMessage("A107980101C4E7F1EA8080209000A04000022"), src, dst);

	assertNotNull(read);
    }

    // /A0E2782021C4E7F1EA808010100003DEE
    @Test
    public void testReadSetValvePositionResponse() throws Exception {

	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = interpreter.read(MessageInterpreter.getRawMessage("A0E2782021C4E7F1EA808010100003DEE"), src, dst);

	checkState(read, BatteryStatus.OK, MotorError.OK, MotorState.STOP, 0);
    }

    @Test
    public void testReadSetValvePositionResponseOk22() {
	Message read = interpreter.read(MessageInterpreter.getRawMessage("A0E5782021C4E7F1EA80801012C003723"), null, null);
	
	checkState(read, BatteryStatus.OK, MotorError.OK, MotorState.STOP, 22);
    }
    
    @Test
    public void testReadSetValvePositionResponseBatteryLow() {
	Message read = interpreter.read(MessageInterpreter.getRawMessage("A0E5782021C4E7F1EA80801011E88381F"), null, null);

	BatteryStatus batStatus = BatteryStatus.LOW;
	MotorError motorErr = MotorError.OK;
	MotorState motorState = MotorState.STOP;

	checkState(read, batStatus, motorErr, motorState, 15);	
    }

    private void checkState(Message read, BatteryStatus batStatus, MotorError motorErr, MotorState motorState, int position) {
	assertNotNull(read);

	assertTrue(read instanceof AckStatusEvent);
	AckStatusEvent ackMsg = (AckStatusEvent) read;
	DeviceState deviceState = ackMsg.getDeviceState();

	assertTrue(deviceState instanceof ValveStateData);
	ValveStateData valveState = (ValveStateData) deviceState;

	assertEquals(batStatus, valveState.getBatteryStatus());
	assertEquals(motorErr, valveState.getMotorError());
	assertEquals(motorState, valveState.getMotorState());
	assertEquals(position, valveState.getPosition());
    }

    // /A0E2782021C4E7F1EA808010100003DEE
    @Test
    public void testReadSetValveConfigResponse() throws Exception {

	AbstractDevice src = null;
	AbstractDevice dst = null;
	Message read = interpreter.read(MessageInterpreter.getRawMessage("A0E2782101C4E7F1EA8080401000000000509110A11EE"), src, dst);

	assertNotNull(read);
	assertTrue(read instanceof AckStatusEvent);
	AckStatusEvent ack = (AckStatusEvent) read;

	DeviceState deviceState = ack.getDeviceState();
	assertNotNull(deviceState);
	assertTrue(deviceState instanceof ValveConfigData);

	ValveConfigData vd = (ValveConfigData) deviceState;

	assertEquals(17, vd.getErrorPosition());
	assertEquals(17, vd.getOffset());

	assertEquals(new BigDecimal("-83.0"), read.getRSSI());
    }

}
