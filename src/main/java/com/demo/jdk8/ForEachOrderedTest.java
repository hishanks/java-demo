package com.demo.jdk8;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 使用并行流时，forEach方法不能保证元素是按顺序处理的
 * 如果需要保证按顺序处理，应该使用forEachOrdered方法
 *
 * @author zhoukai
 * @date 2018-10-16
 */
public class ForEachOrderedTest {

    @Test
    void testSameOrder() {

        List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 4, 1));
        // List是有序的，所以打印输出时有序
        list.forEach(System.out::println);

        System.out.println("----------");

        List<Integer> sameOrder = list.stream().collect(Collectors.toList());
        sameOrder.forEach(System.out::println);
    }

    @Test
    void testSetToList() {

        Set<Integer> set = new HashSet<>(Arrays.asList(2, 3, 4, 1));
        set.forEach(System.out::println);
        System.out.println("----------");

        List<Integer> list = set.stream().collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("----------");

        List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());
        sortedList.forEach(System.out::println);
        System.out.println("----------");

        set.stream().forEachOrdered(System.out::println);
    }
}
