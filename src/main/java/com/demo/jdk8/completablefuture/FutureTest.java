package com.demo.jdk8.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author Shanks
 * @date 2019-03-29
 */
public class FutureTest {

    /**
     * 创建ExecutorService，通过它你可以向线程池提交任务
     */
    private static ExecutorService executor = Executors.newCachedThreadPool();
    private static ExecutorService executorFixed = Executors.newFixedThreadPool(10);
    private static ExecutorService executorSingle = Executors.newSingleThreadExecutor();
    private static ExecutorService executorSingleScheduled = Executors.newSingleThreadScheduledExecutor();

    /**
     * 这种编程方式让你的线程可以在ExecutorService以并发方式调用另一个线程执行耗时操作的同时，去执行一些其他的任务。
     * 接着，如果你已经运行到没有异步操作的结果就无法继续任何有意义的工作时，可以调用它的get方法去获取操作的结果。（个人备注：就是此时的操作，需要异步返回的数据时，可以get获取异步数据，将阻塞放在这里）
     * 如果操作已经完成，该方法会立刻返回操作的结果，否则它会阻塞你的线程，直到操作完成，返回相应的结果。
     */
    @Test
    void testFuture() {
        // 向ExecutorService提交一个Callable对象
        Future<Double> future = executor.submit(() -> {
            // 以异步方式在新的线程中执行耗时的操作

            // 这段代码，在1秒钟无法完成，所以报了TimeoutException
            Double value = null;
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                value = (double) i;
            }
            return value;

            // 正常情况下
            // return new Double("0.01");
        });

        // 异步操作进行的同时，你可以做其他的事情
        System.out.println("do something else..");

        try {
            /* 获取异步操作的结果，如果最终被阻塞，无法得到结果，那么在最多等待1秒钟之后退出
             * 至于future.get()
             * 你能想象这种场景存在怎样的问题吗？如果该长时间运行的操作永远不返回了会怎样？
             * 为了处理这种可能性，虽然Future提供了一个无需任何参数的get方法，我们还是推荐大家使用重
             * 载版本的get方法，它接受一个超时的参数，通过它，你可以定义你的线程等待Future结果的最长时间
             * 而不是像future.get(1, TimeUnit.SECONDS)那样永无止境地等待下去。
             */
            // Future接口提供了方法来检测异步计算是否已经结束（使用isDone方法）
            System.out.println(future.isDone());
            System.out.println(future.isCancelled());
            System.out.println(future.get(1, TimeUnit.SECONDS));
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            // InterruptedException：当前线程在等待过程中被中断
            // TimeoutException： 在Future对象完成之前超过已过期
            // ExecutionException：计算抛出一个异常
            e.printStackTrace();
        }
    }

    /**
     * 使用CompletableFuture的一些工厂方法，更简单的操作！
     * 此例调用包含Executor的重载方法
     */
    @Test
    void testCompletableFuture() {
        CompletableFuture<String> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < Byte.MAX_VALUE; i++) {
                builder.append(i);
            }
            return builder.toString();
        }, executor);

        System.out.println("do sth else..");

        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            String s = supplyAsyncFuture.join();
            System.out.println("Hello World".concat(s));
        }, executor);
        runAsyncFuture.join();
    }
}
