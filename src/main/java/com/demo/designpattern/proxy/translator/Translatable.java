package com.demo.designpattern.proxy.translator;

/**
 * 如果是形容能力的接口名称，取对应的形容词为接口名(通常是–able的形式)
 *
 * @author zhoukai
 * @date 2018/8/15
 */
public interface Translatable {

    /**
     * 定义接口方法
     */
    void translate();

    /**
     * 接口方法二
     */
    void englishToChinese();
}
