package de.gebauer.cul.homematic.out;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openhab.io.transport.cul.CULCommunicationException;
import org.openhab.io.transport.cul.CULHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.PendType;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.CommunicationHandler;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.Channelable;
import de.gebauer.homematic.msg.AbstractConfigMessage;
import de.gebauer.homematic.msg.ConfigPeerListMessage;
import de.gebauer.homematic.msg.ConfigRegisterReadMessage;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageFlag;
import de.gebauer.homematic.msg.MessageType;

/**
 * Sends commands and manages the command stack.
 * 
 * @author Andreas Gebauer
 */
public class MessageSenderImpl implements MessageSender {

    public static class WrappedMessage implements Message {

	private final Message wrapped;
	public short resendCount;

	public WrappedMessage(final Message wrapped) {
	    this.wrapped = wrapped;
	}

	@Override
	public MessageType getType() {
	    return this.wrapped.getType();
	}

	@Override
	public int getCount() {
	    return this.wrapped.getCount();
	}

	@Override
	public boolean isBroadCast() {
	    return this.wrapped.isBroadCast();
	}

	@Override
	public RawMessage getRawMessage() {
	    return this.wrapped.getRawMessage();
	}

	@Override
	public void setRawMessage(final RawMessage msg) {
	    this.wrapped.setRawMessage(msg);
	}

	@Override
	public AbstractDevice getSource() {
	    return this.wrapped.getSource();
	}

	@Override
	public AbstractDevice getDestination() {
	    return this.wrapped.getDestination();
	}

	@Override
	public short getChannel() {
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
	public void setResponse(final Message answer) {
	    this.wrapped.setResponse(answer);
	}

	@Override
	public String toString() {
	    return this.wrapped.toString();
	}

	public Message getWrapped() {
	    return this.wrapped;
	}

	@Override
	public Message getRequest() {
	    return this.wrapped.getRequest();
	}

	@Override
	public Calendar getTimestamp() {
	    return wrapped.getTimestamp();
	}

	@Override
	public int getRSSI() {
	    return this.wrapped.getRSSI();
	}

    }

    private static final Logger LOG = LoggerFactory.getLogger(MessageSenderImpl.class);
    private static final Logger MESSAGES = LoggerFactory.getLogger("MESSAGES");

    private final CULHandler ioDevice;

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public MessageSenderImpl(final CULHandler ioDevice) {
	this.ioDevice = ioDevice;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.gebauer.cul.homematic.out.MessageSender#processCmdStack(de.gebauer .cul.homematic.device.Device)
     */
    @Override
    public void processCmdStack(final AbstractDevice destination) throws IOException {
	if (destination.getCommandStack().isEmpty()) {
	    LOG.trace("Nothing to send for " + destination);
	    return;
	}

	LOG.debug("Processing send stack of {}: {} messages pending", destination, destination.getCommandStack().size());
	this.executor.submit(new CommunicationHandler(this, destination.getCommandStack().poll(), Calendar.getInstance()));
    }

    @Override
    public void send(final WrappedMessage message, int retryCount) throws CULCommunicationException {

	// A 0C 2A A4 41 2190C5 13C86C 01 26 C8
	// A 0B 2A 80 02 13C86C 2190C5 01 00

	// ACK response to WindowStateEvent:
	// A 0B 19 80 02 13C86C 2190C5 01 00

	final String payload = message.getPayload();
	final MessageFlag messageFlag = message.getRawMessage().getMsgFlag();
	final MessageType messageType;
	if (message.getRawMessage() != null && message.getRawMessage().getMsgType() != null) {
	    messageType = message.getRawMessage().getMsgType();
	} else {
	    messageType = message.getType();
	}

	if (payload == null) {
	    LOG.warn("No message content created for {}", message);
	    return;
	}

	final AbstractDevice src = message.getSource();
	final AbstractDevice dst = message.getDestination();

	final Message lastEvent = dst.getLastEventReceived();

	final int msgCount = lastEvent != null ? (retryCount == 0 ? lastEvent.getCount() + 1 : lastEvent.getCount()) : 1;

	// fl ty src dst pl
	final String command = String.format("%02X%02X%s%s%s",
		messageFlag.val,
		messageType.getInt(),
		src.getId(),
		dst.getId(),
		payload);

	final int length = command.length() / 2 + 1;

	final String rawCommand = String.format("As%02X%02X%s", length, msgCount, command);

	RawMessage rawMessage = new RawMessageBuilder()
		.setDst(dst.getId())
		.setLength(String.format("%02X", length))
		.setMsgCount(String.format("%02X", msgCount))
		.setMsgFlag(messageFlag)
		.setMsgType(messageType)
		.setPayload(payload)
		.setSrc(src.getId()).build();

	MESSAGES.info(rawMessage.toString());

	message.setRawMessage(rawMessage);

	LOG.info("Sending " + message);
	this.ioDevice.send(rawCommand);
	src.messageSent(message);
	dst.messageReceived(message);

	responseSetup(dst, message.getWrapped());
    }

    private void responseSetup(AbstractDevice dvc, Message cmd)
    {
	// store all we need to handle the response
	// setup repeatTimer and cmdStackControll
	if (cmd instanceof AbstractConfigMessage) {
	    AbstractConfigMessage configCmd = (AbstractConfigMessage) cmd;
	    if (configCmd instanceof ConfigPeerListMessage) {
		// PeerList-------------
		// --- remember request params in device level
		dvc.getResponseWait().setPending(PendType.PEER_LIST);
		dvc.getResponseWait().forChannel(cmd.getChannel()); // channel info we await

		// define timeout - holdup cmdStack until response complete or timeout
		// InternalTimer(gettimeofday()+rTo, "CUL_HM_respPendTout", "respPend:$dst", 0);

		// --- remove readings in channel
		Channelable chnhash = dvc.getChannel(cmd.getChannel());
		// empty old list
		if (chnhash == null) {
		    chnhash = dvc;
		}

		return;
	    } else if (configCmd instanceof ConfigRegisterReadMessage) {
		// RegisterRead-------
		String peerId = configCmd.getPeerId();
		short list = configCmd.getPeerList();

		// $peerID = ($peerID ne "00000000")?CUL_HM_id2Name($peerID):"";
		// $peerID =~ s/ /_/g;//subs blanks
		// --- set messaging items
		dvc.getResponseWait().setPending(PendType.REGISTER_READ);
		dvc.getResponseWait().forChannel(configCmd.getPeerChannel());
		dvc.getResponseWait().forList(configCmd.getPeerList());
		dvc.getResponseWait().forPeer(configCmd.getPeerId());// this is the HMid + channel

		// define timeout - holdup cmdStack until response complete or timeout
		// InternalTimer(gettimeofday()+$rTo,"CUL_HM_respPendTout","respPend:$dst", 0);#todo General change timer to 1.5
		// --- remove channel entries that will be replaced

		if (list != 3 && list != 4) {
		    peerId = null;
		}

		Channelable chnhash = dvc.getChannel(cmd.getChannel());
		if (chnhash == null) {
		    chnhash = dvc;
		}
		chnhash.setRegL(list, peerId, null);

		// $chnhash = $hash if(!$chnhash);

		// empty val since reading will be cumulative
		// chnhash->{READINGS}{"RegL_".$list.":".$peerID}{VAL}="";
		// delete ($chnhash->{READINGS}{"RegL_".$list.":".$peerID}{TIME});
		return;
	    } else if (configCmd.getSubType() == 0x0E) {
		// StatusReq----------
		// --- set messaging items
		dvc.getResponseWait().setPending(PendType.STATUS_REQUEST);
		dvc.getResponseWait().forChannel(cmd.getChannel());

		// define timeout - holdup cmdStack until response complete or timeout
		// InternalTimer(gettimeofday()+$rTo, "CUL_HM_respPendTout", "respPend:$dst", 0);
		return;
	    }
	}
	if (cmd.needsAck()) {
	    dvc.getResponseWait().setPending(PendType.ACK);
	    dvc.getResponseWait().forCmd(cmd);
	    // my $iohash = hash->{IODev};
	    // //$hash->{helper}{respWait}{Pending}= "Ack";
	    // $hash->{helper}{respWait}{cmd} = $cmd;
	    // $hash->{helper}{respWait}{msgId} = $msgId; #msgId we wait to ack
	    // $hash->{helper}{respWait}{reSent} = 1;
	    //
	    // short off = 2;
	    // //$off += 0.15*int(@{$iohash->{QUEUE}}) if($iohash->{QUEUE});
	    // InternalTimer(gettimeofday()+$off, "CUL_HM_Resend", $hash, 0);
	}
    }

    @Override
    public void tearDown() {
	this.executor.shutdown();
    }
}
