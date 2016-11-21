package net.learn.java.concurrency.counter;

import java.util.concurrent.atomic.LongAdder;

/**
 * Created by gzge on 11/19/16.
 */
public class LongAdderCounter {
    private LongAdder count = new LongAdder();

    public void increment() {
        count.increment();
    }

    public long get() {
        return count.longValue();
    }
}
