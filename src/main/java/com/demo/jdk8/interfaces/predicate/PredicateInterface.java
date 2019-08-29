package com.demo.jdk8.interfaces.predicate;

/**
 * boolean test(T t);
 *
 * @author Shanks
 * @date 2018-10-24
 */
@FunctionalInterface
public interface PredicateInterface<T> {

    boolean test(T t);
}
