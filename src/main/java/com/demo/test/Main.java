package com.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Shanks
 * @date 2018-12-03
 */
public class Main {

    public static void main(String[] args) {

        String[] stringArray = new String[3];
        stringArray[0] = "one";
        stringArray[1] = "two";
        stringArray[2] = "three";
        List<String> stringList = Arrays.asList(stringArray);
        stringList.set(0, "oneList");
        System.out.println(stringList);
        System.out.println(stringArray[0]);
        System.out.println(Arrays.toString(stringArray));

        // 使用工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的add/remove/clear方法会抛出UnsupportedOperationException异常
        // 可以通过set()方法修改集合元素的值，原有数组相应位置的值也同时会被修改，但是不能进行修改元素个数的任何操作，否则报异常
        // Arrays.asList()体现的是适配器模式，后台的数据仍是原有数组，set()方法间接对数组进行值得修改操作
        // asList的返回对象是一个Arrays的内部类，它并没有实现集合个数的相关修改方法，这也正是抛出异常的原因
        // Arrays.asList()的源码，返回值return ArrayList<>(a);但是此ArrayList是内部类
        stringList.add(1, "twoList");
        stringList.remove(2);
        stringList.clear();
    }

    @Test
    void testToArray() {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        // 以下为正确List转Array示例：使用带参toArray方法，并且先根据list.size()初始化数组长度
        Integer[] array = new Integer[list.size()];
        array = list.toArray(array);
        System.out.println(Arrays.toString(array));

        Object[] objects = list.toArray();
        System.out.println(Arrays.toString(objects));
    }

    /**
     * 只是字符集的编码，非URL编码解码！！
     */
    @Test
    void testCharsets() {

        String utf8 = new String("Hello".getBytes(), StandardCharsets.UTF_8);
        System.out.printf("----------> UTF_8: %s%n", utf8);

        String iso88591 = new String("Hello".getBytes(), StandardCharsets.ISO_8859_1);
        System.out.printf("----------> ISO_8859_1: %s%n", iso88591);

        String usAscii = new String("Hello".getBytes(), StandardCharsets.US_ASCII);
        System.out.printf("----------> US_ASCII: %s%n", usAscii);

        String utf16 = new String("Hello".getBytes(), StandardCharsets.UTF_16);
        System.out.printf("----------> UTF_16: %s%n", utf16);

        String utf16Be = new String("Hello".getBytes(), StandardCharsets.UTF_16BE);
        System.out.printf("----------> UTF_16BE: %s%n", utf16Be);

        String utf16le = new String("Hello".getBytes(), StandardCharsets.UTF_16LE);
        System.out.printf("----------> UTF_16LE: %s%n", utf16le);
    }

    @Test
    void utf8AndUnicode() {
        // \u60f3\u4f60\u4e86
        String s = "想你了";
        String unicode = JSON.toJSONString(s, SerializerFeature.BrowserCompatible);
        System.out.println(unicode);

        String utf8 = String.valueOf(JSON.parse(unicode));
        System.out.println(utf8);
    }

    @Test
    void testJavaNetURLDecoder() {

        try {
            System.out.println("-------------------- URL解码 --------------------");
            String decode = URLDecoder.decode("%E5%93%88%E5%93%88%E5%93%88", "UTF-8");
            String word = URLDecoder.decode("香克斯", "UTF-8");
            System.out.println("字符串：%E5%93%88%E5%93%88%E5%93%88，解码后：" + decode);
            System.out.println("字符串：香克斯，解码后：" + word);

            System.out.println("-------------------- URL编码（小写） --------------------");
            String toLowerCase = "%E5%93%88%E5%93%88%E5%93%88".toLowerCase();
            String toLowerCaseDecode = URLDecoder.decode(toLowerCase, "UTF-8");
            System.out.println(toLowerCaseDecode);

            System.out.println("-------------------- URL编码 --------------------");
            String encodeStr = URLEncoder.encode("路飞", "UTF-8");
            System.out.println("字符串：路飞，编码后：" + encodeStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testStringJoin() {
        String a = "";
        String b = "";
        String c = null;
        String d = "";

        System.out.println(String.join(", ", a, b, c));

        System.out.println(StringUtils.join(Arrays.asList(a, b, c), ", "));

        System.out.println(Arrays.asList(a, b, c).stream().collect(Collectors.joining(", ")));

        System.out.println("----------");
        String collect = Stream.of(a, b, c, d)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(", "));
        System.out.println(collect);
    }

    @Test
    void replace() {
        String idCardNo = "342201199103105613";
        System.out.println(replaceStr(idCardNo));

        String phoneNo = "15257117674";
        System.out.println(replacePhone(phoneNo));
    }

    /**
     * 正则替换为*号
     *
     * @param param param
     * @return String
     */
    public String replaceStr(String param) {
        int len = param.length();
        if (len < 9) {
            return param;
        }
        return param.replaceAll("(.{" + (len < 12 ? 3 : 6) + "})(.*)(.{4})", "$1" + "****" + "$3");
    }

    public String replacePhone(String s) {
        // 这里*只要一个，因为会替代多次，每次一个。
        s = s.replaceAll("(?<=[\\d]{3})\\d(?=[\\d]{4})", "*");
        return s;
    }

    @Test
    void testRegex() {
        // ^[\u4e00-\u9fa5,.!，。！a-zA-Z0-9]+$
        String regex = "^[\\u4e00-\\u9fa5,.!，。！a-zA-Z\\d]+$";
        System.out.println("测试用例");
        System.out.println(isMatches("纯汉字", regex));
        System.out.println(isMatches("English", regex));
        System.out.println(isMatches(",.!", regex));
        System.out.println(isMatches("，。！", regex));
        System.out.println(isMatches("中文逗号，句号。感叹号！英文符号,.!six666", regex));
        System.out.println(isMatches("01", regex));
        System.out.println(isMatches("ss", regex));
    }

    public static boolean isMatches(String target, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    @Test
    void testStringApi() {
        String toUpperCase = "shanks_admin_admin".toUpperCase();
        System.out.println(toUpperCase);

        System.out.println(toUpperCase.contains("AD"));
        System.out.println(toUpperCase.contains("A"));
        System.out.println(toUpperCase.contains("MIN"));

        System.out.println("..........");
        String test = "refundPrintData4RuleProvider".toUpperCase();
        System.out.println(test.contains("REFUND"));
        System.out.println(test.contains("DP"));
    }
}
