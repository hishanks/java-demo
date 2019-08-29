package com.demo.interview.thread.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 美[ˈsaɪklɪk] [ˈbæriər]
 * 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 * 我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
 *
 * @author Shanks
 * @date 2019-05-16
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        int parties = 4;
        CyclicBarrier barrier = new CyclicBarrier(parties);
        for (int i = 0; i < parties; i++) {
            new WriterThread(barrier).start();
        }
    }

    private static class WriterThread extends Thread {

        private CyclicBarrier cyclicBarrier;

        public WriterThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                // 以睡眠来模拟写入数据操作
                Thread.sleep(5000);
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                // 用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
