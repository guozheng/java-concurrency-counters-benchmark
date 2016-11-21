package net.learn.java.concurrency.counter;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by gzge on 11/19/16.
 */
public class ReentrantReadWriteLockCounter {
    private long count = 0;
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public void increment() {
        writeLock.lock();
        count += 1;
        writeLock.unlock();
    }

    public long get() {
        readLock.lock();
        long c = count;
        readLock.unlock();
        return c;
    }
}
