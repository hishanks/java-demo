package com.demo.interview.thread.accumulate;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Shanks
 * @date 2019-05-23
 */
public class AccumulateThreadTest {
    public static void main(String[] args) {

        // 开启10个线程来协作计算1+2+3+...+100
        CyclicBarrier barrier = new CyclicBarrier(10, new BarrierAction(new Accumulator()));
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int start = i * 10 + 1;
            int end = start + 9;
            AccumulateThread counterThread = new AccumulateThread(i, start, end, barrier);
            executorService.execute(counterThread);
        }
        executorService.shutdown();
    }
}

/**
 * 触发CyclicBarrier时执行
 * the command to execute when the barrier is tripped, or {@code null} if there is no action
 */
class BarrierAction implements Runnable {

    private Accumulator accumulator;

    public BarrierAction(Accumulator accumulator) {
        this.accumulator = accumulator;
    }

    @Override
    public void run() {
        System.out.println("所有线程运行完毕，最终结果为：" + accumulator.getResult());
    }
}

/**
 * 累加器，用于保存结果
 * 维护一个变量sum，对每一个线程的结果进行一个累加，全部累加后即为最后的结果
 */
class Accumulator {

    private static int result = 0;

    public synchronized static int accumulateEveryThreadResult(int singleThreadResult) {
        result += singleThreadResult;
        return result;
    }

    public int getResult() {
        return result;
    }
}