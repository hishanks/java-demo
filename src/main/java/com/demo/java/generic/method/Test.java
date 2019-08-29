package com.demo.java.generic.method;

/**
 * @author Shanks
 * @date 2019-06-20
 */
public class Test {

    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(1, "apple");
        Pair<Integer, String> p2 = new Pair<>(2, "pear");
        boolean same = Util.compare(p1, p2);
        System.out.println(same);
    }
}