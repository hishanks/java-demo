package com.demo.interview.thread.reference;

/**
 * @author Shanks
 * @date 2019-06-17
 */
public class StrongReferenceTest {

    public static void main(String[] args) {
        Object o1 = new Object();
        System.out.println(o1);

        o1 = null;
        System.gc();

        System.out.println("强引用：" + o1);
    }
}