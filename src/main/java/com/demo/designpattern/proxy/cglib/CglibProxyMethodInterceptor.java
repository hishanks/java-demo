package com.demo.designpattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 实现MethodInterceptor接口，重写intercept方法-->生成方法拦截器
 * 利用Enhancer工具，自定义getProxyInstance方法
 * <p>
 * JDK动态代理: 只能代理实现了接口的类，没有实现接口的类不能实现JDK动态代理
 * Cglib代理：针对类来实现代理，对指定目标，产生一个子类，通过方法拦截技术拦截所有父类方法的调用
 *
 * @author Shanks
 * @date 2018-08-17
 */
public class CglibProxyMethodInterceptor implements MethodInterceptor {

    private Object target;

    public CglibProxyMethodInterceptor(Object target) {
        this.target = target;
    }

    /**
     * 在enhance.create()创建完代理类对象之后
     * 在代理类调用方法中会被我们实现的方法拦截器CglibProxyMethodInterceptor拦截
     * 如果被代理类被final修饰，则被代理类不能被继承，即不能被代理
     * 同样，如果被代理类存在final修饰的方法，那么该方法不能被代理
     *
     * @return 代理实例
     */
    public Object getProxyInstance() {
        // 工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        // 创建子类（代理对象）
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib代理-->执行开始");
        final Object invoke = method.invoke(target, args);
        System.out.println("Cglib代理-->执行结束");
        return invoke;
    }
}