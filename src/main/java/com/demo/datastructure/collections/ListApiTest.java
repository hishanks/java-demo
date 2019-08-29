package com.demo.datastructure.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author Shanks
 * @date 2018-11-23
 */
public class ListApiTest {

    @Test
    void testToArray() {
        List<String> list = new ArrayList<>(2);
        list.add("Hello");
        list.add("World");
        String[] array = new String[list.size()];
        array = list.toArray(array);
        System.out.println(Arrays.toString(array));
        System.out.println(array[0]);
    }

    @Test
    void testAsList() {
        /*
         * 使用工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的add/remove/clear方法会抛出UnsupportedOperationException异常
         * 说明：Arrays.asList()的返回对象是一个Arrays内部类，并没有实现集合的修改方法
         * Arrays.asList体现的是适配器模式，只是转换接口，后台的数据仍是数组
         */
        String[] str = new String[]{"Hello", "World"};
        List list = Arrays.asList(str);
        System.out.println(list);
        // java.lang.UnsupportedOperationException
        // list.add("GoodBye");
        str[0] = "Fuck";
        System.out.println(list.get(0));

        // 这种方式比较安全正确
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(str));
        strings.add("FFFFF");
        str[0] = "?????";
        System.out.println(strings);
    }

    /**
     * 使用此种方式！！！
     */
    @Test
    void removeByIterator() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if ("1".equals(iterator.next())) {
                iterator.remove();
            }
        }
        System.out.println(list);

        // 使用removeIf，底层实现还是迭代器Iterator！
        list.removeIf(s -> "1".equals(s));
        // 方法引用简化
        list.removeIf("1"::equals);
    }

    @Test
    void removeForEach() {

        /* 展示了forEach方式remove集合元素，会产生的问题，若需要遍历几个然后根据条件移除元素，需要使用Iterator！！ */
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            // 若1换成2，则抛出java.util.ConcurrentModificationException
            if ("1".equals(item)) {
                list.remove(item);
            }
        }
        System.out.println(list);
    }

    /**
     * 并集、交集、差集
     */
    @Test
    void testList() {

        List<String> one = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> oneOther = new ArrayList<>(Arrays.asList("B", "C", "D"));
        // 并集（含重复元素）
        one.addAll(oneOther);
        System.out.println(one);

        List<String> two = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> twoOther = new ArrayList<>(Arrays.asList("B", "C", "D"));
        // 并集（不含重复元素）
        twoOther.removeAll(two);
        two.addAll(twoOther);
        System.out.println(two);

        List<String> three = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> threeOther = new ArrayList<>(Arrays.asList("B", "C", "D"));
        // 交集
        three.retainAll(threeOther);
        System.out.println(three);

        List<String> four = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> fourOther = new ArrayList<>(Arrays.asList("B", "C", "D"));
        // 差集
        four.removeAll(fourOther);
        System.out.println(four);
    }
}