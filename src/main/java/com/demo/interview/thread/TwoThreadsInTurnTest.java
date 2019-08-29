package com.demo.interview.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两个线程交替打印1~10
 *
 * @author Shanks
 * @date 2019-06-02
 */
public class TwoThreadsInTurnTest {

    public static void main(String[] args) {
        PrintDemo demo = new PrintDemo();
        for (int i = 1; i <= 2; i++) {
            new Thread(demo, "线程-" + i).start();
        }
    }
}

class PrintDemo implements Runnable {

    private AtomicInteger atomic = new AtomicInteger(1);

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                // 唤醒另一个线程
                notify();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (atomic.intValue() <= 100) {
                    System.out.println(Thread.currentThread().getName() + "....." + atomic.getAndIncrement());
                    try {
                        // 当前线程wait
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}