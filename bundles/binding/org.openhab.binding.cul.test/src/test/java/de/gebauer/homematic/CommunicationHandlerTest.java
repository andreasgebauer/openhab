package de.gebauer.homematic;

import static junit.framework.Assert.fail;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;
import org.openhab.io.transport.cul.CULCommunicationException;

import de.gebauer.cul.homematic.out.MessageSender;
import de.gebauer.cul.homematic.out.MessageSenderImpl.WrappedMessage;
import de.gebauer.homematic.command.Command;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.Model;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.hmlcdim1tpi2.DimMessage;
import de.gebauer.homematic.hmlcdim1tpi2.DimmerDevice;
import de.gebauer.homematic.msg.AckStatusEvent;
import de.gebauer.homematic.msg.Message;

public class CommunicationHandlerTest {

    @Test
    public void testRun() throws Exception {
	final AtomicBoolean failed = new AtomicBoolean(false);
	final VirtualCCU srcDevice = new VirtualCCU("ccu");
	final DimmerDevice dstDevice = new DimmerDevice(null, null, new DeviceInfo(null, Model.HMCCVD, null));
	final MessageSender sender = new MessageSender() {

	    private int msgSentCount;

	    @Override
	    public void tearDown() {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void send(WrappedMessage event, int retryCount) {
		this.msgSentCount++;

		if (msgSentCount == 4) {
		    event.setResponse(new AckStatusEvent(null, dstDevice, srcDevice, (short) 0));
		}
	    }

	    @Override
	    public void send(WrappedMessage wrappedMsg, int resendCount, Integer countForce) throws CULCommunicationException {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void processCmdStack(AbstractDevice srcDevice) throws IOException {
		// TODO Auto-generated method stub

	    }
	};
	Command message = new Command() {

	    private int nextMessage;

	    @Override
	    public void success() {
		// TODO Auto-generated method stub
	    }

	    @Override
	    public Message getNextMessage() {
		if (nextMessage++ == 0) {
		    // first call
		    return new DimMessage(srcDevice, dstDevice, 50);
		} else {
		    return null;
		}
	    }

	    @Override
	    public void failure() {
		fail();
		failed.set(true);
		// TODO Auto-generated method stub

	    }

	    @Override
	    public Integer getCountForce() {
		return null;
	    }
	};
	Calendar ts = Calendar.getInstance();
	CommunicationHandler communicationHandler = new CommunicationHandler(sender, message, ts);
	communicationHandler.start();

	communicationHandler.join();

	if (failed.get()) {
	    fail();
	}
    }
}
