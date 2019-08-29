package com.demo.interview.thread.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 该示例实质上是两个线程，但是分别再跑各自的任务，对于任务而言，没有拆分交给多个线程共同完成，所以该例子不完美
 *
 * @author Shanks
 * @date 2019-05-23
 */
public class CountDownLatchCallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        final int count = 2;
        final CountDownLatch latch = new CountDownLatch(count);

        Task task = new Task(latch);

        int total = 0;
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Future<Integer> future = threadPool.submit(task);
            futures.add(future);
        }

        try {
            System.out.println("主线程阻塞，等待所有子线程执行完毕");
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Future<Integer> future : futures) {
            Integer result = future.get();
            total = Integer.sum(total, result);
        }
        System.out.println("total: " + total);

        threadPool.shutdown();
    }
}


class Task implements Callable<Integer> {

    private CountDownLatch latch;

    public Task(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public Integer call() throws Exception {

        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "正在执行");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            System.out.println("当前线程：" + Thread.currentThread().getName() + "执行完毕");
            return sum;
        } finally {
            latch.countDown();
        }
    }
}
