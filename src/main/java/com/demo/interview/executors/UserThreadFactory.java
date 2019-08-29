package com.demo.interview.executors;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程工厂
 *
 * @author Shanks
 * @date 2019-05-11
 */
public class UserThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    /**
     * 定义线程组名称，在使用jstack来排查线程问题时，非常有帮助
     *
     * @param whatFeatureOfGroup whatFeatureOfGroup
     */
    UserThreadFactory(String whatFeatureOfGroup) {
        namePrefix = "UserThreadFactory's " + whatFeatureOfGroup + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, task, name, 0);
        System.out.println(thread.getName());
        return thread;
    }
}
