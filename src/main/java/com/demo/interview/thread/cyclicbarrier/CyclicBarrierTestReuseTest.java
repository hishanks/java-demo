package com.demo.interview.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Shanks
 * @date 2019-05-16
 */
public class CyclicBarrierTestReuseTest {

    public static void main(String[] args) {

        int parties = 4;
        CyclicBarrier barrier = new CyclicBarrier(parties);

        for (int i = 0; i < parties; i++) {
            new Writer(barrier).start();
        }

        try {
            // 调试的时候，请注意此处的睡眠时间有点长，稍安勿躁
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---------- CyclicBarrier重用 ----------");

        for (int i = 0; i < parties; i++) {
            new Writer(barrier).start();
        }
    }

    private static class Writer extends Thread {

        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程【" + Thread.currentThread().getName() + "】正在写入数据...");
            try {
                Thread.sleep(5000);
                System.out.println("线程【" + Thread.currentThread().getName() + "】写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
