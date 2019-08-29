package com.demo.jdk8;

import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;

/**
 * @author zhoukai
 * @date 2018-10-18
 */
public class BinaryOperatorTest {

    @Test
    void testBinaryOperator() {

        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
    }
}
