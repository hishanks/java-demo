package com.demo.datastructure.collections;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shanks
 * @date 2019-03-01
 */
public class DebugHashMap {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>(14);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        map.put("5", "e");
        map.put("6", "f");
        map.put("7", "g");
        map.put("8", "h");
        map.put("9", "i");
        map.put("10", "j");
        map.put("11", "k");
        map.put("12", "l");
        map.put("13", "m");
        map.put("14", "n");
        map.put("15", "o");
        map.put("16", "p");
        map.put("17", "q");
        map.put("18", "r");
        System.out.println(JSON.toJSONString(map));
    }
}