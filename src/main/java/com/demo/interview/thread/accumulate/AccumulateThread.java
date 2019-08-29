package com.demo.interview.thread.accumulate;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Shanks
 * @date 2019-05-23
 */
public class AccumulateThread extends Thread {

    private int start;
    private int end;
    private CyclicBarrier barrier;

    public AccumulateThread(int id, int start, int end, CyclicBarrier barrier) {
        this.start = start;
        this.end = end;
        this.barrier = barrier;
        setName("Thread-" + id);
        String threadName = getName();
        System.out.println(threadName + " 从 " + start + " 加到 " + end);

    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            int value = 0;
            for (int i = start; i <= end; i++) {
                value += i;
            }
            // 将每一个线程计算得结果逐个累加
            Accumulator.accumulateEveryThreadResult(value);
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
