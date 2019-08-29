package com.demo.jdk8.completablefuture;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 为线程和线程池创建有意义的名称（例如自定义ThreadFactory，重写线程命名方法）
 * 参考下Executors.defaultThreadFactory();源码实现
 *
 * @author Shanks
 * @date 2019-03-04
 */
public class ThreadPoolExecutorUtils {

    private static volatile ThreadPoolExecutor instance;
    private static ThreadFactory factory = new DefaultThreadFactory();
    private static ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();

    private ThreadPoolExecutorUtils() {
    }

    public static ThreadPoolExecutor getInstance(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                                 BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        if (instance == null) {
            synchronized (ThreadPoolExecutorUtils.class) {
                if (instance == null) {
                    if (threadFactory == null) {
                        instance = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, factory, handler);
                    } else {
                        instance = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
                    }
                }
            }
        }
        return instance;
    }

    private static ThreadPoolExecutor threadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                                         BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);

    }

    static class DefaultThreadFactory implements ThreadFactory {

        private final AtomicInteger nThreads = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "DefaultNamedThread-" + nThreads.getAndIncrement());
            System.out.println(t.getName() + " has been created!!");
            return t;
        }
    }
}
