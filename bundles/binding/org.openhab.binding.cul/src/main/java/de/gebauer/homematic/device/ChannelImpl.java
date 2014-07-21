package de.gebauer.homematic.device;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelImpl implements Channelable {

    public static class Holder {

	private Object object;

	public void set(Object object) {
	    this.object = object;
	}

    }

    private static final Logger LOG = LoggerFactory.getLogger(ChannelImpl.class);

    private Object peerList;
    private short channelId;

    private Map<Short, Map<String, Holder>> map = new HashMap<Short, Map<String, Holder>>();
    private String name;

    public ChannelImpl(short id, String name) {
	channelId = id;
	this.name = name;
    }

    public void setPeerList(Object peerList) {
	LOG.debug("setPeerList {}" + peerList);
	this.peerList = peerList;
    }

    public void setRegL(short list, String peerId, Object object) {
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

    @Override
    public String getName() {
	return this.name;
    }

    @Override
    public short getChannel() {
	return channelId;
    }

}
