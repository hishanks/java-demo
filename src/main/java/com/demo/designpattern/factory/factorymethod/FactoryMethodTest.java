package com.demo.designpattern.factory.factorymethod;

/**
 * @author Shanks
 * @date 2019-03-18
 */
public class FactoryMethodTest {

    public static void main(String[] args) {

        CoffeeFactory coffeeFactory = new CoffeeFactory();
        coffeeFactory.createDrink().desc();

        MilkFactory milkFactory = new MilkFactory();
        milkFactory.createDrink().desc();

        TeaFactory teaFactory = new TeaFactory();
        teaFactory.createDrink().desc();
    }
}
