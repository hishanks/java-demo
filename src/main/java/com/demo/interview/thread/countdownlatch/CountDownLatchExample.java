package com.demo.interview.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Shanks
 * @date 2019-05-20
 */
public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(5);
        Service service = new Service(latch);
        Runnable task = service::exec;

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }

        System.out.println("main thread await. ");
        latch.await();
        System.out.println("main thread finishes await. ");
    }
}

class Service {
    private CountDownLatch latch;

    public Service(CountDownLatch latch) {
        this.latch = latch;
    }

    public void exec() {
        try {
            System.out.println(Thread.currentThread().getName() + " execute task. ");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " finished task. ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }
}
