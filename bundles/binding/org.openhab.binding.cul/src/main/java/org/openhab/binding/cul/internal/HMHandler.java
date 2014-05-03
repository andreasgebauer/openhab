package org.openhab.binding.cul.internal;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.regex.Matcher;

import org.openhab.binding.cul.internal.binding.HomeMaticBindingConfig;
import org.openhab.core.library.items.DimmerItem;
import org.openhab.core.library.types.DateTimeType;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.OpenClosedType;
import org.openhab.core.library.types.PercentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.DeviceState;
import de.gebauer.homematic.Utils;
import de.gebauer.homematic.command.PairingCommand;
import de.gebauer.homematic.command.SimpleCommand;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.Model;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.hmcctc.TemperaturePeriodEvent;
import de.gebauer.homematic.hmcctc.WeatherEvent;
import de.gebauer.homematic.hmccvd.ClimateMessage;
import de.gebauer.homematic.hmccvd.ValveData;
import de.gebauer.homematic.hmlcdim1tpi2.DimmerStateChangeEvent;
import de.gebauer.homematic.hmsecsc.ShutterStateEvent;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.AckStatusEvent;
import de.gebauer.homematic.msg.ConfigRegisterReadMessage;
import de.gebauer.homematic.msg.DeviceInfoEvent;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageFlag;
import de.gebauer.homematic.msg.MessageType;
import de.gebauer.homematic.msg.ParamResponseMessage;

public class HMHandler {

    private static final Logger LOG = LoggerFactory.getLogger(HMHandler.class);

    private final VirtualCCU ccu;

    private HomematicCULBinding homematicCULBinding;

    public HMHandler(VirtualCCU ccu, HomematicCULBinding homematicCULBinding) {
	this.ccu = ccu;
	this.homematicCULBinding = homematicCULBinding;
    }

    /**
     * Method handling the event.
     *
     * @throws
     */
    protected void receivedMessage(final Message message) {
	LOG.debug(MessageFormat.format("Received {0}", message));

	communication(message);

	HomeMaticBindingConfig config = this.homematicCULBinding.getWritableBindingForAddress(message.getSource().getName() + ":" + "LASTMESSAGEDATE");
	if (config != null) {
	    this.homematicCULBinding.postUpdate(config.getItem().getName(), new DateTimeType(Calendar.getInstance()));
	}

	config = this.homematicCULBinding.getWritableBindingForAddress(message.getSource().getName() + ":" + "MESSAGECOUNT");
	if (config != null) {
	    this.homematicCULBinding.postUpdate(config.getItem().getName(), new DecimalType(message.getCount()));
	}

	config = this.homematicCULBinding.getWritableBindingForAddress(message.getSource().getName() + ":" + "RSSI");
	if (config != null) {
	    int rssi = message.getRSSI();
	    if (rssi > 127) {
		rssi -= 255;
	    }
	    this.homematicCULBinding.postUpdate(config.getItem().getName(), new DecimalType(rssi));
	}

	if (message instanceof WeatherEvent) {
	    final BigDecimal temperature = ((WeatherEvent) message).getTemperature();
	    final int humidity = ((WeatherEvent) message).getHumidity();

	    config = this.homematicCULBinding.getWritableBindingForAddress(message.getSource().getName() + ":" + "TEMPERATURE");
	    if (config != null) {
		this.homematicCULBinding.postUpdate(config.getItem().getName(), new DecimalType(temperature));
	    }
	    config = this.homematicCULBinding.getWritableBindingForAddress(message.getSource().getName() + ":" + "HUMIDITY");
	    if (config != null) {
		this.homematicCULBinding.postUpdate(config.getItem().getName(), new PercentType(humidity));
	    }
	} else if (message instanceof ClimateMessage) {
	    final int command = ((ClimateMessage) message).getValvePos();

	    config = this.homematicCULBinding.getWritableBindingForAddress(message.getDestination().getName() + ":" + "DESIRED");
	    if (config != null) {
		this.homematicCULBinding.postUpdate(config.getItem().getName(), new PercentType(command));
	    }
	} else if (message instanceof AckStatusEvent) {
	    DeviceState deviceState = ((AckStatusEvent) message).getDeviceState();
	    if (deviceState instanceof ValveData) {
		ValveData valveData = (ValveData) deviceState;
		config = this.homematicCULBinding.getWritableBindingForAddress(message.getSource().getName() + ":" + "VALVEPOS");
		if (config != null) {
		    final int valvePos = valveData.getPosition();
		    this.homematicCULBinding.postUpdate(config.getItem().getName(), new PercentType(valvePos));
		}
		config = this.homematicCULBinding.getWritableBindingForAddress(message.getSource().getName() + ":" + "BATTERY");
		if (config != null) {
		    final int valvePos = valveData.getPosition();
		    this.homematicCULBinding.postUpdate(config.getItem().getName(), new PercentType(valvePos));
		}
	    }
	}
	else if (message instanceof ShutterStateEvent) {
	    config = this.homematicCULBinding.getWritableBindingForAddress(message.getSource().getName() + ":" + "STATE");
	    if (config != null) {
		this.homematicCULBinding.postUpdate(config.getItem().getName(), ((ShutterStateEvent) message).isClosed() ? OpenClosedType.CLOSED
			: OpenClosedType.OPEN);
	    }
	} else if (message instanceof DimmerStateChangeEvent) {
	    config = this.homematicCULBinding.getWritableBindingForAddress(message.getSource().getName() + ":" + "DIM");
	    if (config != null) {
		final int state = ((DimmerStateChangeEvent) message).getState() / 2;
		((DimmerItem) config.getItem()).setState(new PercentType(state));
		this.homematicCULBinding.postUpdate(config.getItem().getName(), new PercentType(state));
	    }
	} else if (message instanceof TemperaturePeriodEvent) {
	    // TODO need to send special ACK!?!
	    // event.getSender().addToSendQueue(new AckStatusMessage(ccu, event.getSender(), (short) 2));
	}

    }

    private void communication(final Message message) {
	if (message instanceof DeviceInfoEvent) {
	    // pairing
	    final String serNo = message.getSource().getInfo().serNo;
	    final RawMessage msg = message.getRawMessage();

	    if (!this.ccu.isPairingEnabled()) {
		LOG.info("Pairing not enabled.");
		return;
	    }

	    if (msg.getSrc().equals(this.ccu.getId())) {
		// repeater?
		return;
	    } else if (msg.getMsgType() == MessageType.UNKNOWN2 && msg.getMsgFlag() == MessageFlag.VAL_00) {
		// TODO why?
		return;
	    }
	    final AbstractDevice destination = message.getDestination();
	    if (destination != null && destination.getId() != this.ccu.getId()) {
		LOG.info("Not our pairing request.");
		return;
	    }

	    LOG.info("Initiating Pairing. clearing messages.");
	    message.getSource().getCommandStack().clear();
	    final RawMessageBuilder msgBuilder = new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_A0);

	    // 02010A130BC80C6D
	    if (message.getSource().getInfo().mdl == Model.HMCCVD) {
		// ACT as TC
		final PairingCommand pairingCommand = new PairingCommand();
		final DeviceInfo info = new DeviceInfo("21", Model.HMCCTC, "0000000000");
		final String pAddr = "000000";
		final short chnl = (short) 01;
		final short pList = (short) 5;
		final short pChnl = (short) 0;

		// [1EA808->1C475A #48; len=1A, flag=VAL_A0, type=UNKNOWN, p=21 0039 4A455130373039393232 58 00 02 00]
		pairingCommand.add(new DeviceInfoEvent(msgBuilder.build(), this.ccu, message.getSource(), info, (short) 0x00, (short) 0x02, "00"));

		// [1EA808->1C475A #49; len=10, flag=VAL_A0, type=CONFIG, p=0104 000000 00 05] ConfigRegisterReadCommand
		final AbstractMessageParameter msgParam = new AbstractMessageParameter(msgBuilder.build(), this.ccu, message.getSource(), chnl);
		pairingCommand.add(new ConfigRegisterReadMessage(msgParam, pAddr, pChnl, pList));
		message.getSource().addToSendQueue(pairingCommand);
	    } else {
		message.getSource().addToSendQueue(new PairingCommand(this.ccu, message.getSource()));
	    }

	    this.ccu.setHmPairSerial(serNo);
	}

	if (!message.isBroadCast()) {
	    final Message request = message.getDestination().getEventSend(message.getCount());
	    // if we have sent a request the we add the response as answer
	    // TODO consider time passed by since we sent the message
	    if (request != null && request.getSource().equals(this.ccu)) {
		// add that as answer no matter if successful or not
		request.setResponse(message);

		if (message instanceof ParamResponseMessage) {
		    final ParamResponseMessage paramResponseMessage = (ParamResponseMessage) message;
		    final ConfigRegisterReadMessage configReadRequest = (ConfigRegisterReadMessage) request;
		    final Matcher matcher = Utils.matcherFor(paramResponseMessage.getData(), ".* 00:00$");
		    if (matcher.matches()) {
			if (configReadRequest.getPeerList() == 0) {
			    LOG.info("Successfully paired {} with {} ", message.getSource(), message.getDestination());
			}
		    }
		}
	    }
	}

	if (this.ccu.equals(message.getDestination())) {
	    if (message.needsAck()) {
		final RawMessage build = new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_80).setPayload(String.format("%02X", 0)).build();
		message.getSource().addToSendQueue(new SimpleCommand(new AckStatusEvent(build, this.ccu, message.getSource(), message.getChannel())));
	    }

	    final Message request = message.getDestination().getEventSend(message.getCount());
	    if (request != null && request.getSource().equals(this.ccu)) {
		if (message instanceof AckStatusEvent || message instanceof ParamResponseMessage) {
		    // if pairing is in progress
		    if (this.ccu.getHmPairSerial() != null
			    && this.ccu.getHmPairSerial().equals(message.getSource().getInfo().serNo)
			    && message.getSource().getCommandStack().isEmpty()) {
			if (request.hasAck()) {
			    LOG.info("Successfully paired CCU with " + message.getSource());
			    this.ccu.pairedWith(message.getSource());
			    this.ccu.setHmPairSerial(null);
			}
		    }
		}
	    }
	}
    }
}
