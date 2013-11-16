package de.gebauer.cul.homematic.out;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.device.Command;
import de.gebauer.cul.homematic.device.Device;
import de.gebauer.homematic.Event;
import de.tobiaswegner.communication.cul4java.CULInterface;

/**
 * Sends commands and manages the command stack.
 * 
 * @author Andreas Gebauer
 */
public class MessageSenderImpl implements MessageSender {

    private static final Logger LOG = LoggerFactory.getLogger(MessageSenderImpl.class);
    private final CULInterface ioDevice;

    private int cmdCount = 0;

    public MessageSenderImpl(final CULInterface ioDevice) {
	this.ioDevice = ioDevice;
    }

    @Override
    public String sendCmd(final Device srcDevice, final Command command, final int i, final int j) throws IOException {
	this.increaseMsgCnt(command);

	String cmd = command.getCommand().substring(2);

	final int length = cmd.length() / 2 + 1;

	cmd = String.format("As%02X%02X%s", length, this.cmdCount, cmd);

	this.ioDevice.sendRAW(cmd);

	return cmd;
    }

    private void increaseMsgCnt(final Command command) {
	final String mnStr = command.getCommand().substring(0, 2);
	if (mnStr.equals("++")) {
	    increaseMessageCount();
	} else {
	    this.cmdCount = Integer.valueOf(mnStr, 16);
	}
    }

    private void increaseMessageCount() {
	this.cmdCount = this.cmdCount != -1 ? this.cmdCount + 1 : 1;
	if (this.cmdCount > 255) {
	    this.cmdCount -= 255;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.gebauer.cul.homematic.out.MessageSender#processCmdStack(de.gebauer
     * .cul.homematic.device.Device)
     */
    @Override
    public String processCmdStack(final Device device) throws IOException {
	if (!device.getCommandStack().isEmpty() && device.getResponseWait() == null) {
	    // LOG.debug("Processing command stack");
	    return this.sendCmd(device, device.getCommandStack().poll(), 1, 1);
	}
	LOG.debug("Nothing to send for " + device);
	return null;
    }

    @Override
    public void send(final Event event) {

	// A 0C 2A A4 41 2190C5 13C86C 01 26 C8
	// As 0B 2A 80 02 13C86C 2190C5 01 00

	// ACK response to WindowStateEvent:
	// As 0B 19 80 02 13C86C 2190C5 01 00

	String cmd = String.format("%s%s%s%s%02X%02X",
		"80", "02", event.getSender().getId(), event.getReceiver().getId(), 1, 0);

	int length = cmd.length() / 2 + 1;
	int msgCount = event.getReceiver().getLastEvent().getCount();

	event.getRawMessage().dst = event.getReceiver().getId();
	event.getRawMessage().length = String.format("%02X", length);
	event.getRawMessage().msgCount = String.format("%02X", msgCount);
	event.getRawMessage().msgFlag = "80";
	event.getRawMessage().msgType = "02";
	event.getRawMessage().p = cmd;
	event.getRawMessage().src = event.getSender().getId();

	final String command = String.format("As%02X%02X%s", length, msgCount, cmd);

	LOG.info("Sending event " + event);
	this.ioDevice.sendRAW(command);
    }
}
