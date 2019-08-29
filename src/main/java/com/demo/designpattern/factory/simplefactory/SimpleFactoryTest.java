package com.demo.designpattern.factory.simplefactory;

/**
 * @author Shanks
 * @date 2019-03-18
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        AbstractDrink coffee = SimpleFactory.createDrink(1);
        assert coffee != null;
        coffee.desc();

        AbstractDrink milk = SimpleFactory.createDrink(2);
        assert milk != null;
        milk.desc();

        AbstractDrink tea = SimpleFactory.createDrink(3);
        assert tea != null;
        tea.desc();
    }
}
