package de.gebauer.homematic.device;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.Message;

public abstract class AbstractDevice implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDevice.class);

    private DeviceInfo info;

    private transient List<Message> eventsReceived = new ArrayList<Message>();
    private transient List<Message> eventsSent = new ArrayList<Message>();

    private String name;

    protected String id;

    private transient Queue<Message> eventStack = new ArrayDeque<Message>();

    private int rxType;

    public AbstractDevice(String name, String id, DeviceInfo info) {
	this.name = name;
	this.id = id;
	this.info = info;
    }

    public DeviceInfo getInfo() {
	return this.info;
    }

    public Message getLastEventReceived() {
	if (this.eventsReceived.size() > 0) {
	    return this.eventsReceived.get(eventsReceived.size() - 1);
	}
	return null;
    }

    public Message getLastEventSend() {
	if (this.eventsSent.size() > 0) {
	    return this.eventsSent.get(eventsSent.size() - 1);
	}
	return null;
    }

    /**
     * Add to history.
     * 
     * @param event
     *            event parsed
     */
    public void addEventReceived(Message event) {
	this.eventsReceived.add(event);
    }

    public void messageSent(Message event) {
	this.eventsSent.add(event);
    }

    public String getName() {
	return name;
    }

    public int getRxType() {
	return rxType;
    }

    public void setRxType(int rxtEntity) {
	rxType = rxtEntity;
    }

    public String getId() {
	return this.id;
    }

    public void setId(final String houseCode) {
	this.id = houseCode;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	AbstractDevice other = (AbstractDevice) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Device [name=" + name + ", id=" + id + "]";
    }

    public void restore() {
	this.eventsReceived = new ArrayList<Message>();
	this.eventsSent = new ArrayList<Message>();
	this.eventStack = new ArrayDeque<Message>();
    }

    public void addToSendQueue(Message event) {
	LOG.info("Adding {} to send queue", event);
	this.eventStack.add(event);
    }

    public Queue<Message> getEventStack() {
	return this.eventStack;
    }

    public abstract Method[] getCommands();

    public abstract DeviceMessageInterpreter getInterpreter();

}
