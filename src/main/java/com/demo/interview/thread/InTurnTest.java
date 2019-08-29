package com.demo.interview.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个线程共同完成打印1~10个任务
 *
 * @author Shanks
 * @date 2019-06-02
 */
public class InTurnTest {

    public static void main(String[] args) {
        Demo demo = new Demo();
        for (int i = 1; i <= 3; i++) {
            new Thread(demo, "线程-" + i).start();
        }
    }
}

class Demo implements Runnable {

    /**
     * 原子操作
     */
    private AtomicInteger atomic = new AtomicInteger(1);
    /**
     * 可重入锁
     */
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            if (lock.tryLock()) {
                if (atomic.intValue() <= 10) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "....." + atomic.getAndIncrement());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    break;
                }
            }
        }
    }
}