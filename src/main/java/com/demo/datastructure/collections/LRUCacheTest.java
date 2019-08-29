package com.demo.datastructure.collections;

import com.alibaba.fastjson.JSON;

/**
 * 测试基于LRU算法缓存的LinkedHashMap
 *
 * @author Shanks
 * @date 2019-06-18
 */
public class LRUCacheTest {

    public static void main(String[] args) {
        LRUCache<String, Object> cache = new LRUCache<>(8, 0.75f, 5);
        cache.put("one", 1);
        System.out.println(JSON.toJSONString(cache));
        cache.put("two", 2);
        System.out.println(JSON.toJSONString(cache));
        cache.put("three", 3);
        System.out.println(JSON.toJSONString(cache));

        // get一次，将该元素移动到双向链表尾部
        System.out.println(cache.get("two"));
        System.out.println(JSON.toJSONString(cache));

        cache.put("four", 4);
        System.out.println(JSON.toJSONString(cache));

        cache.put("five", 5);
        System.out.println(JSON.toJSONString(cache));

        // get一次，将该元素移动到双向链表尾部
        System.out.println(cache.get("three"));
        System.out.println(JSON.toJSONString(cache));

        cache.put("six", 6);
        System.out.println(JSON.toJSONString(cache));
        cache.put("seven", 7);
        System.out.println(JSON.toJSONString(cache));
        cache.put("eight", 8);
        System.out.println(JSON.toJSONString(cache));
        cache.put("nine", 9);
        System.out.println(JSON.toJSONString(cache));
        cache.put("ten", 10);
        System.out.println(JSON.toJSONString(cache));
    }
}