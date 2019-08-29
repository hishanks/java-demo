package com.demo.interview.thread.lock;

/**
 * @author Shanks
 * @date 2019-05-21
 */
public class DeadLockTest {

    private static String a = "1";

    private static String b = "2";

    public void methodA() {
        synchronized (a) {
            System.out.println("我是A方法，获得到了a锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b) {
                System.out.println("我是A方法，获取到b锁");
            }
        }

    }

    public void methodB() {
        synchronized (b) {
            System.out.println("我是B方法，获得到了b锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (a) {
                System.out.println("我是B方法，获取到a锁");
            }
        }
    }

    public static void main(String[] args) {

        DeadLockTest d = new DeadLockTest();
        new Thread(() -> d.methodA()).start();
        new Thread(() -> d.methodB()).start();
    }
}
