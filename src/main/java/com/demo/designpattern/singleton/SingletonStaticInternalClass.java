package com.demo.designpattern.singleton;

/**
 * 静态内部类实现单例模式
 * 解析：定义一个私有的内部类，在第一次用这个嵌套类时，会创建一个实例
 * 而类型为SingletonHolder的类，只有在Singleton.getInstance()中调用
 * 由于私有的属性，他人无法使用SingleHolder，不会调用Singleton.getInstance()，自然就不会创建实例
 * 优点：达到了lazy loading的效果，即按需创建实例
 * ----------
 * JVM在类的初始化阶段（即在Class被加载后，且被线程使用之前），会执行类的初始化。
 * 在执行类的初始化期间，JVM会去获取一个锁。这个锁可以同步多个线程对同一个类的初始化。
 * 基于这个特性，可以实现另一个线程安全的延迟初始化方案（这个方案称之为：Initialization on Demand Holder idiom）
 *
 * @author Shanks
 * @date 2018-07-19
 */
public class SingletonStaticInternalClass {

    private SingletonStaticInternalClass() {
    }

    private static class Holder {
        private static final SingletonStaticInternalClass INSTANCE = new SingletonStaticInternalClass();
    }

    public static SingletonStaticInternalClass getInstance() {
        // 这里将导致Holder类被初始化
        return Holder.INSTANCE;
    }
}