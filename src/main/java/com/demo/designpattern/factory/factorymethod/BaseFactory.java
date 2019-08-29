package com.demo.designpattern.factory.factorymethod;

/**
 * 一个抽象产品类，可以派生出多个具体产品类。
 * 一个抽象工厂类，可以派生出多个具体工厂类。
 * 每个具体工厂类只能创建一个具体产品类的实例。
 * 优点：
 * (1) 符合开-闭原则：新增一种产品时，只需要增加相应的具体产品类和相应的工厂子类即可
 * (2) 符合单一职责原则：每个具体工厂类只负责创建对应的产品
 * 缺点：
 * (1) 增加了系统的复杂度：类的个数将成对增加
 * (2) 增加了系统的抽象性和理解难度
 * (3) 一个具体工厂只能创建一种具体产品
 *
 * @author Shanks
 * @date 2019-03-18
 */
public abstract class BaseFactory {

    abstract BaseDrink createDrink();
}
