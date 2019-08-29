package com.demo.java;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author zhoukai
 * @date 2018/7/21
 */
public class SeasonEnumTest {

    @Test
    void testSeasonEnum() {

        System.out.println(SeasonEnum.SPRING.getCode());
        System.out.println(SeasonEnum.SUMMER.getDesc());
        System.out.println(SeasonEnum.AUTUMN);
        // Arrays.toString()方式打印数组元素
        System.out.println(Arrays.toString(SeasonEnum.values()));
        System.out.println("=====>JDK8方式利用Stream遍历数组：");
        Arrays.stream(SeasonEnum.values()).forEach(System.out::println);
        System.out.println("=====>");
        // valueOf("此处为枚举类的元素变量名")
        System.out.println(SeasonEnum.valueOf("WINTER").getCode());
        System.out.println(SeasonEnum.valueOf("WINTER").getDesc());

        System.out.println("=====>setCode");
        SeasonEnum.WINTER.setDesc("四季如春");
        System.out.println(SeasonEnum.WINTER.getDesc());
    }
}
