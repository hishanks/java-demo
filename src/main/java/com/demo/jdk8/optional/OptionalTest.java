package com.demo.jdk8.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author zhoukai
 * @date 2018-09-21
 */
public class OptionalTest {

    public static void main(String[] args) {

        Person shanks = new Person();
        // 没有Car
        Car carNull = null;
        Optional<Car> optionalCar = Optional.ofNullable(carNull);
        // 有一家保险公司，且保险公司必须有名字（工商局规定）
        Insurance insurance = new Insurance("平安保险");
        // 不给此optionalCar设置insurance
        shanks.setCar(optionalCar);
        /* 以上为自定义一个没有Car为null的People实例
        （即造假数据，所以不用给Car进行setInsurance操作，Car为null的话，再set肯定出现NPE） */
        final String shanksCarInsuranceName = getCarInsuranceName(Optional.of(shanks));
        System.out.println(shanksCarInsuranceName);

        System.out.println("----->");

        Person luffy = new Person();
        Car car = new Car();
        Insurance insuranceAnother = new Insurance("太平洋保险");
        car.setInsurance(Optional.of(insuranceAnother));
        luffy.setCar(Optional.of(car));
        final String luffyCarInsuranceName = getCarInsuranceName(Optional.of(luffy));
        System.out.println(luffyCarInsuranceName);

    }

    private static String getCarInsuranceName(Optional<Person> person) {

        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    @Test
    void testEmpty() {
        final Optional<Object> empty = Optional.empty();
        // Optional.empty
        System.out.println(empty);
        // Optional.empty
        System.out.println(empty.toString());
    }

    @Test
    void testEmptyOptional() {
        final Optional<Object> emptyOptional = Optional.empty();
        final Optional<Object> alsoEmpty = Optional.ofNullable(null);
        // 使用orElse方法则更简洁，当Optional对象为空时，该方法提供了一个备选值
        System.out.println("b".equals(emptyOptional.orElse("b")));
        // orElseGet()方法接受一个Supplier对象，只有在Optional对象真正为空时才会调用
        System.out.println("c".equals(emptyOptional.orElseGet(() -> "c")));
    }
}
