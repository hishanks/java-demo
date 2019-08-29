package com.demo.jdk8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author zhoukai
 * @date 2018/7/3
 */
class FilterTest {

    @Test
    void testFilter() {
        List<String> list = new ArrayList<>(Arrays.asList("abc", "de"));

        List<String> collect = list.stream().filter(p -> {
            return p.length() >= 3;
        }).collect(toList());
        System.out.println(collect);

        List<String> filter = list.stream()
                .filter(p -> p.length() >= 2)
                .collect(toList());
        System.out.println(filter);
    }
}
