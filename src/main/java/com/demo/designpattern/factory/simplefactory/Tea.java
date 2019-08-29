package com.demo.designpattern.factory.simplefactory;

/**
 * @author Shanks
 * @date 2019-03-18
 */
public class Tea extends AbstractDrink {

    @Override
    void desc() {
        System.out.println("简单工厂 - 茶");
    }
}
