package de.gebauer.homematic.device;

import java.util.Calendar;

public class HMCycle implements Cycle {

    private Calendar start;
    private Calendar next;

    private AbstractDevice device;
    private HMCycleCalculator calculator;

    /**
     * 
     * @param start
     * @param
     */
    public HMCycle(final Calendar start, AbstractDevice device) {
	this.start = start;
	this.device = device;
	this.calculator = new HMCycleCalculator(device.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.gebauer.homematic.device.Cycle#getNextCycle(int)
     */
    @Override
    public Calendar getNextCycle() {
	final Calendar next = (Calendar) (this.next != null ? this.next.clone() : this.start.clone());
	int msgCnt = this.device.getLastEventReceived().getCount();
	if (msgCnt > 255) {
	    msgCnt -= 256;
	}

	int increase = calculator.getIncrease(msgCnt);

	while (Calendar.getInstance().after(next) || Calendar.getInstance().equals(next)) {
	    next.add(Calendar.MILLISECOND, increase);
	}

	if (this.next == null || next.getTimeInMillis() != this.next.getTimeInMillis()) {
	    this.next = next;
	}

	return next;
    }

}