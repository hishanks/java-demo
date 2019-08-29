package com.demo.java.datatype;

import org.junit.jupiter.api.Test;

/**
 * JDK1.5之后，Integer的操作引入了一个新的特性Integer.cache，用来节省内存和提高性能。整型对象在内部实现中通过使用相同的对象引用实现了缓存和重用。
 * 此缓存范围为[-128, 127]
 * 这种Integer缓存策略仅在自动装箱（autoboxing）的时候有用，使用构造器创建的Integer对象不能被缓存（new的总是在堆中重新分配内存）。
 * Java编译器把原始类型自动转换为封装类的过程称为自动装箱（autoboxing），这相当于调用valueOf方法：
 * Integer a = 10; // this is autoboxing
 * Integer b = Integer.valueOf(10); // under the hood
 *
 * @author Shanks
 * @date 2019-06-14
 */
public class IntegerTest {

    @Test
    void testIntegerCache() {
        Integer i = 10;
        Integer j = new Integer(10);
        Integer k = Integer.valueOf(10);

        // false
        System.out.println(i == j);
        // true
        System.out.println(i == k);
        // false
        System.out.println(j == k);

        Integer a = 129;
        Integer b = new Integer(129);
        Integer c = Integer.valueOf(129);
        // false
        System.out.println(a == b);
        // false
        System.out.println(a == c);
        // false
        System.out.println(b == c);
    }

    @Test
    void test() {
        // 这种方式，会经过Integer temp = new Integer(10)自动拆箱为int a = temp;还是调用了valueOf(10)自动装箱;实际上后者，这涉及到自动装箱机制
        Integer a = 10;
        Integer b = 10;
        if (a == b) {
            // 相等的
            System.out.println("a == b");
        } else {
            System.out.println("a != b");
        }

        System.out.println("....................");

        // new方式初始化的话，一定不同，因为每new一个，都是在堆中分配新的内存地址
        Integer c = new Integer(10);
        Integer d = new Integer(10);
        if (c == d) {
            System.out.println("c == d");
            System.out.println(a);
            System.out.println(b);
        } else {
            // 所以这两个变量不相等
            System.out.println("c != d");
        }
    }
}
