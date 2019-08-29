package com.demo.commons.lang3.stringutils;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author zhoukai
 * @date 2018-09-18
 */
public class StringUtilsTest {

    @Test
    void testEquals() {

        String str = "hello";
        String anotherStr = new String("hello");
        // true
        System.out.println(StringUtils.equals(str, anotherStr));

        String a = "Hello";
        String b = "hello";
        // true
        System.out.println(StringUtils.equalsIgnoreCase(a, b));
    }

    @Test
    void testCompare() {

        // 0 相等
        System.out.println(StringUtils.compare("a", "a"));
        // -1 后者大
        System.out.println(StringUtils.compare("b", "c"));
        // 1 前者大
        System.out.println(StringUtils.compare("e", "d"));
        // 0 相等
        System.out.println(StringUtils.compare(null, null, true));
        // -1 后者大
        System.out.println(StringUtils.compare(null, "a", true));
        // 1 前者大
        System.out.println(StringUtils.compare("a", null, true));
    }

    @Test
    void testIsNotBlankAndEmpty() {

        // false
        System.out.println(StringUtils.isNotBlank(""));
        // false
        System.out.println(StringUtils.isNotEmpty(""));
        // false
        System.out.println(StringUtils.isNotBlank("  "));
        // true
        System.out.println(StringUtils.isNotEmpty("  "));
    }

    @Test
    void testReverse() {

        // 反转
        System.out.println(StringUtils.reverse("java"));
    }

    @Test
    void testJoin() {

        String[] strings = new String[]{"1", "2", "3"};
        String join = StringUtils.join(strings, ",");
        System.out.println("<== join: " + join);

        System.out.println("----------> 有null时，String join(Object[] array, String separator)");
        String[] ss = new String[]{null, "1", null};
        String s = StringUtils.join(ss, ",");
        System.out.println(s);

        System.out.println("----------> 有null时，String join(CharSequence delimiter, CharSequence... elements)");
        String collect = String.join(",", ss);
        System.out.println(collect);

        /* 最合适最正确的方式 */
        System.out.println("----------> filter..Collector<CharSequence, ?, String> joining(CharSequence delimiter)");
        String collectExcludeNull = Arrays.stream(ss)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(","));
        System.out.println(collectExcludeNull);

        System.out.println("----------> StringJoiner");
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(null);
        joiner.add("1");
        joiner.add(null);
        System.out.println(joiner.toString());

        System.out.println(joiner.add("2").add("3").toString());
    }
}
