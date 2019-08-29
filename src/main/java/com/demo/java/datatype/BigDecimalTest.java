package com.demo.java.datatype;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author Shanks
 * @date 2019-07-02
 */
public class BigDecimalTest {

    /**
     * 【强制】为了防止精度损失，禁止使用构造方法 BigDecimal(double)的方式把 double 值转 化为 BigDecimal 对象。
     * 说明：BigDecimal(double)存在精度损失风险，在精确计算或值比较的场景中可能会导致业务逻辑异常。
     * 如：BigDecimal g = new BigDecimal(0.1f); 实际的存储值为：0.10000000149
     * 正例：优先推荐入参为 String 的构造方法，或使用 BigDecimal 的 valueOf 方法，此方法内部其实执行了 Double 的 toString，而 Double 的 toString 按 double 的实际能表达的精度对尾数进行了截断。
     */
    @Test
    void test() {
        // 建议方式
        BigDecimal bd1 = new BigDecimal("0.1");
        // 建议方式
        BigDecimal bd2 = BigDecimal.valueOf(0.1);
        // 不建议方式，数据精度问题
        BigDecimal bd3 = new BigDecimal(0.1f);

        double d1 = bd1.doubleValue();
        double d2 = bd2.doubleValue();
        double d3 = bd3.doubleValue();
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);

        System.out.println(".......... 比较 ..........");
        // true
        System.out.println(bd1.doubleValue() == bd2.doubleValue());
        // false
        System.out.println(bd1.doubleValue() == bd3.doubleValue());
        // false
        System.out.println(bd2.doubleValue() == bd3.doubleValue());
    }
}