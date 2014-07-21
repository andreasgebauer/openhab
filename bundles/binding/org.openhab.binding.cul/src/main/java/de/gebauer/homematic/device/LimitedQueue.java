package de.gebauer.homematic.device;

import java.util.LinkedList;

final class LimitedQueue<T> extends LinkedList<T> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final int limit;

    public LimitedQueue(final int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(final T o) {
        super.add(o);
        while (this.size() > this.limit) {
    	super.remove();
        }
        return true;
    }
}