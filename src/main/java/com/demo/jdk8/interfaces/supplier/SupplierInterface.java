package com.demo.jdk8.interfaces.supplier;

/**
 * T get();
 *
 * @author Shanks
 * @date 2018-11-20
 */
@FunctionalInterface
public interface SupplierInterface<T> {

    T get();
}
