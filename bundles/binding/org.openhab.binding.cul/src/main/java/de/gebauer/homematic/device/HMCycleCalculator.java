package de.gebauer.homematic.device;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HMCycleCalculator {

    private static final int minIncrease = 120000;
    private static final int decreaseValue = 14500; // or 14250
    private static final int increaseValue = 49500; // or 49750

    private int startSeed;
    private final Properties periodData;

    public HMCycleCalculator(String device) {
	final InputStream is = HMCycleCalculator.class.getResourceAsStream("/" + device + ".cycle");
	if (is == null) {
	    throw new IllegalArgumentException("no period data for device " + device);
	}
	this.periodData = new Properties();
	try {
	    this.periodData.load(is);
	    this.startSeed = Integer.parseInt(periodData.getProperty("startSeed"));
	} catch (final IOException e) {
	    throw new IllegalArgumentException("no period data for device " + device);
	}
    }

    public int getIncrease(int msgCnt) {
	int cnt = 0;
	int increase = startSeed;

	// if (msgCnt > 0) {
	// increase += this.increaseFirstTime;
	// cnt++;
	// }

	while (cnt < msgCnt) {
	    boolean goHigher = increase - decreaseValue < minIncrease;
	    increase = goHigher ? increase + increaseValue : increase - decreaseValue;
	    int val = (increase - 120000) / 250;
	    boolean variance = this.periodData.containsKey(String.valueOf(cnt + 2));
	    if (variance) {
		increase += 250;
	    }
	    cnt++;
	}
	return increase;
    }
}
