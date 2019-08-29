package com.demo.interview.thread.volatiles;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Shanks
 * @date 2019-05-26
 */
public class VolatileIsNotAtomicTest {

    public static void main(String[] args) {
        IncreaseThread increaseThread = new IncreaseThread();
        for (int i = 0; i < 10; i++) {
            new Thread(increaseThread).start();
        }
    }
}

class IncreaseThread implements Runnable {

    private volatile int result = 0;
    private volatile int resultSync = 0;
    private AtomicInteger resultAtomic = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String threadName = Thread.currentThread().getName();
        System.out.println("当前线程【" + threadName + "】result的值为：" + increase());
        // System.out.println("当前线程【" + threadName + "】resultSync的值为：" + increaseSync());
        // System.out.println("当前线程【" + threadName + "】resultAtomic的值为：" + increaseAtomic());
    }

    public int increase() {
        return ++result;
    }

    public int increaseSync() {
        synchronized (this) {
            return ++resultSync;
        }
    }

    public int increaseAtomic() {
        // 这里使用incrementAndGet返回新增操作后的新值，getAndIncrement返回的是自增之前的值
        return resultAtomic.incrementAndGet();
    }
}

