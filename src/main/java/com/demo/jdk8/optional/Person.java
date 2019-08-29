package com.demo.jdk8.optional;

import java.util.Optional;

/**
 * @author zhoukai
 * @date 2018-09-21
 */
public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
