package com.demo.interview.thread.volatiles;

/**
 * @author Shanks
 * @date 2019-05-22
 */
public class VolatileNotAtomic {

    private static volatile long count = 0L;
    private static final int NUMBER = 10000;

    public static void main(String[] args) {

        SubtractThread subtractThread = new SubtractThread();
        // SubtractThread线程进行count--操作
        subtractThread.start();

        // 主线程进行count++操作，注意这里的循环次数和SubtractThread的一样，期望的最后结果是：count为0
        for (int i = 0; i < NUMBER; i++) {
            count++;
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
                count--;
            }
        }
    }
}
