package com.demo.jdk8.optional;

import java.util.Optional;

/**
 * @author zhoukai
 * @date 2018/9/21
 */
public class Car {

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}
