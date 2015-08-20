package org.openhab.binding.cec.internal.protocol.converter;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * Implements a Hex value to integer converter and vice versa.
 * 
 * @author Jan-Willem Spuij
 * @since 1.4.0
 */
public class HexToIntegerConverter extends AbstractSingleValueConverter {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return type.equals(Integer.class) || type.equals(int.class);
	}

	@Override
	public String toString(Object obj) {
		return "0x" + Integer.toHexString((Integer) obj);
	}

	@Override
	public Object fromString(String value) {
		long lVal;
		if (value.startsWith("0x")) {
			lVal = Long.decode(value);
		}
		else {
			lVal = Long.parseLong(value, 16);
		}

		return (int) lVal;
	}
}