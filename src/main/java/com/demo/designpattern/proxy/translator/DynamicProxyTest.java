package com.demo.designpattern.proxy.translator;

import java.lang.reflect.Proxy;

/**
 * @author zhoukai
 * @date 2018/8/15
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        // 接口类型的目标对象
        Translatable target = new Translator();
        System.out.println("----------> target: " + target);
        // class com.demo.designpattern.proxy.translator.Translator
        System.out.println(target.getClass());

        // 类加载器，ClassLoader loader
        ClassLoader loader = target.getClass().getClassLoader();
        System.out.println(loader.toString());
        // 接口，Class<?>[] interfaces
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // 代理，InvocationHandler h
        DynamicProxy proxy = new DynamicProxy(target);
        /*
         * 生成代理类对象
         * 利用Proxy的静态方法Proxy.newProxyInstance()来为一组接口动态的生成代理类及对象
         * 也就是动态生成代理对象的方法
         * */
        Translatable translatable = (Translatable) Proxy.newProxyInstance(loader, interfaces, proxy);
        System.out.println("----------> translatable: " + translatable);
        /*
         * class com.sun.proxy.$Proxy8
         * 内存中动态生成的代理对象
         * */
        System.out.println(translatable.getClass());

        translatable.translate();
        translatable.englishToChinese();
    }
}
