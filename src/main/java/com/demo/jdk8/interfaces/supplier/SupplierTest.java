package com.demo.jdk8.interfaces.supplier;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * T get();
 *
 * @author Shanks
 * @date 2018-11-20
 */
public class SupplierTest {

    @Test
    void testSupplierTest() {

        SupplierInterface<List<Integer>> action = () -> Lists.newArrayList(1, 2, 3);
        List<Integer> list = action.get();
        System.out.println(list);
    }
}