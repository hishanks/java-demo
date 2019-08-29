package com.demo.interview.thread.reference;

import java.lang.ref.SoftReference;

/**
 * 一句话：软引用（假设此时该对象没有其他强引用）可以逃脱GC循环直到JVM耗尽内存
 *
 * @author Shanks
 * @date 2019-06-17
 */
public class SoftReferenceTest {

    /**
     * 配置JVM参数，故意产生大对象（byte数组对象）并配置较小内存（如下大小都设置5MB大小），调试在内存不足的情况下软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRefMemoryNotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        Object o2 = new Object();
        SoftReference<Object> hasStrongRef = new SoftReference<>(o2);

        System.out.println(o1);
        System.out.println(o2);
        System.out.println("o1软引用（强引用o1还在）：" + softReference.get());
        System.out.println("o2软引用（强引用o2还在）：" + hasStrongRef.get());

        o1 = null;
        // 无需手动gc
        // System.gc();

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println("强引用o1：" + o1);
            System.out.println("强引用o2：" + o2);
            System.out.println("o1软引用（强引用o1置为null）：" + softReference.get());
            System.out.println("o2软引用（强引用o2还在）：" + hasStrongRef.get());
        }
    }

    public static void softRefMemoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        Object o2 = new Object();
        SoftReference<Object> hasStrongRef = new SoftReference<>(o2);

        System.out.println("强引用o1：" + o1);
        System.out.println("强引用o2：" + o2);
        System.out.println("o1软引用（强引用o1还在）：" + softReference.get());
        System.out.println("o2软引用（强引用o2还在）：" + hasStrongRef.get());

        o1 = null;
        System.gc();

        // 强引用置null，手动gc会被回收
        System.out.println("强引用o1：" + o1);
        System.out.println("强引用o2：" + o2);
        // 内存足够的情况下，软引用不会被垃圾回收
        System.out.println("o1软引用（强引用o1置为null）：" + softReference.get());
        System.out.println("o2软引用（强引用o2还在）：" + hasStrongRef.get());
    }

    public static void main(String[] args) {
        // softRefMemoryEnough();
        softRefMemoryNotEnough();
    }
}