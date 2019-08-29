package com.demo.designpattern.factory.factorymethod;

/**
 * 一个抽象产品类，可以派生出多个具体产品类。
 * 一个抽象工厂类，可以派生出多个具体工厂类。
 * 每个具体工厂类只能创建一个具体产品类的实例。
 *
 * @author Shanks
 * @date 2019-03-18
 */
public abstract class BaseDrink {

    /**
     * 描述
     */
    abstract void desc();
}
