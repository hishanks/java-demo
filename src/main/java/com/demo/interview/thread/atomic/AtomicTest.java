package com.demo.interview.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Shanks
 * @date 2019-05-22
 */
public class AtomicTest {

    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }
}

class AtomicDemo implements Runnable {

    /**
     * private volatile int num = 0;
     * 即使使用volatile修饰，已然不能保证原子性
     * private volatile int num = 0;
     * <p>
     * Creates a new AtomicInteger with initial value {@code 0}.
     */
    private AtomicInteger num = new AtomicInteger(1);

    @Override
    public void run() {
        // 若将下面的sleep方法去掉，则因为执行速度较快，所以是按照顺序打印1~10，但是sleep(200)之后，不管是CPU层面还是CAS层面，线程都会出现交替执行
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-----" + getNum());
    }

    public int getNum() {
        // return num++;
        return num.getAndIncrement();
    }
}
