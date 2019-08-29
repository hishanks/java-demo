package com.demo.designpattern.proxy.jdk;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * JDK动态代理之使用Proxy.newProxyInstance构造代理实例
 *
 * @author Shanks
 * @date 2019-04-29
 */
public class JdkProxyTest {

    public static void main(String[] args) {

        // 目标对象
        HelloService hello = new HelloServiceImpl();

        // JDK提供Proxy类的静态方法newProxyInstance来生成一个代理对象（proxy）
        HelloService proxy = (HelloService) Proxy.newProxyInstance(
                // 类加载器
                hello.getClass().getClassLoader(),
                // 绑定的接口，也就是把代理对象绑定到哪些接口上，可以是多个
                hello.getClass().getInterfaces(),
                // 绑定代理对象逻辑实现
                new JdkProxyInvocationHandler(hello));

        // 调用对象代理方法
        proxy.sayHello("Shanks", 18);
    }
}