package com.demo.java.base;

/**
 * @author Shanks
 * @date 2019-06-09
 */
public class Father {

    public Father() {
        System.out.println("父类---构造方法");
    }

    {
        System.out.println("父类---非静态代码块");
    }

    static {
        System.out.println("父类---静态代码块");
    }
}