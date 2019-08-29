package com.demo.designpattern.singleton;

/**
 * 从Java 1.5版本起，单元素枚举实现单例模式成为最佳的方法《Effective Java》
 *
 * @author zhoukai
 * @date 2018/7/20
 */
public enum SingletonEnum {
    /**
     * 枚举类单元素
     * SingletonEnum.INSTANCE.getInstance()即可获取实例
     */
    INSTANCE;

    private Resource instance;

    /**
     * 默认private
     */
    SingletonEnum() {
        instance = new Resource();
    }

    public Resource getInstance() {
        return instance;
    }
}

