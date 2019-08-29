package com.demo.interview.thread.reference;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author Shanks
 * @date 2019-06-17
 */
public class WeakHashMapTest {

    public static void main(String[] args) {
        myHashMap();
        System.out.println("....................");
        myWeakHashMap();
    }

    private static void myHashMap() {
        Map<Integer, String> map = new HashMap<>(16);
        Integer key = new Integer(1);
        String value = "HashMap";
        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t " + map.size());
    }

    private static void myWeakHashMap() {
        Map<Integer, String> map = new WeakHashMap<>(16);
        Integer key = new Integer(2);
        String value = "WeakHashMap";
        map.put(key, value);
        System.out.println(map);

        // 此处需要手动将该强引用设置为null，下面才会正确的实现WeakHashMap的特效
        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t " + map.size());
    }
}