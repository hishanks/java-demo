package com.demo.designpattern.proxy.translator;

import org.junit.jupiter.api.Test;

/**
 * 静态代理
 * 优点：可以做到在不修改目标对象的功能前提下对目标功能扩展
 * 缺点：因为代理对象需要与目标对象实现一样的接口，
 * 所以会有很多代理类，导致类太多，同时，一旦接口增加方法，目标对象与代理对象都要维护
 *
 * @author zhoukai
 * @date 2018-08-15
 */
public class TranslatorStaticProxyTest {

    @Test
    void testStaticProxy() {
        // 目标对象实例
        Translator target = new Translator();
        // 代理对象，将目标对象传给代理对象，建立代理关系
        TranslatorProxy translatorProxy = new TranslatorProxy(target);
        // 执行的是代理的方法
        translatorProxy.translate();

        System.out.println("====");

        translatorProxy.englishToChinese();
    }
}
