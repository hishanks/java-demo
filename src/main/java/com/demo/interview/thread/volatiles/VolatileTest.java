package com.demo.interview.thread.volatiles;

/**
 * 使用volatile关键字修饰变量保证线程可见
 * 一个是ThreadDemo线程，一个是主线程，现在都访问flag变量的值，若不加volatile，则会导致该变量在多线程之间不可见
 * 即多个线程获取到的值不一致的情况
 *
 * @author Shanks
 * @date 2019-05-22
 */
public class VolatileTest {

    public static void main(String[] args) {

        // 这是一个线程
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        // 这是另一个线程，即主线程
        while (true) {
            if (td.isFlag()) {
                System.out.println("----------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {

    /**
     * 使用volatile修饰，使得该变量对其他线程可见
     */
    private volatile boolean flag = false;

    // private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        System.out.println("flag=" + isFlag());

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}