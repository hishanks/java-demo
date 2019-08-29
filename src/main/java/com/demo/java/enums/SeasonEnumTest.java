package com.demo.java.enums;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Shanks
 * @date 2019-08-03
 */
public class SeasonEnumTest {

    @Test
    void test() {
        SeasonEnum spring = SeasonEnum.SPRING;
        int seq = spring.getSeq();
        System.out.println(seq);
        // 未生效
        spring.setSeq(100);
        System.out.println(seq);

        SeasonEnum.SPRING.setSeq(200);
        // 设置成功
        int springSeq = SeasonEnum.SPRING.getSeq();
        System.out.println(springSeq);

        // values
        SeasonEnum[] values = SeasonEnum.values();
        System.out.println(Arrays.toString(values));

        // valueOf
        SeasonEnum valueOf = SeasonEnum.valueOf("SPRING");
        System.out.println(valueOf);
    }
}