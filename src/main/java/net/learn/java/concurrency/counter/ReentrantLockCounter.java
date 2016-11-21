package net.learn.java.concurrency.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gzge on 11/19/16.
 */
public class ReentrantLockCounter {
    private final Lock lock = new ReentrantLock();
    private long count = 0;

    public void increment() {
        lock.lock();
        count += 1;
        lock.unlock();
    }

    public long get() {
        lock.lock();
        long c = count;
        lock.unlock();
        return c;
    }
}
