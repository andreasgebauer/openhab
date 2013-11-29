package de.gebauer.cul.homematic.device;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.Event;

public class Device implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(Device.class);

    private DeviceInfo info;

    private transient List<Event> eventsReceived = new ArrayList<Event>();

    private String name;

    protected String id;

    private transient Queue<Command> cmdStack = new ArrayDeque<Command>();

    private transient Queue<Event> eventStack = new ArrayDeque<Event>();

    private int rxType;

    public Device(String name, String id, DeviceInfo info) {
	this.name = name;
	this.id = id;
	this.info = info;
    }

    public DeviceInfo getInfo() {
	return this.info;
    }

    public Event getLastEvent() {
	if (this.eventsReceived.size() > 0) {
	    return this.eventsReceived.get(eventsReceived.size() - 1);
	}
	return null;
    }

    /**
     * Add to history.
     * 
     * @param event
     *            event parsed
     */
    public void addEvent(Event event) {
	this.eventsReceived.add(event);
    }

    public String getName() {
	return name;
    }

    public Queue<Command> getCommandStack() {
	return this.cmdStack;
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
	Device other = (Device) obj;
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
	this.eventsReceived = new ArrayList<Event>();
	this.cmdStack = new ArrayDeque<Command>();
	this.eventStack = new ArrayDeque<Event>();
    }

    public void addToSendQueue(Event event) {
	LOG.info("Adding {} to send queue", event);
	this.eventStack.add(event);
    }

    public Queue<Event> getEventStack() {
	return this.eventStack;
    }

}
