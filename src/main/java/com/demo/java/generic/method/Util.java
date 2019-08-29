package com.demo.java.generic.method;

/**
 * @author Shanks
 * @date 2019-06-20
 */
public class Util {

    /**
     * 这就是个泛型方法
     *
     * @param p1  p1
     * @param p2  p2
     * @param <K> K
     * @param <V> V
     * @return boolean
     */
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }
}