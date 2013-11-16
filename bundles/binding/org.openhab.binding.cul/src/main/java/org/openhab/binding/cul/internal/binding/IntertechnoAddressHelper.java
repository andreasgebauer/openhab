package org.openhab.binding.cul.internal.binding;

import org.openhab.model.item.binding.BindingConfigParseException;

public class IntertechnoAddressHelper {

	public static String getFLSGroupAddress(String group)
			throws BindingConfigParseException {
		StringBuffer addressBuffer = new StringBuffer(4);
		addressBuffer.append("FFFF");
		if ("I".equalsIgnoreCase(group)) {
			addressBuffer.setCharAt(0, '0');

		} else if ("II".equalsIgnoreCase(group)) {
			addressBuffer.setCharAt(1, '0');
		} else if ("III".equalsIgnoreCase(group)) {
			addressBuffer.setCharAt(2, '0');
		} else if ("IV".equalsIgnoreCase(group)) {
			addressBuffer.setCharAt(3, '0');
		} else {
			throw new BindingConfigParseException(
					"Unknown roman number given: " + group);
		}
		return addressBuffer.toString();
	}

	public static String getFLSSubAddress(int remoteId)
			throws BindingConfigParseException {
		if (remoteId < 1 || remoteId > 4) {
			throw new BindingConfigParseException(
					"Only remote addresses in the range 1 - 4 are supported");
		}
		StringBuffer buffer = new StringBuffer(4);
		buffer.append("FFFF");
		buffer.setCharAt(remoteId - 1, '0');
		return buffer.toString();
	}

	public static String getClassicIntertechnoGroupAddress(char address) {
		char aChar = 'A';
		int intValue = address - aChar;
		return getEncodedString(4, intValue, 'F', '0');
	}

	public static String getClassicIntertechnoSubAddress(int address) {
		return getEncodedString(4, address - 1, 'F', '0');
	}

	private static String getEncodedString(int length, int value, char char1,
			char char0) {
		StringBuffer buffer = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			buffer.append(char0);
		}
		if (value == 0) {
			return buffer.toString();
		}
		for (int i = length - 1; i >= 0; i--) {
			int currentBitValue = (int) Math.pow(2, i);
			char bit = char0;
			if (currentBitValue >= value) {
				bit = char1;
				value = value - currentBitValue;
			}
			buffer.setCharAt(i, bit);
		}

		return buffer.toString();

	}

}
