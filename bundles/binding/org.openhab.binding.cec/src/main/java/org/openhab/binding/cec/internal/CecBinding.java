package org.openhab.binding.cec.internal;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openhab.binding.cec.CecBindingProvider;
import org.openhab.binding.cec.internal.device.CecDevice;
import org.openhab.binding.cec.internal.device.DeviceType;
import org.openhab.binding.cec.internal.protocol.CecDatabase;
import org.openhab.binding.cec.internal.protocol.Message;
import org.openhab.binding.cec.internal.protocol.MessageFactory;
import org.openhab.binding.cec.internal.protocol.Payload;
import org.openhab.binding.cec.internal.protocol.config.CecConfig;
import org.openhab.binding.cec.internal.protocol.data.PhysicalAddress;
import org.openhab.binding.cec.internal.protocol.data.PowerStatus;
import org.openhab.core.binding.AbstractActiveBinding;
import org.openhab.core.events.EventPublisher;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.StringType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CecBinding extends AbstractActiveBinding<CecBindingProvider> implements ManagedService, PropertyChangeListener {

	private static final Logger LOG = LoggerFactory.getLogger(CecBinding.class);

	private String device;
	private String cecExec = "/usr/local/bin/cec-client";
	boolean autodetect = true;

	private BufferedWriter writer;
	private Process process;
	private Map<Integer, CecDevice> devices = new HashMap<Integer, CecDevice>();
	private CecDatabase database = new CecDatabase();
	private MessageFactory messageFactory = new MessageFactory(this.database);

	public void updated(final Dictionary<String, ?> config) throws ConfigurationException {
		if (config != null) {
			this.device = (String) config.get("device");
			this.setup();
			this.setProperlyConfigured(true);
		}
	}

	public void setEventPublisher(final EventPublisher eventPublisher) {
		super.setEventPublisher(eventPublisher);
		this.eventPublisher = eventPublisher;
	}

	protected void execute() {
		CecBinding.LOG.trace("Executing refresh");

		try {
			if (process == null || process.exitValue() != 0) {
				LOG.debug("Process not running. Exit with: " + process.exitValue());
				setup();
			}
		} catch (IllegalThreadStateException e) {
			// ignored
		}
	}

	private void setup() {
		this.devices.clear();

		List<String> commandWithArgs = new ArrayList<String>(Arrays.asList(cecExec));
		if (!autodetect) {
			commandWithArgs.addAll(Arrays.asList(this.device));
		}
		commandWithArgs.addAll(Arrays.asList("-o", "openHAB", "-d", "31"));

		try {
			final ProcessBuilder processBuilder = new ProcessBuilder(commandWithArgs);

			LOG.debug("Starting process with command '" + commandWithArgs + "'");
			this.process = processBuilder.start();

			final OutputStream outputStream = this.process.getOutputStream();
			this.writer = new BufferedWriter(new OutputStreamWriter(outputStream));

			final InputStream is = this.process.getInputStream();
			final BufferedReader inputReader = new BufferedReader(new InputStreamReader(is));
			new Thread("Input read thread") {
				@Override
				public void run() {
					String line = null;
					try {
						while ((line = inputReader.readLine()) != null) {
							try {
								CecBinding.this.processLineInput(line);
							} catch (Exception e) {
								LOG.error("Got error on line '" + line + "'", e);
							}
						}
					} catch (IOException e) {
						LOG.error("Error reading line", e);
					}
					LOG.info("input read thread stopped.");
				}
			}.start();
			final InputStream err = this.process.getErrorStream();
			final BufferedReader errReader = new BufferedReader(new InputStreamReader(err));
			new Thread("Error read thread") {
				@Override
				public void run() {
					String line = null;
					try {
						while ((line = errReader.readLine()) != null) {
							LOG.error("ERR Stream: {}", line);
						}
					} catch (IOException e) {
						LOG.error("Error reading", e);
					}
					LOG.info("err read thread stopped.");
				}
			}.start();
		} catch (IOException e) {
			LOG.error("Error setting up cec-client", e);
		}
	}

	protected long getRefreshInterval() {
		return 10000L;
	}

	protected String getName() {
		return "CEC Refresh Service";
	}

	/**
	 * {@inheritDoc}
	 */
	public void deactivate() {
		super.deactivate();
		this.process.destroyForcibly();
	}

	protected void processLineInput(final String line) {
		final Matcher matcher = Pattern.compile("TRAFFIC: \\[.*?\\]\t([><]{2}) (.*)").matcher(line);
		if (matcher.matches()) {
			LOG.trace("Got match for: " + line);
			final String direction = matcher.group(1);
			final String value = matcher.group(2);
			final String[] byteStr = value.split(":");
			Integer sendRecv = Integer.valueOf(byteStr[0], 16);
			final int sender = sendRecv >> 4;
			final int receiver = sendRecv &= 0xF;
			final String opCodeStr = (byteStr.length > 1) ? byteStr[1] : null;
			final Integer opCode = opCodeStr != null ? Integer.parseInt(opCodeStr, 16) : null;

			StringBuilder data = new StringBuilder();
			for (int i = 2; i < byteStr.length; i++) {
				data.append(byteStr[i]);
			}
			String dataString = data.length() > 0 ? data.toString() : null;

			if (direction.equals(">>")) {
				this.incoming(sender, receiver, opCode, dataString);
			}
			else {
				this.outgoing(sender, receiver, opCode, dataString);
			}
		}
		else {
			LOG.trace("line: " + line);
		}
	}

	private void incoming(final int sender, final int receiver, final Integer opCode, final String value) {

		CecDevice senderDevice = getDevice(sender);
		CecDevice receiverDevice = getDevice(receiver);

		LOG.trace("Traffic IN: " + senderDevice + " --> " + receiverDevice + " op:" + opCode + " val:" + value);

		if (opCode == null) {
			LOG.trace("No opCode");
			return;
		}

		Message message = messageFactory.parse(opCode, value);
		LOG.debug(senderDevice + " > " + receiverDevice + ": " + message);

		process(senderDevice, receiverDevice, message);

	}

	private void process(CecDevice senderDevice, CecDevice receiverDevice, Message message) {

		// set the target if defined
		if (message.getType().getTarget() != null) {
			String[] split = message.getType().getTarget().split("\\.");
			String objectName = split[0];
			String property = split[1];
			// handle the system target
			if ("system".equals(objectName)) {

				if ("standBy".equals(property)) {
					senderDevice.setPowerStatus(PowerStatus.STANDBY);
				}
			}
		}

		// each payload can update our device states
		for (Payload payload : message.getPayloads()) {
			if (payload.getTarget() != null) {
				String[] split = payload.getTarget().split("\\.");
				String objectName = split[0];
				String property = split[1];
				if ("sender".equals(objectName)) {
					injectTarget(senderDevice, payload, property);
				} else if ("receiver".equals(objectName)) {
					injectTarget(receiverDevice, payload, property);
				} else if ("system".equals(objectName)) {
					if ("activeSource".equals(property)) {
						if (senderDevice.getDeviceType() == DeviceType.TV) {
							senderDevice.setPowerStatus(PowerStatus.ON);
						}
						StringType state = new StringType(payload.getValue().toString());
						for (CecBindingConfig binding : getBindings(-1, "activeSource")) {
							eventPublisher.postUpdate(binding.item, state);
						}
					} else {
						LOG.warn("No Action found for {} and {}", objectName, property);
						return;
					}
				} else {
					LOG.warn("No Action found for {} and {}", objectName, property);
					return;
				}
			}
		}
	}

	private void injectTarget(CecDevice device, Payload payload, String fieldName) {
		try {
			String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());

			boolean setterFound = false;
			Method[] methods = device.getClass().getMethods();
			for (Method setter : methods) {
				if (setter.getName().equals(setterName)) {
					setter.setAccessible(true);
					setter.invoke(device, payload.getValue());
					setterFound = true;
					break;
				}
			}

			if (!setterFound) {
				LOG.warn("Setter not found for " + fieldName + ". Using the field directly.");
				Field declaredField = device.getClass().getDeclaredField(fieldName);
				declaredField.setAccessible(true);
				declaredField.set(device, payload.getValue());
			}
		} catch (ReflectiveOperationException e) {
			LOG.error("Cannot set Field {} on object {}", fieldName, device);
		}
	}

	private CecDevice getDevice(final int id) {
		CecDevice device = this.devices.get(id);
		if (device == null) {
			device = new CecDevice(DeviceType.valueOf(id));
			device.registerListener(this);

			LOG.debug("Storing device " + id + ": " + device);
			this.devices.put(id, device);
		}
		return device;
	}

	protected void outgoing(final int sender, final int receiver, final Integer opCode, final String dataString) {
		// we are not really interested in outgoing traffic
		CecBinding.LOG.trace("Traffic: OUT: " + sender + ">" + receiver + " op:" + opCode + " val:" + dataString);
	}

	protected void internalReceiveCommand(final String itemName, final Command command) {
		super.internalReceiveCommand(itemName, command);
		for (final CecBindingProvider provider : this.providers) {
			final CecBindingConfig config = provider.getBindingFor(itemName);
			if (config != null) {
				if (config.property.equals("powerStatus")) {
					if (command instanceof OnOffType) {
						final String send = ((OnOffType) command == OnOffType.ON) ? "on" : "standby";
						this.writePlain(String.valueOf(send) + " " + config.address);
						continue;
					}
				} else if (config.property.equals("port")) {
					if (command instanceof StringType) {
						final StringType string = (StringType) command;
						PhysicalAddress phAddr = PhysicalAddress.valueOf(string.toString());
						if ("00 00".equals(phAddr.toHexString())) {
							// This seems to be SAMSUNG specific
							this.write("40 9D 00 00");
						} else {
							this.write("4f 82 " + phAddr.toHexString());
						}
						continue;
					}
				}
				LOG.warn("Config found for item " + itemName + ". But haven't found ");
			}
		}
	}

	protected void write(final String cmd) {
		writePlain("tx " + cmd);
	}

	protected void writePlain(final String cmd) {
		CecBinding.LOG.debug("Sending " + cmd + " to CEC adapter");
		try {
			this.writer.append(cmd);
			this.writer.flush();
		} catch (IOException e) {
			CecBinding.LOG.error("Error writing to process. Shutting down ...", (Throwable) e);
			this.process.destroy();
			// this.setup();
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		LOG.trace("PropertyChangeEvent received: {}:{} -> {}", evt.getSource(), evt.getPropertyName(), evt.getNewValue());

		String property = evt.getPropertyName();
		CecDevice device = (CecDevice) evt.getSource();
		int deviceId = device.getDeviceType().getId();

		List<CecBindingConfig> bindings = this.getBindings(deviceId, property);

		if (bindings.isEmpty()) {
			LOG.warn("No binding found for {} and {}", device, property);
			return;
		}

		Object newValue = evt.getNewValue();

		State state = null;
		if (newValue instanceof PowerStatus) {
			switch ((PowerStatus) newValue) {
			case ON:
				state = OnOffType.ON;
				break;
			default:
				state = OnOffType.OFF;
				break;
			}
		}

		if (state == null) {
			state = new StringType(newValue.toString());
		}

		for (CecBindingConfig binding : bindings) {
			eventPublisher.postUpdate(binding.item, state);
		}

		// final CecBindingConfig state = this.getBinding(sender, "state");
		// if (state != null) {
		// LOG.trace("Got config for" + senderDevice + ": " + state);
		// if (opCode == 0x80 || opCode == 0x82) {
		// this.eventPublisher.postUpdate(state.item, (State) OnOffType.ON);
		// }
		// if (opCode == 0x36) {
		// this.eventPublisher.postUpdate(state.item, (State) OnOffType.OFF);
		// }
		// else if (opCode == 0x90 && value != null && value.length() == 2) {
		// if (value.equals("00")) {
		// this.eventPublisher.postUpdate(state.item, (State) OnOffType.ON);
		// }
		// else {
		// this.eventPublisher.postUpdate(state.item, (State) OnOffType.OFF);
		// }
		// }
		// }
		// final CecBindingConfig route = this.getBinding(sender, "route");
		// if (route != null) {
		// if (message.hasOpcode(0x80)) {
		// message.getPayload("newAddress");
		// final String physical = value.substring(4);
		// this.eventPublisher.postUpdate(route.item, (State) new StringType(physical));
		// }
		// else if (message.hasOpcode(0x82)) {
		// final String physical = value;
		// this.eventPublisher.postUpdate(route.item, (State) new StringType(physical));
		// }
		// }
		// final CecBindingConfig activeSource = this.getBinding(sender, "activeSource");
		// if (activeSource != null && opCode == 0x82) {
		// final String osdName = senderDevice.getOsdName();
		// if (osdName != null) {
		// this.eventPublisher.postUpdate(activeSource.item, (State) new StringType(osdName));
		// }
		// else {
		// this.eventPublisher.postUpdate(activeSource.item, (State) UnDefType.NULL);
		// }
		// }
		// if (opCode != 0x80 && opCode != 0x82 && opCode != 0x84) {
		// if (opCode == 0x87) {
		// }
		// else if (opCode == 0x47) {
		// senderDevice.setOsdName(this.toAsciiString(value));
		// }
		// }
	}

	/**
	 * Return the binding associated with the sender id and property.
	 * 
	 * @param sender
	 * @param property
	 * @return
	 */
	private List<CecBindingConfig> getBindings(final int sender, final String property) {
		List<CecBindingConfig> bindings = new ArrayList<CecBindingConfig>();
		for (final CecBindingProvider provider : this.providers) {
			final Collection<String> itemNames = (Collection<String>) provider.getItemNames();
			for (final String item : itemNames) {
				final CecBindingConfig binding = provider.getBindingFor(item);
				final String address = binding.address;
				if (property.equals(binding.property)) {
					if (address == null || address != null && address.equals(String.valueOf(sender))) {
						bindings.add(binding);
					}
				}
			}
		}
		return bindings;
	}

}
