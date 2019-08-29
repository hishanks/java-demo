package com.demo.interview.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程thread2获取当前锁
 * 此时线程thread1当前锁被别人占用，等待3s后仍无法获取，放弃
 * 线程thread2执行完毕释放锁
 *
 * @author Shanks
 * @date 2019-05-13
 */
public class LockTryLockOverloadTest {

    private Lock lock = new ReentrantLock();

    public void tryLockParamTest(Thread thread) throws InterruptedException {
        // 尝试获取锁 获取不到锁，就等3秒，如果3秒后还是获取不到就返回false
        if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
            try {
                // 打印当前锁的名称
                System.out.println(thread.getName() + "\t " + "获取当前锁");
                // 这里的时间分别改为2000和4000测试下
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(thread.getName() + "\t 发生了异常释放锁");
            } finally {
                System.out.println(thread.getName() + "\t 执行完毕释放锁");
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "\t 当前锁被别人占用，等待3s后仍无法获取，放弃");
        }
    }

    public static void main(String[] args) {
        LockTryLockOverloadTest lockTest = new LockTryLockOverloadTest();
        Thread thread1 = new Thread(() -> {
            try {
                lockTest.tryLockParamTest(Thread.currentThread());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            try {
                lockTest.tryLockParamTest(Thread.currentThread());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2");

        thread2.start();
        thread1.start();
    }
}
