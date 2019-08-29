package com.demo.datastructure.collections;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 像Arrays.asList()以及Map的keySet()、values()、entrySet()等都是集合视图的东西，修改集合视图，原数据也会跟着变动
 *
 * @author Shanks
 * @date 2019-02-20
 */
public class CollectionsViewTest {

    /**
     * testListView()方法展示的就是“集合视图”这一概念
     * <p>
     * 是不是感觉很神奇，其实Arrays.asList()只是将传入的数组与Arrays中的一个内部类ArrayList（注意，它与java.util包下的ArrayList不是同一个）做了一个”绑定“，
     * 在调用get()时会直接根据下标返回数组中的元素，而调用set()时也会直接修改数组中对应下标的元素。
     * 相对于直接复制来说，集合视图的优点是内存利用率更高，
     * 假设你有一个数组，又很想使用List的API来操作它，那么你不用new一个ArrayList以拷贝数组中的元素，只需要一点额外的内存（通过Arrays.ArrayList对数组进行封装），
     * 原始数据依然是在数组中的，并不会复制成多份。
     */
    @Test
    void testListView() {
        String[] strings = {"a", "b", "c"};
        List<String> list = Arrays.asList(strings);
        // "a"
        System.out.println(list.get(0));

        // 修改数组下标为0的位置上的数据
        strings[0] = "d";
        // "d"
        System.out.println(list.get(0));

        list.set(0, "e");
        // "e"
        System.out.println(strings[0]);

    }
}
