package de.gebauer.homematic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.gebauer.cul.homematic.in.RawMessage;

public class Utils {

    /**
     * Build a matcher for payload of given message using given regular expression.
     * 
     * @param msg
     * @param regex
     * @return
     */
    public static Matcher matcherFor(RawMessage msg, String regex) {
	return matcherFor(msg.getPayload(), regex);
    }

    public static Matcher matcherFor(String data, String regex) {
	return Pattern.compile(regex).matcher(data);
    }
}
