package com.demo.interview.string;

import org.junit.jupiter.api.Test;

/**
 * @author Shanks
 * @date 2019-05-10
 */
public class Main {

    @Test
    void test() {

        String x = "admin";
        String y = "admin";
        // true，这两个都是来自String常量池
        System.out.println(x == y);

        System.out.println("--------------------------------------------------");

        String s = new String("sd");
        String b = new String("sd");
        // false，显然，因为使用了new，变量s和变量b在堆内存中的引用地址是不同的
        System.out.println(s == b);

        System.out.println("--------------------------------------------------");

        String s1 = "a";
        String s2 = new String("a");
        boolean doubleEqual = s1 == s2;
        // false，变量s1在常量池，变量s2在堆内存中，所以引用地址不同
        System.out.println(doubleEqual);

        System.out.println("--------------------------------------------------");

        String s3 = "b";
        String s4 = new String("b");
        boolean intern = s3 == s4.intern();
        /*
         * true，s3在常量池中，s4在堆内存
         * 但是s4.intern()操作时，此时拿到s4的值为"b"，发现在常量池中，已经有值"b"，所以直接把s3对应的"b"的地址返回
         * 所以s3 == s4.intern()的实质，是s3在和自己比较，自然是相等
         */
        System.out.println(intern);

        System.out.println("--------------------------------------------------");

        // 道理同上吧
        String s5 = "c";
        String s6 = new String("c");
        String s7 = s6.intern();
        boolean flag = s5 == s7;
        // true
        System.out.println(flag);
    }

    @Test
    void testSplice() {

        String temp = "a";
        final String tempFinal = "a";
        final String tempFinalNew = new String("a");
        String x = "ab";
        String y = temp + "b";
        String z = tempFinal + "b";
        String tempFinalNewStr = tempFinalNew + "b";
        // false
        System.out.println(x == y);
        // true，这里需要注意的是，tempFinal被final修饰，是个字符串常量，String z = tempFinal + "b";等同于String z = "ab";
        System.out.println(x == z);
        // false
        System.out.println(y == z);
        // false
        System.out.println(x == tempFinalNewStr);

        System.out.println("--------------------------------------------------");

        String str1 = "ab";
        String str2 = "a" + "b";
        /*
         * true，这里考查的关键是：String str2 = "a" + "b";编译型，其实就等同于String str2 = "ab";
         */
        System.out.println(str1 == str2);

        System.out.println("--------------------------------------------------");

        String str3 = "xy";
        String str4 = new String("x") + "y";
        // false
        System.out.println(str3 == str4);

        System.out.println("--------------------------------------------------");

        final String str5 = "ef";
        String str6 = new String("e") + "f";
        // false
        System.out.println(str5 == str6);

        System.out.println("--------------------------------------------------");

        String str7 = "hi";
        final String str8 = new String("h") + "i";
        // false
        System.out.println(str7 == str8);
    }

    /**
     * 需要注意的是，equals方法里面一般都会有一个instanceof判断的，即先判断是否为同类型
     */
    @Test
    void testEquals() {
        System.out.println("1".equals(1));
        System.out.println(new Integer(1).equals("1"));
        System.out.println("1" + 1);
    }
}
