package com.demo.java.datatype;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author Shanks
 * @date 2019-07-02
 */
public class FloatTest {

    /**
     * 浮点数采用“尾数+阶码”的编码方式，类似于科学计数法的“有效数字+指数”的表示方式。二进制无法精确表示大部分的十进制小数。
     */
    @Test
    void test() {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a == b);

        Float x = Float.valueOf(a);
        Float y = Float.valueOf(b);
        System.out.println(x.equals(y));
    }

    /**
     * 指定一个误差范围，两个浮点数的差值在此范围之内，则认为是相等的。
     */
    @Test
    void testDiff() {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        float diff = 1e-6f;
        if (Math.abs(a - b) < diff) {
            System.out.println("true");
        }
    }

    /**
     * 使用 BigDecimal 来定义值，再进行浮点数的运算操作。
     */
    @Test
    void testBigDecimal() {
        /*
         * 这里还需要注意的一个小细节就是：
         * new BigDecimal("0.9");
         * new BigDecimal(0.9);
         */
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);
        if (x.equals(y)) {
            System.out.println("true");
        }

        BigDecimal i = new BigDecimal("0.4");
        // 这种方式会导致精度丢失
        BigDecimal j = new BigDecimal(0.4f);
        System.out.println(i.floatValue() == j.floatValue());

        System.out.println(i.doubleValue() == 0.4);
        System.out.println(j.doubleValue() == 0.4);
    }

}