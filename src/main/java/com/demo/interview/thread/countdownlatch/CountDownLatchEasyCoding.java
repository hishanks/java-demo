package com.demo.interview.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Shanks
 * @date 2019-05-22
 */
public class CountDownLatchEasyCoding {

    public static void main(String[] args) {

        CountDownLatch count = new CountDownLatch(3);
        Thread thread1 = new TranslateThread("1st content", count);
        Thread thread2 = new TranslateThread("2nd content", count);
        Thread thread3 = new TranslateThread("3rd content", count);
        thread1.start();
        thread2.start();
        thread3.start();

        String mainThreadName = Thread.currentThread().getName();
        try {
            System.out.println("主线程调用count.await之前，当前count值为：" + count.getCount());
            count.await(10, TimeUnit.SECONDS);
            System.out.println("当前线程：" + mainThreadName + "，调用count.await()，线程被挂起，等待子线程执行...");
            System.out.println("主线程调用count.await之后，当前count值为：" + count.getCount());
            System.out.println("所有子线程执行完毕，现在执行主线程：" + mainThreadName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TranslateThread extends Thread {

    private String content;
    private final CountDownLatch count;

    public TranslateThread(String content, CountDownLatch count) {
        this.content = content;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            System.out.println("线程：" + Thread.currentThread().getName() + "，正在执行翻译...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("原文【" + content + "】的翻译已完成");
            count.countDown();
        }
    }
}
