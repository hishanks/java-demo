package com.demo.guava.strings;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Shanks
 * @date 2019-04-30
 */
public class StringTest {

    /**
     * 目前看来Guava的Strings略逊于Apache的StringUtils
     */
    @Test
    void testStrings() {
        System.out.println(".......... com.google.common.base.Strings ..........");
        String empty = "";
        String blank = "  ";
        System.out.println(Strings.isNullOrEmpty(empty));
        System.out.println(Strings.isNullOrEmpty(blank));
        System.out.println(Strings.isNullOrEmpty(null));

        System.out.println(".......... org.commons.commons.lang3.StringUtils ..........");
        System.out.println(StringUtils.isNotEmpty(empty));
        System.out.println(StringUtils.isNotBlank(blank));
        System.out.println(StringUtils.isNotEmpty(null));
        System.out.println(StringUtils.isNotBlank(null));
    }

    /**
     * 匹配器
     */
    @Test
    void testCharMatcher() {
        System.out.println(".......... CharMatcher anyOf countIn ..........");
        String txt = "112358";
        int count = CharMatcher.anyOf("123").countIn(txt);
        System.out.println(count);

        CharMatcher or = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z'));
        System.out.println(or.toString());

        /* CharMatcher.digit() --> CharMatcher.inRange('0', '9') */
        String removeFrom = CharMatcher.digit().removeFrom("shanks 4396");
        System.out.println(removeFrom);
        // 匹配到数字字符后并删除
        String remove = CharMatcher.inRange('0', '6').removeFrom("admin 142857");
        System.out.println(remove);
    }

    /**
     * 连接器
     */
    @Test
    void testGuavaJoiner() {
        System.out.println(".......... Joiner on skipNulls join ..........");
        List<Integer> ids = new ArrayList<>(Arrays.asList(1, 2, 3, null, 4));
        String join = Joiner.on(",").skipNulls().join(ids);
        System.out.println(join);

        System.out.println(".................... empty || blank ..............................");
        List<String> stringList = new ArrayList<>(Arrays.asList("a", "e", "  ", "i", "", "0", null, "u"));
        String stringJoin = Joiner.on(",").skipNulls().join(stringList);
        // a,e,i,,0,u -->根据结果发现，skipNulls()只是跳过null，对empty和blank的情况没有过滤
        System.out.println(stringJoin);

        // useForNull只是做了对null进行字符替换，也还是没有处理empty和blank的情况
        String useForNull = Joiner.on(",").useForNull("default").join(stringList);
        System.out.println(useForNull);

        System.out.println(".................... JDK8 ....................");
        String collect = stringList.stream().filter(StringUtils::isNotBlank).collect(Collectors.joining(","));
        // a-e-i-0-u
        System.out.println(collect);

        System.out.println(".......... Joiner on withKeyValueSeparator join..........");
        Map<String, Object> userIdNameMap = Maps.newHashMap();
        userIdNameMap.put("1", "a");
        userIdNameMap.put("2", "b");
        userIdNameMap.put("3", "c");
        userIdNameMap.put("4", "d");
        // 意思是：K、V之间用"|"分隔，item之间用","分隔
        String s = Joiner.on(",").withKeyValueSeparator("|").join(userIdNameMap);
        System.out.println(s);
    }

    /**
     * 常规的连接操作
     */
    @Test
    void joiner() {
        System.out.println(".................... common joiner ....................");
        String[] keys = new String[]{"1", "2", "", "3", null, "4", "   ", null};
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            if (StringUtils.isNotBlank(key)) {
                sb.append(key).append(",");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb.toString());

        /* 下面对比下JDK8的操作，利用收集器做不同的收集结果 */
        System.out.println(".................... JDK8 joiner ....................");
        // 收集为集合
        List<String> collect = Stream.of(keys).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        System.out.println(collect);
        // 收集为字符串
        String joining = Stream.of(keys).filter(StringUtils::isNotBlank).collect(Collectors.joining(","));
        System.out.println(joining);

        System.out.println(".................... StringUtils and String joiner ....................");
        // 结果不是很满意
        String joinByStringUtils = StringUtils.join(keys, ",");
        System.out.println(joinByStringUtils);
        // String自带的join结果也不是很满意
        String joinByString = String.join(",", keys);
        System.out.println(joinByString);
    }

    /**
     * 拆分器
     * limit(int) -- 限制拆分出的字符串数量
     * trimResults() --移除结果字符串的前导空白和尾部空白
     * omitEmptyStrings() -- 从结果中自动忽略空字符串
     * trimResults(CharMatcher) -- 给定匹配器，移除结果字符串的前导匹配字符和尾部匹配字符
     */
    @Test
    void testSplitter() {
        // 按单个字符拆分
        System.out.println(".......... onChar ..........");
        List<String> onChar = Splitter.on(',').splitToList("a,,b,c");
        // [a, , b, c]
        System.out.println(onChar);
        System.out.println(".......... onChar omitEmptyStrings ..........");
        onChar = Splitter.on(',').omitEmptyStrings().splitToList("a,,b,c");
        // [a, b, c]
        System.out.println(onChar);

        // 按字符串拆分
        System.out.println(".......... onString ..........");
        Iterable<String> onString = Splitter.on("|").trimResults().split("a||b|c");
        // [a, , b, c]
        System.out.println(onString);
        System.out.println(".......... onString omitEmptyStrings ..........");
        onString = Splitter.on("|").trimResults().omitEmptyStrings().split("a||b|c");
        // [a, b, c]
        System.out.println(onString);

        // 按字符匹配器拆分
        System.out.println(".......... charMatcher ..........");
        List<String> onCharMatcher = Splitter.on(CharMatcher.inRange('0', '9')).splitToList("a11d2m3i4n5");
        // [a, , d, m, i, n, ]
        System.out.println(onCharMatcher);
        System.out.println(".......... charMatcher omitEmptyStrings ..........");
        onCharMatcher = Splitter.on(CharMatcher.inRange('0', '9')).omitEmptyStrings().splitToList("a11d2m3i4n5");
        // [a, d, m, i, n]
        System.out.println(onCharMatcher);

        // 按正则表达式拆分
        System.out.println(".......... onPattern ..........");
        List<String> onPattern = Splitter.onPattern("\\d").omitEmptyStrings().splitToList("a1d4m2i8n57");
        // [a, d, m, i, n]
        System.out.println(onPattern);
        onPattern = Splitter.onPattern("\\d").omitEmptyStrings().splitToList("a12d4m2i8n57");
        // [a, d, m, i, n]
        System.out.println(onPattern);

        System.out.println("..................................................");

        // 按固定长度拆分，最后一段可能比给定长度短，但不会为空
        System.out.println(".......... fixedLength ..........");
        List<String> fixedLength = Splitter.fixedLength(3).omitEmptyStrings().splitToList("admin142857");
        System.out.println(fixedLength);

        System.out.println(".......... <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) ..........");
        ArrayList<String> newArrayList = Lists.newArrayList(Splitter.fixedLength(3).omitEmptyStrings().split("admin142857"));
        System.out.println(newArrayList);
        ArrayList<String> newArrayList1 = Lists.newArrayList(Splitter.fixedLength(3).omitEmptyStrings().splitToList("admin142857"));
        System.out.println(newArrayList1);
    }

    /**
     * 字符串做大小写转换（CaseFormat枚举），可以对字符串做模式匹配
     */
    @Test
    void testCaseFormat() {
        System.out.println(".......... UPPER_UNDERSCORE ..........");
        String constantName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");
        System.out.println(constantName);

        constantName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "CONSTANT_NAME");
        System.out.println(constantName);

        constantName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, "CONSTANT_NAME");
        System.out.println(constantName);

        System.out.println(".......... LOWER_UNDERSCORE ..........");
        constantName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");
        System.out.println(constantName);

        constantName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "CONSTANT_NAME");
        System.out.println(constantName);

        constantName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, "CONSTANT_NAME");
        System.out.println(constantName);
    }
}