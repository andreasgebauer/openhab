package org.openhab.binding.cul.internal;

import java.math.BigDecimal;
import java.util.Calendar;

import org.openhab.binding.cul.internal.binding.HomeMaticBindingConfig;
import org.openhab.core.library.items.DimmerItem;
import org.openhab.core.library.types.DateTimeType;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.OpenClosedType;
import org.openhab.core.library.types.PercentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.homematic.DeviceState;
import de.gebauer.homematic.hmcctc.TemperaturePeriodEvent;
import de.gebauer.homematic.hmcctc.WeatherEvent;
import de.gebauer.homematic.hmccvd.BatteryStatus;
import de.gebauer.homematic.hmccvd.ClimateMessage;
import de.gebauer.homematic.hmccvd.ValveData;
import de.gebauer.homematic.hmlcdim1tpi2.DimmerStateChangeEvent;
import de.gebauer.homematic.hmlcsw1pbufm.HMLCSW1PBUFMInterpreter;
import de.gebauer.homematic.hmlcsw1pbufm.SwitchStateMessage;
import de.gebauer.homematic.hmsecsc.ShutterStateEvent;
import de.gebauer.homematic.msg.AckStatusEvent;
import de.gebauer.homematic.msg.Message;

/**
 * Transforms incoming messages into openHAB events.
 * 
 * @author andreas
 *
 */
public class HMHandler {

    private static final Logger LOG = LoggerFactory.getLogger(HMHandler.class);

    private HomematicCULBinding homematicCULBinding;

    public HMHandler(HomematicCULBinding homematicCULBinding) {
	this.homematicCULBinding = homematicCULBinding;
    }

    /**
     * Method handling the event.
     *
     * @throws
     */
    protected void receivedMessage(final Message message) {

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
	    int rssi = message.getRSSI() + 32;
	    if (rssi > 255) {
		rssi -= 256;
	    }
	    this.homematicCULBinding.postUpdate(config.getItem().getName(), new PercentType(rssi));
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
		    final BatteryStatus batStatus = valveData.getBatteryStatus();
		    this.homematicCULBinding.postUpdate(config.getItem().getName(), batStatus == BatteryStatus.OK ? OnOffType.ON : OnOffType.OFF);
		}
	    } else if (deviceState instanceof HMLCSW1PBUFMInterpreter.SwitchState) {
		config = this.homematicCULBinding.getWritableBindingForAddress(message.getSource().getName() + ":" + "STATE");
		if (config != null) {
		    boolean on = ((SwitchStateMessage) message).getState().isOn();
		    this.homematicCULBinding.postUpdate(config.getItem().getName(), on ? OnOffType.ON : OnOffType.OFF);
		}
	    }
	} else if (message instanceof ShutterStateEvent) {
	    config = this.homematicCULBinding.getWritableBindingForAddress(message.getSource().getName() + ":" + "STATE");
	    if (config != null) {
		this.homematicCULBinding.postUpdate(config.getItem().getName(), ((ShutterStateEvent) message).isClosed() ? OpenClosedType.CLOSED
			: OpenClosedType.OPEN);
	    }
	} else if (message instanceof SwitchStateMessage) {
	    config = this.homematicCULBinding.getWritableBindingForAddress(message.getSource().getName() + ":" + "STATE");
	    if (config != null) {
		boolean on = ((SwitchStateMessage) message).getState().isOn();
		this.homematicCULBinding.postUpdate(config.getItem().getName(), on ? OnOffType.ON : OnOffType.OFF);
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

}
