package com.demo.test.benchmark.forward;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

/**
 * @author Shanks
 * @date 2019-05-31
 */
public class CountPref {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void count() {
        for (int i = 0; i < 1_000_000; i++) {

        }
    }
}
