package com.demo.jdk8.interfaces.function;

/**
 * R apply(T t);
 *
 * @author zhoukai
 * @date 2018-10-18
 */
@FunctionalInterface
public interface FunctionInterface<T, R> {

    R apply(T t);
}