package com.demo.java.datatype;

/**
 * 关于String，这一篇就够了，知道原理后，百变不离其宗，根本不用去死记硬背，顺着原理理解一切都很明白
 * String s = "abc";与new String("abc");与String Pool的关系，以及分别发生了什么
 *
 * @author Shanks
 * @date 2019-06-13
 */
public class StringTest {

    public static void main(String[] args) {
        /*
         * 最后打印结果如下：
         * 语句(4): false
         * 语句(5): false
         * 语句(6): false
         * 语句(7): false
         * 语句(8): true
         * 语句(9): true
         * 语句(13): true
         * 语句(14): false
         */

        // 语句(1)
        String str = new String("abc");
        // 语句(2)
        String str1 = "abc";
        // 语句(3)
        String str2 = new String("abc");

        // 语句(4)
        System.out.println("语句(4): " + (str == str1));
        // 语句(5)
        System.out.println("语句(5): " + (str == str2));
        // 语句(6)
        System.out.println("语句(6): " + (str1 == str2));

        // 语句(7)
        System.out.println("语句(7): " + (str == str.intern()));
        // 语句(8)
        System.out.println("语句(8): " + (str1 == str1.intern()));
        // 语句(9)
        System.out.println("语句(9): " + (str.intern() == str2.intern()));

        // 语句(10)
        String hello = "hello";
        // 语句(11)
        String hel = "hel";
        // 语句(12)
        String lo = "lo";

        // 语句(13)
        System.out.println("语句(13): " + (hello == "hel" + "lo"));
        // 语句(14)
        System.out.println("语句(14): " + (hello == "hel" + lo));
    }
}