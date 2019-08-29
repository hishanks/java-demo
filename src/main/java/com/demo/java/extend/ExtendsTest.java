package com.demo.java.extend;

import org.junit.jupiter.api.Test;

/**
 * @author Shanks
 * @date 2019-07-28
 */
public class ExtendsTest {

    @Test
    void test() {

        Sub sub = new Sub();
        sub.setName("父类字段name");
        sub.setNo(1);
        sub.setCode(2);
        sub.setDesc("子类字段desc");

        System.out.println(sub.toString());
    }
}
