package com.demo.java.generic.stove;

/**
 * @author Shanks
 * @date 2019-04-22
 */
public class StoveGeneric {

    public static <T> T heat(T food) {
        System.out.println(food + " is done");
        return food;
    }

    public static void main(String[] args) {
        Meat meat = new Meat();
        meat = StoveGeneric.heat(meat);

        Soup soup = new Soup();
        soup = StoveGeneric.heat(soup);
    }
}


