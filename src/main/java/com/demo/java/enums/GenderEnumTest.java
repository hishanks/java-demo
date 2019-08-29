package com.demo.java.enums;

import org.junit.jupiter.api.Test;

/**
 * @author Shanks
 * @date 2019-08-03
 */
public class GenderEnumTest {

    @Test
    void test() {
        System.out.println(GenderEnum.FEMALE.compareTo(GenderEnum.FEMALE));
        System.out.println(GenderEnum.FEMALE.compareTo(GenderEnum.MALE));

        int seq = GenderEnum.FEMALE.getSeq();
        System.out.println(seq);
        String desc = GenderEnum.FEMALE.getDesc();
        System.out.println(desc);
    }
}