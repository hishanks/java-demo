package com.demo.jdk8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * 函数式接口：Function
 * 抽象方法：R apply(T t);
 * 简单理解就是：有参数T，然后返回值为R
 *
 * @author zhoukai
 * @date 2018/6/28
 */
class FunctionTest {

    @Test
    void testFunction() {
        // 传入Integer参数，返回String结果
        // R apply(T t);
        Function<Integer, String> function = x -> x + "str";
        String apply = function.apply(0);
        System.out.println(apply);

        Function<Long, String> long2Str = String::valueOf;
        String s = long2Str.apply(1L);
        System.out.println(s);

        List<Long> list = Arrays.asList(1L, 2L, 3L);
        List<String> strings = list.stream().map(String::valueOf).collect(toList());
        System.out.println(strings.toString());
        List<String> collect = list.stream().map(long2Str).collect(toList());
        System.out.println(collect.toString());
    }
}
