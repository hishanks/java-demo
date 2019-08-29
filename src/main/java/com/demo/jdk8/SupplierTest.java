package com.demo.jdk8;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

/**
 * 抽象方法也是唯一的方法：T get();
 *
 * @author zhoukai
 * @date 2018/6/27
 */
public class SupplierTest {

    @Test
    public void testSupplier() {
        // 无参数，有返回值
        Supplier<Double> supplier = Math::random;
        System.out.println(supplier.get());

        Supplier<String> stringSupplier = String::new;
        System.out.println(stringSupplier.get().getClass());
    }
}

