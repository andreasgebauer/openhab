package de.gebauer.cul.homematic.out;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.device.Command;
import de.gebauer.cul.homematic.device.Device;
import de.gebauer.homematic.AckStatusEvent;
import de.gebauer.homematic.DimCommand;
import de.gebauer.homematic.Event;
import de.gebauer.homematic.WindowStateEvent;
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
    public String sendCmd(final Device destination, final Command command) throws IOException {
	this.increaseMsgCnt(command);

	String cmd = command.toString().substring(2);

	final int length = cmd.length() / 2 + 1;

	cmd = String.format("As%02X%02X%s", length, this.cmdCount, cmd);

	this.ioDevice.sendRAW(cmd);

	return cmd;
    }

    private void increaseMsgCnt(final Command command) {
	final String mnStr = command.toString().substring(0, 2);
	if (mnStr.equals("++")) {
	    this.increaseMessageCount();
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
    public void processCmdStack(final Device destination) throws IOException {
	if (!destination.getEventStack().isEmpty()) {
	    LOG.info("Processing send stack of {}: {}", destination, destination.getEventStack());
	    this.send(destination.getEventStack().poll());
	} else if (!destination.getCommandStack().isEmpty()) {
	    // LOG.debug("Processing command stack");
	    LOG.info("Processing send stack of {}: {}", destination, destination.getCommandStack());
	    this.sendCmd(destination, destination.getCommandStack().poll());
	}
	LOG.debug("Nothing to send for " + destination);
    }

    @Override
    public void send(final Event event) throws IOException {

	// A 0C 2A A4 41 2190C5 13C86C 01 26 C8
	// A 0B 2A 80 02 13C86C 2190C5 01 00

	// ACK response to WindowStateEvent:
	// A 0B 19 80 02 13C86C 2190C5 01 00

	// command for dimmer:
	// 0201 00 00 00 OFF
	// 0201 00 03 20 FFFF 0%
	// 0201 20 03 20 FFFF 10%
	// 0201 28 03 20 FFFF 20%
	// 0201 64 03 20 FFFF 50%
	// 0201 C8 03 20 FFFF 100%
	// 0201 C8 00 00 ON
	// 10 27 A0 11 13C86C 20E916 0201140320FFFF
	// 0E 00 A0 11 13C86D 20E916 0201C80000
	// As1000A01113C86D20E9160201000320FFFF

	String messageType = null;
	String messageFlag = null;
	String payload = null;

	if (event instanceof WindowStateEvent) {
	    messageFlag = "80";
	    messageType = "02";
	    payload = String.format("%02X%02X", 1, 0);
	} else if (event instanceof DimCommand) {
	    messageFlag = "A0";
	    messageType = "11";
	    payload = event.getRawMessage().p;
	} else if (event instanceof AckStatusEvent) {
	    messageFlag = "80";
	    messageType = "02";
	    payload = String.format("%02X", 0);
	}

	if (payload != null) {
	    final String sender = event.getSender().getId();
	    final String destination = event.getDestination().getId();

	    final String command = String.format("%s%s%s%s%s", messageFlag, messageType, sender, destination, payload);

	    final int length = command.length() / 2 + 1;
	    final Event lastEvent = event.getDestination().getLastEvent();

	    final int msgCount = lastEvent != null ? lastEvent.getCount() : 1;

	    event.getRawMessage().dst = destination;
	    event.getRawMessage().length = String.format("%02X", length);
	    event.getRawMessage().msgCount = String.format("%02X", msgCount);
	    event.getRawMessage().msgFlag = messageFlag;
	    event.getRawMessage().msgType = messageType;
	    event.getRawMessage().p = payload;
	    event.getRawMessage().src = sender;

	    final String rawCommand = String.format("As%02X%02X%s", length, msgCount, command);

	    LOG.info("Sending event " + event);
	    this.ioDevice.sendRAW(rawCommand);

	    event.getDestination().addEvent(event);
	} else {
	    LOG.warn("No message created for {}", event);
	}

    }
}
