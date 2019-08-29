package com.demo.designpattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * JDK动态代理：定义MyInvocationHandler，实现InvocationHandler接口，重写invoke方法
 *
 * @author Shanks
 * @date 2019-04-29
 */
public class JdkProxyInvocationHandler implements InvocationHandler {

    /**
     * 目标对象
     */
    private Object target;

    public JdkProxyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 处理代理对象方法逻辑
     *
     * @param proxy  代理对象
     * @param method 当前方法
     * @param args   运行参数
     * @return 方法调用结果
     * @throws Throwable 异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("打印参数......");
        System.out.println(Arrays.toString(args));
        System.out.println("调用原有对象的事件方法......");
        Object result = method.invoke(target, args);
        System.out.println("调用完毕......");
        return result;
    }
}