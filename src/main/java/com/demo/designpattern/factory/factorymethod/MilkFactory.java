package com.demo.designpattern.factory.factorymethod;

/**
 * @author Shanks
 * @date 2019-03-18
 */
public class MilkFactory extends BaseFactory {
    
    @Override
    BaseDrink createDrink() {
        return new Milk();
    }
}
