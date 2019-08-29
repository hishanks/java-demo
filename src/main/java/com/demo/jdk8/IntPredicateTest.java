package com.demo.jdk8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

/**
 * @author zhoukai
 * @date 2018/8/7
 */
class IntPredicateTest {

    @Test
    void testIntPredicate() {

        IntPredicate evenNumbers = i -> i % 2 == 0;
        boolean b = evenNumbers.test(100);
        System.out.println(b);

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        list.stream().filter(evenNumbers::test).forEach(System.out::println);
    }
}
