package de.gebauer.cul.homematic.device;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import de.gebauer.homematic.DeviceInfo;
import de.gebauer.homematic.Event;

public class Device implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private DeviceInfo info;

    private transient List<Event> events = new ArrayList<Event>();

    private String lastMessage;

    private String name;

    private String id;

    private ResponseWait responseWait;

    private transient Queue<Command> cmdStack = new ArrayDeque<Command>();

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
	if (this.events.size() > 0) {
	    return this.events.get(events.size() - 1);
	}
	return null;
    }

    public void addEvent(Event event) {
	this.events.add(event);
    }

    public String getLastMessage() {
	return this.lastMessage;
    }

    public void setLastMessage(String msgX) {
	this.lastMessage = msgX;
    }

    public String getName() {
	return name;
    }

    public ResponseWait getResponseWait() {
	return this.responseWait;
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
	this.events = new ArrayList<Event>();
	this.cmdStack = new ArrayDeque<Command>();
    }

}
