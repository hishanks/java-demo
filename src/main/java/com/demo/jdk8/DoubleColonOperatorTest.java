package com.demo.jdk8;

import com.demo.jdk8.model.User;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 双冒号操作符
 *
 * @author zhoukai
 * @date 2018/6/26
 */
class DoubleColonOperatorTest {

    @Test
    void testDoubleColonOperator() {
        List<String> list = new ArrayList<>(Arrays.asList("one", " two", "three "));
        // list.stream().map(s -> s.trim()).collect(toList())
        List<String> collect = list.stream().map(String::trim).collect(toList());
        System.out.println(collect);

        User a = new User("1", 1);
        User b = new User("2", 2);
        List<User> users = Lists.newArrayList(a, b);
        List<String> strings = users.stream().map(user -> user.getId()).collect(Collectors.toList());
        List<String> stringList = users.stream().map(User::getId).collect(Collectors.toList());
    }
}
