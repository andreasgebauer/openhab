package de.gebauer.cul.homematic.out;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.CommunicationHandler;
import de.gebauer.homematic.Message;
import de.gebauer.homematic.MessageFlag;
import de.gebauer.homematic.MessageType;
import de.gebauer.homematic.device.AbstractDevice;
import de.tobiaswegner.communication.cul4java.CULInterface;

/**
 * Sends commands and manages the command stack.
 * 
 * @author Andreas Gebauer
 */
public class MessageSenderImpl implements MessageSender {

    private static final int MILLIS_WAIT_FOR_ACK = 150;

    public static class WrappedMessage implements Message {

	private Message wrapped;
	public short resendCount;

	public WrappedMessage(Message wrapped) {
	    this.wrapped = wrapped;
	}

	@Override
	public MessageType getType() {
	    return wrapped.getType();
	}

	@Override
	public int getCount() {
	    return wrapped.getCount();
	}

	@Override
	public boolean isBroadCast() {
	    return wrapped.isBroadCast();
	}

	@Override
	public RawMessage getRawMessage() {
	    return wrapped.getRawMessage();
	}

	@Override
	public void setRawMessage(RawMessage msg) {
	    this.wrapped.setRawMessage(msg);
	}

	@Override
	public AbstractDevice getSender() {
	    return this.wrapped.getSender();
	}

	@Override
	public AbstractDevice getDestination() {
	    return this.wrapped.getDestination();
	}

	@Override
	public int getChannel() {
	    return this.wrapped.getChannel();
	}

	@Override
	public String getPayload() {
	    return this.wrapped.getPayload();
	}

	@Override
	public boolean needsAck() {
	    return this.wrapped.needsAck();
	}

	@Override
	public boolean hasAck() {
	    return this.wrapped.hasAck();
	}

	@Override
	public void setAnswer(Message answer) {
	    this.wrapped.setAnswer(answer);
	}

	@Override
	public String toString() {
	    return wrapped.toString();
	}

    }

    private static final Logger LOG = LoggerFactory.getLogger(MessageSenderImpl.class);
    private static final Logger MESSAGES = LoggerFactory.getLogger("MESSAGES");

    private final CULInterface ioDevice;

    private int cmdCount = 0;
    private ExecutorService scheduler = Executors.newSingleThreadExecutor();

    public MessageSenderImpl(final CULInterface ioDevice) {
	this.ioDevice = ioDevice;
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
     * @see de.gebauer.cul.homematic.out.MessageSender#processCmdStack(de.gebauer .cul.homematic.device.Device)
     */
    @Override
    public void processCmdStack(final AbstractDevice destination) throws IOException {
	if (destination.getEventStack().isEmpty()) {
	    LOG.debug("Nothing to send for " + destination);
	    return;
	}

	LOG.debug("Processing send stack of {}: {}", destination, destination.getEventStack());
	this.scheduler.submit(new CommunicationHandler(this, wrap(destination.getEventStack().poll()), Calendar.getInstance()));
    }

    private WrappedMessage wrap(Message poll) {
	return new WrappedMessage(poll);
    }

    @Override
    public void send(final WrappedMessage message) throws IOException {

	// A 0C 2A A4 41 2190C5 13C86C 01 26 C8
	// A 0B 2A 80 02 13C86C 2190C5 01 00

	// ACK response to WindowStateEvent:
	// A 0B 19 80 02 13C86C 2190C5 01 00

	String messageType = null;
	MessageFlag messageFlag = message.getRawMessage().getMsgFlag();
	String payload = message.getPayload();

	if (payload == null) {
	    LOG.warn("No message content created for {}", message);
	    return;
	}

	final String sender = message.getSender().getId();
	final String destination = message.getDestination().getId();

	// ty fl src dst pl
	final String command = String.format("%02X%02X%s%s%s", messageFlag.val, message.getType().getInt(), sender,
		destination, payload);

	final int length = command.length() / 2 + 1;
	final Message lastEvent = message.getDestination().getLastEventReceived();

	final int msgCount = lastEvent != null ? lastEvent.getCount() + 1 : 1;

	if (message.getRawMessage() != null && message.getRawMessage().getMsgType() != null) {
	    messageType = message.getRawMessage().getMsgType().getStr();
	}

	message.setRawMessage(new RawMessageBuilder()
		.setDst(destination)
		.setLength(String.format("%02X", length))
		.setMsgCount(String.format("%02X", msgCount))
		.setMsgFlag(String.format("%02X", messageFlag.val))
		.setMsgType(messageType)
		.setPayload(payload)
		.setSrc(sender).build()
		);

	final String rawCommand = String.format("As%02X%02X%s", length, msgCount, command);

	MESSAGES.info(message.getRawMessage().toString());
	LOG.info("Sending event " + message);
	this.ioDevice.sendRAW(rawCommand);
	message.getDestination().messageSent(message);

	if (message.needsAck() && !message.hasAck()) {
	    if (message.resendCount == 3) {
		LOG.warn("3 retries failed. Giving up.");
		return;
	    }

	    try {
		Thread.sleep(MILLIS_WAIT_FOR_ACK);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	    if (message.hasAck()) {
		return;
	    }

	    this.scheduler.submit(new CommunicationHandler(this, message, Calendar.getInstance()));
	}

    }

    @Override
    public void tearDown() {
	this.scheduler.shutdown();
    }
}
