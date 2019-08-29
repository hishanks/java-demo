package com.demo.jdk8.collections;

import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Shanks
 * @date 2019-05-05
 */
public class Test {

    public static void main(String[] args) {
        User user1 = User.builder().id(1).age(10).build();
        User user2 = User.builder().id(1).name("a").build();
        User user3 = User.builder().id(3).age(30).build();
        User user4 = User.builder().id(3).name("b").build();
        List<User> userList = Lists.newArrayList(user1, user2, user3, user4);
        System.out.println("---------- 初始化数据：");
        System.out.println(userList);

        List<User> tempList = new ArrayList<>();
        userList.stream()
                .collect(Collectors.toMap(User::getId, Function.identity(), (oldVal, newVal) -> (User) combine(oldVal, newVal)))
                .forEach((k, v) -> tempList.add(v));
        System.out.println("---------- 处理后数据：");
        System.out.println(tempList);
    }

    private static <T> T combine(T sourceBean, T targetBean) {
        Class<?> sourceBeanClass = sourceBean.getClass();
        Class<?> targetBeanClass = targetBean.getClass();
        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = targetBeanClass.getDeclaredFields();
        for (int i = 0; i < sourceFields.length; i++) {
            Field sourceField = sourceFields[i];
            if (isStatic(sourceField)) {
                continue;
            }
            Field targetField = targetFields[i];
            if (isStatic(targetField)) {
                continue;
            }
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                if ((sourceField.get(sourceBean) != null) && !"serialVersionUID".equals(sourceField.getName())) {
                    targetField.set(targetBean, sourceField.get(sourceBean));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return targetBean;
    }

    private static boolean isStatic(Field field) {
        return Modifier.isStatic(field.getModifiers());
    }
}