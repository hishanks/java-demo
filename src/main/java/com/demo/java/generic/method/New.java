package com.demo.java.generic.method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 泛型不仅可以应用于整个类上，还可以在类中包含参数化的方法。
 * 而这个类所在的类可以是泛型类，也可以是非泛型类。这就意味着，是否拥有泛型方法，与所在的类是否是泛型没有关系。
 * <p>
 * 泛型方法可以让该方法独立于整个类而变化。这就意味着，只要能做到，那么就应当使用泛型方法。
 * 也就是说，如果泛型方法可以取代整个类泛型化，那么就应该只使用泛型方法，因为它可以使事情更加地清楚明白。
 * 另外，对于一个static的方法而言，无法访问泛型类的类型参数，所以static要想使用泛型，则必须声明为泛型方法。
 * <p>
 * 当使用泛型类的时，必须在创建对象的时候指定类型参数的值，而使用泛型方法的时候，通常不必指明参数类型，因为编译器会为我们找出具体的类型。
 * 这称为类型参数推断。
 *
 * @author Shanks
 * @date 2019-06-20
 */
public class New {

    public static <K, V> Map<K, V> mapOf() {
        return new HashMap<>();
    }

    public static <T> List<T> listOf() {
        return new ArrayList<>();
    }

    public static <T> LinkedList<T> linkListOf() {
        return new LinkedList<>();
    }

    public static <T> Set<T> setOf() {
        return new HashSet<>();
    }

    public static <T> Queue<T> queueOf() {
        return new LinkedList<>();
    }

    public static void main(String[] args) {
        Map<String, List<String>> map = New.mapOf();
        List<String> list = New.listOf();
        LinkedList<String> linkedList = New.linkListOf();
        Set<String> set = New.setOf();
        Queue<String> queue = New.queueOf();

        List<Integer> listOf = New.listOf();
        listOf.add(1);
        listOf.add(2);
        System.out.println(listOf);
    }
}
