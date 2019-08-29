package com.demo.jdk8.interfaces.predicate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * boolean test(T t);
 *
 * @author Shanks
 * @date 2018-10-24
 */
public class PredicateTest {

    @Test
    void testPredicateInterface() {

        PredicateInterface<Integer> action = p -> p.equals(0);
        System.out.println(action.test(10));
    }

    @Test
    void testPredicate() {

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        Predicate<Integer> predicate = p -> p >= 2;
        List<Integer> collect = list.stream().filter(predicate).collect(Collectors.toList());
        collect.forEach(System.out::print);
        List<Integer> integers = list.stream().filter(x -> x <= 3).collect(Collectors.toList());
        integers.forEach(System.out::print);
    }
}
