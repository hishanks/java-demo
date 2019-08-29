package com.demo.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 为什么泛型类型不能使用基本数据类型？一定要使用基本数据类型的包装类呢？
 *
 * @author Shanks
 * @date 2019-06-10
 */
public class WhyWrapper {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        System.out.println(strings);

        // 反编译之后，这里实际上做了强转，(String)strings.get(0);
        String s = strings.get(0);
        System.out.println(s);

        int i = 10;
        Object o = (Object) i;
        System.out.println(o);
    }
}
