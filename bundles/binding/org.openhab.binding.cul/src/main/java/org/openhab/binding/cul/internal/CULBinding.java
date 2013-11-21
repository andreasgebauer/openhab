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
import de.gebauer.cul.homematic.VirtualCCU;
import de.gebauer.cul.homematic.device.Device;
import de.gebauer.cul.homematic.device.DeviceStore;
import de.gebauer.cul.homematic.device.Model;
import de.gebauer.cul.homematic.in.MessageInterpreter;
import de.gebauer.cul.homematic.in.RawMessage;
import de.gebauer.cul.homematic.out.MessageSenderImpl;
import de.gebauer.homematic.AckStatusEvent;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.DeviceInfoEvent;
import de.gebauer.homematic.Event;
import de.gebauer.homematic.HomeMaticDeviceType;
import de.gebauer.homematic.WeatherEvent;
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
     * Property name to retrieve the device name to use. On an UNIXoid OS the
     * user running openHAB has to be in the group dialout and the argument
     * -Dgnu.io.rxtx.SerialPorts=/dev/ttyACM0 has to be given if your serial
     * port is /dev/ttyACM0
     */
    private final static String PROPERTY_DEVICE = "device";

    private int intertechnoRepitions = 6;
    private int intertechnoWavelength = 420;

    /**
     * The house code to use as our own house code. This has nothing to do with
     * the housecodes of the FHTs. It has to be valid house code
     */
    private final static String PROPERTY_HOUSECODE = "housecode";

    private final static String PROPERTY_INTERTECHNO_REPITIONS = "itrepetitions";

    private final static String PROPERTY_INTERTECHNO_WAVE_LENGTH = "itwavelength";

    private final static String PROPERTY_UPDATE_TIME = "fht.time.update";

    private final static String PROPERTY_UPDATE_CRON = "fht.time.update.cron";

    private static final String PAIRING_ENABLED = "pairingEnabled";

    /**
     * Indicates whether this binding is properly configured which means all
     * necessary configurations are set. Only Bindings which are properly
     * configured get's started and will call the execute method though.
     */
    private boolean isProperlyConfigured = false;

    /**
     * the refresh interval which is used to poll values from the CULBinding
     * server (optional, defaults to 30000ms)
     */
    private long refreshInterval = 30000;

    private boolean doTimeUpdate = false;

    /**
     * We can send only a limited amount of commands per time slot and the ui
     * issues a new command update every time you press the '+' or '-' buttons,
     * so we queue these commands here and only execute the last one
     */
    private Map<String, FHTQueueItem> fhtCommandQueue = new LinkedHashMap<String, CULBinding.FHTQueueItem>();

    private DeviceStore dvcStore;

    public CULBinding() {
	INSTANCE = this;
	cul = new CULTransceiver();
	logger.debug("Created a new instance of the CULBinding");
    }

    /**
     * Here we are creating the protocol handlers and bind them to the CUL
     * interface. Also we register this as a protocol listener for every
     * protocol handler and bind the CUL to the configured port.
     */
    public void activate() {
	fhtHandler = new FHTHandler(cul);
	fs20Handler = new FS20Handler(cul);
	dvcStore = new DeviceStore();
	homeMaticHandler = new HMHandler(cul, new VirtualCCU("hmId"), new MessageInterpreter(dvcStore),
		new MessageSenderImpl(cul));
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
     * Here we are closing the serial device and deactivating the receive mode
     * of the CUL.
     */
    public void deactivate() {
	logger.debug("Deactivating CULBinding, closing serial device");
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
     * We somehow misuse this method here. We are not polling values from the
     * FHT/FS20 system but we issue the last known command for a FHT device
     * here. The problem is that every button press on Setpoint widget in the ui
     * triggers internalReceiveCommand. This can fill the sendbuffer of the CUL
     * very fast. So we memorize only the last command per FHT device and send
     * it in execute().
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
		    Device device = dvcStore.get(deviceId);
		    if (device == null) {
			String dvcType = (String) config.get("device." + deviceId + ".type");
			String dvcName = (String) config.get("device." + deviceId + ".name");

			Model model = Model.valueOf(dvcType);
			DeviceInfo dvcInfo = new DeviceInfo(null, model, null,
				model.getDeviceType(), 0, 1);
			dvcStore.add(deviceId, new Device(dvcName, deviceId, dvcInfo));
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
			logger.error(
				"Can't open CUL device after configiration change",
				e);
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
	    String pairingEnabled = (String) config.get(PAIRING_ENABLED);
	    if (!StringUtils.isEmpty(pairingEnabled)) {
		this.pairingEnabled = Boolean.valueOf(pairingEnabled);
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

	    // cul.sendRAW("it" + intertechnoWavelength);
	    // cul.sendRAW("isr" + intertechnoRepitions);
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

    @Override
    public void receivedEvent(Event event) {
	logger.debug(MessageFormat
		.format("Received new HMEvent {0} for device {1}",
			event, event.getReceiver()));

	if (event instanceof DeviceInfoEvent) {
	    String serNo = event.getSender().getInfo().serNo;
	    RawMessage msg = event.getRawMessage();

	    if (event.isBroadCast()) {
		if (!homeMaticHandler.getCCU().isPairingEnabled()) {
		    logger.info("Pairing not enabled.");
		    return;
		}

	    } else if (msg.dst.equals(homeMaticHandler.getCCU().getId())) {
		return;
	    } else if (msg.msgType.equals("04") && msg.msgFlag.equals("00")) {
		return;
	    }
	    Device receiver = event.getReceiver();
	    if (receiver != null && receiver.getId() != this.homeMaticHandler.getCCU().getId()) {
		logger.info("Not our pairing request.");
		return;
	    }

	    logger.info("Initiating Pairing");

	    String idStr = homeMaticHandler.getCCU().getId();
	    int s = 0xA;
	    // 13C86C
	    // 0201 0A130BC80C6C
	    String content = "";
	    for (int i = 0; i < 3; i++) {
		content += String.format("%02X", s++);
		content += idStr.substring(i * 2, i * 2 + 2);
	    }

	    event.getSender().getCommandStack().clear();

	    pushConfig(event.getSender(), 0, 0, 0, 0, "0201"
		    + content);

	    homeMaticHandler.getCCU().setHmPairSerial(serNo);

	    // this.messageSender.sendCmd(event.getSourceDevice(),
	    // event.getSourceDevice().getCommandStack().poll(), 1, 1);

	} else if (event instanceof AckStatusEvent) {
	    Device srcDevice = event.getSender();
	    if (srcDevice.getCommandStack().isEmpty()
		    && homeMaticHandler.getCCU().getHmPairSerial() != null
		    && homeMaticHandler.getCCU().getHmPairSerial().equals(srcDevice.getInfo().serNo)) {
		logger.info("Successfully paired CCU with " + srcDevice);
		homeMaticHandler.getCCU().setHmPairSerial(null);
	    }
	} else if (event instanceof WeatherEvent) {
	    float temperature = ((WeatherEvent) event).getTemperature();
	    int humidity = ((WeatherEvent) event).getHumidity();
	    
	    
	}
	else {
	    Device receiver = event.getReceiver();
	    if (receiver != null && receiver.getId() == this.homeMaticHandler.getCCU().getId()) {
		// TODO answer!!!

		this.homeMaticHandler.getMessageSender()
			.send(new AckStatusEvent(new RawMessage(), this.homeMaticHandler.getCCU(), event.getSender(),
				0, null));
	    }
	}
    }

    private void pushConfig(Device receiver, int chnl, int peerAddr, int peerChnl, int list, String content) {
	logger.info("pushConfig: " + receiver + " " + content);

	// pushCmdStack(sourceDevice, "++" + flag + "01" + src + dst + chnlStr +
	// "05" + peerAddrStr + peerChnStr + listStr);
	pushCmdStack(receiver, String.format("++A001%s%s%02X%s%s", this.homeMaticHandler.getCCU().getId(),
		receiver.getId(), chnl, "05",
		String.format("%s%02X%02X", "000000", peerChnl, list)));

	int tl = content.length();

	for (int l = 0; l < tl; l += 28) {
	    int ml = tl - l < 28 ? tl - l : 8;
	    // pushCmdStack(sourceDevice,
	    // "++A001" + src + dst + chnlStr + "08" + content.substring(l,
	    // ml));
	    pushCmdStack(receiver, String.format("++A001%s%s%02X%s%s", homeMaticHandler.getCCU().getId(),
		    receiver.getId(), chnl, "08",
		    content.substring(l, ml)));
	}
	// pushCmdStack(sourceDevice, "++A001" + src + dst + chnlStr + "06");
	pushCmdStack(receiver, String.format("++A001%s%s%02X%s%s", homeMaticHandler.getCCU().getId(), receiver.getId(),
		chnl, "06", ""));
    }

    private void pushCmdStack(Device receiver, String command) {
	logger.debug("Push cmd: " + receiver + ": " + command);
	receiver.getCommandStack().add(new de.gebauer.cul.homematic.device.Command(command));
    }

}
