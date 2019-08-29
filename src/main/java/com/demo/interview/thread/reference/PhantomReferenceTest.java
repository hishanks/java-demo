package com.demo.interview.thread.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用ReferenceQueue在gc之后总会放在引用队列中，类似Spring中的后置通知
 * 如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动
 * 这相当于一种通知机制
 *
 * @author Shanks
 * @date 2019-06-17
 */
public class PhantomReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);

        System.out.println(o1);
        // 虚引用每次调用get总是返回null
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("....................");

        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}