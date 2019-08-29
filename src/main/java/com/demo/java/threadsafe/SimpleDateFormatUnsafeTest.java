package com.demo.java.threadsafe;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * SimpleDateFormat是线程不安全的类，一般不要定义为static变量，如果定义为static，必须加锁，或者使用DateUtils工具类。《阿里巴巴手册》
 * ----------
 * SimpleDateFormat的不安全来自于使用了一个全局变量Calendar，即多个线程共享了这个全局变量
 * 而这个变量在操作过程中做了clear，set操作，类似-1，+1操作，这样就导致了SimpleDateFormat在多线程下操作是不安全的
 * 所以，会出现各种各样的异常
 *
 * @author Shanks
 * @date 2019-03-15
 */
public class SimpleDateFormatUnsafeTest {


    /**
     * 线程不安全
     *
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    @Test
    void testSimpleDateFormatNoSafe() throws ExecutionException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return sdf.parse("20190101");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Date> future = pool.submit(task);
            list.add(future);
        }

        for (Future<Date> future : list) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }

    /**
     * 鉴于上面多线程的问题，所以这里可以使用ThreadLocal来解决一下，但是要注意的是ThreadLocal带来的内存泄漏和OOM
     *
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    @Test
    void testSimpleDateFormatSafe() throws ExecutionException, InterruptedException {
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return DateFormatThreadLocal.convert("20190101");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(pool.submit(task));
        }

        for (Future<Date> future : list) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }

    /**
     * synchronized保证线程安全
     *
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    @Test
    void testSimpleDateFormatSynchronized() throws ExecutionException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Callable<Date> callable = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                synchronized (sdf) {
                    return sdf.parse("20190101");
                }
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<Date>> futures = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            futures.add(pool.submit(callable));
        }
        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        pool.shutdown();
    }
}
