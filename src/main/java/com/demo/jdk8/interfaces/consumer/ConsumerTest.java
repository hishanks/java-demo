package com.demo.jdk8.interfaces.consumer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * void accept(T t);
 *
 * @author Shanks
 * @date 2018-10-18
 */
public class ConsumerTest {

    @Test
    void testConsumer() {
        // 定义一个Consumer类型的Lambda表达式
        ConsumerInterface<String> stringPrint = s -> System.out.println(s);
        // 传参数，消费
        stringPrint.accept("Hello World!");

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

        Consumer<Integer> action = x -> System.out.println(x);
        list.forEach(action);

        list.forEach(p -> System.out.println(p));
    }
}