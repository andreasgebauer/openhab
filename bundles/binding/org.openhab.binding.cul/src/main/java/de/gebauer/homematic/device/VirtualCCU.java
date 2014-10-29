package de.gebauer.homematic.device;

import static org.quartz.TriggerBuilder.newTrigger;

import java.lang.reflect.Method;
import java.util.Calendar;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.in.CCUInterpreter;
import de.gebauer.cul.homematic.in.DeviceMessageInterpreter;
import de.gebauer.homematic.CyclicSendJob;
import de.gebauer.homematic.DeviceInfo;

public class VirtualCCU extends AbstractDevice {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(VirtualCCU.class);

    private boolean pairingEnabled;
    private String hmPairSerial = "";

    private DeviceMessageInterpreter interpreter = new CCUInterpreter();

    public VirtualCCU(final String hmId) {
	super(hmId, hmId, new DeviceInfo("21", Model.CCU, "12345678901234567890"));

	// Cycle cycle = new HMCycle(Calendar.getInstance(), this, 11);
	// this.setCycle(cycle);
    }

    public void setPairingEnabled(final boolean enabled) {
	this.pairingEnabled = enabled;
    }

    public boolean isPairingEnabled() {
	return this.pairingEnabled;
    }

    public String getHmPairSerial() {
	return this.hmPairSerial;
    }

    public void setHmPairSerial(final String serialNo) {
	this.hmPairSerial = serialNo;
    }

    @Override
    public Method[] getCommands() {
	return null;
    }

    @Override
    public DeviceMessageInterpreter getInterpreter() {
	return this.interpreter;
    }

    public void setId(String houseCode) {
	this.id = houseCode;
    }

    public void scheduleCycle(AbstractDevice cycleDevice, AbstractDevice source) {
	final Calendar nextCycle = cycleDevice.getCycle().getNextCycle();
	try {
	    Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
	    int msgCount = cycleDevice.getLastEventReceived().getCount() + 1;
	    TriggerKey triggerKey = new TriggerKey(cycleDevice.getName());// + String.format("%02X", msgCount));
	    JobDetail jobdet = JobBuilder.newJob(CyclicSendJob.class)
		    .withIdentity(triggerKey.getName())
		    .build();
	    jobdet.getJobDataMap().put("destination", cycleDevice);
	    jobdet.getJobDataMap().put("source", source);
	    jobdet.getJobDataMap().put("count", msgCount);
	    Trigger trigger = newTrigger()
		    .withIdentity(triggerKey)
		    .startAt(nextCycle.getTime())
		    .build();

	    long mSeconds = nextCycle.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
	    LOG.debug("Scheduling {} ({}) in {}", triggerKey.getName(), String.format("%02X", msgCount), mSeconds);
	    if (sched.checkExists(triggerKey)) {
		sched.rescheduleJob(triggerKey, trigger);
	    } else {
		sched.scheduleJob(jobdet, trigger);
	    }
	} catch (SchedulerException e) {
	    e.printStackTrace();
	}

    }

}
