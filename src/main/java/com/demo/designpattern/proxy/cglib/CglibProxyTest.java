package com.demo.designpattern.proxy.cglib;

/**
 * 多理解几次就清楚了..
 *
 * @author Shanks
 * @date 2018-08-17
 */
public class CglibProxyTest {

    public static void main(String[] args) {

        CglibTarget target = new CglibTarget();
        CglibTarget proxy = (CglibTarget) new CglibProxyMethodInterceptor(target).getProxyInstance();
        // 执行代理对象的方法
        proxy.enterZoneState();
    }
}