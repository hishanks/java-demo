package com.demo.designpattern.factory.factorymethod;

/**
 * @author Shanks
 * @date 2019-03-18
 */
public class CoffeeFactory extends BaseFactory {

    @Override
    BaseDrink createDrink() {
        return new Coffee();
    }
}
