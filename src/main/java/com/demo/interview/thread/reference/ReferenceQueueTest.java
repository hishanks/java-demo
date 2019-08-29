package com.demo.interview.thread.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author Shanks
 * @date 2019-06-17
 */
public class ReferenceQueueTest {

    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1, referenceQueue);

        System.out.println(o1);
        System.out.println(weakReference.get());
        // 现在引用队列还是空的
        System.out.println(referenceQueue.poll());

        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println("....................");

        System.out.println(o1);
        System.out.println(weakReference.get());
        // 在gc之前，弱引用被放入引用队列
        System.out.println(referenceQueue.poll());
    }
}