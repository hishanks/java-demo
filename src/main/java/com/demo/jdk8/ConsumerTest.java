package com.demo.jdk8;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * 抽象方法：void accept(T t);
 *
 * @author zhoukai
 * @date 2018/6/27
 */
class ConsumerTest {

    @Test
    void testConsumer() {
        // 传参数，然后执行，无返回值
        Consumer<String> consumer = p -> System.out.println(p + "...");
        consumer.accept("Hello World");
    }
}
