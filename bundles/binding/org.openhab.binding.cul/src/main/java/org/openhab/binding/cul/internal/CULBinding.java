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

import org.apache.commons.lang.StringUtils;
import org.openhab.binding.cul.CULBindingProvider;
import org.openhab.binding.cul.internal.binding.AbstractCulBindingConfig;
import org.openhab.binding.cul.internal.binding.FHTBindingConfig;
import org.openhab.binding.cul.internal.binding.FS20CommandHelper;
import org.openhab.binding.cul.internal.binding.HomeMaticBindingConfig;
import org.openhab.core.binding.AbstractActiveBinding;
import org.openhab.core.binding.BindingConfig;
import org.openhab.core.binding.BindingProvider;
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
import de.gebauer.homematic.AckStatusMessage;
import de.gebauer.homematic.ConfigEndCommand;
import de.gebauer.homematic.ConfigStartCommand;
import de.gebauer.homematic.ConfigWriteCommand;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.DeviceInfoEvent;
import de.gebauer.homematic.Message;
import de.gebauer.homematic.MessageFlag;
import de.gebauer.homematic.MessageType;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.DeviceFactory;
import de.gebauer.homematic.device.DeviceStore;
import de.gebauer.homematic.device.Model;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.hmcctc.TemperaturePeriodEvent;
import de.gebauer.homematic.hmcctc.WeatherEvent;
import de.gebauer.homematic.hmccvd.ClimateCommand;
import de.gebauer.homematic.hmlcdim1tpi2.DimmerStateChangeEvent;
import de.gebauer.homematic.hmsecsc.ShutterStateEvent;
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

    private static final Logger logger = LoggerFactory
	    .getLogger(CULBinding.class);

    static CULBinding INSTANCE;
    private CULInterface cul;
    private FHTHandler fhtHandler;
    private FS20Handler fs20Handler;
    private HMHandler homeMaticHandler;

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
    private Map<String, FHTQueueItem> fhtCommandQueue = new LinkedHashMap<String, CULBinding.FHTQueueItem>();

    private DeviceStore dvcStore;

    public CULBinding(CULInterface culTransceiver, DeviceStore deviceStore) {
	INSTANCE = this;
	cul = culTransceiver;
	dvcStore = deviceStore;
	logger.debug("Created a new instance of the CULBinding");
    }

    public CULBinding() {
	this(new CULTransceiver(), new DeviceStore());
    }

    /**
     * Here we are creating the protocol handlers and bind them to the CUL interface. Also we register this as a protocol listener for every protocol handler
     * and bind the CUL to the configured port.
     */
    public void activate() {
	fhtHandler = new FHTHandler(cul);
	fs20Handler = new FS20Handler(cul);
	homeMaticHandler = new HMHandler(cul, dvcStore);
	fhtHandler.registerListener(this);
	fs20Handler.registerListener(this);
	homeMaticHandler.registerListener(this);
	cul.registerHandler(fhtHandler);
	cul.registerHandler(fs20Handler);
	cul.registerHandler(homeMaticHandler);
	logger.debug("Activating CULBinding with device " + deviceName);
	try {
	    bindCulInterface();
	} catch (Exception e) {
	    logger.error("Can't open CUL device", e);
	}
    }

    private void scheduleTimeUpdateJob() {
	if (!doTimeUpdate) {
	    return;
	}
	try {
	    Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
	    JobDetail detail = JobBuilder.newJob(UpdateFHTTimeJob.class)
		    .withIdentity("FHT time update job", "cul").build();

	    CronTrigger trigger = TriggerBuilder
		    .newTrigger()
		    .forJob(detail)
		    .withSchedule(
			    CronScheduleBuilder.cronSchedule(cronExpression))
		    .build();
	    updateTimeJobKey = detail.getKey();
	    sched.scheduleJob(detail, trigger);
	} catch (SchedulerException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void unscheduleTimeUpdateJob() {
	if (updateTimeJobKey == null) {
	    return;
	}
	try {
	    Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
	    sched.deleteJob(updateTimeJobKey);
	} catch (SchedulerException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /**
     * Here we are closing the serial device and deactivating the receive mode of the CUL.
     */
    public void deactivate() {
	logger.debug("Deactivating CULBinding, closing serial device");
	this.homeMaticHandler.tearDown();
	cul.close();
    }

    /**
     * @{inheritDoc
     */
    @Override
    protected long getRefreshInterval() {
	return refreshInterval;
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
	logger.debug(isProperlyConfigured ? "We are properly configured"
		: "We are not properly configured");
	return isProperlyConfigured;
    }

    /**
     * We somehow misuse this method here. We are not polling values from the FHT/FS20 system but we issue the last known command for a FHT device here. The
     * problem is that every button press on Setpoint widget in the ui triggers internalReceiveCommand. This can fill the sendbuffer of the CUL very fast. So we
     * memorize only the last command per FHT device and send it in execute().
     * 
     * @{inheritDoc
     */
    @Override
    protected void execute() {

	logger.debug(MessageFormat
		.format("execute() method is called, executing {0} waiting commands for FHTs!",
			fhtCommandQueue.size()));
	LinkedHashMap<String, FHTQueueItem> copyQueue = new LinkedHashMap<String, CULBinding.FHTQueueItem>();
	copyQueue.putAll(fhtCommandQueue);
	for (String address : copyQueue.keySet()) {
	    FHTQueueItem item = copyQueue.get(address);
	    if (item != null) {
		synchronized (cul) {
		    item.execute();
		}
	    }
	    fhtCommandQueue.remove(address);
	}

    }

    public List<FHTBindingConfig> getAllFHTBindingConfigs() {
	List<FHTBindingConfig> allFHTConfigs = new LinkedList<FHTBindingConfig>();
	for (CULBindingProvider provider : providers) {
	    allFHTConfigs.addAll(provider.getFHT80bBindings());
	}
	return allFHTConfigs;
    }

    public void updateFHTTime(FHTBindingConfig config, Date date) {
	synchronized (cul) {
	    config.updateTime(cul, date);
	}
    }

    /**
     * @{inheritDoc
     */
    @Override
    protected void internalReceiveCommand(String itemName, Command command) {
	// the code being executed when a command was sent on the openHAB
	// event bus goes here. This method is only called if one of the
	// BindingProviders provide a binding for the given 'itemName'.
	logger.debug("internalReceiveCommand() is called for item " + itemName);
	AbstractCulBindingConfig config = getBindingForItem(itemName);
	if (config != null && config.isWriteable()) {
	    if (config instanceof FHTBindingConfig) {
		FHTQueueItem item = new FHTQueueItem((FHTBindingConfig) config,
			command);
		fhtCommandQueue.put(config.getAddress(), item);
	    } else if (config instanceof HomeMaticBindingConfig) {
		if (config.executeCommand(cul, command)) {
		    try {
			homeMaticHandler.getMessageSender().processCmdStack(
				homeMaticHandler.getDeviceStore().get(((HomeMaticBindingConfig) config).getId()));
		    } catch (IOException e) {
			logger.error("Unexpected Exception:", e);
		    }
		}
	    } else {
		synchronized (cul) {
		    config.executeCommand(cul, command);
		}
	    }
	}
    }

    /**
     * @{inheritDoc
     */
    @Override
    protected void internalReceiveUpdate(String itemName, State newState) {
	// the code being executed when a state was sent on the openHAB
	// event bus goes here. This method is only called if one of the
	// BindingProviders provide a binding for the given 'itemName'.
	logger.debug("internalReceiveUpdate() is called for item " + itemName);
	for (CULBindingProvider provider : providers) {
	    logger.debug("Checking provider with names {}", provider.getItemNames());
	    AbstractCulBindingConfig parameterAddress = provider.getBindingConfigForItem(itemName);

	    // setStateOnDevice(newState, parameterAddress);
	}
    }

    /**
     * To check wether we provide a valid binding for the given item name
     */
    @Override
    protected boolean providesBindingFor(String itemName) {
	return getBindingForItem(itemName) != null;
    }

    private AbstractCulBindingConfig getBindingForItem(String itemName) {
	if (providers != null) {
	    for (BindingProvider provider : this.providers) {
		BindingConfig config = ((CULBindingProvider) provider)
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

    private AbstractCulBindingConfig getReadOnlyBindingForAddress(String address) {
	if (providers != null) {
	    for (CULBindingProvider provider : providers) {
		AbstractCulBindingConfig config = provider
			.getReadOnlyBindingConfigForAddress(address);
		if (config != null) {
		    return config;
		}
	    }
	}
	logger.warn("Couldn't find read only config for device with address "
		+ address);
	return null;
    }

    private AbstractCulBindingConfig getWritableBindingForAddress(String address) {
	if (providers != null) {
	    for (CULBindingProvider provider : providers) {
		AbstractCulBindingConfig config = provider
			.getWriteableBindingConfigForAddress(address);
		if (config != null) {
		    return config;
		}
	    }
	}
	logger.warn("Couldn't find writeable config for device with address "
		+ address);
	return null;
    }

    /**
     * @{inheritDoc
     */
    @Override
    public void updated(Dictionary<String, ?> config)
	    throws ConfigurationException {
	logger.debug("Receiving new config");
	if (config != null) {
	    dvcStore.clear();
	    Enumeration<String> keys = config.keys();
	    while (keys.hasMoreElements()) {
		String nextElement = keys.nextElement();
		if (nextElement.startsWith("device.")) {
		    String[] split = nextElement.split("\\.");
		    String deviceId = split[1];
		    AbstractDevice device = dvcStore.get(deviceId);
		    if (device == null) {
			String dvcType = (String) config.get("device." + deviceId + ".type");
			String dvcName = (String) config.get("device." + deviceId + ".name");

			Model model = Model.valueOf(dvcType);
			DeviceInfo dvcInfo = new DeviceInfo(null, model, null, 0, 1);
			dvcStore.add(deviceId, new DeviceFactory().createDevice(dvcName, deviceId, dvcInfo));
		    }
		}
	    }

	    // to override the default refresh interval one has to add a
	    // parameter to openhab.cfg like
	    // <bindingName>:refresh=<intervalInMs>
	    String refreshIntervalString = (String) config.get("refresh");
	    if (StringUtils.isNotBlank(refreshIntervalString)) {
		refreshInterval = Long.parseLong(refreshIntervalString);
	    }
	    String houseCode = (String) config.get(PROPERTY_HOUSECODE);
	    if (!StringUtils.isEmpty(houseCode)
		    && !houseCode.equals(this.houseCode)) {
		this.houseCode = houseCode;
		this.homeMaticHandler.getCCU().setId(this.houseCode);
		dvcStore.add(this.houseCode, this.homeMaticHandler.getCCU());
	    }

	    // read further config parameters here ...
	    String deviceName = (String) config.get(PROPERTY_DEVICE);
	    logger.debug("Received new device name: " + deviceName);
	    if (!StringUtils.isEmpty(deviceName)) {
		if (!deviceName.equals(this.deviceName)) {
		    this.deviceName = deviceName;
		    cul.close();
		    try {
			bindCulInterface();
		    } catch (Exception e) {
			isProperlyConfigured = false;
			logger.error("Can't open CUL device after configuration change", e);
			throw new ConfigurationException(PROPERTY_DEVICE,
				"Can't open CUL device", e);
		    }
		    isProperlyConfigured = true;
		}
	    } else {
		isProperlyConfigured = false;
		logger.warn("The serial device is not properly configured");
		throw new ConfigurationException("device",
			"The serial device to use is not configured");
	    }

	    String repitions = (String) config
		    .get(PROPERTY_INTERTECHNO_REPITIONS);
	    if (!StringUtils.isEmpty(repitions)) {
		try {
		    intertechnoRepitions = Integer.parseInt(repitions);
		} catch (NumberFormatException e) {
		    throw new ConfigurationException(
			    PROPERTY_INTERTECHNO_REPITIONS,
			    "This is not a parseable integer " + repitions, e);
		}
	    }

	    String wavelength = (String) config
		    .get(PROPERTY_INTERTECHNO_WAVE_LENGTH);
	    if (!StringUtils.isEmpty(wavelength)) {
		try {
		    intertechnoWavelength = Integer.parseInt(wavelength);
		    if (intertechnoWavelength < 360
			    || intertechnoWavelength > 470) {
			throw new ConfigurationException(
				PROPERTY_INTERTECHNO_WAVE_LENGTH,
				"The length of a single wave puls must be between 360 and 470");
		    }
		} catch (NumberFormatException e) {
		    throw new ConfigurationException(
			    PROPERTY_INTERTECHNO_WAVE_LENGTH,
			    "This is not a parseable integer " + repitions, e);
		}
	    }
	    String cronParam = (String) config.get(PROPERTY_UPDATE_CRON);
	    if (!StringUtils.isEmpty(cronParam)) {
		if (!cronParam.equals(cronExpression)) {
		    this.cronExpression = cronParam;
		    logger.debug("Read cron expression for time update: "
			    + cronExpression);
		    unscheduleTimeUpdateJob();
		}
	    }
	    String updateTime = (String) config.get(PROPERTY_UPDATE_TIME);
	    if (!StringUtils.isEmpty(updateTime)) {
		boolean update = Boolean.parseBoolean(updateTime);
		doTimeUpdate = update;
		if (doTimeUpdate) {
		    logger.debug("Activating time update for FHTs");
		    scheduleTimeUpdateJob();
		} else {
		    unscheduleTimeUpdateJob();
		}
	    }
	    String pairingEnabled = (String) config.get(PAIRING);
	    if (!StringUtils.isEmpty(pairingEnabled)) {
		OnOffType onOff = OnOffType.valueOf(pairingEnabled);
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
	if (deviceName != null) {
	    cul.open(deviceName);
	    if (houseCode != null) {
		logger.debug("Setting house code on CUL to " + houseCode);
		cul.setOwnHouseCode(houseCode);
	    }

	    cul.sendRAW("it" + intertechnoWavelength);
	    cul.sendRAW("isr" + intertechnoRepitions);
	}
    }

    @Override
    public void messageReceived(String housecode, String address,
	    FS20Command command) {
	logger.debug(MessageFormat
		.format("Received fs20 message with housecode {0} from address {1} with command {2}",
			housecode, address, command.toString()));
	AbstractCulBindingConfig config = getReadOnlyBindingForAddress(housecode
		+ address);
	if (config != null) {
	    State state = FS20CommandHelper.getStateFromFS20Command(command);
	    if (state != null) {
		eventPublisher.postUpdate(config.getItem().getName(), state);
	    } else {
		logger.warn("Can't find matching state for fs20 command "
			+ command.toString());
	    }
	}

    }

    @Override
    public void receivedFHTState(String device,
	    de.tobiaswegner.communication.cul4java.FHTState state) {
	logger.debug(MessageFormat.format("Received state {0} for device {1}",
		state, device));
	AbstractCulBindingConfig config = getReadOnlyBindingForAddress(device);
	if (config != null) {
	    switch (state) {
	    case WINDOW_CLOSED:
		eventPublisher.postUpdate(config.getItem().getName(),
			OpenClosedType.CLOSED);
		break;

	    case WINDOW_OPEN:
		eventPublisher.postUpdate(config.getItem().getName(),
			OpenClosedType.OPEN);
		break;
	    case BATTERY_LOW:
	    default:
		logger.warn("Received unimplemented state: " + state.toString());
	    }
	}

    }

    @Override
    public void receivedFHTValue(String device, FHTEvent event,
	    double temperature) {
	logger.debug(MessageFormat
		.format("Received new FHTEvent {0} for device {1} with temperature {2}",
			event, device, temperature));
	AbstractCulBindingConfig config = null;
	switch (event) {
	case ACTUATOR_SETTING:
	    break;
	case DESIRED_TEMPREATURE:
	    config = getWritableBindingForAddress(device);
	    if (config != null) {
		eventPublisher.postUpdate(config.getItem().getName(),
			new DecimalType(temperature));
	    }
	    break;
	case MEASURED_TEMPERATURE:
	    config = getReadOnlyBindingForAddress(device);
	    if (config != null) {
		eventPublisher.postUpdate(config.getItem().getName(),
			new DecimalType(temperature));
	    }
	    break;
	default:
	    break;

	}
    }

    @Override
    public void receivedActuatorStatus(String device, int actuatorNumer,
	    double opening) {
	logger.debug(MessageFormat
		.format("Received new valve opening ({0}) for valve number {1} on device {2}",
			opening, actuatorNumer, device));
	AbstractCulBindingConfig config = getReadOnlyBindingForAddress(device
		+ "0" + actuatorNumer);
	if (config != null) {
	    eventPublisher.postUpdate(config.getItem().getName(),
		    new PercentType(Double.toString(opening)));
	}

    }

    private class FHTQueueItem {
	private FHTBindingConfig config;
	private Command command;

	public FHTQueueItem(FHTBindingConfig config, Command command) {
	    this.command = command;
	    this.config = config;
	}

	public void execute() {
	    config.executeCommand(cul, command);
	}
    }

    /**
     * Method handling the event.
     * 
     * @throws
     */
    @Override
    public void receivedMessage(Message message) {
	logger.debug(MessageFormat.format("Received {0} for {1}", message, message.getDestination()));

	VirtualCCU ccu = this.homeMaticHandler.getCCU();
	if (message instanceof DeviceInfoEvent) {
	    // pairing
	    String serNo = message.getSender().getInfo().serNo;
	    RawMessage msg = message.getRawMessage();

	    if (message.isBroadCast()) {
		if (!ccu.isPairingEnabled()) {
		    logger.info("Pairing not enabled.");
		    return;
		}

	    } else if (msg.getDst().equals(ccu.getId())) {
		// TODO why?
		return;
	    } else if (msg.getMsgType() == MessageType.UNKNOWN2 && msg.getMsgFlag() == MessageFlag.VAL_00) {
		// TODO why?
		return;
	    }
	    AbstractDevice destination = message.getDestination();
	    if (destination != null && destination.getId() != ccu.getId()) {
		logger.info("Not our pairing request.");
		return;
	    }

	    // Pairing CCU with HMCCTC (OK)
	    // 1A C4 84 00 1EA808 000000 21 00394A45513037303939323258 00 FFFF
	    // 10 05 A0 01 13C86C 1EA808 00 05 0000000000
	    // 0A 05 80 02 1EA808 13C86C 00
	    // 13 06 A0 01 13C86C 1EA808 00 08 0201 0A130BC80C6C
	    // 0A 06 80 02 1EA808 13C86C 00
	    // 0B 07 A0 01 13C86C 1EA808 00 06
	    // 0A 07 80 02 1EA808 13C86C 00

	    // NOK:
	    // 22:13:31.646 [1EA808->000000 #DE; len=1A, flag=VAL_84, type=UNKNOWN, p=2100394A4551303730393932325800FFFF]
	    // 22:13:31.772 [13C86D->1EA808 #DF; len=10, flag=VAL_A0, type=CONFIG, p=00050000000000]
	    // 22:13:31.902 [1EA808->13C86D #DF; len=0A, flag=VAL_80, type=ACK, p=00]
	    // 22:13:31.974 [13C86D->1EA808 #E0; len=13, flag=VAL_A0, type=CONFIG, p=00 08 0201 0A130BC80C6D]

	    // Pairing HMCCTC with HMCCVD
	    // 1A A0 84 00 1EA808 000000 21 00394A45513037303939323258 00 FFFF
	    // 1A 8A 84 00 1C4E7F 000000 20 003A4A45513033313237323158 01 0100
	    // 1A A1 A0 00 1EA808 1C4E7F 21 00394A45513037303939323258 00 0200
	    // 0A A1 80 02 1C4E7F 1EA808 00
	    // 10 A2 A0 01 1EA808 1C4E7F 01 040000000005
	    // 10 A2 80 10 1C4E7F 1EA808 02 09000A040000

	    // 1A A2 84 00 1EA808 000000 21 00394A45513037303939323258 00 FFFF
	    // 1A 0C 84 00 1C475A 000000 20 003A4A45513033313333373258 01 0100
	    // 1A A3 A0 00 1EA808 1C475A 21 00394A45513037303939323258 00 0200
	    // 0A A3 80 02 1C475A 1EA808 00
	    // 10 A4 A0 01 1EA808 1C475A 01 040000000005
	    // 10 A4 80 10 1C475A 1EA808 02 09000A0F0000

	    // Pairing CCU with HMSECSC
	    // 1A E4 84 00 2190C5 000000 21 002F4B45513031363434363380810101
	    // 10 E5 A0 01 13C86E 2190C5 00 050000000000
	    // 0A E5 80 02 2190C5 13C86E 00
	    // 13 E6 A0 01 13C86E 2190C5 00 0802010A130BC80C6E
	    // 0A E6 80 02 2190C5 13C86E 00
	    // 0B E7 A0 01 13C86E 2190C5 00 06
	    // 0A E7 80 02 2190C5 13C86E 00

	    // Pairing HMCCTC with HMSECSC
	    // [1EA808->000000 #CD; len=1A, flag=VAL_84, type=UNKNOWN, p=2100394A4551303730393932325800FFFF]
	    // [2190C5->000000 #07; len=1A, flag=VAL_84, type=UNKNOWN, p=21002F4B45513031363434363380810101]
	    // [1EA808->2190C5 #CE; len=1A, flag=VAL_A0, type=UNKNOWN, p=2100394A45513037303939323258000300]
	    // [2190C5->1EA808 #CE; len=0A, flag=VAL_80, type=ACK, p=00]
	    // [1EA808->2190C5 #CF; len=10, flag=VAL_A0, type=CONFIG, p=00051EA8080300]
	    // [2190C5->1EA808 #CF; len=0A, flag=VAL_80, type=ACK, p=00]
	    // [1EA808->2190C5 #D0; len=0D, flag=VAL_A0, type=CONFIG, p=00080901]
	    // [2190C5->1EA808 #D0; len=0A, flag=VAL_80, type=ACK, p=00]
	    // [1EA808->2190C5 #D1; len=0B, flag=VAL_A0, type=CONFIG, p=0006]
	    // [2190C5->1EA808 #D1; len=0A, flag=VAL_80, type=ACK, p=00]
	    // [1EA808->2190C5 #D2; len=10, flag=VAL_A0, type=CONFIG, p=01051EA8080304]
	    // [2190C5->1EA808 #D2; len=0A, flag=VAL_80, type=ACK, p=00]
	    // [1EA808->2190C5 #D3; len=0D, flag=VAL_A0, type=CONFIG, p=01080101]
	    // [2190C5->1EA808 #D3; len=0A, flag=VAL_80, type=ACK, p=00]
	    // [1EA808->2190C5 #D4; len=0B, flag=VAL_A0, type=CONFIG, p=0106]
	    // [2190C5->1EA808 #D4; len=0A, flag=VAL_80, type=ACK, p=00]
	    // [2190C5->1EA808 #08; len=10, flag=VAL_A0, type=CONFIG, p=03052190C50103]
	    // [1EA808->2190C5 #08; len=0A, flag=VAL_80, type=ACK, p=80]

	    logger.info("Initiating Pairing. clearing messages.");
	    message.getSender().getEventStack().clear();
	    final RawMessageBuilder msgBuilder = new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_A0);

	    short channel = 0;
	    // 02010A130BC80C6D
	    int s = 0xA;
	    String content = "0201";
	    for (int i = 0; i < 3; i++) {
		content += String.format("%02X", s++);
		content += homeMaticHandler.getCCU().getId().substring(i * 2, i * 2 + 2);
	    }

	    message.getSender().addToSendQueue(new ConfigStartCommand(msgBuilder.build(), ccu, message.getSender(), channel, 0, (short) 0, (short) 0));
	    for (int l = 0; l < content.length(); l += 28) {
		int ml = content.length() - l < 28 ? content.length() - l : 8;
		message.getSender().addToSendQueue(new ConfigWriteCommand(msgBuilder.build(), ccu, message.getSender(), channel, content.substring(l, ml)));
	    }
	    message.getSender().addToSendQueue(new ConfigEndCommand(msgBuilder.build(), ccu, message.getSender(), channel));

	    homeMaticHandler.getCCU().setHmPairSerial(serNo);

	} else if (message instanceof AckStatusMessage) {
	    AbstractDevice sender = message.getSender();
	    Message lastEventSend = sender.getLastEventSend();
	    // if we have sent a request the we add the response as answer of the request
	    if (lastEventSend != null && lastEventSend.getSender().equals(ccu)) {
		if (sender.getEventStack().isEmpty() && ccu.getHmPairSerial() != null && ccu.getHmPairSerial().equals(sender.getInfo().serNo)) {
		    logger.info("Successfully paired CCU with " + sender);
		    ccu.setHmPairSerial(null);
		} else {
		    // add that as answer no matter if successful or not
		    sender.getLastEventSend().setAnswer(message);
		    Boolean success = ((AckStatusMessage) message).getSuccess();
		    if (Boolean.TRUE.equals(success)) {
		    }
		}
	    }
	} else if (message instanceof WeatherEvent) {
	    float temperature = ((WeatherEvent) message).getTemperature();
	    int humidity = ((WeatherEvent) message).getHumidity();

	    AbstractDevice device = this.dvcStore.get(message.getSender().getId());

	    AbstractCulBindingConfig config = getWritableBindingForAddress(device.getName() + ":" + "TEMPERATURE");
	    if (config != null) {
		eventPublisher.postUpdate(config.getItem().getName(), new DecimalType(temperature));
	    }
	    config = getWritableBindingForAddress(device.getName() + ":" + "HUMIDITY");
	    if (config != null) {
		eventPublisher.postUpdate(config.getItem().getName(), new DecimalType(humidity));
	    }

	} else if (message instanceof ClimateCommand) {
	    int command = ((ClimateCommand) message).getCommand();
	    int valvePos = ((ClimateCommand) message).getValvePos();

	    AbstractDevice device = this.dvcStore.get(message.getDestination().getId());

	    AbstractCulBindingConfig config = getWritableBindingForAddress(device.getName() + ":" + "DESIRED");
	    if (config != null) {
		eventPublisher.postUpdate(config.getItem().getName(), new DecimalType(command));
	    }
	    config = getWritableBindingForAddress(device.getName() + ":" + "VALVEPOS");
	    if (config != null) {
		eventPublisher.postUpdate(config.getItem().getName(), new DecimalType(valvePos));
	    }
	} else if (message instanceof ShutterStateEvent) {
	    AbstractCulBindingConfig config = getWritableBindingForAddress(message.getSender().getName() + ":" + "STATE");
	    if (config != null) {
		eventPublisher.postUpdate(config.getItem().getName(),
			((ShutterStateEvent) message).isClosed() ? OpenClosedType.CLOSED
				: OpenClosedType.OPEN);
	    }
	} else if (message instanceof DimmerStateChangeEvent) {
	    AbstractCulBindingConfig config = getWritableBindingForAddress(message.getSender().getName() + ":" + "DIM");
	    if (config != null) {
		int state = ((DimmerStateChangeEvent) message).getState() / 2;
		eventPublisher.postUpdate(config.getItem().getName(), new DecimalType(state));
	    }
	} else if (message instanceof TemperaturePeriodEvent) {
	    // TODO need to send special ACK?
	    // event.getSender().addToSendQueue(new AckStatusMessage(ccu, event.getSender(), (short) 2));
	}
	AbstractDevice destination = message.getDestination();
	if (destination != null && destination.getId() == ccu.getId()) {
	    // TODO answer!!!

	    if (ccu.equals(message.getDestination())) {
		if (message.needsAck()) {
		    RawMessage build = new RawMessageBuilder().setMsgFlag(MessageFlag.VAL_80).setPayload(String.format("%02X", 0)).build();
		    message.getSender().addToSendQueue(new AckStatusMessage(build, ccu, message.getSender(), (short) message.getChannel(), null));
		}
	    }
	}
    }

}
