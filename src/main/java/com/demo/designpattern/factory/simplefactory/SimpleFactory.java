package com.demo.designpattern.factory.simplefactory;

/**
 * 简单工厂方法
 * 属于创建型模式，又叫做静态工厂方法模式，不属于23种GOF设计模式之一。
 * 是由一个工厂对象决定创建出哪一种产品类的实例。实质是由一个工厂类根据传入的参数，动态决定应该创建哪一个产品类（这些产品类继承自一个父类或接口）的实例。
 * <p>
 * 负责实现创建所有实例的内部逻辑，并提供一个外界调用的方法，创建所需的产品对象
 * 简单描述：一个抽象产品类，可以派生出多个具体产品类。一个具体工厂类，通过往此工厂的static方法中传入不同参数，产出不同的具体产品类实例。
 * 优点：将创建使用工作分开，不必关心类对象如何创建，实现了解耦；
 * 缺点：违背“开放 - 关闭原则”，一旦添加新产品就不得不修改工厂类的逻辑，这样就会造成工厂逻辑过于复杂。
 *
 * @author Shanks
 * @date 2019-03-18
 */
public class SimpleFactory {

    private static final int COFFEE = 1;
    private static final int MILK = 2;
    private static final int TEA = 3;

    /**
     * 静态方法，由简单工厂类直接调用，接收相应产品参数，内部判断，生成对应的产品
     *
     * @param type type
     * @return AbstractDrink
     */
    public static AbstractDrink createDrink(int type) {
        switch (type) {
            case COFFEE:
                return new Coffee();
            case MILK:
                return new Milk();
            case TEA:
                return new Tea();
            default:
                return null;
        }
    }
}
