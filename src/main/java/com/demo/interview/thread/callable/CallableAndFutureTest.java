package com.demo.interview.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Shanks
 * @date 2019-05-16
 */
public class CallableAndFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Java虚拟机可用的处理器数，实际上这个东西不是很准确，关系到操作系统和硬件。
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors: " + availableProcessors);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> task = () -> {
            System.out.println("start thread.....");
            Thread.sleep(2000);
            System.out.println("end thread.....");
            return "这是Callable的call()方法返回的内容";
        };

        Future<String> future = executor.submit(task);
        Thread.sleep(1000);

        boolean cancelled = future.isCancelled();
        // false
        System.out.println("cancelled: " + cancelled);
        boolean done = future.isDone();
        // false
        System.out.println("done: " + done);
        String result = future.get();
        System.out.println("future.get(): " + result);
        // true
        System.out.println("done: " + future.isDone());

        boolean isShutdown = executor.isShutdown();
        // false
        System.out.println("是否shutdown：" + isShutdown);
        // 手动关闭线程池
        executor.shutdown();
        // true
        System.out.println("是否shutdown: " + executor.isShutdown());
    }
}
