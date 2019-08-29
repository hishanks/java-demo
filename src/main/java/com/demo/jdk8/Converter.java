package com.demo.jdk8;

/**
 * 参照函数式接口Function，自己撸个函数式接口
 * 通过@FunctionalInterface注解声明这是个函数式接口
 * 接口泛型<T, R>，其中抽象方法：
 * R convert(T t);
 * 传入T类型参数，返回R类型
 *
 * @author zhoukai
 * @date 2018/8/7
 */
@FunctionalInterface
public interface Converter<T, R> {

    /**
     * 传入F类型参数，返回T类型结果
     *
     * @param t T
     * @return R
     */
    R convert(T t);
}
