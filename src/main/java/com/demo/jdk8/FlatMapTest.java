package com.demo.jdk8;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 多个Stream转为一个Stream，流的扁平化
 *
 * @author zhoukai
 * @date 2018/6/23
 */
public class FlatMapTest {

    @Test
    void flatMapTest() {

        String[] arrayOfWords = {"Hello", "World"};
        System.out.println("-----> 方式一：");
        List<String> collect = Arrays.stream(arrayOfWords)
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        // [H, e, l, o, W, r, d]
        System.out.println(collect);

        System.out.println("-----> 方式二");
        List<String> strings = Arrays.stream(arrayOfWords)
                .flatMap(s -> Arrays.stream(s.split("")))
                .distinct()
                .peek(System.out::println)
                .collect(Collectors.toList());
        // [H, e, l, o, W, r, d]
        System.out.println(strings);

        // 获取单词，并且去重
        List<String> list = Arrays.asList("a,b", "a,c", "b,c", "a,b,c");

        // map和flatMap的区别
        System.out.println("----->stream().map");
        /*
         * java.util.stream.ReferencePipeline$Head@79fc0f2f
         * java.util.stream.ReferencePipeline$Head@50040f0c
         * java.util.stream.ReferencePipeline$Head@2dda6444
         * java.util.stream.ReferencePipeline$Head@5e9f23b4
         * */
        list.stream().
                map(item -> Arrays.stream(item.split(",")))
                .distinct()
                .peek(System.out::println)
                .collect(toList())
                .forEach(System.out::println);

        System.out.println("----->stream().flatMap");
        /*
         * a
         * b
         * c
         */
        list.stream()
                .flatMap(item -> Arrays.stream(item.split(",")))
                .distinct()
                .peek(System.out::println)
                .collect(toList())
                .forEach(System.out::println);


        System.out.println("-----> 先map再flatMap");
        // 也可以这样
        list.stream()
                .map(item -> item.split(","))
                .flatMap(Arrays::stream)
                .distinct()
                .peek(System.out::println)
                .collect(toList())
                .forEach(System.out::println);
    }

    /**
     * 给定两个数字列表，如何返回所有的数对呢？
     * 例如，给定列表[1, 2, 3]和列表[3, 4]，应该返回[[1, 3], [1, 4], [2, 3], [2, 4], [3, 3], [3, 4]]
     * 为简单起见，你可以用有两个元素的数组来代表数对
     */
    @Test
    void testCombine() {

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .peek(ints -> {
                    // 下面一行可以打断点
                    System.out.println(ints[0] + "-->" + ints[1]);
                })
                .collect(Collectors.toList());
        System.out.println("↑↑↑ peek ↑↑↑");
        /*
         * 1-->3
         * 1-->4
         * 2-->3
         * 2-->4
         * 3-->3
         * 3-->4
         */
        pairs.forEach(ints -> System.out.println(ints[0] + "-->" + ints[1]));
        pairs.forEach(ints -> System.out.println(Arrays.toString(ints)));

        // 相互组合
        List<String> listOne = Arrays.asList("你", "我", "他");
        List<String> listTwo = Arrays.asList("a", "b");

        System.out.println("=====>map");
        // map不好使
        listOne.stream()
                .map(item -> listTwo.stream().map(item2 -> item + " --> " + item2))
                .collect(toList())
                .forEach(System.out::println);

        // flatMap
        System.out.println("=====>flatMap");
        listOne.stream()
                .flatMap(item -> listTwo.stream().map(item2 -> item + " --> " + item2))
                .collect(toList())
                .forEach(System.out::println);
    }

    /**
     * 扁平化，多个流转换为一个流
     */
    @Test
    void testFlatMap() {
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(Collection::stream)
                .collect(toList());
        // [1,2,3,4]
        System.out.println(JSON.toJSONString(together));

        System.out.println("----------> JSON.toJSONString");
        String jsonString = JSON.toJSONString(together);
        System.out.println(jsonString);
        List<Integer> integers = JSON.parseArray(jsonString, Integer.class);
        System.out.println("----------> JSON.parseArray");
        integers.forEach(System.out::println);
    }

    @Test
    void test() {
        Map<String, Object> map = new HashMap<>(8);
        map.put("one", 1);
        map.put("two", 2);
        Map<String, Object> map1 = new HashMap<>(8);
        map1.put("three", 3);
        map1.put("four", 4);
        List<Map<String, Object>> listOne = new ArrayList<>(Arrays.asList(map, map1));
        System.out.println("----------> listOne:");
        System.out.println(JSON.toJSONString(listOne));

        Map<String, Object> objectMap = Maps.newHashMap();
        objectMap.put("name", "shanks");
        objectMap.put("age", 20);
        Map<String, Object> objectMap1 = Maps.newHashMap();
        objectMap1.put("sex", "man");
        objectMap1.put("address", "SH");
        List<Map<String, Object>> listTwo = Lists.newArrayList();
        listTwo.add(objectMap);
        listTwo.add(objectMap1);
        System.out.println("----------> listTwo:");
        System.out.println(JSON.toJSONString(listTwo));

        List<Map<String, Object>> collect = Stream.of(listOne, listTwo)
                .flatMap(Collection::stream)
                .collect(toList());
        System.out.println("----------> collect:");
        System.out.println(JSON.toJSONString(collect));
    }
}
