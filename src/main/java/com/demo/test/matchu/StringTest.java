package com.demo.test.matchu;

import org.junit.jupiter.api.Test;

/**
 * 关于+，只有"a"+"b"这一种形式，才等价于"ab"，其他任何形式的+都是不行的！！
 *
 * @author Shanks
 * @date 2019-08-29
 */
public class StringTest {

    /**
     * 反编译之后的代码：
     * String s1 = "Programming";
     * String s2 = new String("Programming");
     * String s3 = "Program";
     * String s4 = "ming";
     * String s5 = "Programming";
     * String s6 = s3 + s4;
     * System.out.println(s1 == s2);
     * System.out.println(s1 == s5);
     * System.out.println(s1 == s6);
     * System.out.println(s1 == s6.intern());
     * System.out.println(s2 == s2.intern());
     */
    @Test
    void common() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        // false
        System.out.println(s1 == s2);
        // true
        System.out.println(s1 == s5);
        // false
        System.out.println(s1 == s6);
        // true
        System.out.println(s1 == s6.intern());
        // false
        System.out.println(s2 == s2.intern());
    }

    /**
     * 反编译之后的代码：
     * String str1 = "HelloWorld";
     * String str2 = "Hello";
     * String str3 = "World";
     * String str4 = str2 + str3;
     * String str5 = "HelloWorld";
     * String str6 = "Hello" + new String("World");
     * String str7 = str2.intern() + str3.intern();
     * System.out.println(str1 == str4);
     * System.out.println(str1 == str5);
     * System.out.println(str1 == str6);
     * System.out.println(str1 == str7);
     */
    @Test
    void plus() {
        String str1 = "HelloWorld";
        String str2 = "Hello";
        String str3 = "World";
        String str4 = str2 + str3;
        // 等价于String str5 = "HelloWorld";
        String str5 = "Hello" + "World";
        String str6 = "Hello" + new String("World");
        String str7 = str2.intern() + str3.intern();
        // false
        System.out.println(str1 == str4);
        // true
        System.out.println(str1 == str5);
        // false
        System.out.println(str1 == str6);
        // false
        System.out.println(str1 == str7);
    }
}