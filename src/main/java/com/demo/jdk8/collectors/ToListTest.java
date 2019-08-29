package com.demo.jdk8.collectors;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shanks
 * @date 2018-09-04
 */
public class ToListTest {

    @Test
    void testToList() {

        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Integer> collect = list.stream().filter(p -> p >= 2).collect(Collectors.toList());
        System.out.println(collect);

        List<Object> objectList = Lists.newArrayList();
        collect.stream().peek(System.out::println).forEach(objectList::add);
        System.out.println(objectList);
    }
}