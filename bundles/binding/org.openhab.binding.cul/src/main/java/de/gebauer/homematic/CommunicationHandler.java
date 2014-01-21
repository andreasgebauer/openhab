package de.gebauer.homematic;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.out.MessageSender;
import de.gebauer.cul.homematic.out.MessageSenderImpl.WrappedMessage;
import de.gebauer.homematic.command.Command;
import de.gebauer.homematic.command.PairingCommand;
import de.gebauer.homematic.msg.Message;

public class CommunicationHandler extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(CommunicationHandler.class);

    private static final int MILLIS_WAIT_FOR_ACK = 200;
    private static final int MAX_RETRIES = 5;

    private final Command command;
    private final Calendar timestampSend;
    private final MessageSender sender;

    public CommunicationHandler(final MessageSender sender, final Command message, final Calendar ts) {
	this.sender = sender;
	this.command = message;
	this.timestampSend = ts;
    }

    @Override
    public void run() {
	Message msg = null;
	boolean success = false;
	while ((msg = this.command.getNextMessage()) != null) {
	    final WrappedMessage wrappedMsg = new WrappedMessage(msg);
	    int resendCount = 0;
	    try {
		do {
		    this.sender.send(wrappedMsg);
		    resendCount++;
		    try {
			Thread.sleep(MILLIS_WAIT_FOR_ACK);
		    } catch (final InterruptedException e) {
			e.printStackTrace();
		    }
		} while (wrappedMsg.needsAck() && !wrappedMsg.hasAck() && resendCount < MAX_RETRIES);

		if (wrappedMsg.needsAck() && !wrappedMsg.hasAck() && resendCount == MAX_RETRIES) {
		    LOG.warn("{} retries failed. Giving up.", MAX_RETRIES);
		    break;
		}
	    } catch (final Exception e) {
		LOG.error("", e);
	    }
	}

	if (success) {
	    this.command.success();
	} else {
	    this.command.failure();
	}
    }
}
