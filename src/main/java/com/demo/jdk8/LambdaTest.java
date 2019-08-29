package com.demo.jdk8;

import com.demo.jdk8.model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda表达式
 *
 * @author zhoukai
 * @date 2018/6/24
 */
class LambdaTest {

    @Test
    void testLambda() {
        List<String> list = Arrays.asList("Olajuwon", "Jordan", "McGrady", "Kobe");

        System.out.println("=====>Before Sort");
        System.out.println(list.toString());
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 自定义倒序排列
                return o2.compareTo(o1);
            }
        });
        System.out.println("=====>After Sort");
        System.out.println(list.toString());

        // 换成JDK8的语法
        // Collections.sort都省了，直接list.sort
        list.sort(Comparator.reverseOrder());
        list.sort(String::compareTo);

        System.out.println(list.toString());

        User user1 = new User("001", 100);
        User user2 = new User("002", 300);
        User user3 = new User("003", 200);
        List<User> userList = new ArrayList<>(Arrays.asList(user1, user2, user3));

        User user = userList.stream().max(Comparator.comparing(User::getValue)).orElse(new User());
        System.out.println(user);

        userList.stream().max(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
    }
}
