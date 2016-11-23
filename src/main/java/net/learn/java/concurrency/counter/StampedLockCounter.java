package net.learn.java.concurrency.counter;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by gzge on 11/22/16.
 */
public class StampedLockCounter {
    private final StampedLock lock = new StampedLock();
    private long count = 0;

    public void increment() {
        long stamp = lock.writeLock();
        try {
            count += 1;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public long get() {
        long stamp = lock.readLock();
        try {
            return count;
        } finally {
            lock.unlockRead(stamp);
        }
    }
}
