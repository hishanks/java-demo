package com.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zhoukai
 * @date 2018-10-09
 */
public class JSONUtils {

    private static <T> T toJavaObject(String jsonString, Class<T> clazz) {

        JSONObject jsonObject = JSON.parseObject(jsonString);
        return JSON.toJavaObject(jsonObject, clazz);
    }
}
