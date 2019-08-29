package com.demo.datastructure.collections;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Shanks
 * @date 2019-03-07
 */
public class LinkedHashMapTest {

    @Test
    void testLinkedHashMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("x", "1");
        map.put("z", "2");
        map.put("y", "3");
        System.out.println(JSON.toJSONString(map));

        System.out.println(map.get("x"));

        System.out.println(JSON.toJSONString(map));

        System.out.println("------------------------------");

        Map<String, Object> linkedMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedMap.put("a", 1);
        linkedMap.put("b", 2);
        linkedMap.put("c", 3);
        System.out.println(JSON.toJSONString(linkedMap));

        System.out.println(linkedMap.get("b"));
        System.out.println(JSON.toJSONString(linkedMap));
    }
}