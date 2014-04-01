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
import java.text.MessageFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;
import org.openhab.binding.cul.CULBindingProvider;
import org.openhab.binding.cul.internal.binding.AbstractCulBindingConfig;
import org.openhab.binding.cul.internal.binding.FHTBindingConfig;
import org.openhab.binding.cul.internal.binding.FS20CommandHelper;
import org.openhab.binding.cul.internal.binding.HomeMaticBindingConfig;
import org.openhab.core.binding.AbstractActiveBinding;
import org.openhab.core.binding.BindingConfig;
import org.openhab.core.binding.BindingProvider;
import org.openhab.core.library.items.DimmerItem;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.OpenClosedType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.communication.cul4java.HMListener;
import de.gebauer.communication.cul4java.impl.HMHandler;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.cul.homematic.in.RawMessageBuilder;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.Utils;
import de.gebauer.homematic.command.PairingCommand;
import de.gebauer.homematic.command.SimpleCommand;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.DeviceFactory;
import de.gebauer.homematic.device.DeviceStore;
import de.gebauer.homematic.device.Model;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.hmcctc.TemperaturePeriodEvent;
import de.gebauer.homematic.hmcctc.WeatherEvent;
import de.gebauer.homematic.hmccvd.ClimateMessage;
import de.gebauer.homematic.hmlcdim1tpi2.DimmerStateChangeEvent;
import de.gebauer.homematic.hmsecsc.ShutterStateEvent;
import de.gebauer.homematic.msg.AbstractMessageParameter;
import de.gebauer.homematic.msg.AckStatusMessage;
import de.gebauer.homematic.msg.ConfigRegisterReadMessage;
import de.gebauer.homematic.msg.DeviceInfoEvent;
import de.gebauer.homematic.msg.Message;
import de.gebauer.homematic.msg.MessageFlag;
import de.gebauer.homematic.msg.MessageType;
import de.gebauer.homematic.msg.ParamResponseMessage;
import de.tobiaswegner.communication.cul4java.CULInterface;
import de.tobiaswegner.communication.cul4java.FHTEvent;
import de.tobiaswegner.communication.cul4java.FHTListener;
import de.tobiaswegner.communication.cul4java.FS20Command;
import de.tobiaswegner.communication.cul4java.FS20Listener;
import de.tobiaswegner.communication.cul4java.impl.CULTransceiver;
import de.tobiaswegner.communication.cul4java.impl.FHTHandler;
import de.tobiaswegner.communication.cul4java.impl.FS20Handler;

/**
 * This class implements a binding to a CUL device in FS20 (slow RF) mode.
 * 
 * @author Till Klocke
 * @since 1.2.0
 */
public class CULBinding extends AbstractActiveBinding<CULBindingProvider>
	implements ManagedService, FHTListener, FS20Listener, HMListener {

    static final Logger logger = LoggerFactory
	    .getLogger(CULBinding.class);

    public static CULBinding INSTANCE;
    private final CULInterface cul;
    private FHTHandler fhtHandler;
    private FS20Handler fs20Handler;
    public HMHandler homeMaticHandler;

    private String deviceName;
    private String houseCode;
    private boolean pairingEnabled = false;

    private JobKey updateTimeJobKey;
    private String cronExpression = "0 4 0 * * ?";

    /**
     * Property name to retrieve the device name to use. On an UNIXoid OS the user running openHAB has to be in the group dialout and the argument
     * -Dgnu.io.rxtx.SerialPorts=/dev/ttyACM0 has to be given if your serial port is /dev/ttyACM0
     */
    private final static String PROPERTY_DEVICE = "device";

    private int intertechnoRepitions = 6;
    private int intertechnoWavelength = 420;

    /**
     * The house code to use as our own house code. This has nothing to do with the housecodes of the FHTs. It has to be valid house code
     */
    private final static String PROPERTY_HOUSECODE = "housecode";

    private final static String PROPERTY_INTERTECHNO_REPITIONS = "itrepetitions";

    private final static String PROPERTY_INTERTECHNO_WAVE_LENGTH = "itwavelength";

    private final static String PROPERTY_UPDATE_TIME = "fht.time.update";

    private final static String PROPERTY_UPDATE_CRON = "fht.time.update.cron";

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

    private boolean doTimeUpdate = false;

    /**
     * We can send only a limited amount of commands per time slot and the ui issues a new command update every time you press the '+' or '-' buttons, so we
     * queue these commands here and only execute the last one
     */
    private final Map<String, FHTQueueItem> fhtCommandQueue = new LinkedHashMap<String, CULBinding.FHTQueueItem>();

    DeviceStore dvcStore;

    public CULBinding(final CULInterface culTransceiver, final DeviceStore deviceStore) {
	INSTANCE = this;
	this.cul = culTransceiver;
	this.dvcStore = deviceStore;
	logger.debug("Created a new instance of the CULBinding");
    }

    public CULBinding() {
	this(new CULTransceiver(), new DeviceStore());
    }

    /**
     * Here we are creating the protocol handlers and bind them to the CUL interface. Also we register this as a protocol listener for every protocol handler
     * and bind the CUL to the configured port.
     */
    @Override
    public void activate() {
	this.fhtHandler = new FHTHandler(this.cul);
	this.fs20Handler = new FS20Handler(this.cul);
	this.homeMaticHandler = new HMHandler(this.cul, this.dvcStore);
	this.fhtHandler.registerListener(this);
	this.fs20Handler.registerListener(this);
	this.homeMaticHandler.registerListener(this);
	this.cul.registerHandler(this.fhtHandler);
	this.cul.registerHandler(this.fs20Handler);
	this.cul.registerHandler(this.homeMaticHandler);
	logger.debug("Activating CULBinding with device " + this.deviceName);
	try {
	    this.bindCulInterface();
	} catch (final Exception e) {
	    logger.error("Can't open CUL device", e);
	}
    }

    private void scheduleTimeUpdateJob() {
	if (!this.doTimeUpdate) {
	    return;
	}
	try {
	    final Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
	    final JobDetail detail = JobBuilder.newJob(UpdateFHTTimeJob.class)
		    .withIdentity("FHT time update job", "cul").build();

	    final CronTrigger trigger = TriggerBuilder
		    .newTrigger()
		    .forJob(detail)
		    .withSchedule(
			    CronScheduleBuilder.cronSchedule(this.cronExpression))
		    .build();
	    this.updateTimeJobKey = detail.getKey();
	    sched.scheduleJob(detail, trigger);
	} catch (final SchedulerException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void unscheduleTimeUpdateJob() {
	if (this.updateTimeJobKey == null) {
	    return;
	}
	try {
	    final Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
	    sched.deleteJob(this.updateTimeJobKey);
	} catch (final SchedulerException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /**
     * Here we are closing the serial device and deactivating the receive mode of the CUL.
     */
    @Override
    public void deactivate() {
	logger.debug("Deactivating CULBinding, closing serial device");
	this.homeMaticHandler.tearDown();
	try {
	    this.cul.close();
	} catch (final IOException e) {
	    logger.error("Error while writing to cul", e);
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
	return "CULBinding Refresh Service";
    }

    /**
     * @{inheritDoc
     */
    @Override
    public boolean isProperlyConfigured() {
	logger.debug(this.isProperlyConfigured ? "We are properly configured"
		: "We are not properly configured");
	return this.isProperlyConfigured;
    }

    /**
     * We somehow misuse this method here. We are not polling values from the FHT/FS20 system but we issue the last known command for a FHT device here. The
     * problem is that every button press on Setpoint widget in the ui triggers internalReceiveCommand. This can fill the sendbuffer of the CUL very fast. So we
     * memorize only the last command per FHT device and send it in execute().
     * 
     * @throws IOException
     * 
     * @{inheritDoc
     */
    @Override
    protected void execute() {

	logger.debug(MessageFormat
		.format("execute() method is called, executing {0} waiting commands for FHTs!",
			this.fhtCommandQueue.size()));
	final LinkedHashMap<String, FHTQueueItem> copyQueue = new LinkedHashMap<String, CULBinding.FHTQueueItem>();
	copyQueue.putAll(this.fhtCommandQueue);
	for (final String address : copyQueue.keySet()) {
	    final FHTQueueItem item = copyQueue.get(address);
	    if (item != null) {
		synchronized (this.cul) {
		    try {
			item.execute();
		    } catch (final IOException e) {
			logger.error("Error while execution", e);
		    }
		}
	    }
	    this.fhtCommandQueue.remove(address);
	}

    }

    public List<FHTBindingConfig> getAllFHTBindingConfigs() {
	final List<FHTBindingConfig> allFHTConfigs = new LinkedList<FHTBindingConfig>();
	for (final CULBindingProvider provider : this.providers) {
	    allFHTConfigs.addAll(provider.getFHT80bBindings());
	}
	return allFHTConfigs;
    }

    public void updateFHTTime(final FHTBindingConfig config, final Date date) throws IOException {
	synchronized (this.cul) {
	    config.updateTime(this.cul, date);
	}
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
	logger.debug("internalReceiveCommand() is called for item " + itemName);
	final AbstractCulBindingConfig config = this.getBindingForItem(itemName);

	try {
	    if (config != null && config.isWriteable()) {
		if (config instanceof FHTBindingConfig) {
		    final FHTQueueItem item = new FHTQueueItem((FHTBindingConfig) config, command);
		    this.fhtCommandQueue.put(config.getAddress(), item);
		} else if (config instanceof HomeMaticBindingConfig) {
		    if (config.executeCommand(this.cul, command)) {
			this.homeMaticHandler.getMessageSender().processCmdStack(
				this.homeMaticHandler.getDeviceStore().get(((HomeMaticBindingConfig) config).getId()));
		    }
		} else {
		    synchronized (this.cul) {
			config.executeCommand(this.cul, command);
		    }
		}
	    }
	} catch (final IOException e) {
	    logger.error("Unexpected Exception:", e);
	}
    }

    /**
     * @{inheritDoc
     */
    @Override
    protected void internalReceiveUpdate(final String itemName, final State newState) {
	// the code being executed when a state was sent on the openHAB
	// event bus goes here. This method is only called if one of the
	// BindingProviders provide a binding for the given 'itemName'.
	logger.debug("internalReceiveUpdate() is called for item " + itemName);
	for (final CULBindingProvider provider : this.providers) {
	    logger.debug("Checking provider with names {}", provider.getItemNames());
	    final AbstractCulBindingConfig parameterAddress = provider.getBindingConfigForItem(itemName);

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

    private AbstractCulBindingConfig getBindingForItem(final String itemName) {
	if (this.providers != null) {
	    for (final BindingProvider provider : this.providers) {
		final BindingConfig config = ((CULBindingProvider) provider)
			.getBindingConfigForItem(itemName);
		if (config != null) {
		    return (AbstractCulBindingConfig) config;
		}
	    }
	}
	logger.warn("Couldn't find config for device with item name "
		+ itemName);
	return null;
    }

    private AbstractCulBindingConfig getReadOnlyBindingForAddress(final String address) {
	if (this.providers != null) {
	    for (final CULBindingProvider provider : this.providers) {
		final AbstractCulBindingConfig config = provider
			.getReadOnlyBindingConfigForAddress(address);
		if (config != null) {
		    return config;
		}
	    }
	}
	return null;
    }

    private AbstractCulBindingConfig getWritableBindingForAddress(final String address) {
	if (this.providers != null) {
	    for (final CULBindingProvider provider : this.providers) {
		final AbstractCulBindingConfig config = provider
			.getWriteableBindingConfigForAddress(address);
		if (config != null) {
		    return config;
		}
	    }
	}
	return null;
    }

    /**
     * @{inheritDoc
     */
    @Override
    public void updated(final Dictionary<String, ?> config)
	    throws ConfigurationException {
	logger.debug("Receiving new config");
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
		this.homeMaticHandler.getCCU().setId(this.houseCode);
		this.dvcStore.add(this.houseCode, this.homeMaticHandler.getCCU());
	    }

	    // read further config parameters here ...
	    final String deviceName = (String) config.get(PROPERTY_DEVICE);
	    logger.debug("Received new device name: " + deviceName);
	    if (!StringUtils.isEmpty(deviceName)) {
		if (!deviceName.equals(this.deviceName)) {
		    this.deviceName = deviceName;
		    try {
			this.cul.close();
			this.bindCulInterface();
		    } catch (final Exception e) {
			this.isProperlyConfigured = false;
			logger.error("Can't open CUL device after configuration change", e);
			throw new ConfigurationException(PROPERTY_DEVICE, "Can't open/close CUL device", e);
		    }
		    this.isProperlyConfigured = true;
		}
	    } else {
		this.isProperlyConfigured = false;
		logger.warn("The serial device is not properly configured");
		throw new ConfigurationException("device",
			"The serial device to use is not configured");
	    }

	    final String repitions = (String) config
		    .get(PROPERTY_INTERTECHNO_REPITIONS);
	    if (!StringUtils.isEmpty(repitions)) {
		try {
		    this.intertechnoRepitions = Integer.parseInt(repitions);
		} catch (final NumberFormatException e) {
		    throw new ConfigurationException(
			    PROPERTY_INTERTECHNO_REPITIONS,
			    "This is not a parseable integer " + repitions, e);
		}
	    }

	    final String wavelength = (String) config
		    .get(PROPERTY_INTERTECHNO_WAVE_LENGTH);
	    if (!StringUtils.isEmpty(wavelength)) {
		try {
		    this.intertechnoWavelength = Integer.parseInt(wavelength);
		    if (this.intertechnoWavelength < 360
			    || this.intertechnoWavelength > 470) {
			throw new ConfigurationException(
				PROPERTY_INTERTECHNO_WAVE_LENGTH,
				"The length of a single wave puls must be between 360 and 470");
		    }
		} catch (final NumberFormatException e) {
		    throw new ConfigurationException(
			    PROPERTY_INTERTECHNO_WAVE_LENGTH,
			    "This is not a parseable integer " + repitions, e);
		}
	    }
	    final String cronParam = (String) config.get(PROPERTY_UPDATE_CRON);
	    if (!StringUtils.isEmpty(cronParam)) {
		if (!cronParam.equals(this.cronExpression)) {
		    this.cronExpression = cronParam;
		    logger.debug("Read cron expression for time update: "
			    + this.cronExpression);
		    this.unscheduleTimeUpdateJob();
		}
	    }
	    final String updateTime = (String) config.get(PROPERTY_UPDATE_TIME);
	    if (!StringUtils.isEmpty(updateTime)) {
		final boolean update = Boolean.parseBoolean(updateTime);
		this.doTimeUpdate = update;
		if (this.doTimeUpdate) {
		    logger.debug("Activating time update for FHTs");
		    this.scheduleTimeUpdateJob();
		} else {
		    this.unscheduleTimeUpdateJob();
		}
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
		this.homeMaticHandler.getCCU().setPairingEnabled(this.pairingEnabled);
	    }
	} else {
	    logger.debug("Received config is null");
	}
    }

    private void bindCulInterface() throws Exception {
	if (this.deviceName != null) {
	    this.cul.open(this.deviceName);
	    if (this.houseCode != null) {
		logger.debug("Setting house code on CUL to " + this.houseCode);
		this.cul.setOwnHouseCode(this.houseCode);
	    }

	    this.cul.sendRAW("it" + this.intertechnoWavelength);
	    this.cul.sendRAW("isr" + this.intertechnoRepitions);
	}
    }

    @Override
    public void messageReceived(final String housecode, final String address,
	    final FS20Command command) {
	logger.debug(MessageFormat
		.format("Received fs20 message with housecode {0} from address {1} with command {2}",
			housecode, address, command.toString()));
	final AbstractCulBindingConfig config = this.getReadOnlyBindingForAddress(housecode
		+ address);
	if (config != null) {
	    final State state = FS20CommandHelper.getStateFromFS20Command(command);
	    if (state != null) {
		this.eventPublisher.postUpdate(config.getItem().getName(), state);
	    } else {
		logger.warn("Can't find matching state for fs20 command "
			+ command.toString());
	    }
	}

    }

    @Override
    public void receivedFHTState(final String device,
	    final de.tobiaswegner.communication.cul4java.FHTState state) {
	logger.debug(MessageFormat.format("Received state {0} for device {1}",
		state, device));
	final AbstractCulBindingConfig config = this.getReadOnlyBindingForAddress(device);
	if (config != null) {
	    switch (state) {
	    case WINDOW_CLOSED:
		this.eventPublisher.postUpdate(config.getItem().getName(),
			OpenClosedType.CLOSED);
		break;

	    case WINDOW_OPEN:
		this.eventPublisher.postUpdate(config.getItem().getName(),
			OpenClosedType.OPEN);
		break;
	    case BATTERY_LOW:
	    default:
		logger.warn("Received unimplemented state: " + state.toString());
	    }
	}

    }

    @Override
    public void receivedFHTValue(final String device, final FHTEvent event,
	    final double temperature) {
	logger.debug(MessageFormat
		.format("Received new FHTEvent {0} for device {1} with temperature {2}",
			event, device, temperature));
	AbstractCulBindingConfig config = null;
	switch (event) {
	case ACTUATOR_SETTING:
	    break;
	case DESIRED_TEMPREATURE:
	    config = this.getWritableBindingForAddress(device);
	    if (config != null) {
		this.eventPublisher.postUpdate(config.getItem().getName(),
			new DecimalType(temperature));
	    }
	    break;
	case MEASURED_TEMPERATURE:
	    config = this.getReadOnlyBindingForAddress(device);
	    if (config != null) {
		this.eventPublisher.postUpdate(config.getItem().getName(),
			new DecimalType(temperature));
	    }
	    break;
	default:
	    break;

	}
    }

    @Override
    public void receivedActuatorStatus(final String device, final int actuatorNumer,
	    final double opening) {
	logger.debug(MessageFormat
		.format("Received new valve opening ({0}) for valve number {1} on device {2}",
			opening, actuatorNumer, device));
	final AbstractCulBindingConfig config = this.getReadOnlyBindingForAddress(device
		+ "0" + actuatorNumer);
	if (config != null) {
	    this.eventPublisher.postUpdate(config.getItem().getName(),
		    new PercentType(Double.toString(opening)));
	}

    }

    private class FHTQueueItem {
	private final FHTBindingConfig config;
	private final Command command;

	public FHTQueueItem(final FHTBindingConfig config, final Command command) {
	    this.command = command;
	    this.config = config;
	}

	public void execute() throws IOException {
	    this.config.executeCommand(CULBinding.this.cul, this.command);
	}
    }

    /**
     * Method handling the event.
     * 
     * @throws
     */
    @Override
    public void receivedMessage(final Message message) {
	if (message.isBroadCast()) {
	    logger.debug(MessageFormat.format("Received {0} broadcast", message));
	} else {
	    logger.debug(MessageFormat.format("Received {0} for {1}", message, message.getDestination()));
	}

	final VirtualCCU ccu = this.homeMaticHandler.getCCU();
	if (message instanceof DeviceInfoEvent) {
	    // pairing
	    final String serNo = message.getSource().getInfo().serNo;
	    final RawMessage msg = message.getRawMessage();

	    if (!ccu.isPairingEnabled()) {
		logger.info("Pairing not enabled.");
		return;
	    }

	    if (msg.getDst().equals(ccu.getId())) {
		// repeater?
		return;
	    } else if (msg.getMsgType() == MessageType.UNKNOWN2 && msg.getMsgFlag() == MessageFlag.VAL_00) {
		// TODO why?
		return;
	    }
	    final AbstractDevice destination = message.getDestination();
	    if (destination != null && destination.getId() != ccu.getId()) {
		logger.info("Not our pairing request.");
		return;
	    }

	    logger.info("Initiating Pairing. clearing messages.");
	    message.getSource().getCommandStack().clear();
	    final RawMessageBuilder msgBuilder = new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_A0);

	    // 02010A130BC80C6D
	    if (message.getSource().getInfo().mdl == Model.HMCCVD) {
		// ACT as TC
		PairingCommand pairingCommand = new PairingCommand();
		final DeviceInfo info = new DeviceInfo("21", Model.HMCCTC, "0000000000");
		final String pAddr = "000000";
		final short chnl = (short) 01;
		final short pList = (short) 5;
		final short pChnl = (short) 0;

		// [1EA808->1C475A #48; len=1A, flag=VAL_A0, type=UNKNOWN, p=21 0039 4A455130373039393232 58 00 02 00]
		pairingCommand.add(new DeviceInfoEvent(msgBuilder.build(), ccu, message.getSource(), info, (short) 0x00, (short) 0x02, "00"));

		// [1EA808->1C475A #49; len=10, flag=VAL_A0, type=CONFIG, p=0104 000000 00 05] ConfigRegisterReadCommand
		AbstractMessageParameter msgParam = new AbstractMessageParameter(msgBuilder.build(), ccu, message.getSource(), chnl);
		pairingCommand.add(new ConfigRegisterReadMessage(msgParam, pAddr, pChnl, pList));
		message.getSource().addToSendQueue(pairingCommand);
	    } else {
		message.getSource().addToSendQueue(new PairingCommand(ccu, message.getSource()));
	    }

	    this.homeMaticHandler.getCCU().setHmPairSerial(serNo);
	}

	final AbstractDevice source = message.getSource();
	if (ccu.equals(message.getDestination())) {
	    if (message.needsAck()) {
		final RawMessage build = new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_80).setPayload(String.format("%02X", 0)).build();
		message.getSource().addToSendQueue(new SimpleCommand(new AckStatusMessage(build, ccu, message.getSource(), message.getChannel(), null)));
	    }

	    final Message request = message.getDestination().getEventSend(message.getCount());
	    // if we have sent a request the we add the response as answer
	    // TODO consider time passed by since we sent the message
	    if (request != null && request.getSource().equals(ccu)) {
		// add that as answer no matter if successful or not
		request.setResponse(message);
		message.setRequest(request);

		if (message instanceof ParamResponseMessage) {
		    final ParamResponseMessage paramResponseMessage = (ParamResponseMessage) message;
		    final ConfigRegisterReadMessage configReadRequest = (ConfigRegisterReadMessage) request;
		    final Matcher matcher = Utils.matcherFor(paramResponseMessage.getData(), ".* 00:00$");
		    if (matcher.matches()) {
			if (configReadRequest.getPeerList() == 0) {
			    logger.info("Successfully paired {} with {} ", source, message.getDestination());
			}
		    }
		}

		if (message instanceof AckStatusMessage || message instanceof ParamResponseMessage) {
		    // if pairing is in progress
		    if (ccu.getHmPairSerial() != null && ccu.getHmPairSerial().equals(source.getInfo().serNo) && source.getCommandStack().isEmpty()) {
			if (request.hasAck()) {
			    logger.info("Successfully paired CCU with " + source);
			    ccu.pairedWith(source);
			    ccu.setHmPairSerial(null);
			}
		    }
		}
	    }
	}

	if (message instanceof WeatherEvent) {
	    final float temperature = ((WeatherEvent) message).getTemperature();
	    final int humidity = ((WeatherEvent) message).getHumidity();

	    final AbstractDevice device = this.dvcStore.get(message.getSource().getId());

	    AbstractCulBindingConfig config = this.getWritableBindingForAddress(device.getName() + ":" + "TEMPERATURE");
	    if (config != null) {
		this.eventPublisher.postUpdate(config.getItem().getName(), new DecimalType(temperature));
	    }
	    config = this.getWritableBindingForAddress(device.getName() + ":" + "HUMIDITY");
	    if (config != null) {
		this.eventPublisher.postUpdate(config.getItem().getName(), new DecimalType(humidity));
	    }

	} else if (message instanceof ClimateMessage) {
	    final int command = ((ClimateMessage) message).getCommand();
	    final int valvePos = ((ClimateMessage) message).getValvePos();

	    final AbstractDevice device = this.dvcStore.get(message.getDestination().getId());

	    AbstractCulBindingConfig config = this.getWritableBindingForAddress(device.getName() + ":" + "DESIRED");
	    if (config != null) {
		this.eventPublisher.postUpdate(config.getItem().getName(), new DecimalType(command));
	    }
	    config = this.getWritableBindingForAddress(device.getName() + ":" + "VALVEPOS");
	    if (config != null) {
		this.eventPublisher.postUpdate(config.getItem().getName(), new DecimalType(valvePos));
	    }
	} else if (message instanceof ShutterStateEvent) {
	    final AbstractCulBindingConfig config = this.getWritableBindingForAddress(message.getSource().getName() + ":" + "STATE");
	    if (config != null) {
		this.eventPublisher.postUpdate(config.getItem().getName(),
			((ShutterStateEvent) message).isClosed() ? OpenClosedType.CLOSED
				: OpenClosedType.OPEN);
	    }
	} else if (message instanceof DimmerStateChangeEvent) {
	    final AbstractCulBindingConfig config = this.getWritableBindingForAddress(message.getSource().getName() + ":" + "DIM");
	    if (config != null) {
		final int state = ((DimmerStateChangeEvent) message).getState() / 2;
		((DimmerItem) config.getItem()).setState(new PercentType(state));
		this.eventPublisher.postUpdate(config.getItem().getName(), new PercentType(state));
	    }
	} else if (message instanceof TemperaturePeriodEvent) {
	    // TODO need to send special ACK!?!
	    // event.getSender().addToSendQueue(new AckStatusMessage(ccu, event.getSender(), (short) 2));
	}

    }
}
