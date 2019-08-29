package com.demo.java.base;

/**
 * @author Shanks
 * @date 2019-06-09
 */
public class Main {

    public static void main(String[] args) {
        Son son = new Son();
        /*
         * 父类---静态代码块
         * 子类---静态代码块
         * 父类---非静态代码块
         * 父类---构造方法
         * 子类---非静态代码块
         * 子类---构造方法
         */
        System.out.println(son);

        System.out.println("....................");

        Father upObject = new Son();
        /*
         * 上转型对象的情况下，则不执行父类和子类的静态代码块
         * 父类---非静态代码块
         * 父类---构造方法
         * 子类---非静态代码块
         * 子类---构造方法
         */
        System.out.println(upObject);
    }
}