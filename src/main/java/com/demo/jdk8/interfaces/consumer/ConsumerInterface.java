package com.demo.jdk8.interfaces.consumer;

/**
 * void accept(T t);
 *
 * @author Shanks
 * @date 2018-10-18
 */
@FunctionalInterface
public interface ConsumerInterface<T> {

    void accept(T t);
}
