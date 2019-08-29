package com.demo.guava.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Shanks
 * @date 2019-04-30
 */
public class CollectionsTest {

    @Test
    void testLists() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        System.out.println(integers);
        ImmutableList<Integer> immutableList = ImmutableList.of(1, 2, 3, 4);
        System.out.println(immutableList);
        List<Integer> collect = new ArrayList<>(immutableList);
        System.out.println(collect);

        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        int[] ints = Ints.toArray(list);
        System.out.println(Arrays.toString(ints));

        System.out.println(".......... 一般正确做法 ..........");
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Integer[] intArr = new Integer[integerList.size()];
        integerList.toArray(intArr);

        List<String> strings = new ArrayList<>(2);
        strings.add("A");
        strings.add("B");
        String[] strArr = new String[strings.size()];
        strArr = strings.toArray(strArr);
        System.out.println(Arrays.toString(strArr));
    }

    @Test
    void testMaps() {
        HashMap<Object, Object> map = Maps.newHashMap();
    }
}
