package com.demo.java.bigdecimal;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * 关于BigDecimal，这一篇就够了~
 *
 * @author Shanks
 * @date 2019-08-18
 */
public class BigDecimalTest {

    @Test
    void constructor() {

        BigDecimal num1 = new BigDecimal(1);
        // 1
        System.out.println(num1);

        // 错误示范
        BigDecimal num2 = new BigDecimal(0.49);
        // 0.4899999999999999911182158029987476766109466552734375
        System.out.println(num2);

        BigDecimal num3 = new BigDecimal(2L);
        // 2
        System.out.println(num3);

        // 使用BigDecimal构造函数的正确姿势
        BigDecimal num4 = new BigDecimal("0.49");
        // 0.49
        System.out.println(num4);
    }

    /**
     * 重头戏：关于保留小数位后的舍入操作！！！
     */
    @Test
    void setScale() {
        BigDecimal num = new BigDecimal("0.4967");
        BigDecimal scale = num.setScale(2, BigDecimal.ROUND_CEILING);
        System.out.println(scale);
    }
}