package com.demo.test.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author Shanks
 * @date 2019-05-31
 */
public class HelloWorldRunner {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include("HelloWorld")
                .exclude("CountPref")
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(3)
                .build();
        new Runner(options).run();
    }
}