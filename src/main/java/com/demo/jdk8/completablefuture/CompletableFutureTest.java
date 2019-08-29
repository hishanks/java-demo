package com.demo.jdk8.completablefuture;

import com.alibaba.fastjson.JSON;
import com.demo.jdk8.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 重点部分！！
 *
 * @author Shanks
 * @date 2018-11-21
 */
public class CompletableFutureTest {

    private static int corePoolSize = 2;
    private static int maximumPoolSize = 4;
    private static long keepAliveTime = 10;
    private static TimeUnit unit = TimeUnit.SECONDS;
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private static RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

    private static ThreadPoolExecutor pool =
            ThreadPoolExecutorUtils.getInstance(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, null, handler);
    private static ThreadPoolExecutor executor =
            ThreadPoolExecutorUtils.getInstance(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);

    @Test
    void testCompletableFutureWithPool() {

        System.out.println("-------------------------------------------------- runAsync");
        List<Integer> list = new ArrayList<>(Arrays.asList(12, 22, 42, 24, 2, 532));
        System.out.println("-----> 1: " + JSON.toJSONString(list));
        CompletableFuture<Void> runAsyncSortList = CompletableFuture.runAsync(() -> list.sort(Integer::compareTo), pool);
        System.out.println("-----> 2: " + JSON.toJSONString(list));
        runAsyncSortList.join();
        System.out.println("-----> 3: " + JSON.toJSONString(list));

        System.out.println("-------------------------------------------------- supplyAsync");
        List<Integer> integers = new ArrayList<>(Arrays.asList(12, 22, 42, 24, 2, 532));
        System.out.println("-----> 1: " + integers);
        CompletableFuture<List<Integer>> supplyAsyncCollectList =
                CompletableFuture.supplyAsync(() -> integers.stream().map(i -> i * 10).collect(Collectors.toList()), pool);
        System.out.println("-----> 2: " + integers);
        List<Integer> join = supplyAsyncCollectList.join();
        System.out.println("-----> 3: " + integers);
        System.out.println("-----> 4: " + join);
    }

    @Test
    void testCompletableFutureTest() throws ExecutionException, InterruptedException {

        /*
         * thenApply有点类似map
         */
        System.out.println("-----> get");
        CompletableFuture<Integer> integerFuture = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<String> thenApply = integerFuture.thenApplyAsync(integer -> integer * 10).thenApply(Object::toString);
        // 令人倒胃口的检查异常
        String value = thenApply.get();
        System.out.println(value);

        /*
         * join()比get()方法好用很多，没有令人倒胃口的检查异常
         * 通常根据业务需求，搭配不同的函数式接口
         */
        System.out.println("---------- join ----------");
        // 比如下面这个thenApply，就是为了异步获取一个结果，当最后apply.join()后，数据返回String join
        System.out.println("-----> thenApply");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 20);
        CompletableFuture<String> apply = future.thenApplyAsync(integer -> integer + 10).thenApply(Object::toString);
        String join = apply.join();
        System.out.println(join);

        System.out.println("-----> thenAccept");
        User user = new User();
        // 再比如下面这个，异步查询一个字段且把这个字段值set给User对象的id属性上，当thenAccept.join()执行后，数据异步执行完成
        CompletableFuture<Void> thenAccept = future.thenApplyAsync(integer -> integer + 10).thenApply(Object::toString).thenAccept(user::setId);
        thenAccept.join();
        System.out.println(JSON.toJSONString(user));

        System.out.println("-----> supplyAsync.join()");
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> 99);
        Integer integer = supplyAsync.join();
        System.out.println(integer.toString());

        /*
         * thenCompose有点类似flatMap，避免CompletableFuture<CompletableFuture<String>>这种写法
         * 简单的理解就是，异步里面套异步
         */
        System.out.println("-----> thenCompose");
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> helloWorld = hello.thenCompose(str -> CompletableFuture.supplyAsync(() -> str.concat("World")));
        System.out.println(helloWorld.join());
    }
}
