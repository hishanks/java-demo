package com.demo.interview.thread.state;

/**
 * @author Shanks
 * @date 2019-06-15
 */
public class ThreadStateTest {

    public static void main(String[] args) {
        Demo demo = new Demo();
        /*
         * main run...
         * main run...
         * main run...
         * main run...
         * main run...
         */
        for (int i = 0; i < 5; i++) {
            demo.run();
        }

        System.out.println("....................");

        /*
         * Thread-0 run...
         * Thread-1 run...
         * Thread-2 run...
         * Thread-3 run...
         * Thread-4 run...
         */
        MyDemo myDemo = new MyDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(myDemo).start();
        }
    }
}

class Demo implements Runnable {

    @Override
    public void run() {
        System.out.printf("%s run...%n", Thread.currentThread().getName());
    }
}

class MyDemo implements Runnable {

    @Override
    public void run() {
        System.out.printf("%s run...%n", Thread.currentThread().getName());
    }
}
