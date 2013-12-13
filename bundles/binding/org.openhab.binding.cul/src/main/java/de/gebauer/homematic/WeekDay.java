package de.gebauer.homematic;

public enum WeekDay {

    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(0),
    SUNDAY(1), ;

    private int val;

    private WeekDay(int val) {
	this.val = val;
    }

    public static WeekDay of(int i) {
	for (WeekDay wd : values()) {
	    if (wd.val == i) {
		return wd;
	    }
	}
	return null;
    }
}
