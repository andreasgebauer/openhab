package de.gebauer.homematic.hmcctc;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;

import org.openhab.io.transport.cul.CULCommunicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.cul.homematic.out.MessageSender;
import de.gebauer.cul.homematic.out.MessageSenderImpl.WrappedMessage;
import de.gebauer.homematic.CommunicationHandler;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.command.AbstractCommand;
import de.gebauer.homematic.command.Command;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.MessageSentListener;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.ConfigEndMessage;
import de.gebauer.homematic.msg.ConfigStartMessage;
import de.gebauer.homematic.msg.ConfigWriteMessage;
import de.gebauer.homematic.msg.HaveDataMessage;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageFlag;

public class ThermoControlDevice extends AbstractDevice implements ThermoControl {

    private static final class CommHandler extends CommunicationHandler implements MessageSentListener {

	private static final Logger LOG = LoggerFactory.getLogger(CommHandler.class);
	private boolean broadCastReceived = false;
	private Object monitor = new Object();

	private CommHandler(MessageSender sender, Command message, Calendar ts) {
	    super(sender, message, ts);
	}

	@Override
	public void run() {
	    Message msg = this.command.getNextMessage();

	    WrappedMessage wrappedMsg = new WrappedMessage(msg);

	    try {
		// the first message

		do {
		    this.sender.send(wrappedMsg, 0, 1);
		    try {
			synchronized (this.monitor) {
			    monitor.wait(5000);
			}
		    } catch (final InterruptedException e) {
		    }
		} while (!broadCastReceived);

		try {
		    Thread.sleep(100);
		} catch (InterruptedException e) {
		}

		super.run();

	    } catch (CULCommunicationException e) {
		this.command.failure();
	    }
	}

	@Override
	public void messageSent(Message event) {
	    LOG.debug("Received {} from ThermoControl", event);
	    if (event.isBroadCast()) {
		this.broadCastReceived = true;
		synchronized (this.monitor) {
		    this.monitor.notify();
		}

	    }
	}
    }

    private static final DeviceMessageInterpreter interpreter = new HMCCTCInterpreter();

    public ThermoControlDevice(final String name, final String id, final DeviceInfo info) {
	super(name, id, info);
    }

    /**
     * <pre>
     * L:09 N:AC F:A1 CMD:12 SRC:13C86C DST:1EA808 (HAVE_DATA) (,WAKEUP,BIDI,RPTEN) 
     * L:10 N:AD F:A0 CMD:01 SRC:13C86C DST:1EA808 02050000000005 (CONFIG_START CHANNEL:0x02 PEER_ADDRESS:0x000000 PEER_CHANNEL:0x00 PARAM_LIST:0x05) (,BIDI,RPTEN)
     * L:0D N:AE F:A0 CMD:01 SRC:13C86C DST:1EA808 02080100 (CONFIG_WRITE_INDEX CHANNEL:0x02 DATA:0x0100) (,BIDI,RPTEN)
     * L:0B N:AF F:A0 CMD:01 SRC:13C86C DST:1EA808 0206 (CONFIG_END CHANNEL:0x02) (,BIDI,RPTEN)
     * </pre>
     * 
     * @param src
     * @param ctrlMode
     */
    @Override
    public boolean controlMode(final AbstractDevice src, final ControlMode ctrlMode) {
	final short channel = (short) 02;

	final RawMessageBuilder msgBuilder = new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_A1);

	SetControlModeCommand command = new SetControlModeCommand(1);
	command.add(new HaveDataMessage(msgBuilder.build(), src, this));

	msgBuilder.setMsgFlag(MessageFlag.VAL_A0);

	AbstractMessageParameter msgParam = new AbstractMessageParameter(msgBuilder.build(), src, this, channel);
	command.add(new ConfigStartMessage(msgParam, "000000", (short) 0, (short) 5));
	//
	// cent 0110 0001 0000
	// auto 0108 0000 1000
	// manu 0100 0000 0000
	// part 0118 0001 1000

	// 2014-10-29 got these values when sniffing FHEM:
	// cent 0120 0010 0000 (0x00)
	// auto 0128 0010 1000 (0x08)
	// manu 0130 0011 0000 (0x10)
	// part 0138 0011 1000 (0x18)

	// CENTRAL: 0120 (00)
	// AUTO: 0128 (01)
	// MANUAL: 0130 (10)
	// PARTY: 0138 (11)

	int magic = 0x0120;
	int i = (ctrlMode.getVal() << 3);

	String dataString = String.format("%04X", magic + i);
	command.add(new ConfigWriteMessage(msgParam, dataString));
	command.add(new ConfigEndMessage(msgParam));

	this.addToSendQueue(command);

	return false;
    }

    /**
     * 
     */
    // As 09 02 A1 12 13C86D 1EA6D2 (HAVE-DATA)
    // /A 0A 02 80 02 1EA6D2 13C86D 00 (ACK)
    // As 0C 03 A0 11 13C86D 1EA6D2 020222 (SET TEMP 17.0)
    // /A 0E 03 80 02 1EA6D2 13C86D 0102220041

    // im Abstand von : 5,23675s
    // [09:57:49.790] A 09 04 B1 12 13C86D 1EA6D2
    // [09:57:55.583] A 09 04 B1 12 13C86D 1EA6D2 + 5.793
    // [09:57:59.760] A 09 04 B1 12 13C86D 1EA6D2 + 4.177
    // [09:58:05.385] A 09 04 B1 12 13C86D 1EA6D2 + 5.625
    // [09:58:11.393] A 09 04 B1 12 13C86D 1EA6D2 + 6.008
    // [09:58:16.999] A 09 04 B1 12 13C86D 1EA6D2 + 5.606
    // [09:58:22.003] A 09 04 B1 12 13C86D 1EA6D2 + 5.004
    // [09:58:26.034] A 09 04 B1 12 13C86D 1EA6D2 + 4.031
    // [09:58:31.684] A 09 04 B1 12 13C86D 1EA6D2 + 5.650
    // [09:58:32.095] A 0C FE 86 70 1EA6D2 000000 00CC45
    // [09:58:32.201] A 09 05 A1 12 13C86D 1EA6D2
    // [09:58:32.333] A 0A 05 80 02 1EA6D2 13C86D 00
    // [09:58:32.447] A 0C 06 A0 11 13C86D 1EA6D2 020226
    // [09:58:32.579] A 0E 06 80 02 1EA6D2 13C86D 0102260041

    // A 09 05 A1 12 13C86D 1EA6D2 28

    // we sent
    // A 09 59 A1 12 13C86D 206185 1B -> no response

    @Override
    public boolean setTemperature(AbstractDevice src, BigDecimal temp) {

	final AbstractCommand command = new AbstractCommand() {
	    @Override
	    public int getRetryCount() {
		return 20;
	    }

	    @Override
	    public boolean hasCustomCommunicationHandler() {
		return true;
	    }

	    @Override
	    public CommunicationHandler getCommunicationHandler(MessageSender sender) {
		CommHandler communicationHandler = new CommHandler(sender, this, Calendar.getInstance());
		ThermoControlDevice.this.registerMessageListener(communicationHandler);
		return communicationHandler;
	    }

	};

	final RawMessageBuilder msgBuilder = new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_B1);
	HaveDataMessage message = new HaveDataMessage(msgBuilder.build(), src, this);
	command.add(message);

	msgBuilder.setMsgFlag(MessageFlag.VAL_A1);
	command.add(new HaveDataMessage(msgBuilder.build(), src, this));

	AbstractMessageParameter msgParam = new AbstractMessageParameter(msgBuilder.build(), src, this, (short) 0x02);
	command.add(new SetDesiredTemperatureMessage(msgParam, temp));

	this.addToSendQueue(command);

	return true;
    }

    @Override
    public Method[] getCommands() {
	return ThermoControl.class.getDeclaredMethods();
    }

    @Override
    public DeviceMessageInterpreter getInterpreter() {
	return interpreter;
    }

}
