package com.demo.interview.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author shank
 * @date 2019/5/16
 */
public class CyclicBarrierRunnableTest {

    public static void main(String[] args) {

        int parties = 4;
        Runnable barrierAction = () -> System.out.println("当前线程：" + Thread.currentThread().getName());
        CyclicBarrier barrier = new CyclicBarrier(parties, barrierAction);

        for (int i = 0; i < parties; i++) {
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程[" + Thread.currentThread().getName() + "]正在写入数据...");
            try {
                // 以睡眠来模拟写入数据操作
                Thread.sleep(5000);
                System.out.println("线程[" + Thread.currentThread().getName() + "]写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
