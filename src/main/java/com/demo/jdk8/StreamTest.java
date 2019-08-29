package com.demo.jdk8;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.apache.commons.collections4.CollectionUtils.filter;

/**
 * Stream
 *
 * @author zhoukai
 * @date 2018/6/24
 */
class StreamTest {

    @Test
    void testStream() {
        List<String> list = new ArrayList<>(Arrays.asList("Java", "Python", "Go"));
        list.stream().sorted(Comparator.comparing(String::trim).reversed()).forEach(System.out::println);
        System.out.println((long) list.size());
        System.out.println(new ArrayList<>(list));
    }

    /**
     * 判断一个操作是惰性求值还是及早求值很简单：只需看它的返回值。
     * 如果返回值是Stream，那么是惰性求值；如果返回值是另一个值或为空，那么就是及早求值。
     * 使用这些操作的理 想方式就是形成一个惰性求值的链，最后用一个及早求值的操作返回想要的结果，这正是它的合理之处。
     * 计数的示例也是这样运行的，但这只是最简单的情况：只含两步操作。
     */
    @Test
    void testFilter() {
        List<String> languages = new ArrayList<>(Arrays.asList("Java", "Python", "C", ""));

        languages.stream().filter(StringUtils::isNotEmpty).forEach(System.out::println);

        languages.stream().filter("Java"::equals).forEach(System.out::println);

        boolean flag = filter(languages, str -> str.endsWith("n"));
        System.out.println(flag);

        System.out.println("----- 分割线 -----");

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println("-----> 1.");
        list.stream().filter(p -> p.contains("a"));
        System.out.println("-----> 2.");
        // 不会打印p（像filter这样，只描述Stream，不产生新的集合的方法，叫做惰性求值方法）
        list.stream().filter(p -> {
            System.out.println(p);
            return p.contains("a");
        });
        System.out.println("-----> 3.");
        // 像count这样，最终会从Stream上产生值得方法叫做及早求值方法
        list.stream().filter(p -> {
            System.out.println(p);
            return p.contains("a");
        }).count();
    }

    /**
     * range
     * rangeClosed
     */
    @Test
    void testIntStream() {
        final int sum = IntStream.rangeClosed(1, 10)
                .reduce(Integer::sum)
                .orElse(0);
        System.out.println(sum);

        System.out.println("=====>");

        final int var = IntStream.range(1, 4).reduce(Integer::sum).orElse(0);
        System.out.println(var);

        System.out.println("=====>range then sum..");
        // 1+2+3
        final int total = IntStream.range(1, 4).sum();
        System.out.println(total);

        System.out.println("=====>rangeClosed then sum..");
        // 1+2+3+4
        final int i = IntStream.rangeClosed(1, 4).sum();
        System.out.println(i);

        System.out.println("=====>concat..");
        int[] arr = {1, 2, 3};
        System.out.println(IntStream.concat(Arrays.stream(arr), Arrays.stream(arr)).count());
        System.out.println(IntStream.concat(Arrays.stream(arr), Arrays.stream(arr)).sum());
        System.out.println(Arrays.toString(IntStream.concat(Arrays.stream(arr), Arrays.stream(arr)).toArray()));
        System.out.println(Arrays.toString(IntStream.concat(Arrays.stream(arr), Arrays.stream(arr)).distinct().toArray()));
    }

    /**
     * LongStream与DoubleStream，几乎等同于IntStream
     */
    @Test
    void testLongStream() {
    }

    @Test
    void testDoubleStream() {
    }

    @Test
    void testToList() {
        List<String> collect = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println("---------->");
        assert Arrays.asList("a", "b", "c").equals(collect);
    }
}
