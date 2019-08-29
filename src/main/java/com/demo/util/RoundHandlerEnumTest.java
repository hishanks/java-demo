package com.demo.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author Shanks
 * @date 2019-08-17
 */
public class RoundHandlerEnumTest {

    @Test
    void test() {
        System.out.println(RoundHandlerEnum.JIN_YI.apply(2, new BigDecimal("4.2955")));
    }
}
