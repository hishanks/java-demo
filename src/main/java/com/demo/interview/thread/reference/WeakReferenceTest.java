package com.demo.interview.thread.reference;

import java.lang.ref.WeakReference;

/**
 * @author Shanks
 * @date 2019-06-17
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        /*
         * 弱引用不能超越任何GC循环，并且如果对象没有其他强引用时，则将会被回收
         */
        Object o1 = new Object();
        Object o2 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        WeakReference<Object> hasStrongRef = new WeakReference<>(o2);
        System.out.println("o1: " + o1);
        System.out.println("o2:" + o2);
        System.out.println("weakReference:" + weakReference.get());
        System.out.println("hasStrongRef: " + hasStrongRef.get());

        // 这里将o1置为null，在手动gc之后，o1对应的弱引用则会被gc，但是o2的弱引用不会
        o1 = null;
        System.gc();

        System.out.println("o1: " + o1);
        System.out.println("o2:" + o2);
        System.out.println("weakReference:" + weakReference.get());
        System.out.println("hasStrongRef: " + hasStrongRef.get());
    }
}