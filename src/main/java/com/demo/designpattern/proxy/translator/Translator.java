package com.demo.designpattern.proxy.translator;

/**
 * @author zhoukai
 * @date 2018/8/15
 */
public class Translator implements Translatable {

    @Override
    public void translate() {
        System.out.println("真正的类--翻译");
    }

    @Override
    public void englishToChinese() {
        System.out.println("英语翻译为中文");
    }

    void translatorOwner() {
        System.out.println("Translator自有的方法");
    }
}
