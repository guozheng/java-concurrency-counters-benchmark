package net.learn.java.concurrency.counter;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by gzge on 11/19/16.
 */
public class CounterPerfTest {
    public static final int WRITE_THREAD_COUNT = 5;
    public static final int READ_THREAD_COUNT = 5;
    public static final long WRITE_THREAD_TIME_MS = 10;
    public static final long READ_THREAD_TIME_MS = 2;

    @State(Scope.Benchmark)
    public static class CounterPerfState {

        AtomicLongCounter atomicLongCounter = new AtomicLongCounter();
        LongAdderCounter longAdderCounter = new LongAdderCounter();

        ReentrantLockCounter reentrantLockCounter = new ReentrantLockCounter();
        FairReentrantLockCounter fairReentrantLockCounter = new FairReentrantLockCounter();

        ReentrantReadWriteLockCounter reentrantReadWriteLockCounter = new ReentrantReadWriteLockCounter();
        FairReentrantReadWriteLockCounter fairReentrantReadWriteLockCounter = new FairReentrantReadWriteLockCounter();

        SemaphoreCounter semaphoreCounter = new SemaphoreCounter();
        FairSemaphoreCounter fairSemaphoreCounter = new FairSemaphoreCounter();

        StampedLockCounter stampedLockCounter = new StampedLockCounter();
        OptimisticStampedLockCounter optimisticStampedLockCounter = new OptimisticStampedLockCounter();

        VolatileCounter volatileCounter = new VolatileCounter();

        @Setup(Level.Trial)
        public void setup () {}

        @TearDown(Level.Trial)
        public void teardown () {}
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("AtomicLongCounter")
    @GroupThreads(WRITE_THREAD_COUNT)
    public void testAtomicLongCounterWrite(CounterPerfState state) {
        state.atomicLongCounter.increment();
        try {
            Thread.sleep(CounterPerfTest.WRITE_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("AtomicLongCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public long testAtomicLongCounterRead(CounterPerfState state) {
        long c = state.atomicLongCounter.get();
        try {
            Thread.sleep(CounterPerfTest.READ_THREAD_TIME_MS);
        } catch (InterruptedException e) {}
        return c;
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("LongAdderCounter")
    @GroupThreads(WRITE_THREAD_COUNT)
    public void testLongAdderCounterWrite(CounterPerfState state) {
        state.longAdderCounter.increment();
        try {
            Thread.sleep(CounterPerfTest.WRITE_THREAD_TIME_MS);
        } catch (InterruptedException e) {}
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("LongAdderCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public long testLongAdderCounterRead(CounterPerfState state) {
        long c = state.longAdderCounter.get();
        try {
            Thread.sleep(CounterPerfTest.READ_THREAD_TIME_MS);
        } catch (InterruptedException e) {}
        return c;
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("ReentrantLockCounter")
    @GroupThreads(WRITE_THREAD_COUNT)
    public void testReentrantLockCounterWrite(CounterPerfState state) {
        state.reentrantLockCounter.increment();
        try {
            Thread.sleep(CounterPerfTest.WRITE_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("ReentrantLockCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public long testReentrantLockCounterRead(CounterPerfState state) {
        long c = state.reentrantLockCounter.get();
        try {
            Thread.sleep(CounterPerfTest.READ_THREAD_TIME_MS);
        } catch (InterruptedException e) {}
        return c;
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("FairReentrantLockCounter")
    @GroupThreads(WRITE_THREAD_COUNT)
    public void testFairReentrantLockCounterWrite(CounterPerfState state) {
        state.fairReentrantLockCounter.increment();
        try {
            Thread.sleep(CounterPerfTest.WRITE_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("FairReentrantLockCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public long testFairReentrantLockCounterRead(CounterPerfState state) {
        long c = state.fairReentrantLockCounter.get();
        try {
            Thread.sleep(CounterPerfTest.READ_THREAD_TIME_MS);
        } catch (InterruptedException e) {}
        return c;
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("ReentrantReadWriteLockCounter")
    @GroupThreads(WRITE_THREAD_COUNT)
    public void testReentrantReadWriteLockCounterWrite(CounterPerfState state) {
        state.reentrantReadWriteLockCounter.increment();
        try {
            Thread.sleep(CounterPerfTest.WRITE_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("ReentrantReadWriteLockCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public long testReentrantReadWriteLockCounterRead(CounterPerfState state) {
        long c = state.reentrantReadWriteLockCounter.get();
        try {
            Thread.sleep(CounterPerfTest.READ_THREAD_TIME_MS);
        } catch (InterruptedException e) {}
        return c;
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("FairReentrantReadWriteLockCounter")
    @GroupThreads(WRITE_THREAD_COUNT)
    public void testFairReentrantReadWriteLockCounterWrite(CounterPerfState state) {
        state.fairReentrantReadWriteLockCounter.increment();
        try {
            Thread.sleep(CounterPerfTest.WRITE_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("FairReentrantReadWriteLockCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public long testFairReentrantReadWriteLockCounterRead(CounterPerfState state) {
        long c = state.fairReentrantReadWriteLockCounter.get();
        try {
            Thread.sleep(CounterPerfTest.READ_THREAD_TIME_MS);
        } catch (InterruptedException e) {}
        return c;
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("SemaphoreCounter")
    @GroupThreads(WRITE_THREAD_COUNT)
    public void testSemaphoreCounterWrite(CounterPerfState state) {
        state.semaphoreCounter.increment();
        try {
            Thread.sleep(CounterPerfTest.WRITE_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("SemaphoreCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public long testSemaphoreCounterRead(CounterPerfState state) {
        long c = state.semaphoreCounter.get();
        try {
            Thread.sleep(CounterPerfTest.READ_THREAD_TIME_MS);
        } catch (InterruptedException e) {}
        return c;
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("FairSemaphoreCounter")
    @GroupThreads(WRITE_THREAD_COUNT)
    public void testFairSemaphoreCounterWrite(CounterPerfState state) {
        state.fairSemaphoreCounter.increment();
        try {
            Thread.sleep(CounterPerfTest.WRITE_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("FairSemaphoreCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public void testFairSemaphoreCounterRead(CounterPerfState state) {
        state.fairSemaphoreCounter.get();
        try {
            Thread.sleep(CounterPerfTest.READ_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("StampedLockCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public void testStampedLockCounterWrite(CounterPerfState state) {
        state.stampedLockCounter.increment();
        try {
            Thread.sleep(CounterPerfTest.WRITE_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("StampedLockCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public void testStampedLockCounterRead(CounterPerfState state) {
        state.stampedLockCounter.get();
        try {
            Thread.sleep(CounterPerfTest.READ_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("OptimisticStampedLockCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public void testOptimisticStampedLockCounterWrite(CounterPerfState state) {
        state.optimisticStampedLockCounter.increment();
        try {
            Thread.sleep(CounterPerfTest.WRITE_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("OptimisticStampedLockCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public void testOptimisticStampedLockCounterRead(CounterPerfState state) {
        state.optimisticStampedLockCounter.get();
        try {
            Thread.sleep(CounterPerfTest.READ_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("VolatileCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public void testVolatileCounterWrite(CounterPerfState state) {
        state.volatileCounter.increment();
        try {
            Thread.sleep(CounterPerfTest.WRITE_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Group("VolatileCounter")
    @GroupThreads(READ_THREAD_COUNT)
    public void testVolatileCounterRead(CounterPerfState state) {
        state.volatileCounter.get();
        try {
            Thread.sleep(CounterPerfTest.READ_THREAD_TIME_MS);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(CounterPerfTest.class.getName())
            .forks(1)
            .build();

        new Runner(options).run();
    }
}
