package com.demo.util;

import java.lang.reflect.Field;

/**
 * @author Shanks
 * @date 2019-05-05
 */
public class ReflectUtils {

    public static <T> void mergeObject(T sourceObject, T targetObject) {
        if (sourceObject == null || targetObject == null) {
        }

        Field[] declaredFields = sourceObject.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            try {
                field.setAccessible(true);
                Object value = field.get(sourceObject);
                if (value != null) {
                    field.set(targetObject, value);
                }
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
