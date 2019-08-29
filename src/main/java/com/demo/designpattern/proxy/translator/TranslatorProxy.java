package com.demo.designpattern.proxy.translator;

/**
 * @author zhoukai
 * @date 2018-08-15
 */
public class TranslatorProxy implements Translatable {

    /**
     * 声明为接口类型
     */
    private Translatable target;

    /**
     * 调用时，传参目标对象实例
     *
     * @param target
     */
    public TranslatorProxy(Translatable target) {
        this.target = target;
    }

    @Override
    public void translate() {
        System.out.println("=====》代理开始");
        // 真正的类执行具体方法
        this.target.translate();
        System.out.println("=====》代理结束");
    }

    @Override
    public void englishToChinese() {
        System.out.println("----->代理开始");
        this.target.englishToChinese();
        System.out.println("----->代理结束");
    }
}
