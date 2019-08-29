package com.demo.designpattern.proxy.translator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 实现InvocationHandler接口
 * 重写invoke方法
 *
 * @author zhoukai
 * @date 2018/8/15
 */
public class DynamicProxy implements InvocationHandler {

    /**
     * 维护一个目标对象
     */
    private Object target;

    public DynamicProxy(Object object) {
        this.target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始");
        System.out.println("--> method");
        System.out.println(method.getName());
        System.out.println(args == null ? "-----" : args[0].toString());
        final Object invoke = method.invoke(target, args);
        System.out.println("结束");
        return invoke;
    }
}
