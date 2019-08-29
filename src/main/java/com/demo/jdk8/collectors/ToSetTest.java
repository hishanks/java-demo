package com.demo.jdk8.collectors;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhoukai
 * @date 2018/9/4
 */
public class ToSetTest {

    @Test
    void testToSet() {
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        Set<Integer> set = list.stream().collect(Collectors.toSet());
        System.out.println(set);

    }
}
