package com.demo.jvm.classloader;

/**
 * @author zhoukai
 * @date 2018/8/7
 */
public class Sub extends Super {

    public static String subStaticVariable = "Sub Static Variable";

    static {
        System.out.println("Sub Class Static Code Block");
    }

    {
        System.out.println("Sub Class Non Static Code Block");
    }

    public Sub() {
        System.out.println("Sub Constructor");
    }
}
