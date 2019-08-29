package com.demo.json.netsfjson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;
import net.sf.json.processors.JsonValueProcessor;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Shanks
 * @date 2018-12-12
 */
public class ApiTest {

    @Test
    void testEmptyArrayString() {

        String emptyList = "[]";
        JSONArray jsonArray = JSONArray.fromObject(emptyList);
        System.out.println(jsonArray);
        List list = JSONArray.toList(jsonArray, new Object(), null);
        boolean empty = CollectionUtils.isEmpty(list);
        System.out.println(empty);
    }

    @Test
    void testA() {

        Remark remark = new Remark();
        remark.setId(1L);
        remark.setContent("Redis");
        remark.setUserId(34);
        String jsonString = remark.toJSONString();
        System.out.println(jsonString);
        System.out.println("==========");

        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        System.out.println(jsonObject);
        System.out.println("==========");

        Remark toBean = (Remark) JSONObject.toBean(jsonObject, Remark.class);
        System.out.println(toBean.toString());
        System.out.println("==========");

        JSONObject fromObject = JSONObject.fromObject(remark);
        System.out.println(fromObject.toString());
    }

    @Test
    void testJsonConfig() {

        Remark remark = new Remark();
        remark.setId(1L);
        remark.setContent("RabbitMQ");
        remark.setUserId(34);
        // 使用JsonConfig过滤属性
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"content", "userId" });
        JSONObject jsonObject = JSONObject.fromObject(remark, jsonConfig);
        System.out.println(jsonObject.toString());
        System.out.println("==========");

        Remark object = new Remark();
        object.setId(2L);
        object.setContent("Spring Cloud");
        object.setUserId(23);
        JsonConfig config = new JsonConfig();
        config.registerPropertyExclusions(Remark.class, new String[]{"id", "userId" });
        JSONObject fromObject = JSONObject.fromObject(object, config);
        System.out.println(fromObject.toString());
    }

    /**
     * JsonBeanProcessor和实现JsonString很类似，返回一个代表原来目标对象的合法JSONObject
     */
    @Test
    void testJsonBeanProcessor() {

        Remark remarkObject = new Remark();
        remarkObject.setId(3L);
        remarkObject.setContent("MongoDB");
        remarkObject.setUserId(24);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonBeanProcessor(Remark.class, new JsonBeanProcessor() {
            @Override
            public JSONObject processBean(Object o, JsonConfig jsonConfig) {
                Remark obj = (Remark) o;
                return new JSONObject()
                        .element("id", obj.getId())
                        .element("content", obj.getContent())
                        .element("userId", obj.getUserId());
            }
        });
        JSONObject jsonObject = JSONObject.fromObject(remarkObject, jsonConfig);
        System.out.println(jsonObject.toString());
    }

    /**
     * 控制JSON序列化过程中的Date对象的格式化，以及数值的格式化，JsonValueProcessor是最好的选择
     */
    @Test
    void testJsonValueProcessor() {

        Map<String, Object> map = new HashMap<>();
        map.put("date", new Date());
        map.put("dateArray", Arrays.asList(new Date(), new Date()));
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            @Override
            public Object processArrayValue(Object o, JsonConfig jsonConfig) {
                return simpleDateFormat.format(o);
            }

            @Override
            public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
                return simpleDateFormat.format(o);
            }
        });
        JSONObject jsonObject = JSONObject.fromObject(map, jsonConfig);
        System.out.println(jsonObject.toString());
    }
}
