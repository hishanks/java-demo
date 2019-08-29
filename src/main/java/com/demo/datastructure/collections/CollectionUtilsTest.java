package com.demo.datastructure.collections;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Shanks
 * @date 2018-09-18
 */
public class CollectionUtilsTest {

    /* commons commons */

    @Test
    void testCollectionUtils() {
        List<Integer> listOne = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 5, 8));
        List<Integer> listTwo = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7));
        int size = CollectionUtils.size(listOne);
        System.out.println("<== size: " + size);

        // A∩B 交集
        Collection<Integer> intersectionOne = CollectionUtils.intersection(listOne, listTwo);
        System.out.println("<== intersectionOne: " + intersectionOne);
        Collection<Integer> intersectionTwo = CollectionUtils.intersection(listTwo, listOne);
        System.out.println("<== intersectionTwo: " + intersectionTwo);

        // A∪B 并集
        Collection<Integer> union = CollectionUtils.union(listOne, listTwo);
        System.out.println("<== union: " + union);

        // A∪B-A∩B 交集的补集
        Collection<Integer> disjunction = CollectionUtils.disjunction(listOne, listTwo);
        System.out.println("<== disjunction: " + disjunction);
        Collection<Integer> disjunctionOther = CollectionUtils.disjunction(listTwo, listOne);
        System.out.println("<== disjunction: " + disjunctionOther);

        System.out.println("####################");
        // A-A∩B
        Collection<Integer> subtract = CollectionUtils.subtract(listOne, listTwo);
        System.out.println("<==      subtract: " + subtract);
        // B-A∩B
        Collection<Integer> subtractOther = CollectionUtils.subtract(listTwo, listOne);
        System.out.println("<== subtractOther: " + subtractOther);

        boolean a = CollectionUtils.isEqualCollection(listOne, listTwo);
        System.out.println(a);
        boolean b = CollectionUtils.isEqualCollection(listOne, listTwo);
        System.out.println(b);
    }

    /* jdk API */

    /**
     * 交集
     */
    @Test
    void retainAll() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 5, 8));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7));

        list1.retainAll(list2);
        System.out.println(list1);

        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 4, 2, 8, 5, 7));
        List<Integer> list4 = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7));
        list4.retainAll(list3);
        System.out.println(list4);
    }

    /**
     * 并集（交集数据重复），需要注意的是：这里的交是指2个集合的元素汇总在一起，也就是说不是数学意义上的并集（交集部分只算一次）
     */
    @Test
    void addAll() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 5, 8));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7));
        list1.addAll(list2);
        System.out.println(list1);
    }

    /**
     * 并集（交集数据不会重复），主要是先进行了一次remove操作，将list2中包含list1的数据清除掉了，然后再与list1进行并集
     * 此做法与Apache commons包下的工具类CollectionUtils.union功能一致
     */
    @Test
    void addAllAndRemoveAll() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 5, 8));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7));
        list2.removeAll(list1);
        list1.addAll(list2);
        System.out.println(list1);
    }

    /**
     * A-A∩B
     */
    @Test
    void removeAll() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 4, 2, 8, 5, 7));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7));
        list1.removeAll(list2);
        System.out.println(list1);
    }
}
