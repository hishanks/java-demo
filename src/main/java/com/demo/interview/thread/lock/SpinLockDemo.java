package com.demo.interview.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 借助AtomicReference<Thread>实现自旋锁
 * 核心方法：compareAndSet
 *
 * @author Shanks
 * @date 2019-06-17
 */
public class SpinLockDemo {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName() + "\t is coming O(∩_∩)O哈哈~");
        while (!atomicReference.compareAndSet(null, currentThread)) {
        }
        System.out.println(currentThread.getName() + "\t 获取成功");
    }

    public void myUnlock() {
        Thread currentThread = Thread.currentThread();
        atomicReference.compareAndSet(currentThread, null);
        System.out.println(currentThread.getName() + "\t invoked myUnlock()");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.myUnlock();
            }
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            // 上面模拟操作中，线程B一直在自旋尝试获取，直到线程A睡眠5秒后释放锁，线程B才能获取到
            spinLockDemo.myLock();
            try {

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.myUnlock();
            }
        }, "B").start();
    }
}