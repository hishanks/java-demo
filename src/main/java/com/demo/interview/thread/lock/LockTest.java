package com.demo.interview.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程thread2获取当前锁
 * 线程thread2执行完毕释放锁
 * 线程thread1获取当前锁
 * 线程thread1执行完毕释放锁
 * https://www.cnblogs.com/iyyy/p/7993788.html
 *
 * @author Shanks
 * @date 2019-05-13
 */
public class LockTest {

    private Lock lock = new ReentrantLock();

    /**
     * 使用完毕释放后其他线程才能获取锁
     */
    public void lockTest(Thread thread) {
        // 获取锁
        lock.lock();
        try {
            // 打印当前锁的名称
            System.out.println(thread.getName() + "\t 获取当前锁");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(thread.getName() + "\t 发生了异常释放锁");
        } finally {
            System.out.println(thread.getName() + "\t 执行完毕释放锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        Thread thread1 = new Thread(() -> lockTest.lockTest(Thread.currentThread()), "线程-1");
        Thread thread2 = new Thread(() -> lockTest.lockTest(Thread.currentThread()), "线程-2");
        thread2.start();
        thread1.start();
    }
}