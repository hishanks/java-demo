package com.demo.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhoukai
 * @date 2018-09-17
 */
public class ApiTest {

    @Test
    void testApiJsonArray() {

        JSONArray jsonArray = new JSONArray();
        jsonArray.add("1");
        jsonArray.add("2");
        jsonArray.add("3");
        System.out.println(jsonArray);
        System.out.println(jsonArray.get(0));

        System.out.println("-----");

        String jsonString = JSON.toJSONString(jsonArray);
        System.out.println(jsonString);

        System.out.println("-----");

        System.out.println("JSON字符串 --> List");
        List<String> stringList = JSON.parseArray(jsonString, String.class);
        System.out.println(stringList);
    }

    @Test
    void testToJson() {

        /* Java对象的JSON格式化 */
        User user = new User("shanks", 35);
        System.out.println(user.toString());
        String userStr = JSON.toJSONString(user);
        System.out.println(userStr);

        System.out.println("-----");

        String jsonString = JSON.toJSONString(user, true);
        System.out.println(jsonString);

        System.out.println("-----");
        JSONObject jsonObject = (JSONObject) JSON.toJSON(user);
        System.out.println(jsonObject);
        Integer age = jsonObject.getInteger("age");
        String name = jsonObject.getString("name");
        System.out.printf("%s---%d", name, age);

        // 转JSONObject
        final JSONObject parseObject = JSON.parseObject(JSON.toJSONString(user));
    }

    @Test
    void testList() {

        Map<String, Object> map = new HashMap<>();
        map.put("id", "002");
        List<Object> list = new ArrayList<>();
        list.add("shanks");
        list.add(35);
        map.put("details", list);

        System.out.println(map.toString());

        /* Map转JSONObject --> JSON.parseObject(JSON.toJSONString(map)) */
        JSONObject parseObject = JSON.parseObject(JSON.toJSONString(map));
        System.out.println(parseObject);

        System.out.println(parseObject.get("id"));
        System.out.println(parseObject.get("details"));

        JSONArray array = parseObject.getJSONArray("details");
        System.out.println(array);
        System.out.println(array.get(0));
    }

    @Test
    void testEntity() {

        User a = new User("shanks", 30);
        User b = new User("luffy", 16);
        User c = new User("ace", 20);

        Map<String, Object> map = Maps.newHashMap();
        map.put("name", "one piece");
        List<User> list = Lists.newArrayList();
        list.add(a);
        list.add(b);
        list.add(c);
        map.put("peoples", list);

        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
        System.out.println(jsonObject);

        /* --> JSONArray */
        JSONArray jsonArray = jsonObject.getJSONArray("peoples");

        String name = jsonObject.getString("name");
        System.out.println(name);

        /* --> User */
        User user = JSON.parseObject(JSON.toJSONString(jsonArray.get(0)), User.class);
        System.out.println(user);
    }

    @Test
    void testEmptyList() {
        String emptyList = "[]";
        String jsonString = JSON.toJSONString(emptyList);
        System.out.println(jsonString);
        System.out.println("==========");

        JSONArray jsonArray = JSON.parseArray(emptyList);
        boolean empty = jsonArray.isEmpty();
        System.out.println(empty);
    }
}
