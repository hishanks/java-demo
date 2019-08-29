package com.demo.jdk8.collectors;

import com.alibaba.fastjson.JSON;
import com.demo.jdk8.collectors.model.User;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * throw new IllegalStateException(String.format("Duplicate key %s", u));
 * 解决方法：调用三个参数的toMap重载方法，第三个参数为：BinaryOperator<U> mergeFunction
 *
 * @author Shanks
 * @date 2018-10-10
 */
public class ToMapTest {

    private static User user = new User(1, "Tom");
    private static User user1 = new User(2, "Jerry");
    private static User user2 = new User(3, "Rose");
    private static User user3 = new User(3, "Jack");
    private static User user4 = new User(4, "Jerry");
    private static List<User> list = Lists.newArrayList(user, user1, user2);
    private static List<User> listDuplicateKey = Lists.newArrayList(user, user1, user2, user3, user4);

    @Test
    void testUniqueKey() {
        Map<Integer, String> map = list.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        map.forEach((k, v) -> System.out.println(k + "-->" + v));
    }

    /**
     * toMap默认方法有个参数：throwingMerger()，当key重复值，抛异常：
     * throw new IllegalStateException(String.format("Duplicate key %s", u));
     */
    @Test
    void testDuplicateKeyThrowException() {
        Map<Integer, String> map = listDuplicateKey.stream().collect(Collectors.toMap(User::getId, User::getName));
        map.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    @Test
    void testDuplicateKey() {
        Map<Integer, String> map = listDuplicateKey.stream()
                .collect(Collectors.toMap(User::getId, User::getName, (oldValue, newValue) -> newValue));
        map.forEach((k, v) -> System.out.println(k + "-->" + v));
    }

    /**
     * Function.identity()获取List里面的泛型属性实体，此方式也是不允许出现重复key
     */
    @Test
    void testIdentityUniqueKey() {
        // t -> t 等价于 Function.identity()
        Map<Integer, User> map = list.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        map.forEach((k, v) -> System.out.println(k + "-->" + v));
    }

    @Test
    void testIdentityDuplicateKey() {

        System.out.println(JSON.toJSONString(listDuplicateKey));

        System.out.println("=====>");
        Map<Integer, User> map = listDuplicateKey.stream()
                .collect(Collectors.toMap(User::getId, Function.identity(), (oldValue, newValue) -> newValue));
        map.forEach((k, v) -> System.out.println(k + "-->" + v));

        System.out.println("=====>");
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    void testDuplicateKeyWithOtherAsKey() {

        /*
         * last-write-win 保持最后更新
         * Collector to impose last-write-win policy
         * toMap(keyMapper, valueMapper, (oldVal, newVal) -> newVal)
         */
        System.out.println(JSON.toJSONString(listDuplicateKey));

        System.out.println("=====>Collectors.toMap，添加处理重复key的参数..");
        Map<String, User> map = listDuplicateKey.stream()
                .collect(
                        Collectors.toMap(
                                user -> user.getName() + "_" + LocalDate.now(),
                                user -> user,
                                (oldValue, newValue) -> newValue
                        )
                );
        map.forEach((k, v) -> System.out.println(k + "-->" + v));

        System.out.println("=====> user -> user 等价于 Function.identity()");
        Map<String, User> collect = listDuplicateKey.stream()
                .collect(Collectors.toMap(User::getName, Function.identity(), (oldValue, newValue) -> newValue));
        System.out.println(JSON.toJSONString(collect));
    }
}