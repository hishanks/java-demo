package com.demo.interview.executors;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义拒绝策略
 * 在ThreadPoolExecutor中提供了四个公开的内部静态类
 * 1.AbortPolicy（默认）：丢弃任务并抛出RejectedExecutionException异常
 * 2.DiscardPolicy：丢弃任务，但是不抛出异常，这是不推荐的做法
 * 3.DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加入到队列中
 * 4.CallerRunsPolicy：调用任务的run()方法绕过线程池直接执行
 *
 * @author Shanks
 * @date 2019-05-11
 */
public class UserRejectedHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
        System.out.println("task rejected. " + executor.toString());
    }
}
