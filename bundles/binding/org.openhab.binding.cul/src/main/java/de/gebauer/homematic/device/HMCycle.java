package de.gebauer.homematic.device;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

import de.gebauer.homematic.CyclicSendJob;

public class HMCycle implements Cycle {

    private static final Properties ONE_PEER_PROPS;

    static {
	try {
	    InputStream resource = CyclicSendJob.class.getResourceAsStream("/1_peer_diff.properties");
	    ONE_PEER_PROPS = new Properties();
	    ONE_PEER_PROPS.load(resource);
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    private Calendar start;
    private Calendar next;

    private int curDevice = 0;

    private AbstractDevice device;

    /**
     * 
     * @param start
     * @param period
     *            the time between commands in seconds
     */
    public HMCycle(final Calendar start, AbstractDevice device) {
	this.start = start;
	this.device = device;
    }

    @Override
    public Calendar getNextCycle(int messageCount) {
	final Calendar next = (Calendar) (this.next != null ? this.next.clone() : this.start.clone());
	int msgCnt = messageCount - 1;
	if (msgCnt < 0) {
	    msgCnt += 256;
	}
	int increase = Integer.valueOf(ONE_PEER_PROPS.getProperty(String.format("%02X", msgCnt)).split(" ")[0]);

	// final int increaseLower = 14500; // or 14250
	// final int increaseHigher = 49500; // or 49750
	// final int increase = this.curIncrease - increaseLower < this.minIncrease ? this.curIncrease + increaseHigher : this.curIncrease - increaseLower;

	Calendar now = Calendar.getInstance();
	while (now.after(next) || now.equals(next)) {
	    next.add(Calendar.MILLISECOND, increase);
	}

	if (this.next == null || next.getTimeInMillis() != this.next.getTimeInMillis()) {
	    this.next = next;
	}

	return next;
    }

    @Override
    public AbstractDevice getNextCycleDevice() {
	if (this.device.getPairedDevices().size() > 0) {
	    return this.device.getPairedDevices().get(curDevice);
	}
	return null;
    }
}