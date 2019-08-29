package com.demo.jdk8;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author zhoukai
 * @date 2018/7/6
 */
class OptionalTest {

    @Test
    void testOf() {
        System.out.println("==========Optional.of(\"kevin\")");
        Optional<String> name = Optional.of("kevin");
        System.out.println(name.get());
    }

    @Test
    void testOfNullable() {
        System.out.println("==========Optional.ofNullable(null)");
        Optional<Object> nullObj = Optional.ofNullable(null);
        // IDEA提示：The call to 'get' always fails
        System.out.println(nullObj.get());
    }

    @Test
    void testEmpty() {
        System.out.println("==========Optional.empty()");
        Optional<Object> empty = Optional.empty();
        // IDEA提示：The call to 'get' always fails
        System.out.println(empty.get());
    }

    /**
     * 如果创建的Optional中有值存在，则返回此值，否则返回一个默认值
     */
    @Test
    void testOrElse() {
        Optional<String> dog = Optional.of("dog");
        String cat = dog.orElse("cat");
        System.out.println(cat);
        Optional<Object> name = Optional.empty();
        System.out.println(name.orElse("张三"));
    }

    @Test
    void testOrElseGet() {
        Optional<Double> num = Optional.of(1.0);
        System.out.println(num.orElseGet(Math::random));
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.orElseGet(Math::random));
    }
}
