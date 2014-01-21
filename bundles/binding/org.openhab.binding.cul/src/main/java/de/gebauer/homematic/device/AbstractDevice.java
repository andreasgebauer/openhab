package de.gebauer.homematic.device;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.device.ResponseWait;
import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.cul.homematic.out.MessageSenderImpl.WrappedMessage;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.command.Command;
import de.gebauer.homematic.device.ChannelImpl.Holder;
import de.gebauer.homematic.msg.Message;

public abstract class AbstractDevice implements Serializable, Channelable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDevice.class);

    private DeviceInfo info;
    private final String name;
    protected String id;
    private int rxType;
    private Cycle cycle;

    private transient LinkedList<Message> messagesReceived = new LimitedQueue<Message>(255);
    private transient LinkedList<Message> messagesSent = new LimitedQueue<Message>(255);
    private transient Queue<Message> messageStack = new ArrayDeque<Message>();
    private transient Queue<Command> commandStack = new ArrayDeque<Command>();

    private final Map<Short, Map<String, Holder>> map = new HashMap<Short, Map<String, Holder>>();

    private final ResponseWait responseWait;

    private Object peerList;

    protected Map<Short, Channelable> channels = new HashMap<Short, Channelable>();

    private List<AbstractDevice> pairedDevices = new ArrayList<AbstractDevice>();

    public AbstractDevice(final String name, final String id, final DeviceInfo info) {
	this.name = name;
	this.id = id;
	this.info = info;
	this.responseWait = new ResponseWait();
    }

    public DeviceInfo getInfo() {
	return this.info;
    }

    public void setInfo(final DeviceInfo deviceInfo) {
	this.info = deviceInfo;
    }

    public Message getLastEventReceived() {
	if (this.messagesReceived.size() > 0) {
	    return this.messagesReceived.get(this.messagesReceived.size() - 1);
	}
	return null;
    }

    public Message getLastEventSent() {
	if (this.messagesSent.size() > 0) {
	    Message lastEventSend = this.messagesSent.get(this.messagesSent.size() - 1);
	    while (lastEventSend instanceof WrappedMessage) {
		lastEventSend = ((WrappedMessage) lastEventSend).getWrapped();
	    }
	    return lastEventSend;
	}
	return null;
    }

    /**
     * Retrieves the message sent with the specified count.
     * 
     * @param count
     * @return
     */
    public Message getEventSend(final int count) {
	for (int i = this.messagesSent.size() - 1; i >= 0; i--) {
	    Message msg = this.messagesSent.get(i);
	    if (msg.getCount() == count) {
		LOG.debug("Found {} for {} at {} total: {}", msg, count, i, this.messagesSent.size());
		while (msg instanceof WrappedMessage) {
		    msg = ((WrappedMessage) msg).getWrapped();
		}
		return msg;
	    }
	}
	return null;
    }

    /**
     * Add to history.
     * 
     * @param event
     *            event parsed
     */
    public void messageReceived(final Message event) {
	this.messagesReceived.add(event);
    }

    public void messageSent(final Message event) {
	if (!this.messagesSent.contains(event)) {
	    this.messagesSent.add(event);
	}
    }

    public void addToSendQueue(Command command) {
	LOG.info("Adding {} to send queue", command);
	this.commandStack.add(command);
    }

    public Queue<Command> getCommandStack() {
	return this.commandStack;
    }

    @Override
    public String getName() {
	return this.name;
    }

    public int getRxType() {
	return this.rxType;
    }

    public void setRxType(final int rxtEntity) {
	this.rxType = rxtEntity;
    }

    public String getId() {
	return this.id;
    }

    public void restore() {
	this.messagesReceived = new LimitedQueue<Message>(255);
	this.messagesSent = new LimitedQueue<Message>(255);
	this.messageStack = new ArrayDeque<Message>();
    }

    public Cycle getCycle() {
	return this.cycle;
    }

    public void setCycle(final Cycle cycle) {
	this.cycle = cycle;
    }

    public Message getCycleMessage(final VirtualCCU virtualCCU) {
	return null;
    }

    public ResponseWait getResponseWait() {
	return this.responseWait;
    }

    @Override
    public void setPeerList(final Object object) {
	LOG.debug("setPeerList {}" + object);
	this.peerList = object;
    }

    @Override
    public void setRegL(final short list, final String peerId, final Object object) {
	LOG.debug("setRegL {} {} {}", list, peerId, object);
	Map<String, Holder> holderMap = this.map.get(list);
	if (holderMap == null) {
	    holderMap = new HashMap<String, ChannelImpl.Holder>();
	    this.map.put(list, holderMap);
	}

	Holder holder = holderMap.get(peerId);
	if (holder == null) {
	    holder = new Holder();
	    holderMap.put(peerId, holder);
	}

	holder.set(object);
    }

    public void pairedWith(AbstractDevice source) {
	this.pairedDevices.add(source);
    }

    public List<AbstractDevice> getPairedDevices() {
	return this.pairedDevices;
    }

    public Channelable getChannel(final short chn) {
	return this.channels.get(chn);
    }

    public void addChannel(final Channelable channel) {
	this.channels.put(channel.getChannel(), channel);
    }

    @Override
    public short getChannel() {
	return 0;
    }

    /**
     * Returns iterator over messages sent in descending order.
     * 
     * @return
     */
    public Iterator<Message> getEventsSent() {
	return this.messagesSent.descendingIterator();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (this.id == null ? 0 : this.id.hashCode());
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (this.getClass() != obj.getClass()) {
	    return false;
	}
	final AbstractDevice other = (AbstractDevice) obj;
	if (this.id == null) {
	    if (other.id != null) {
		return false;
	    }
	} else if (!this.id.equals(other.id)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Device [name=" + this.name + ", id=" + this.id + "]";
    }

    public abstract Method[] getCommands();

    public abstract DeviceMessageInterpreter getInterpreter();

}
