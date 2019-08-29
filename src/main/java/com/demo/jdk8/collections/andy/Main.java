package com.demo.jdk8.collections.andy;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shanks
 * @date 2019-07-09
 */
public class Main {

    public static void main(String[] args) {
        /*
         * Problem(code=A, name=12)
         * Problem(code=A, name=13)
         * Problem(code=B, name=16)
         * Problem(code=B, name=14)
         * Problem(code=C, name=18)
         * ...
         * Problem(code=A, name=12,13)
         * Problem(code=B, name=16,14)
         * Problem(code=C, name=18)
         */
        Problem p1 = Problem.builder().code("A").name("12").build();
        Problem p2 = Problem.builder().code("A").name("13").build();
        Problem p3 = Problem.builder().code("B").name("16").build();
        Problem p4 = Problem.builder().code("B").name("14").build();
        Problem p5 = Problem.builder().code("C").name("18").build();
        List<Problem> problems = Lists.newArrayList(p1, p2, p3, p4, p5);
        problems.forEach(System.out::println);

        System.out.println("..................................................");
        System.out.println("..................................................");

        /*
         * 这里的思路是List先根据code作为key收集为Map，然后对value进行join操作
         * 最后再对Map进行遍历，生成一个个新的Problem对象用最后add到新的List中。
         */
        List<Problem> result = new ArrayList<>();
        problems.stream().collect(
                Collectors.groupingBy(
                        Problem::getCode,
                        HashMap::new,
                        Collectors.mapping(Problem::getName, Collectors.joining(","))
                )
        ).forEach((k, v) -> result.add(Problem.builder().code(k).name(v).build()));

        result.forEach(System.out::println);
    }
}