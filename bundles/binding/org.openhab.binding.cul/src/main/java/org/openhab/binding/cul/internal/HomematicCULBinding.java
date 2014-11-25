/**
 * openHAB, the open Home Automation Bus.
 * Copyright (C) 2010-2013, openHAB.org <admin@openhab.org>
 *
 * See the contributors.txt file in the distribution for a
 * full listing of individual contributors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Additional permission under GNU GPL version 3 section 7
 *
 * If you modify this Program, or any covered work, by linking or
 * combining it with Eclipse (or a modified version of that library),
 * containing parts covered by the terms of the Eclipse Public License
 * (EPL), the licensors of this Program grant you additional permission
 * to convey the resulting work.
 */
package org.openhab.binding.cul.internal;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;
import org.openhab.binding.cul.HomematicCULBindingProvider;
import org.openhab.binding.cul.internal.binding.HomeMaticBindingConfig;
import org.openhab.core.binding.AbstractActiveBinding;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.IncreaseDecreaseType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;
import org.openhab.core.types.UnDefType;
import org.openhab.io.transport.cul.CULDeviceException;
import org.openhab.io.transport.cul.CULHandler;
import org.openhab.io.transport.cul.CULListener;
import org.openhab.io.transport.cul.CULManager;
import org.openhab.io.transport.cul.CULMode;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.MessageInterpreter;
import de.gebauer.cul.homematic.in.MessageParser;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.cul.homematic.out.MessageSender;
import de.gebauer.cul.homematic.out.MessageSenderImpl;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.Utils;
import de.gebauer.homematic.command.PairingCommand;
import de.gebauer.homematic.command.SimpleCommand;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.DeviceFactory;
import de.gebauer.homematic.device.DeviceStore;
import de.gebauer.homematic.device.Model;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.hmcctc.ControlMode;
import de.gebauer.homematic.hmcctc.ThermoControl;
import de.gebauer.homematic.hmcctc.ThermoControlDevice;
import de.gebauer.homematic.hmlcdim1tpi2.DimMessage;
import de.gebauer.homematic.hmlcdim1tpi2.Dimmer;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.AckStatusEvent;
import de.gebauer.homematic.msg.ConfigRegisterReadMessage;
import de.gebauer.homematic.msg.DeviceInfoEvent;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageFlag;
import de.gebauer.homematic.msg.MessageType;
import de.gebauer.homematic.msg.ParamResponseMessage;

/**
 * This class implements a binding to a CUL device in FS20 (slow RF) mode.
 *
 * @author Till Klocke
 * @since 1.2.0
 */
public class HomematicCULBinding extends AbstractActiveBinding<HomematicCULBindingProvider>
	implements ManagedService, CULListener {

    static final Logger LOG = LoggerFactory.getLogger(HomematicCULBinding.class);

    public static HomematicCULBinding INSTANCE;

    private String deviceName;
    private String houseCode;
    private boolean pairingEnabled = false;

    /**
     * Property name to retrieve the device name to use. On an UNIXoid OS the user running openHAB has to be in the group dialout and the argument
     * -Dgnu.io.rxtx.SerialPorts=/dev/ttyACM0 has to be given if your serial port is /dev/ttyACM0
     */
    private final static String PROPERTY_DEVICE = "device";

    /**
     * The house code to use as our own house code. This has nothing to do with the housecodes of the FHTs. It has to be valid house code
     */
    private final static String PROPERTY_HOUSECODE = "housecode";

    private static final String PAIRING = "pairing";

    /**
     * Indicates whether this binding is properly configured which means all necessary configurations are set. Only Bindings which are properly configured get's
     * started and will call the execute method though.
     */
    private boolean isProperlyConfigured = false;

    /**
     * the refresh interval which is used to poll values from the CULBinding server (optional, defaults to 30000ms)
     */
    private long refreshInterval = 30000;

    private CULHandler cul;

    public final VirtualCCU ccu = new VirtualCCU("CCU");

    public DeviceStore dvcStore;

    private MessageParser messageParser;

    public MessageSender messageSender;

    protected HMHandler hmHandler;

    public HomematicCULBinding() {
	INSTANCE = this;
    }

    /**
     * Here we are creating the protocol handlers and bind them to the CUL interface. Also we register this as a protocol listener for every protocol handler
     * and bind the CUL to the configured port.
     */
    @Override
    public void activate() {
	this.dvcStore = new DeviceStore();
	this.messageParser = new MessageInterpreter(this.dvcStore);
	this.hmHandler = new HMHandler(this);
	this.bindCULHandler();
    }

    private void bindCULHandler() {
	if (!StringUtils.isEmpty(this.deviceName)) {
	    try {
		this.cul = CULManager.getOpenCULHandler(this.deviceName, CULMode.ASK_SIN2);
		this.messageSender = new MessageSenderImpl(this.cul);
		this.cul.registerListener(this);
	    } catch (final CULDeviceException e) {
		LOG.error("Can't open CUL", e);
	    }
	}
    }

    /**
     * Here we are closing the serial device and deactivating the receive mode of the CUL.
     */
    @Override
    public void deactivate() {
	if (this.cul != null) {
	    this.cul.unregisterListener(this);
	    CULManager.close(this.cul);
	}
    }

    /**
     * @{inheritDoc
     */
    @Override
    protected long getRefreshInterval() {
	return this.refreshInterval;
    }

    /**
     * @{inheritDoc
     */
    @Override
    protected String getName() {
	return "Homematic CULBinding Refresh Service";
    }

    /**
     * @{inheritDoc
     */
    @Override
    public boolean isProperlyConfigured() {
	LOG.debug(this.isProperlyConfigured ? "We are properly configured"
		: "We are not properly configured");
	return this.isProperlyConfigured;
    }

    /**
     * @throws IOException
     * @{inheritDoc
     */
    @Override
    protected void internalReceiveCommand(final String itemName, final Command command) {
	// the code being executed when a command was sent on the openHAB
	// event bus goes here. This method is only called if one of the
	// BindingProviders provide a binding for the given 'itemName'.
	LOG.debug("internalReceiveCommand() is called for item " + itemName);
	final HomeMaticBindingConfig config = this.getBindingForItem(itemName);

	try {
	    if (config != null && config.isWriteable()) {
		if (this.executeCommand(this.cul, command, config)) {
		    this.messageSender.processCmdStack(this.dvcStore.get(config.getId()));
		}
	    } else {
		LOG.warn("Config null or {} not writable", command);
	    }
	} catch (final IOException e) {
	    LOG.error("Unexpected Exception:", e);
	}
    }

    public boolean executeCommand(final CULHandler cul, final Command command, final HomeMaticBindingConfig config) {
	final AbstractDevice destination = this.dvcStore.get(config.getId());
	State state = config.getItem().getState();

	if (destination instanceof Dimmer) {
	    DimMessage message = null;
	    if (command instanceof PercentType) {
		final int intValue = ((PercentType) command).intValue();
		message = new DimMessage(this.ccu, destination, intValue);
	    } else if (command instanceof OnOffType) {
		switch ((OnOffType) command) {
		case ON:
		    message = new DimMessage(this.ccu, destination, true);
		    break;
		case OFF:
		    message = new DimMessage(this.ccu, destination, false);
		    break;
		default:
		    break;
		}
	    } else if (command instanceof IncreaseDecreaseType) {
		if (state instanceof UnDefType) {
		    state = PercentType.ZERO;
		}
		final DecimalType stateAs = (DecimalType) state;
		switch ((IncreaseDecreaseType) command) {
		case INCREASE:
		    message = new DimMessage(this.ccu, destination, stateAs.intValue() + 5);
		    break;
		case DECREASE:
		    message = new DimMessage(this.ccu, destination, stateAs.intValue() - 5);
		    break;
		default:
		    break;
		}
	    }
	    if (message != null) {
		destination.addToSendQueue(new SimpleCommand(message));
	    }
	    return true;
	} else if (destination instanceof ThermoControl) {
	    if (command instanceof DecimalType) {
		final int intValue = ((DecimalType) command).intValue();
		final ControlMode valueOf = ControlMode.valueOf(intValue);
		((ThermoControl) destination).controlMode(this.ccu, valueOf);
		return false;
	    }
	}
	return false;
    }

    /**
     * @{inheritDoc
     */
    @Override
    protected void internalReceiveUpdate(final String itemName, final State newState) {
	// the code being executed when a state was sent on the openHAB
	// event bus goes here. This method is only called if one of the
	// BindingProviders provide a binding for the given 'itemName'.
	LOG.debug("internalReceiveUpdate() is called for item " + itemName);
	for (final HomematicCULBindingProvider provider : this.providers) {
	    LOG.debug("Checking provider with names {}", provider.getItemNames());
	    final HomeMaticBindingConfig parameterAddress = provider.getBindingConfigForItem(itemName);

	    // setStateOnDevice(newState, parameterAddress);
	}
    }

    /**
     * To check wether we provide a valid binding for the given item name
     */
    @Override
    protected boolean providesBindingFor(final String itemName) {
	return this.getBindingForItem(itemName) != null;
    }

    private HomeMaticBindingConfig getBindingForItem(final String itemName) {
	if (this.providers != null) {
	    for (final HomematicCULBindingProvider provider : this.providers) {
		final HomeMaticBindingConfig config = provider.getBindingConfigForItem(itemName);
		if (config != null) {
		    return config;
		}
	    }
	}
	LOG.warn("Couldn't find config for device with item name " + itemName);
	return null;
    }

    /**
     * @{inheritDoc
     */
    @Override
    public void updated(final Dictionary<String, ?> config)
	    throws ConfigurationException {
	LOG.debug("Receiving new config");
	if (config != null) {
	    this.dvcStore.clear();
	    final Enumeration<String> keys = config.keys();
	    while (keys.hasMoreElements()) {
		final String nextElement = keys.nextElement();
		if (nextElement.startsWith("device.")) {
		    final String[] split = nextElement.split("\\.");
		    final String deviceId = split[1];
		    final AbstractDevice device = this.dvcStore.get(deviceId);
		    if (device == null) {
			final String dvcType = (String) config.get("device." + deviceId + ".type");
			final String dvcName = (String) config.get("device." + deviceId + ".name");

			final Model model = Model.valueOf(dvcType);
			final DeviceInfo dvcInfo = new DeviceInfo(null, model, null);
			this.dvcStore.add(deviceId, new DeviceFactory().createDevice(dvcName, deviceId, dvcInfo));
		    }
		}
	    }

	    // to override the default refresh interval one has to add a
	    // parameter to openhab.cfg like
	    // <bindingName>:refresh=<intervalInMs>
	    final String refreshIntervalString = (String) config.get("refresh");
	    if (StringUtils.isNotBlank(refreshIntervalString)) {
		this.refreshInterval = Long.parseLong(refreshIntervalString);
	    }
	    final String houseCode = (String) config.get(PROPERTY_HOUSECODE);
	    if (!StringUtils.isEmpty(houseCode)) {
		this.houseCode = houseCode;
		this.ccu.setId(this.houseCode);
		this.dvcStore.add(this.houseCode, this.ccu);
	    }

	    // read further config parameters here ...
	    final String deviceName = (String) config.get(PROPERTY_DEVICE);
	    LOG.debug("Received new device name: " + deviceName);
	    if (!StringUtils.isEmpty(deviceName)) {
		if (!deviceName.equals(this.deviceName)) {
		    this.deviceName = deviceName;
		    try {
			CULManager.close(this.cul);
			this.bindCULHandler();
		    } catch (final Exception e) {
			this.isProperlyConfigured = false;
			LOG.error("Can't open CUL device after configuration change", e);
			throw new ConfigurationException(PROPERTY_DEVICE, "Can't open/close CUL device", e);
		    }
		    this.isProperlyConfigured = true;
		}
	    } else {
		this.isProperlyConfigured = false;
		LOG.warn("The serial device is not properly configured");
		throw new ConfigurationException("device", "The serial device to use is not configured");
	    }

	    final String pairingEnabled = (String) config.get(PAIRING);
	    if (!StringUtils.isEmpty(pairingEnabled)) {
		final OnOffType onOff = OnOffType.valueOf(pairingEnabled);
		switch (onOff) {
		case ON:
		    this.pairingEnabled = true;
		    break;
		case OFF:
		    this.pairingEnabled = false;
		    break;
		}
		this.ccu.setPairingEnabled(this.pairingEnabled);
	    }
	} else {
	    LOG.debug("Received config is null");
	}
    }

    HomeMaticBindingConfig getWritableBindingForAddress(final String string) {
	for (final HomematicCULBindingProvider provider : this.providers) {
	    final HomeMaticBindingConfig writeableBindingConfigForAddress = provider.getWriteableBindingConfigForAddress(string);
	    if (writeableBindingConfigForAddress != null) {
		return writeableBindingConfigForAddress;
	    }
	}
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.openhab.io.transport.cul.CULListener#dataReceived(java.lang.String)
     */
    @Override
    public void dataReceived(final String data) {
	try {
	    final Message message = this.messageParser.parse(data);

	    if (message == null) {
		LOG.debug("Could not interpret " + data);
		return;
	    }

	    LOG.debug("Received {} {}", message, message.getRSSI());

	    message.getSource().messageSent(message);

	    // LOG.debug("Parsed event " + event);
	    if (message.getDestination() != null) {
		message.getDestination().messageReceived(message);
	    }

	    communication(message);

	    this.hmHandler.receivedMessage(message);

	    // just process if the message received is for us or if we received a broadcast
	    if (message.isBroadCast() || this.ccu.equals(message.getDestination())) {
		this.messageSender.processCmdStack(message.getSource());
	    }

	} catch (final IOException e) {
	    LOG.error("Error while writing to ");
	}
    }

    private void communication(final Message message) {
	if (message instanceof DeviceInfoEvent) {
	    // pairing
	    if (!this.ccu.isPairingEnabled()) {
		LOG.info("Pairing not enabled.");
		return;
	    }

	    final RawMessage msg = message.getRawMessage();
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

	    LOG.info("Initiating Pairing. clearing commands.");
	    message.getSource().getCommandStack().clear();
	    
	    final RawMessageBuilder msgBuilder = new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_A0);

	    // 02010A130BC80C6D
	    if (message.getSource().getInfo().mdl == Model.HMCCVD) {
		// ACT as TC
		final PairingCommand pairingCommand = new PairingCommand();
		// 21 0039 4B455130303339363531 58 00 02 00
		String serNo = "4B455130303339363531";
//		String serNo = "00000000000000000000";
		final DeviceInfo info = new DeviceInfo("21", Model.HMCCTC, serNo);
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

	    this.ccu.setHmPairSerial(message.getSource().getInfo().serNo);
	}

	final AbstractDevice destination = message.getDestination();
	final Message request;
	if (destination != null) {
	    request = destination.getEventSend(message.getCount());
	} else {
	    request = null;
	}

	if (!message.isBroadCast()) {
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

	    if (request != null && request.getSource().equals(this.ccu)) {
		if (message instanceof AckStatusEvent || message instanceof ParamResponseMessage) {
		    // if pairing is in progress
		    if (this.ccu.getHmPairSerial() != null
			    && this.ccu.getHmPairSerial().equals(message.getSource().getInfo().serNo)
			    && message.getSource().getCommandStack().isEmpty()) {
			if (request.hasAck()) {
			    LOG.info("Successfully paired CCU with " + message.getSource());
			    this.ccu.pairedWith(message.getSource());
			    
			    this.ccu.scheduleCycle(message.getSource(), this.ccu);
			    this.ccu.setHmPairSerial(null);
			}
		    }
		}
	    }
	}
    }

    @Override
    public void error(final Exception e) {
	// TODO Auto-generated method stub

    }

    @Override
    protected void execute() {
	// TODO Auto-generated method stub

    }

    public void postUpdate(String itemName, State newState) {
	this.eventPublisher.postUpdate(itemName, newState);
    }
}
