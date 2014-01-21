package de.gebauer.homematic.device;

public interface Channelable {

    void setPeerList(Object object);

    void setRegL(short list, String peerId, Object object);

    String getName();

    short getChannel();

}
