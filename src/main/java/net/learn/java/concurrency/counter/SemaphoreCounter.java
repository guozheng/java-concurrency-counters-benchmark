package net.learn.java.concurrency.counter;

import java.util.concurrent.Semaphore;

/**
 * Created by gzge on 11/19/16.
 */
public class SemaphoreCounter {
    private Semaphore sem = new Semaphore(1);
    private long count = 0;

    public void increment() {
        try {
            sem.acquire();
            count += 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem.release();
        }
    }

    public long get() {
        long c = 0;
        try {
            sem.acquire();
            c = count;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sem.release();
        }
        return c;
    }
}
