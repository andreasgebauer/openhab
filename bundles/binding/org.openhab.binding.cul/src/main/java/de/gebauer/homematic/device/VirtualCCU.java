package de.gebauer.homematic.device;

import static org.quartz.TriggerBuilder.newTrigger;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Iterator;

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
import de.gebauer.homematic.msg.Message;

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

	Cycle cycle = new HMCycle(Calendar.getInstance(), this);
	this.setCycle(cycle);
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

    public void scheduleCycle() {
	AbstractDevice nextCycleDevice = this.getCycle().getNextCycleDevice();
	Iterator<Message> eventsSent = this.getEventsSent();
	int messageCount = 0;
	while (eventsSent.hasNext()) {
	    Message next = eventsSent.next();
	    if (next.getDestination().equals(nextCycleDevice)) {
		messageCount = next.getCount() + 1;
	    }
	}
	final Calendar nextCycle = this.getCycle().getNextCycle(messageCount);
	try {
	    Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
	    TriggerKey triggerKey = new TriggerKey(this.getName() + " Cycle");
	    JobDetail jobdet = JobBuilder.newJob(CyclicSendJob.class)
		    .withIdentity(triggerKey.getName())
		    .build();
	    Trigger trigger = newTrigger()
		    .withIdentity(triggerKey)
		    .startAt(nextCycle.getTime())
		    .build();

	    LOG.debug("Scheduling {} in {} at {}", this, (nextCycle.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 1000, nextCycle.getTime());
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
