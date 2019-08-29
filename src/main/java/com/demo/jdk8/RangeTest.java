package com.demo.jdk8;

import org.junit.jupiter.api.Test;

/**
 * 对于Lambda表达式外部的变量，其访问权限的粒度与匿名对象的方式非常类似
 * 你能够访问局部对应的外部区域的局部final变量，以及成员变量和静态变量
 * Variable used in lambda expression should be final or effectively final
 * 释义为：lambda表达式中使用的变量应该是final或者有效的final
 *
 * @author zhoukai
 * @date 2018/8/7
 */
public class RangeTest {

    /**
     * 访问局部变量
     */
    @Test
    void testRange() {

        // 我们可以访问lambda表达式外部的final局部变量：
        final int num = 10;
        Converter<Integer, String> int2Str = t -> String.valueOf(t + num);
        String s = int2Str.convert(20);
        // 30
        System.out.println(s);

        // 但是与匿名对象不同的是，变量num并不需要一定是final。下面的代码依然是合法的：
        int age = 50;
        Converter<Integer, String> sum = t -> String.valueOf(t + age);
        String s1 = sum.convert(30);
        System.out.println(s1);

        // 然而，num在编译的时候被隐式地当做final变量来处理。下面的代码就不合法：
        // 在lambda表达式内部企图改变num的值也是不允许的！！
        int money = 100;
        Converter<Integer, String> moneyCount = t -> String.valueOf(t + money);
        String convert = moneyCount.convert(200);
        // 编写下面的money = 500时，moneyCount那行的Lambda内部是用的变量money就会报错：Variable used in lambda expression should be final or effectively final
        // money = 500;
    }

    /**
     * 访问成员变量和静态变量
     */

    /**
     * 访问默认接口方法
     * 默认方法无法在lambda表达式内部被访问！！
     */

}
