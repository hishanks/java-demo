package com.demo.designpattern.factory.factorymethod;

/**
 * @author Shanks
 * @date 2019-03-18
 */
public class Milk extends BaseDrink {

    @Override
    void desc() {
        System.out.println("工厂方法 - 牛奶");
    }
}
