package com.demo.interview.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程thread2获取当前锁
 * 我是线程thread1当前锁被别人占用，我无法获取
 * 线程thread2执行完毕释放锁
 *
 * @author Shanks
 * @date 2019-05-13
 */
public class LockTryLockTest {
    private Lock lock = new ReentrantLock();

    /**
     * 尝试获取锁 tryLock() 它表示用来尝试获取锁，如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false
     */
    public void tryLockTest(Thread thread) {
        // 尝试获取锁
        if (lock.tryLock()) {
            try {
                System.out.println("线程【" + thread.getName() + "】获取当前锁");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("线程【" + thread.getName() + "】发生了异常释放锁");
            } finally {
                System.out.println("线程【" + thread.getName() + "】执行完毕释放锁");
                lock.unlock();
            }
        } else {
            // tryLock失败
            System.out.println("我是线程【" + Thread.currentThread().getName() + "】，当前锁被别人占用，我无法获取");
        }
    }

    public static void main(String[] args) {
        LockTryLockTest lockTest = new LockTryLockTest();
        Thread thread1 = new Thread(() -> lockTest.tryLockTest(Thread.currentThread()), "thread1");
        Thread thread2 = new Thread(() -> lockTest.tryLockTest(Thread.currentThread()), "thread2");
        thread2.start();
        thread1.start();
    }
}
