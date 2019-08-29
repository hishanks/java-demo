package com.demo.jdk8.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author Shanks
 * @date 2019-03-18
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private static final long serialVersionUID = -6551839616438978732L;

    /**
     * 拆分临界值
     */
    private static final long THRESHOLD = 10000;
    private long start;
    private long end;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        long sum = 0L;
        if (length <= THRESHOLD) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            // 拆分子任务，同时压入线程池队列
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();
            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();

            // 先fork拆分，计算好结果后join得出最终结果
            return left.join() + right.join();
        }
    }
}
