package com.demo.datastructure.collections;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shanks
 * @date 2019-03-01
 */
public class DebugArrayList {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(4);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(JSON.toJSONString(list));
    }
}