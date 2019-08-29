package com.demo.interview.executors;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Shanks
 * @date 2019-05-11
 */
public class Task implements Runnable {

    private final AtomicLong count = new AtomicLong(0L);

    @Override
    public void run() {
        System.out.println("running_" + count.getAndIncrement());
    }
}
