package org.openhab.binding.boblight4j.internal;

import org.openhab.core.scriptengine.action.ActionService;

public class Boblight4JActionService implements ActionService {

    @Override
    public String getActionClassName() {
	return Boblight4JAction.class.getCanonicalName();
    }

    @Override
    public Class<?> getActionClass() {
	return Boblight4JActionService.class;
    }

}
