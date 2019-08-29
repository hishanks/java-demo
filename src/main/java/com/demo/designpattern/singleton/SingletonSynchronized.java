package com.demo.designpattern.singleton;

/**
 * 解析：在Singleton基础上加上了同步锁，使得在多线程的情况下可以用
 * 当两个线程想同时创建实例，由于在一个时刻只有一个线程能得到同步锁
 * 当第一个线程加上锁以后，第二个线程只能等待
 * 第一个线程发现实例没有创建，创建实例，第一个线程释放同步锁，第二个线程才可以加上同步锁
 * 由于第一个线程已经创建了实例，所以第二个线程不需要创建实例，保证在多线程的环境下也只有一个实例
 * 缺点：每次通过getInstance方法得到实例的时候都有一个试图去获取同步锁的过程，aka，加锁是很耗时的，能避免则避免
 *
 * @author zhoukai
 * @date 2018/7/19
 */
public class SingletonSynchronized {

    private static SingletonSynchronized instance;

    private SingletonSynchronized() {
    }

    public static synchronized SingletonSynchronized getInstance() {
        if (instance == null) {
            instance = new SingletonSynchronized();
        }
        return instance;
    }
}