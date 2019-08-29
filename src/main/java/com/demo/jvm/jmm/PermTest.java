package com.demo.jvm.jmm;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 7和Java 8将字符串常量池由永久代转移到堆中，并且jdk1.8中已经不存在永久代！！
 * 永久代在Java 8中被完全的移除了。所以永久代的参数-XX:PermSize和-XX：MaxPermSize也被移除了。
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at java.util.Arrays.copyOf(Arrays.java:3332)
 * 	at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
 * 	at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:448)
 * 	at java.lang.StringBuilder.append(StringBuilder.java:136)
 * 	at com.demo.jvm.jmm.PermTest.main(PermTest.java:23)
 *
 * @author Shanks
 * @date 2019-03-18
 */
public class PermTest {

    private static String base = "StringPool";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
        System.out.println(JSON.toJSONString(list));
    }
}
