package net.learn.java.concurrency.counter;

/**
 * Created by gzge on 11/22/16.
 */
public class VolatileCounter {
    private volatile long count = 0;

    public void increment() {
        count += 1;
    }

    public long get() {
        return count;
    }
}
