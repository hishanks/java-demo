package com.demo.interview.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 1. ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
 * 2. 写：readWriteLock.writeLock().lock();
 * 3. 读：readWriteLock.readLock().lock();
 * 4. 最后finally代码块中，调用对应的unlock();
 *
 * @author Shanks
 * @date 2019-06-17
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // 没事干的话，对下面的方式进行研究下
        // MyCacheReentrant myCache = new MyCacheReentrant();

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> myCache.put(String.valueOf(tempInt), String.valueOf(tempInt)), String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> myCache.get(String.valueOf(tempInt)), String.valueOf(i)).start();
        }
    }
}

/**
 * 正确示例类
 */
class MyCache {

    /**
     * 注意一定要加volatile
     */
    private volatile Map<String, Object> map = new HashMap<>(16);
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入..." + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取...");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

class MyCacheReentrant {

    private volatile Map<String, Object> map = new HashMap<>(16);
    private Lock lock = new ReentrantLock();

    public void put(String k, String v) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入..." + k);
            // 在测试时候，也可以把sleep这段代码注释掉，然后查看不同的结果
            Thread.sleep(300);
            map.put(k, v);
            System.out.println(Thread.currentThread().getName() + "\t 写入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用对读进行lock操作，这里好像又回到了之前，搭配上面的put，这里其实就是读锁都是lock，性能较读写锁略低
     *
     * @param k key
     */
    public void get(String k) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在读取...");
            Thread.sleep(300);
            Object result = map.get(k);
            System.out.println(Thread.currentThread().getName() + "\t" + "读取成功：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 如果这里get操作不做任何lock操作，则在上面多线程代码执行过程中，可能get的结果都是null，但是最后的put都是正常成功的
     * @param k key
     */
    /*public void get(String k) {
        System.out.println(Thread.currentThread().getName() + "\t" + "正在读取...");
        Object result = map.get(k);
        System.out.println(Thread.currentThread().getName() + "\t" + "读取成功：" + result);
    }*/
}