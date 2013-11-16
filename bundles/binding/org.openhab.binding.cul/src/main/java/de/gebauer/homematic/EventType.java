package de.gebauer.homematic;

public enum EventType {
    DEVICE_INFO,
    CLIMATE(),
    WEATHER(),
    UNKNOW,
    PAIRING,
    /**
     * 0x02
     */
    ACK,
    CONFIG_START,
    STATUS_CHANGE,
    WINDOW;
}
