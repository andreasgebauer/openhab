package org.openhab.binding.cul.internal;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.openhab.binding.cul.internal.binding.FHTBindingConfig;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateFHTTimeJob implements Job {

    private final static long updateInterval = 65000;

    private final static Logger log = LoggerFactory.getLogger(UpdateFHTTimeJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
	if (CULBinding.INSTANCE == null) {
	    log.error("The instance of the CUL binding was NULL, can't update FHT times");
	    return;
	}
	log.debug("Starting update of FHT times");
	Iterator<FHTBindingConfig> bindingIterator = CULBinding.INSTANCE.getAllFHTBindingConfigs().iterator();
	while (bindingIterator.hasNext()) {
	    FHTBindingConfig config = bindingIterator.next();
	    log.debug("Updating time of FHT with address " + config.getAddress());
	    try {
		CULBinding.INSTANCE.updateFHTTime(config, new Date());
	    } catch (IOException e1) {
		throw new JobExecutionException(e1);
	    }
	    if (bindingIterator.hasNext()) {
		try {
		    // We sleep a certain so we reduce the chance of flooding
		    // the CUL with messages and filling the send buffer
		    Thread.sleep(updateInterval);
		} catch (InterruptedException e) {
		    log.warn("Thread wouldn't sleep for the update interval of " + updateInterval + " milliseconds");
		}
	    }
	}
    }

}
