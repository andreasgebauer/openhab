package org.openhab.binding.cul.internal;

import java.io.IOException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import de.gebauer.communication.cul4java.impl.HMHandler;
import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.DeviceStore;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.msg.AckStatusMessage;
import de.gebauer.homematic.msg.ConfigRegisterReadMessage;
import de.gebauer.homematic.msg.ParamResponseMessage;
import de.tobiaswegner.communication.cul4java.CULHandler;
import de.tobiaswegner.communication.cul4java.CULInterface;

public class CULBindingTest {

    private CULBinding binding;

    @Before
    public void setup() {
	binding = new CULBinding();

	DeviceStore dvcStore = new DeviceStore();
	binding.dvcStore = dvcStore;
	binding.homeMaticHandler = new HMHandler(new CULInterface() {

	    @Override
	    public void unregisterHandler(char type) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void unregisterHandler(CULHandler<?> handler) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void setOwnHouseCode(String housecode) throws IOException {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void sendRAW(String sendString) throws IOException {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void registerHandler(CULHandler<?> handler) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void open(String deviceName) throws Exception {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	    }

	    @Override
	    public <H extends CULHandler<?>> H getHandlerForType(char type) {
		// TODO Auto-generated method stub
		return null;
	    }

	    @Override
	    public void decode(String cmdLine) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void close() throws IOException {
		// TODO Auto-generated method stub

	    }
	}, dvcStore);
    }

    @Test
    public void testReceivedMessage() throws Exception {
	VirtualCCU ccu = binding.homeMaticHandler.getCCU();

	Short channel = 1;
	AbstractDevice src = new AbstractDevice("", null, null) {

	    @Override
	    public DeviceMessageInterpreter getInterpreter() {
		return null;
	    }

	    @Override
	    public Method[] getCommands() {
		return null;
	    }
	};
	RawMessage msg = new RawMessageBuilder().setMsgCount("00").build();
	src.messageSent(new ConfigRegisterReadMessage(msg, ccu, src, channel, null, (short) -1, (short) -1));

	binding.receivedMessage(new ParamResponseMessage(msg, src, ccu, null, " 00:00"));

    }
}
