package com.demo.java.generic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 通配符 ? 的简单使用
 *
 * @author Shanks
 * @date 2019-06-02
 */
public class Main {

    @Test
    void testGeneric() {
        List<?> list = new ArrayList<>();
        // list.add("Shanks");
        // list.add(1);
        // list.add(new Object());

        // 只允许add为null的元素
        list.add(null);

        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");

        list = strings;

        Object o = list.get(0);
        System.out.println(o);
        String val = (String) list.get(1);
        System.out.println(val);
    }
}