package com.demo.jdk8;

import org.junit.jupiter.api.Test;

/**
 * @author zhoukai
 * @date 2018/8/7
 */
class ConverterTest {

    @Test
    void testConvert() {

        Converter<String, Integer> str2Int = Integer::parseInt;
        Integer i = str2Int.convert("123");
        System.out.println(i);
    }
}
