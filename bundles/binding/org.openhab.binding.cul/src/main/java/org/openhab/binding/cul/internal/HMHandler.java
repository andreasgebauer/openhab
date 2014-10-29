package org.openhab.binding.cul.internal;

import java.math.BigDecimal;
import java.util.Calendar;

import org.openhab.binding.cul.internal.binding.HomeMaticBindingConfig;
import org.openhab.core.library.types.DateTimeType;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.OpenClosedType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.library.types.StringType;
import org.openhab.core.types.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.homematic.DeviceState;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.hmcctc.TemperaturePeriodEvent;
import de.gebauer.homematic.hmcctc.TemperatureSetMessage;
import de.gebauer.homematic.hmcctc.WeatherEvent;
import de.gebauer.homematic.hmccvd.BatteryStatus;
import de.gebauer.homematic.hmccvd.ClimateMessage;
import de.gebauer.homematic.hmccvd.MotorError;
import de.gebauer.homematic.hmccvd.ValveConfigData;
import de.gebauer.homematic.hmccvd.ValveStateData;
import de.gebauer.homematic.hmlcdim1tpi2.DimmerState;
import de.gebauer.homematic.hmlcdim1tpi2.DimmerStateChangeEvent;
import de.gebauer.homematic.hmlcsw1pbufm.HMLCSW1PBUFMInterpreter;
import de.gebauer.homematic.hmlcsw1pbufm.HMLCSW1PBUFMInterpreter.SwitchState;
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

	updateIfExists(message.getSource(), "ACTIVE_TIMESTAMP", new DateTimeType(Calendar.getInstance()));
	updateIfExists(message.getSource(), "MESSAGE_COUNT", new DecimalType(message.getCount()));
	updateIfExists(message.getSource(), "RSSI", new DecimalType(message.getRSSI()));

	if (message instanceof WeatherEvent) {
	    final BigDecimal temperature = ((WeatherEvent) message).getTemperature();
	    final int humidity = ((WeatherEvent) message).getHumidity();
	    updateIfExists(message.getSource(), "TEMPERATURE", new DecimalType(temperature));
	    updateIfExists(message.getSource(), "HUMIDITY", new PercentType(humidity));
	} else if (message instanceof ClimateMessage) {
	    final int command = ((ClimateMessage) message).getValvePos();
	    updateIfExists(message.getDestination(), "DESIRED_POSITION", new PercentType(command));
	} else if (message instanceof AckStatusEvent) {
	    DeviceState deviceState = ((AckStatusEvent) message).getDeviceState();
	    if (deviceState instanceof ValveStateData) {
		ValveStateData state = (ValveStateData) deviceState;
		updateIfExists(message.getSource(), "POSITION", new PercentType(state.getPosition()));
		updateIfExists(message.getSource(), "BATTERY", new StringType(state.getBatteryStatus().name()));
		updateIfExists(message.getSource(), "MOTOR_ERROR", new StringType(state.getMotorError().name()));
		updateIfExists(message.getSource(), "MOTOR_STATE", new StringType(state.getMotorState().name()));
	    } else if (deviceState instanceof ValveConfigData) {
		ValveConfigData state = (ValveConfigData) deviceState;
		updateIfExists(message.getDestination(), "ERROR_POSITION", new PercentType(state.getErrorPosition()));
		updateIfExists(message.getDestination(), "OFFSET", new PercentType(state.getOffset()));
	    } else if (deviceState instanceof HMLCSW1PBUFMInterpreter.SwitchState) {
		SwitchState state = (HMLCSW1PBUFMInterpreter.SwitchState) deviceState;
		updateIfExists(message.getSource(), "STATE", state.isOn() ? OnOffType.ON : OnOffType.OFF);
	    }
	} else if (message instanceof ShutterStateEvent) {
	    ShutterStateEvent state = (ShutterStateEvent) message;
	    updateIfExists(message.getSource(), "STATE", state.isClosed() ? OpenClosedType.CLOSED : OpenClosedType.OPEN);
	} else if (message instanceof SwitchStateMessage) {
	    SwitchState state = ((SwitchStateMessage) message).getState();
	    updateIfExists(message.getSource(), "STATE", state.isOn() ? OnOffType.ON : OnOffType.OFF);
	} else if (message instanceof DimmerStateChangeEvent) {
	    DimmerState state = ((DimmerStateChangeEvent) message).getState();
	    updateIfExists(message.getSource(), "STATE", new PercentType(state.getVal()));
	    updateIfExists(message.getSource(), "OVERLOAD", state.isOverload() ? OnOffType.ON : OnOffType.OFF);
	    updateIfExists(message.getSource(), "OVERHEAT", state.isOverheat() ? OnOffType.ON : OnOffType.OFF);
	    updateIfExists(message.getSource(), "REDUCED", state.isReduced() ? OnOffType.ON : OnOffType.OFF);
	} else if (message instanceof TemperaturePeriodEvent) {

	} else if (message instanceof TemperatureSetMessage) {
	    updateIfExists(message.getSource(), "DESIRED_TEMPERATURE", new DecimalType(((TemperatureSetMessage) message).getDesiredTemp()));
	}
    }

    private void updateIfExists(AbstractDevice device, String parameter, State state) {
	HomeMaticBindingConfig config = this.homematicCULBinding.getBindingForAddress(device.getName(), parameter);
	if (config != null) {
	    this.homematicCULBinding.postUpdate(config.getItem().getName(), state);
	}
    }

}
