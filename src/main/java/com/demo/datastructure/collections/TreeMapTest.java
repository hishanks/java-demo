package com.demo.datastructure.collections;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Shanks
 * @date 2019-06-10
 */
public class TreeMapTest {

    @Test
    void testTreeMap() {
        Map<String, Object> treeMap = new TreeMap<>();
        treeMap.put("b", 2);
        treeMap.put("a", 1);
        treeMap.put("c", 3);
        System.out.println(treeMap);
        System.out.println(JSON.toJSONString(treeMap));

        System.out.println("....................");

        Map<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("b", 2);
        linkedHashMap.put("a", 1);
        linkedHashMap.put("c", 3);
        System.out.println(linkedHashMap);
        System.out.println(JSON.toJSONString(linkedHashMap));
    }
}