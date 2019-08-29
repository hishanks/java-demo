package com.demo.java.generic.stove;

/**
 * @author Shanks
 * @date 2019-04-22
 */
public class Stove {

    public static Object heat(Object food) {
        System.out.println(food + " is done");
        return food;
    }

    public static void main(String[] args) {
        Meat meat = new Meat();
        meat = (Meat) Stove.heat(meat);

        Soup soup = new Soup();
        soup = (Soup) Stove.heat(soup);
    }
}

