package com.demo.interview.thread.volatiles;

/**
 * 若想达到预期的效果，则必须在count++和count--操作上加锁
 *
 * @author Shanks
 * @date 2019-05-22
 */
public class VolatileNotAtomicTest {

    private static volatile long count = 0L;
    private static final int NUMBER = 10000;

    public static void main(String[] args) {

        SubtractThread subtractThread = new SubtractThread();
        subtractThread.start();

        for (int i = 0; i < NUMBER; i++) {
            synchronized (VolatileNotAtomicTest.class) {
                count++;
            }
        }

        // 等待减法线程结束
        while (subtractThread.isAlive()) {
        }
        System.out.println("count最后的值为：" + count);
    }

    private static class SubtractThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < NUMBER; i++) {
                synchronized (VolatileNotAtomicTest.class) {
                    count--;
                }
            }
        }
    }
}
