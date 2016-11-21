package net.learn.java.concurrency.counter;

/**
 * Created by gzge on 11/19/16.
 */
public class SynchronizedCounter {
    private long count = 0;

    public synchronized void increment() {
        count += 1;
    }

    public synchronized long get() {
        return count;
    }
}
