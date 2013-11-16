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

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tobiaswegner.communication.cul4java.CULInterface;
import de.tobiaswegner.communication.cul4java.FS20Command;
import de.tobiaswegner.communication.cul4java.FS20Listener;

public class FS20Handler extends AbstractCulHandler<FS20Listener> {

	private final static Logger log = LoggerFactory
			.getLogger(FS20Handler.class);

	public FS20Handler(CULInterface cul) {
		super(cul);
	}

	public void send(String houseCode, String address, FS20Command command) {
		if (validateHouseCodeAndAddress(houseCode, address)) {
			String sendString = "F" + houseCode + address
					+ command.getHexValue();
			cul.sendRAW(sendString.toUpperCase());
		}
	}

	public void send(String houseCode, String address, FS20Command command,
			String extension) {
		if (validateHouseCodeAndAddress(houseCode, address)) {
			String sendString = "F" + houseCode + address
					+ command.getHexValue() + extension;
			cul.sendRAW(sendString.toUpperCase());
		}
	}

	private boolean validateHouseCodeAndAddress(String houseCode, String address) {
		if ((houseCode.length() == 4) && (address.length() == 2)) {
			return true;
		}
		return false;
	}

	protected void parseFS20(String line) {
		try {
			String houseCode = (line.substring(1, 5));
			String address = (line.substring(5, 7));
			String command = line.substring(7, 9);

			FS20Command fs20Command = FS20Command.getFromHexValue(command);
			Iterator<FS20Listener> listenerIterator = listeners.iterator();

			while (listenerIterator.hasNext()) {
				FS20Listener listener = listenerIterator.next();
				listener.messageReceived(houseCode, address, fs20Command);
			}
		} catch (StringIndexOutOfBoundsException e) {
			log.error("Can't parse line: " + line, e);
		}
	}

	@Override
	public void parseData(String receivedLine) {
		parseFS20(receivedLine);

	}

	@Override
	public char getCULReceiverPrefix() {
		return 'F';
	}
}
