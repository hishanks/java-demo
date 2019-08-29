package com.demo.interview.clazz;

import org.junit.jupiter.api.Test;

/**
 * 整个类加载阶段分五步：加载、验证、准备、解释、初始化。
 *
 * @author Shanks
 * @date 2019-05-10
 */
public class ClassTest {

    @Test
    void testClassForName() throws ClassNotFoundException {

        System.out.println(".......... 1 ..........");
        Class<?> test = Class.forName("com.demo.interview.clazz.ClassLoaderDemo");
        // 静态代码块
        // .......... Class.forName代码执行完成 ..........
        System.out.println(".......... Class.forName代码执行完成 ..........");

        String name = test.getName();
        // com.demo.interview.clazz.ClassLoaderDemo
        System.out.println(name);

        System.out.println(".......... 2 ..........");
        ClassLoader testClassLoader = test.getClassLoader();
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(testClassLoader);
    }

    @Test
    void testClassloader() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> cls = systemClassLoader.loadClass("com.demo.interview.clazz.ClassLoaderDemo");
        ClassLoaderDemo test = (ClassLoaderDemo) cls.newInstance();
        String name = test.getName();
        System.out.println(name);
    }

    @Test
    void instantiation() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        ClassLoaderDemo test = (ClassLoaderDemo) Class.forName("com.demo.interview.clazz.ClassLoaderDemo").newInstance();

        System.out.println("..........");

        ClassLoaderDemo test1 = new ClassLoaderDemo();
    }
}
