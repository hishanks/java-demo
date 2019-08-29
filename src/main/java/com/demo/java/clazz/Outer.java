package com.demo.java.clazz;

/**
 * @author Shanks
 * @date 2019-03-28
 */
public class Outer {

    private static final String TOKEN = "001";

    private Outer() {
    }

    /**
     * 静态内部类
     * 使用static来修饰一个内部类，则这个内部类就属于外部类（此处外部类为Outer）本身，而不属于外部类的某个对象；
     * 称为静态内部类（也可称为类内部类），这样的内部类是类级别的，static关键字的作用是把类的成员变成类相关，而不是实例相关。
     * 注意：
     * 1.非静态内部类中不允许定义静态成员（final修饰时可以，疑点？）
     * 2.外部类的静态成员不可以直接使用非静态内部类
     * 3.静态内部类，不能访问外部类的实例成员，只能访问外部类的类成员
     */
    private static class InnerStatic {
        private static String tokenCopy = TOKEN;
        private static Outer outer = new Outer();
    }

    /**
     * 内部类
     */
    private class InnerNonStatic {
        // Inner classes cannot have static declarations 即内部类不能有静态声明
        // private static String tokenCopy = TOKEN;
        // private static Outer outer = new Outer();

        private static final int SIGN = 10;
    }
}
