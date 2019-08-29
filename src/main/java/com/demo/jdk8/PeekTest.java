package com.demo.jdk8;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhoukai
 * @date 2018/8/10
 */
public class PeekTest {

    @Test
    void testPeek() {

        String str = "a#b#c#d#e";
        System.out.println("方式一：");
        List<String> list = Lists.newArrayList();
        Arrays.stream(str.split("#"))
                .peek(System.out::println)
                .forEach(list::add);
        System.out.println(list);

        // 个人看来，第二种比较美观，代码更精炼！
        System.out.println("=====>方式二：");
        List<String> collect = Arrays.stream(str.split("#"))
                .peek(System.out::println)
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
