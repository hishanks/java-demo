package com.demo.java.reflect;

import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Shanks
 * @date 2019-03-25
 */
public class ReflectTest {

    @Test
    void test() {
        User user = new User(1, "admin");
        System.out.println("----------> before: ");
        System.out.println(user.toString());

        Class<? extends User> clazz = user.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(field -> {
            field.setAccessible(true);
            String fieldName = field.getName();
            try {
                switch (fieldName) {
                    case "id":
                        field.set(user, 10);
                        break;
                    case "name":
                        field.set(user, "Shanks");
                        break;
                    default:
                        break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        System.out.println("----------> after: ");
        System.out.println(user.toString());
    }

    @Test
    void test1() throws NoSuchFieldException {
        User user = new User(100, "Shanks");
        Class<? extends User> clazz = user.getClass();
        Field fieldId = clazz.getDeclaredField("id");
        Field fieldName = clazz.getDeclaredField("name");
        System.out.println(fieldId.getName());
        System.out.println(fieldName.getName());

        System.out.println("----- getFields() -----");
        Field[] fields = clazz.getFields();
        System.out.println("fields.length: " + fields.length);
        Arrays.stream(fields).forEach(field -> System.out.println(field.getName()));

        System.out.println("----- getDeclaredFields -----");
        Field[] declaredFields = clazz.getDeclaredFields();
        System.out.println("declaredFields.length: " + declaredFields.length);
        Arrays.stream(declaredFields).forEach(field -> {
            Class<? extends Field> cls = field.getClass();
            String name = field.getName();
            Class<?> type = field.getType();
            System.out.println(String.format("class: %s, name: %s, type: %s\n", cls, name, type));
        });
    }

    @Test
    void test2() {
        User u = new User(200, "admin");
        Class<? extends User> clazz = u.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(field -> {
            String name = field.getName();
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(name, clazz);
                Method method = descriptor.getReadMethod();
                Object value = method.invoke(u);
                System.out.println(String.format("name: %s, value: %s\n", name, value));
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 待修正
     */
    @Test
    void test3() {
        User user = new User(2, "Zero");
        Class<? extends User> clazz = user.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(field -> {
            field.setAccessible(true);
            String name = field.getName();
            try {
                Object value = field.get(user);
                System.out.println(String.format("name: %s, value: %s\n", name, value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
