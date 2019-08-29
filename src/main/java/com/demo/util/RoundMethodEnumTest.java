package com.demo.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author Shanks
 * @date 2019-08-16
 */
public class RoundMethodEnumTest {

    @Test
    void test() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        /* 舍入：四舍五入
         * 假设2位小数
         * 4.2955→4.30
         * 4.3789→4.38
         * 4.29→4.29
         */
        BigDecimal bd1 = RoundMethodEnum.SHE_RU.apply(new BigDecimal("2"), new BigDecimal("4.2955"));
        System.out.println(bd1);
        BigDecimal bd2 = RoundMethodEnum.SHE_RU.apply(new BigDecimal("2"), new BigDecimal("4.3789"));
        System.out.println(bd2);
        BigDecimal bd3 = RoundMethodEnum.SHE_RU.apply(new BigDecimal("2"), new BigDecimal("4.29"));
        System.out.println(bd3);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("--------------------------------------------------");
        /*
         * 去尾
         * 在界定的小数位之后的值全部去掉
         * 假设2位小数
         * 4.2955→4.29
         * 4.3789→4.37
         * 4.29→4.29
         */
        BigDecimal bd4 = RoundMethodEnum.QU_WEI.apply(new BigDecimal("2"), new BigDecimal("4.2955"));
        System.out.println(bd4);
        BigDecimal bd5 = RoundMethodEnum.QU_WEI.apply(new BigDecimal("2"), new BigDecimal("4.3789"));
        System.out.println(bd5);
        BigDecimal bd6 = RoundMethodEnum.QU_WEI.apply(new BigDecimal("2"), new BigDecimal("4.29"));
        System.out.println(bd6);
        System.out.println("--------------------------------------------------");

        System.out.println("==================================================");
        /*
         * 进一
         * 在界定的小数位之后的值，全不为0时，进1到界定的小数位上，全为0时，不进1
         * 假设2位小数
         * 4.2955→4.30
         * 4.3789→4.38
         * 4.2913→4.30
         * 4.29→4.29
         */
        BigDecimal bd7 = RoundMethodEnum.JIN_YI.apply(new BigDecimal("2"), new BigDecimal("4.2955"));
        System.out.println(bd7);
        BigDecimal bd8 = RoundMethodEnum.JIN_YI.apply(new BigDecimal("2"), new BigDecimal("4.3789"));
        System.out.println(bd8);
        BigDecimal bd9 = RoundMethodEnum.JIN_YI.apply(new BigDecimal("2"), new BigDecimal("4.2913"));
        System.out.println(bd9);
        BigDecimal bd10 = RoundMethodEnum.SHE_RU.apply(new BigDecimal("2"), new BigDecimal("4.29"));
        System.out.println(bd10);
        System.out.println("==================================================");
    }

    @Test
    void three() {
        /*
         * 进一
         * 在界定的小数位之后的值，全不为0时，进1到界定的小数位上，全为0时，不进1
         * 假设2位小数
         * 4.2955→4.30
         * 4.3789→4.38
         * 4.2913→4.30
         * 4.29→4.29
         */
        System.out.println(RoundMethodEnum.JIN_YI.apply(new BigDecimal("2"), new BigDecimal("4.2955")));
        System.out.println(RoundMethodEnum.JIN_YI.apply(new BigDecimal("2"), new BigDecimal("4.3789")));
        System.out.println(RoundMethodEnum.JIN_YI.apply(new BigDecimal("2"), new BigDecimal("4.2913")));
        System.out.println(RoundMethodEnum.JIN_YI.apply(new BigDecimal("2"), new BigDecimal("4.29")));

        System.out.println("--------------------");
        System.out.println(RoundMethodEnum.JIN_YI.apply(new BigDecimal("2"), new BigDecimal("4.2111")));
        System.out.println(RoundMethodEnum.JIN_YI.apply(new BigDecimal("2"), new BigDecimal("4.2900")));
        System.out.println(RoundMethodEnum.JIN_YI.apply(new BigDecimal("2"), new BigDecimal("4.29999")));
        System.out.println(RoundMethodEnum.JIN_YI.apply(new BigDecimal("2"), new BigDecimal("4.2000")));
        System.out.println(RoundMethodEnum.JIN_YI.apply(new BigDecimal("2"), new BigDecimal("4")));
    }
}