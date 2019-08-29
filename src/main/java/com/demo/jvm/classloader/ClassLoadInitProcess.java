package com.demo.jvm.classloader;

/**
 * 1.父类static代码块
 * 2.子类static代码块
 * <p>
 * 3.父类非static代码块
 * 4.父类构造器
 * <p>
 * 5.子类非static代码块
 * 6.子类构造器
 *
 * @author zhoukai
 * @date 2018/8/7
 */
public class ClassLoadInitProcess {

    public static void main(String[] args) {

        new Sub();
    }
}
