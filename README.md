# Java Concurrent Counter Performance Comparison

A simple counter implemented using different java concurrency utilities to compare the performance characteristics.

## Environment

* *Hardware*: a mid-2012 Macbook Pro with 2.6 GHz Core i7 CPU and 16GB 1600 MHz DDR3 memory.
* *Software*: macOS Sierra (v10.12.1), Java 8 (build 1.8.0_66-b17), jmh 1.16

## Tests and results

The tests uses different concurrency utilities to implement a thread-safe counter with a `long` type value:

* Synchronized
* Volatile
* AtomicLong
* LongAdder
* ReentrantLock in reguar and fair modes
* ReentrantReadWriteLock in reguar and fair modes
* StampedLock in regular read/write locks and optimistic reading lock
* Semaphore in regular and fair mode

The tests data can be found in `data`. The sub-dir name represents the jmh command line options used, e.g. `wi2-i2-f1` means 2 warmup iterations, 2 benchmark iterations and 1 fork benchmark run. The raw jmh output can be found in the actual data files. The data file name means the write and read thread counts used for the benchmark run. Inside the `graphs` sub-dir, these are visualizations for some of the comparisons.

I used Excel as a quick way to run analysis and generate visualizations. You can find the Excel file (`.xlsx`) also in the data sub-dir.

If you want to contribute to the repo, please use the same naming conventions in the `data` directory.

## How to run the tests

The tests are written using JMH (Java Microbenchmark Harness), one of the standard way of doing micro performance benchmarking.

By default perform 20 warmup iterations, 20 measurement iterations and fork 10 times for the benchmark.

```bash
mvn clean install
java -jar target/benchmarks.jar
```

Perform 5 warmup iterations, 5 measurement iterations and fork 1 time for the benchmark.

```bash
mvn clean install
java -jar target/benchmarks.jar -wi 5 -i 5 -f 1
```

## `jmh` command line options

```bash
$ java -jar target/benchmarks.jar -h

Usage: java -jar ... [regexp*] [options]
 [opt] means optional argument.
 <opt> means required argument.
 "+" means comma-separated list of values.
 "time" arguments accept time suffixes, like "100ms".

  [arguments]                 Benchmarks to run (regexp+).

  -bm <mode>                  Benchmark mode. Available modes are: [Throughput/thrpt,
                              AverageTime/avgt, SampleTime/sample, SingleShotTime/ss,
                              All/all]

  -bs <int>                   Batch size: number of benchmark method calls per
                              operation. Some benchmark modes may ignore this
                              setting, please check this separately.

  -e <regexp+>                Benchmarks to exclude from the run.

  -f <int>                    How many times to fork a single benchmark. Use 0 to
                              disable forking altogether. Warning: disabling
                              forking may have detrimental impact on benchmark
                              and infrastructure reliability, you might want
                              to use different warmup mode instead.

  -foe <bool>                 Should JMH fail immediately if any benchmark had
                              experienced an unrecoverable error? This helps
                              to make quick sanity tests for benchmark suites,
                              as well as make the automated runs with checking error
                              codes.

  -gc <bool>                  Should JMH force GC between iterations? Forcing
                              the GC may help to lower the noise in GC-heavy benchmarks,
                              at the expense of jeopardizing GC ergonomics decisions.
                              Use with care.

  -h                          Display help.

  -i <int>                    Number of measurement iterations to do. Measurement
                              iterations are counted towards the benchmark score.

  -jvm <string>               Use given JVM for runs. This option only affects forked
                              runs.

  -jvmArgs <string>           Use given JVM arguments. Most options are inherited
                              from the host VM options, but in some cases you want
                              to pass the options only to a forked VM. Either single
                              space-separated option line, or multiple options
                              are accepted. This option only affects forked runs.

  -jvmArgsAppend <string>     Same as jvmArgs, but append these options before
                              the already given JVM args.

  -jvmArgsPrepend <string>    Same as jvmArgs, but prepend these options before
                              the already given JVM arg.

  -l                          List the benchmarks that match a filter, and exit.

  -lp                         List the benchmarks that match a filter, along with
                              parameters, and exit.

  -lprof                      List profilers.

  -lrf                        List machine-readable result formats.

  -o <filename>               Redirect human-readable output to a given file.

  -opi <int>                  Override operations per invocation, see @OperationsPerInvocation
                              Javadoc for details.

  -p <param={v,}*>            Benchmark parameters. This option is expected to
                              be used once per parameter. Parameter name and parameter
                              values should be separated with equals sign. Parameter
                              values should be separated with commas.

  -prof <profiler>            Use profilers to collect additional benchmark data.
                              Some profilers are not available on all JVMs and/or
                              all OSes. Please see the list of available profilers
                              with -lprof.

  -r <time>                   Minimum time to spend at each measurement iteration.
                              Benchmarks may generally run longer than iteration
                              duration.

  -rf <type>                  Format type for machine-readable results. These
                              results are written to a separate file (see -rff).
                              See the list of available result formats with -lrf.

  -rff <filename>             Write machine-readable results to a given file.
                              The file format is controlled by -rf option. Please
                              see the list of result formats for available formats.

  -si <bool>                  Should JMH synchronize iterations? This would significantly
                              lower the noise in multithreaded tests, by making
                              sure the measured part happens only when all workers
                              are running.

  -t <int>                    Number of worker threads to run with. 'max' means
                              the maximum number of hardware threads available
                              on the machine, figured out by JMH itself.

  -tg <int+>                  Override thread group distribution for asymmetric
                              benchmarks. This option expects a comma-separated
                              list of thread counts within the group. See @Group/@GroupThreads
                              Javadoc for more information.

  -to <time>                  Timeout for benchmark iteration. After reaching
                              this timeout, JMH will try to interrupt the running
                              tasks. Non-cooperating benchmarks may ignore this
                              timeout.

  -tu <TU>                    Override time unit in benchmark results. Available
                              time units are: [m, s, ms, us, ns].

  -v <mode>                   Verbosity mode. Available modes are: [SILENT, NORMAL,
                              EXTRA]

  -w <time>                   Minimum time to spend at each warmup iteration. Benchmarks
                              may generally run longer than iteration duration.

  -wbs <int>                  Warmup batch size: number of benchmark method calls
                              per operation. Some benchmark modes may ignore this
                              setting.

  -wf <int>                   How many warmup forks to make for a single benchmark.
                              All iterations within the warmup fork are not counted
                              towards the benchmark score. Use 0 to disable warmup
                              forks.

  -wi <int>                   Number of warmup iterations to do. Warmup iterations
                              are not counted towards the benchmark score.

  -wm <mode>                  Warmup mode for warming up selected benchmarks.
                              Warmup modes are: INDI = Warmup each benchmark individually,
                              then measure it. BULK = Warmup all benchmarks first,
                              then do all the measurements. BULK_INDI = Warmup
                              all benchmarks first, then re-warmup each benchmark
                              individually, then measure it.

  -wmb <regexp+>              Warmup benchmarks to include in the run in addition
                              to already selected by the primary filters. Harness
                              will not measure these benchmarks, but only use them
                              for the warmup.
```

## Special thanks and references

1. [Isuru's blog post: Benchmarking Java Locks with Counters](http://isuru-perera.blogspot.com/2016/05/benchmarking-java-locks-with-counters.html) and the [git repo](https://github.com/chrishantha/microbenchmarks). I found out this in the middle of my experiment and found it was doing almost the same tests. Based on his blog, I made further improvements on mine. Comparing to his great work, I tried several more combinations, pushed read and write thread count higher and reported read and write results separately.
1. Takipi's blog post on [Java 8 LongAdder performance](http://blog.takipi.com/java-8-longadders-the-fastest-way-to-add-numbers-concurrently/) and [Java 8 StampedLock performance](http://blog.takipi.com/java-8-stampedlocks-vs-readwritelocks-and-synchronized/). And their git repo for counter benchmark (they did not use jmh somehow).
1. Jakob Jenkov's great [beginner's intro to JMH](http://tutorials.jenkov.com/java-performance/jmh.html), all his series are very enjoyable and informative readings.
1. java-performance.info posts: [Introduction to JMH](http://java-performance.info/jmh/) and [Introduction to JMH Profilers](http://java-performance.info/introduction-jmh-profilers/).
1. Nitsan Wakart's posts: [JMH Resources](http://psy-lob-saw.blogspot.com/p/jmh-related-posts.html), [Writing Java Micro Benchmarks with JMH: Juice](http://psy-lob-saw.blogspot.fr/2013/04/writing-java-micro-benchmarks-with-jmh.html), [Using JMH to Benchmark Multi-Threaded Code](http://psy-lob-saw.blogspot.com/2013/05/using-jmh-to-benchmark-multi-threaded.html)