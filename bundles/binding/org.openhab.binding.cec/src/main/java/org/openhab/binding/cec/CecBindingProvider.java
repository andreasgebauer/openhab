package org.openhab.binding.cec;

import org.openhab.binding.cec.internal.CecBindingConfig;
import org.openhab.core.binding.BindingProvider;

public interface CecBindingProvider extends BindingProvider
{
	CecBindingConfig getBindingFor(String p0);
}
