package de.gebauer.homematic;

import org.openhab.binding.cul.internal.HomematicCULBinding;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;

import de.gebauer.cul.homematic.out.MessageSenderImpl;
import de.gebauer.homematic.command.SimpleCommand;
import de.gebauer.homematic.device.AbstractDevice;
import de.gebauer.homematic.device.VirtualCCU;
import de.gebauer.homematic.msg.Message;

public final class CyclicSendJob implements Job {

    @Override
    public void execute(final JobExecutionContext ctxt) throws JobExecutionException {

	try {
	    VirtualCCU ccu = HomematicCULBinding.INSTANCE.ccu;

	    AbstractDevice destination = (AbstractDevice) ctxt.getJobDetail().getJobDataMap().get("destination");
	    AbstractDevice source = (AbstractDevice) ctxt.getJobDetail().getJobDataMap().get("source");
	    if (destination != null) {
		LoggerFactory.getLogger(CyclicSendJob.class).debug("Cyclic send job {} for {} executed.", ctxt.getTrigger().getKey().getName(),
			destination.getName());
		Message cycleMessage = destination.getCycleMessage(source);
		if (cycleMessage != null) {
		    destination.addToSendQueue(new SimpleCommand(new MessageSenderImpl.WrappedMessage(cycleMessage)));
		    HomematicCULBinding.INSTANCE.messageSender.processCmdStack(destination);
		}
	    }

	    ccu.scheduleCycle(destination, source);

	} catch (final Exception e) {
	    throw new JobExecutionException(e);
	}
    }
}