package com.demo.java.base;

/**
 * @author Shanks
 * @date 2019-06-09
 */
public class Son extends Father {

    {
        System.out.println("子类---非静态代码块");
    }

    static {
        System.out.println("子类---静态代码块");
    }

    public Son() {
        System.out.println("子类---构造方法");
    }
}
