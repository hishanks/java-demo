package com.demo.util;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * @author zhoukai
 * @date 2018-09-25
 */
public class Base64Utils {

    private static final String CLASS_NAME = "com.sun.org.commons.xerces.internal.impl.dv.util.Base64";

    public static String encodeBase64(byte[] bytes) throws Exception {
        Class clazz = Class.forName(CLASS_NAME);
        Method method = clazz.getMethod("encode", byte[].class);
        method.setAccessible(true);
        Object object = method.invoke(null, new Object[]{bytes});
        return object.toString();
    }

    /**
     * Base64加密
     *
     * @param s 明文
     * @return Base64密文
     * @throws Exception
     */
    public static String encodeBase64(String s) throws Exception {
        byte[] bytes = s.getBytes();
        Class clazz = Class.forName(CLASS_NAME);
        Method method = clazz.getMethod("encode", byte[].class);
        method.setAccessible(true);
        Object object = method.invoke(null, new Object[]{bytes});
        return (String) object;
    }

    /**
     * Base64解密
     *
     * @param s Base64密文
     * @return 明文
     * @throws Exception
     */
    public static String decodeBase64(String s) throws Exception {
        Class clazz = Class.forName(CLASS_NAME);
        Method method = clazz.getMethod("decode", String.class);
        method.setAccessible(true);
        Object object = method.invoke(null, s);
        return new String((byte[]) object, StandardCharsets.UTF_8);
    }
}
