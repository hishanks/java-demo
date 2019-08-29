package com.demo.designpattern.singleton;

/**
 * 饿汉式
 * 解析：初试化静态的instance创建一次
 * 缺点：没有lazy loading的效果
 * 如果我们在Singleton类里面写一个静态的方法不需要创建实例，它仍然会早早的创建一次实例，而降低内存的使用率
 *
 * @author zhoukai
 * @date 2018/7/19
 */
public class SingletonHungryMan {

    private static SingletonHungryMan instance = new SingletonHungryMan();

    private SingletonHungryMan() {
    }

    public static SingletonHungryMan getInstance() {
        return instance;
    }
}
