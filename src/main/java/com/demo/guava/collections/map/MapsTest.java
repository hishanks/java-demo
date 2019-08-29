package com.demo.guava.collections.map;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author Shanks
 * @date 2019-05-01
 */
public class MapsTest {

    @Test
    void testMaps() {
        Map<String, Object> immutableMap = ImmutableMap.of("name", "admin", "age", 10);
        immutableMap.forEach((k, v) -> System.out.println(k + ", " + v));

    }
}
