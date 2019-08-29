package com.demo.serialization;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

/**
 * @author Shanks
 * @date 2019-07-08
 */
public class SerializableTest {

    @Test
    void test() {
        Computer computer = Computer.builder()
                .name("MacBook Pro")
                .brand("Mac")
                .price(10000)
                .build();
        System.out.println(computer);

        System.out.println(".......... 序列化：");
        byte[] serialize = SerializationUtils.serialize(computer);
        System.out.println(serialize.length);

        System.out.println(".......... 反序列化：");
        Computer deserialize = SerializationUtils.deserialize(serialize);
        System.out.println(deserialize);
    }
}