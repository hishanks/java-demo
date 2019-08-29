package com.demo.jdk8.interfaces.function;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * R apply(T t);
 *
 * @author zhoukai
 * @date 2018-10-18
 */
public class FunctionTest {

    @Test
    void testFunctional() {
        // 显而易见，obj -> JSON.toJSONString(obj)可以方法引用简写为：JSON::toJSONString
        FunctionInterface<Map<String, Object>, String> action = obj -> JSON.toJSONString(obj);
        Map<String, Object> map = Maps.newHashMap();
        map.put("name", "shanks");
        map.put("age", 20);
        System.out.println(map.toString());
        String jsonString = action.apply(map);
        System.out.println(jsonString);
    }
}