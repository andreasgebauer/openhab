/*
 *  CUL4Java - Java API for CULfw wireless transceiver
 *  
 *  Copyright (C) 2011-2012  Tobias Wegner
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.tobiaswegner.communication.cul4java.impl;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.openhab.binding.cul.FHTMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tobiaswegner.communication.cul4java.CULInterface;
import de.tobiaswegner.communication.cul4java.FHTEvent;
import de.tobiaswegner.communication.cul4java.FHTListener;
import de.tobiaswegner.communication.cul4java.FHTState;

public class FHTHandler extends AbstractCulHandler<FHTListener> {

	private final static Logger log = LoggerFactory.getLogger(FHTHandler.class);
	HashMap<String, Integer> valueCache = new HashMap<String, Integer>();

	private final static SimpleDateFormat configDateFormat = new SimpleDateFormat(
			"mm:HH:dd:MM:yy");

	public FHTHandler(CULInterface cul) {
		super(cul);
	}

	protected void parseFHT(String line) {
		try {
			log.debug("Received FHT frame: " + line);

			if (line.length() >= 13) {
				// is FHT80b frame
				String device = line.substring(1, 5); // dev
				String command = line.substring(5, 7); // cde
				FHTCommand cde = FHTCommand.getEventById(Integer.parseInt(
						command, 16));
				String origin = line.substring(7, 9); // ??
				String argument = line.substring(9, 11); // val

				if (cde != null) {
					switch (cde) {
					case FHT_DESIRED_TEMP:
						double desiredTemperature = ((double) Integer.parseInt(
								argument, 16)) / 2.0;

						for (FHTListener fhtListener : listeners) {
							fhtListener.receivedFHTValue(device,
									FHTEvent.DESIRED_TEMPREATURE,
									desiredTemperature);
						}

						log.debug("FHT " + device + ": desired temp = "
								+ desiredTemperature);

						break;
					case FHT_MEASURED_TEMP_LOW:
						valueCache.put(device + "lowtemp",
								new Integer(Integer.parseInt(argument, 16)));

						break;
					case FHT_MEASURED_TEMP_HIGH:
						Integer lowtemp = valueCache.get(device + "lowtemp");

						if (lowtemp != null) {
							double temperature = (double) lowtemp
									+ ((double) Integer.parseInt(argument, 16))
									* 256.0;

							temperature /= 10.0;

							for (FHTListener fhtListener : listeners) {
								fhtListener.receivedFHTValue(device,
										FHTEvent.MEASURED_TEMPERATURE,
										temperature);
							}

							log.debug("FHT " + device + ": measured temp = "
									+ temperature);
						}

						break;
					case FHT_ACTUATOR_0:
					case FHT_ACTUATOR_1:
					case FHT_ACTUATOR_2:
					case FHT_ACTUATOR_3:
					case FHT_ACTUATOR_4:
					case FHT_ACTUATOR_5:
					case FHT_ACTUATOR_6:
					case FHT_ACTUATOR_7:
					case FHT_ACTUATOR_8:
						double valve = (((double) Integer
								.parseInt(argument, 16)) / 255.0) * 100.0;
						log.debug(MessageFormat
								.format("Received valve setting for actuator {0} with setting of {1} with housecode {2}",
										cde, valve, device));
						for (FHTListener fhtListener : listeners) {
							fhtListener.receivedActuatorStatus(device,
									cde.getId(), valve);
						}

						break;
					default:
						log.warn("Unknown message: FHT " + device + ": "
								+ command + "=" + argument + "\r\n");
					}
				}
			} else if (line.length() == 11) {
				// is FHT8b frame
				log.debug("We received probably a FHT 8b frame");
				String device = line.substring(1, 7);
				String argument = line.substring(7, 9);
				FHTState state = null;

				if ((argument.startsWith("1")) || (argument.startsWith("9"))) {
					state = FHTState.BATTERY_LOW;

					log.debug("Sensor: " + device + " battery low");
				}

				if (argument.substring(1).equals("1")) {
					state = FHTState.WINDOW_OPEN;

					log.debug("Sensor: " + device + " opened");
				}

				if (argument.substring(1).equals("2")) {
					state = FHTState.WINDOW_CLOSED;

					log.debug("Sensor: " + device + " closed");
				}

				if (state != null) {
					Iterator<FHTListener> listenerIterator = listeners
							.iterator();

					while (listenerIterator.hasNext()) {
						FHTListener listener = listenerIterator.next();
						listener.receivedFHTState(device, state);
					}
				} else {
					log.warn("Received unknown state (" + argument
							+ ") from device " + device);
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
			log.error("Can't parse line: " + line, e);
		}
	}

	public void setDesiredTemperature(String device, double temperature) {
		if ((temperature >= 5.5) && (temperature <= 30.5)) {
			int temp = (int) (temperature * 2.0);

			String command = "T" + device + "41" + Integer.toHexString(temp)
					+ "\r\n";
			cul.sendRAW(command.toUpperCase());
		}

	}

	public void setFHTMode(String device, FHTMode mode) {
		if (mode != null) {
			writeRegister(device, "3E", mode.getValue());
		} else {
			log.error("Can't set mode for device, given FHTMode was NULL");
		}
	}

	public void setDateTime(String device, Date date) {
		String[] rawDateValues = configDateFormat.format(date).split(":");
		writeRegister(device, "64",
				convertDecimalStringToHexString(rawDateValues[0]));
		writeRegister(device, "63",
				convertDecimalStringToHexString(rawDateValues[1]));
		writeRegister(device, "62",
				convertDecimalStringToHexString(rawDateValues[2]));
		writeRegister(device, "61",
				convertDecimalStringToHexString(rawDateValues[3]));
		writeRegister(device, "60",
				convertDecimalStringToHexString(rawDateValues[4]));
	}

	private String convertDecimalStringToHexString(String in) {
		int integer = Integer.parseInt(in);
		String hexString = Integer.toHexString(integer);
		if (hexString.length() == 1) {
			hexString = '0' + hexString;
		}
		return hexString;
	}

	private void writeRegister(String device, String register, String value) {
		StringBuffer sendBuffer = new StringBuffer(8);
		sendBuffer.append(getCULReceiverPrefix());
		sendBuffer.append(device);
		sendBuffer.append(register); // register to write
		sendBuffer.append(value);
		cul.sendRAW(sendBuffer.toString());
	}

	@Override
	public void parseData(String receivedLine) {
		parseFHT(receivedLine);

	}

	@Override
	public char getCULReceiverPrefix() {
		return 'T';
	}
}
