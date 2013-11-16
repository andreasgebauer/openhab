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

    private Map<Character, CULHandler<? extends CULListener>> culHandler = new HashMap<Character, CULHandler<? extends CULListener>>();

    @Override
    public void open(String deviceName) throws
	    IOException, TooManyListenersException, SerialPortException {

	serialPort = new SerialPort(deviceName);
	if (!serialPort.isOpened()) {
	    boolean openPort = serialPort.openPort();

	    if (!openPort) {
		throw new IOException("Unable to open port");
	    }

	    serialPort.setParams(38400, SerialPort.DATABITS_8,
		    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

	    sendRAW("Ar");
	    sendRAW("X00");

	    serialPort.addEventListener(this);
	    isOpen = true;
	}

	// sendRAW("X21\r\n");
    }

    @Override
    public void close() {
	log.debug("Closing the connection to the serial device");
	active = false;
	if (serialPort != null) {
	    sendRAW("X00\r\n");
	    try {
		serialPort.removeEventListener();
		serialPort.closePort();
	    } catch (SerialPortException e) {
		e.printStackTrace();
	    }
	}
	isOpen = false;
    }

    @Override
    public boolean isOpen() {
	return isOpen;
    }

    public void FS20_Send(String HouseCode, String Address, String Command) {
	// String sendString = fs20Handler.Send(HouseCode, Address, Command);
	//
	// if (sendString.length() > 0)
	// RAW_Send(sendString);
    }

    @Override
    public void decode(String line) {
	if (line.equals(END_OF_BUFFER)) {
	    log.error("Send buffer is full, can't send command");
	    return;
	}
	log.debug("CUL received a message: " + line.replaceAll("\r\n", "\\r\\n"));

	char type = line.charAt(0);
	CULHandler<? extends CULListener> handler = getHandlerForType(type);

	for (String string : line.split("\r\n")) {
	    if (handler != null) {
		handler.parseData(string);
	    } else {
		log.warn("No handler found to parse the message: " + line);
	    }
	}
    }

    @Override
    public void sendRAW(String sendString) {
	log.debug("Sending raw message to CUL: " + sendString);
	if (!sendString.endsWith("\r\n")) {
	    sendString = sendString + "\r\n";
	}
	synchronized (serialPort) {
	    try {
		serialPort.writeString(sendString);
		// log.debug("CUL sent: " + sendString);
	    } catch (SerialPortException e) {
		log.error("Can't write to serial port", e);
	    }
	}
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
	if (event.getEventType() == SerialPortEvent.RXCHAR) {
	    try {
		String receivedLine = serialPort.readString();
		// log.debug("CUL received data: " + receivedLine);
		decode(receivedLine);
	    } catch (SerialPortException e) {
		log.error("Can't read from serial port", e);
	    }
	} else {
	    log.debug("A serial event occured: " + event.getEventType());
	}

    }

    @Override
    public void registerHandler(CULHandler<? extends CULListener> handler) {
	if (handler != null) {
	    culHandler.put(handler.getCULReceiverPrefix(), handler);
	}

    }

    @Override
    public void unregisterHandler(CULHandler<? extends CULListener> handler) {
	if (handler != null) {
	    culHandler.remove(handler.getCULReceiverPrefix());
	}

    }

    @Override
    public void unregisterHandler(char type) {
	if (culHandler.containsKey(type)) {
	    culHandler.remove(type);
	}

    }

    @SuppressWarnings("unchecked")
    @Override
    public <H extends CULHandler<?>> H getHandlerForType(char type) {
	return (H) culHandler.get(type);
    }

    @Override
    public void setOwnHouseCode(String housecode) {
	if (!StringUtils.isEmpty(housecode) && housecode.length() == 4) {
	    sendRAW("T01" + housecode);
	}

    }

}
