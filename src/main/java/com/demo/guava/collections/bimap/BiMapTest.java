package com.demo.guava.collections.bimap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Test;

/**
 * +--------------------------------------------------------------+
 * | Key-Value Map Impl   Value-Key Map Impl  Corresponding BiMap |
 * +--------------------------------------------------------------+
 * | HashMap              HashMap             HashBiMap           |
 * +--------------------------------------------------------------+
 * | ImmutableMap         ImmutableMap        ImmutableBiMap      |
 * +--------------------------------------------------------------+
 * | EnumMap              EnumMap             EnumBiMap           |
 * +--------------------------------------------------------------+
 * | EnumMap              HashMap             EnumHashBiMap       |
 * +--------------------------------------------------------------+
 * <p>
 * 应用场景：国家<-->首都，一个国家对应一个首都，一个首都对应一个国家
 *
 * @author Shanks
 * @date 2019-07-10
 */
public class BiMapTest {

    @Test
    void testBiMap() {
        BiMap<String, String> weekNameMap = HashBiMap.create();
        weekNameMap.put("星期一", "Monday");
        weekNameMap.put("星期二", "Tuesday");
        weekNameMap.put("星期三", "Wednesday");
        weekNameMap.put("星期四", "Thursday");
        weekNameMap.put("星期五", "Friday");
        weekNameMap.put("星期六", "Saturday");
        weekNameMap.put("星期日", "Sunday");

        System.out.println("星期日的英文名是：" + weekNameMap.get("星期日"));
        System.out.println("Sunday的中文是：" + weekNameMap.inverse().get("Sunday"));
    }

    /**
     * put操作时需要保证value不是重复的
     */
    @Test
    void testDuplicateValue() {
        BiMap<Object, Object> hashBiMap = HashBiMap.create();
        hashBiMap.put(1, "a");
        System.out.println(hashBiMap);

        // key相同会覆盖
        hashBiMap.put(2, "x");
        hashBiMap.put(2, "y");
        System.out.println(hashBiMap);

        // value相同会报异常，java.lang.IllegalArgumentException: value already present: y
        hashBiMap.put(3, "y");
        System.out.println(hashBiMap);
    }

    @Test
    void testDuplicateValueForcePut() {
        BiMap<Integer, String> logfileMap = HashBiMap.create();
        logfileMap.put(1, "a.log");
        logfileMap.put(2, "b.log");
        logfileMap.put(3, "c.log");

        logfileMap.put(4, "d.log");
        logfileMap.forcePut(5, "d.log");
        System.out.println("logfileMap: " + logfileMap);
    }

    @Test
    void testInverse() {
        BiMap<Integer, String> logfileMap = HashBiMap.create();
        logfileMap.put(1, "a.log");
        logfileMap.put(2, "b.log");
        logfileMap.put(3, "c.log");
        System.out.println("logfileMap: " + logfileMap);
        System.out.println("..................................................");

        BiMap<String, Integer> fileLogMap = logfileMap.inverse();
        System.out.println("fileLogMap: " + fileLogMap);
        System.out.println("..................................................");

        logfileMap.put(4, "d.log");

        System.out.println("logfileMap: " + logfileMap);
        System.out.println("fileLogMap: " + fileLogMap);
    }
}