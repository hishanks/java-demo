package com.demo.jdk8.forkjoin;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author Shanks
 * @date 2019-03-18
 */
public class ForkJoinTest {

    private static final long NUM = 1000000000L;

    @Test
    void testForkJoin() {
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        // ForkJoinCalculate extends RecursiveTask<V> extends ForkJoinTask<V> implements Future<V>
        ForkJoinTask<Long> task = new ForkJoinCalculate(0L, NUM);
        Long sum = pool.invoke(task);
        System.out.printf("sum: %d%n", sum);
        Instant end = Instant.now();
        System.out.printf("cost time: %sms%n", Duration.between(start, end).toMillis());
    }

    @Test
    void testFor() {
        Instant start = Instant.now();
        long sum = 0L;
        for (long i = 0; i < NUM; i++) {
            sum += i;
        }
        System.out.printf("sum: %d%n", sum);
        Instant end = Instant.now();
        System.out.printf("cost time: %sms%n", Duration.between(start, end).toMillis());
    }

    @Test
    void testParallel() {
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0L, NUM)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.printf("sum: %d%n", sum);
        Instant end = Instant.now();
        System.out.printf("cost time: %sms%n", Duration.between(start, end).toMillis());
    }
}
