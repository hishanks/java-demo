package com.demo.jdk8;

import com.google.common.collect.Lists;
import com.demo.jdk8.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * obj.stream().map();
 *
 * @author zhoukai
 * @date 2018/6/24
 */
class MapTest {

    @Test
    void testMap() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        /*
         * 112.0
         * 224.0
         * 336.0
         * 448.0
         * 560.0
         * map相当于一个映射，只是一个"取"的过程，不会对源数据进行修改
         * */
        costBeforeTax.stream().map(cost -> cost + 0.12 * cost).forEach(System.out::println);
        // [100, 200, 300, 400, 500]（List还是原来的List，因为流操作之后没有collect）
        System.out.println(costBeforeTax.toString());

        System.out.println("----- 我是分割线 -----");

        List<Object> costAfterTax = new ArrayList<>();
        // 遍历集合对其进行操作，自然会影响到源数据
        costBeforeTax.forEach(cost -> costAfterTax.add(cost + cost * 0.12));
        costAfterTax.forEach(System.out::println);
    }

    @Test
    void testMapToXxx() {
        List<User> list = Lists.newArrayList();
        list.add(new User("abc", 10));
        list.add(new User("xyz", 20));

        list.stream().map(User::getValue).forEach(System.out::println);
        list.stream().mapToInt(User::getValue).forEach(System.out::println);
        list.stream().mapToLong(User::getValue).forEach(System.out::println);
        list.stream().mapToDouble(User::getValue).forEach(System.out::println);

        System.out.println("------ 我是分割线 ------");

        System.out.println("=====>stream().mapToInt().sum()");
        System.out.println(list.stream().mapToInt(User::getValue).sum());
        System.out.println("=====>stream().mapToInt().count()");
        System.out.println(list.stream().mapToInt(User::getValue).count());
        System.out.println("=====>stream().mapToInt().toArray()");
        System.out.println(Arrays.toString(list.stream().mapToInt(User::getValue).toArray()));
        System.out.println("=====>stream().mapToInt().average()");
        System.out.println(list.stream().mapToInt(User::getValue).average().orElse(0));
        System.out.println("=====>stream().mapToInt().boxed()");
        final List<Integer> collect = list.stream()
                .mapToInt(User::getValue)
                .boxed()
                .collect(toList());
        /*
         * java.lang.Integer
         * java.lang.Integer
         * */
        collect.stream().map(p -> p.getClass().getName()).forEach(System.out::println);

        final List<Double> doubles = list.stream()
                .mapToDouble(User::getValue)
                .boxed()
                .collect(toList());
        /*
         * java.lang.Double
         * java.lang.Double
         * */
        doubles.stream().map(p -> p.getClass().getName()).forEach(System.out::println);

    }
}
