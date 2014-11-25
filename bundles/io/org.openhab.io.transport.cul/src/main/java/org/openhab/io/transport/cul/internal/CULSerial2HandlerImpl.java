/**
 * Copyright (c) 2010-2014, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.io.transport.cul.internal;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import org.openhab.io.transport.cul.CULDeviceException;
import org.openhab.io.transport.cul.CULMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation for culfw based devices which communicate via serial port (cullite for example). This is based on jssc and assumes constant parameters for the
 * serial port.
 *
 * @author Till Klocke
 * @since 1.4.0
 */
public class CULSerial2HandlerImpl extends AbstractCULHandler implements SerialPortEventListener {

    private final static Logger log = LoggerFactory.getLogger(CULSerial2HandlerImpl.class);

    private SerialPort serialPort;

    private final StringBuilder buffer = new StringBuilder();

    public CULSerial2HandlerImpl(final String deviceName, final CULMode mode) {
	super(deviceName, mode);
    }

    @Override
    public void serialEvent(final SerialPortEvent event) {
	if (event.getEventType() == SerialPortEvent.RXCHAR) {
	    try {
		synchronized (this.buffer) {
		    this.buffer.append(this.serialPort.readString());
		    int indexOf;
		    while ((indexOf = this.buffer.indexOf("\r\n")) != -1) {
			final String data = this.buffer.substring(0, indexOf);
			log.debug("Received raw message from CUL: " + data);
			if ("EOB".equals(data)) {
			    log.warn("(EOB) End of Buffer. Last message lost. Try sending less messages per time slot to the CUL");
			    return;
			} else if ("LOVF".equals(data)) {
			    log.warn("(LOVF) Limit Overflow: Last message lost. You are using more than 1% transmitting time. Reduce the number of rf messages");
			    return;
			}
			this.notifyDataReceived(data);
			this.buffer.delete(0, indexOf + 2);
		    }
		}
	    } catch (final SerialPortException e) {
		log.error("Exception while reading from serial port", e);
		this.notifyError(e);
	    }
	}else{
	    log.warn("Uninterpreted: {}", event);
	}

    }

    @Override
    protected void writeMessage(final String message) {
	log.debug("Sending raw message to CUL: " + message);
	synchronized (this.serialPort) {
	    try {
		this.serialPort.writeString(message);
	    } catch (final SerialPortException e) {
		log.error("Can't write to CUL", e);
	    }
	}

    }

    @Override
    protected void openHardware() throws CULDeviceException {
	log.debug("Opening serial CUL connection for " + this.deviceName);
	try {
	    this.serialPort = new SerialPort(this.deviceName);
	    if (this.serialPort.isOpened()) {
		throw new CULDeviceException("The port " + this.deviceName + " is currenty used by "
			+ this.serialPort);
	    }
	    if (!this.serialPort.openPort()) {
		throw new CULDeviceException("The device " + this.deviceName + " could not be opened");
	    }
	    this.serialPort.setParams(SerialPort.BAUDRATE_38400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
	    this.serialPort.addEventListener(this);
	    log.debug("Adding serial port event listener");
	} catch (final SerialPortException e) {
	    throw new CULDeviceException("The device " + this.deviceName + " could not be opened", e);
	}

    }

    @Override
    protected void closeHardware() {
	log.debug("Closing serial device " + this.deviceName);
	try {
	    if (this.serialPort != null) {
		this.serialPort.removeEventListener();
	    }
	} catch (final SerialPortException e) {
	    log.error("Can't close the input and output streams propberly", e);
	} finally {
	    if (this.serialPort != null) {
		try {
		    this.serialPort.closePort();
		} catch (final SerialPortException e) {
		    log.error("Can't close the input and output streams propberly", e);
		}
	    }
	}

    }
}
