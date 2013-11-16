package org.openhab.binding.cul.internal.binding;

import java.math.BigDecimal;

import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.library.types.UpDownType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;

import de.tobiaswegner.communication.cul4java.FS20Command;

public class FS20CommandHelper {

	public static FS20Command convertHABCommandToFS20Command(Command command) {
		if (command instanceof UpDownType) {
			return convertUpDownType((UpDownType) command);
		} else if (command instanceof OnOffType) {
			return convertOnOffType((OnOffType) command);
		} else if (command instanceof PercentType) {
			return convertPercentType((PercentType) command);
		}
		return null;
	}

	public static State getStateFromFS20Command(FS20Command command) {

		switch (command) {
		case ON:
			return OnOffType.ON;
		case OFF:
			return OnOffType.OFF;
		case DIM_UP:
			return UpDownType.UP;
		case DIM_DOWN:
			return UpDownType.DOWN;
		case DIM_1:
		case DIM_2:
		case DIM_3:
		case DIM_4:
		case DIM_5:
		case DIM_6:
		case DIM_7:
		case DIM_8:
		case DIM_9:
		case DIM_10:
		case DIM_11:
		case DIM_12:
		case DIM_13:
		case DIM_14:
		case DIM_15:
			double value = Integer.parseInt(command.getHexValue(), 16) * 6.25;
			PercentType percent = new PercentType(BigDecimal.valueOf(value));
			return percent;
		case TOGGLE:
			return OnOffType.ON;
		default:
			return null;
		}
	}

	private static FS20Command convertPercentType(PercentType percentType) {
		double percentValue = percentType.doubleValue();
		int step = (int) (percentValue / 6.25);
		String hexValue = Integer.toHexString(step);
		if (hexValue.length() == 1) {
			hexValue = "0" + hexValue;
		}
		return FS20Command.getFromHexValue(hexValue);
	}

	private static FS20Command convertOnOffType(OnOffType onOff) {
		switch (onOff) {
		case OFF:
			return FS20Command.OFF;
		case ON:
			return FS20Command.ON;
		default:
			return null;
		}
	}

	private static FS20Command convertUpDownType(UpDownType upDown) {
		switch (upDown) {
		case DOWN:
			return FS20Command.DIM_DOWN;
		case UP:
			return FS20Command.DIM_UP;
		default:
			return null;
		}
	}
}
