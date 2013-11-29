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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TooManyListenersException;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.tobiaswegner.communication.cul4java.CULHandler;
import de.tobiaswegner.communication.cul4java.CULInterface;
import de.tobiaswegner.communication.cul4java.CULListener;

public class CULTransceiver implements CULInterface, SerialPortEventListener {

    private final static Logger log = LoggerFactory
	    .getLogger(CULTransceiver.class);

    private final static String END_OF_BUFFER = "EOB\r\n";

    protected boolean active = true;

    private boolean isOpen = false;

    private SerialPort serialPort;

    private final Map<Character, CULHandler<? extends CULListener>> culHandler = new HashMap<Character, CULHandler<? extends CULListener>>();

    private String buffer = "";

    @Override
    public void open(final String deviceName) throws
	    IOException, TooManyListenersException, SerialPortException {

	this.serialPort = new SerialPort(deviceName);
	if (!this.serialPort.isOpened()) {
	    final boolean openPort = this.serialPort.openPort();

	    if (!openPort) {
		throw new IOException("Unable to open port");
	    }

	    this.serialPort.setParams(38400, SerialPort.DATABITS_8,
		    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

	    this.sendRAW("Ar");
	    this.sendRAW("X00");

	    this.serialPort.addEventListener(this);
	    this.isOpen = true;
	}

	// sendRAW("X21\r\n");
    }

    @Override
    public void close() {
	log.debug("Closing the connection to the serial device");
	this.active = false;
	if (this.serialPort != null) {
	    try {
		this.sendRAW("X00");
		this.serialPort.removeEventListener();
		this.serialPort.closePort();
	    } catch (final SerialPortException e) {
		e.printStackTrace();
	    }
	}
	this.isOpen = false;
    }

    @Override
    public boolean isOpen() {
	return this.isOpen;
    }

    public void FS20_Send(final String HouseCode, final String Address, final String Command) {
	// String sendString = fs20Handler.Send(HouseCode, Address, Command);
	//
	// if (sendString.length() > 0)
	// RAW_Send(sendString);
    }

    @Override
    public void decode(final String line) {
	if (line.equals(END_OF_BUFFER)) {
	    log.error("Send buffer is full, can't send command");
	    return;
	}
	log.debug("CUL received a message: " + line.replaceAll("\r\n", "\\r\\n"));

	final char type = line.charAt(0);
	final CULHandler<? extends CULListener> handler = this.getHandlerForType(type);

	if (handler != null) {
	    handler.parseData(line);
	} else {
	    log.warn("No handler found to parse the message: " + line);
	}
    }

    @Override
    public void sendRAW(String sendString)  {
	log.debug("Sending raw message to CUL: " + sendString);
	if (!sendString.endsWith("\r\n")) {
	    sendString += "\r\n";
	}
	synchronized (this.serialPort) {
	    try {
		this.serialPort.writeString(sendString);
		// log.debug("CUL sent: " + sendString);
	    } catch (final SerialPortException e) {
		log.error("Can't write to serial port", e);
	    }
	}
    }

    @Override
    public void serialEvent(final SerialPortEvent event) {
	if (event.getEventType() == SerialPortEvent.RXCHAR) {
	    try {
		// this can be a partial line too
		String receivedLine = this.buffer + this.serialPort.readString();
		int indexOf = receivedLine.indexOf("\r\n");
		while (indexOf != -1) {
		    final String message = receivedLine.substring(0, indexOf);

		    try {
			this.decode(message);
		    } catch (final RuntimeException e) {
			log.error("Error: ", e);
		    }

		    receivedLine = receivedLine.substring(indexOf + 2);
		    indexOf = receivedLine.indexOf("\r\n");
		}
		this.buffer = receivedLine;

	    } catch (final SerialPortException e) {
		log.error("Can't read from serial port", e);
	    }
	} else {
	    log.debug("A serial event occured: " + event.getEventType());
	}

    }

    @Override
    public void registerHandler(final CULHandler<? extends CULListener> handler) {
	if (handler != null) {
	    this.culHandler.put(handler.getCULReceiverPrefix(), handler);
	}

    }

    @Override
    public void unregisterHandler(final CULHandler<? extends CULListener> handler) {
	if (handler != null) {
	    this.culHandler.remove(handler.getCULReceiverPrefix());
	}

    }

    @Override
    public void unregisterHandler(final char type) {
	if (this.culHandler.containsKey(type)) {
	    this.culHandler.remove(type);
	}

    }

    @Override
    public <H extends CULHandler<?>> H getHandlerForType(final char type) {
	return (H) this.culHandler.get(type);
    }

    @Override
    public void setOwnHouseCode(final String housecode) throws IOException {
	if (!StringUtils.isEmpty(housecode) && housecode.length() == 4) {
	    this.sendRAW("T01" + housecode);
	}

    }

}
