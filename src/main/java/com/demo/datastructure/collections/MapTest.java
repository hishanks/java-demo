package com.demo.datastructure.collections;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Shanks
 * @date 2019-01-18
 */
public class MapTest {

    @Test
    void testMerge() {

        String str = "a,b,c,d,a,b,c";
        Stream<String> words = Arrays.stream(str.split(","));
        Map<String, Integer> map = new HashMap<>(16);
        // words.forEach(word -> map.merge(word.toLowerCase(), 1, (count, incr) -> count + incr));
        words.forEach(word -> map.merge(word.toLowerCase(), 1, Integer::sum));
        System.out.println(JSON.toJSONString(map));

        System.out.println("..................................................");

        Stream<String> stream = Arrays.stream(str.split(","));
        Map<String, Long> collect = stream.collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
        System.out.println(JSON.toJSONString(collect));
    }
}