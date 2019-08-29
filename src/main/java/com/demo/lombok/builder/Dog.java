package com.demo.lombok.builder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 反编译之后只有一个默认空构造方法
 * public Dog() {}
 *
 * @author Shanks
 * @date 2019-07-31
 */
@Getter
@Setter
@ToString
public class Dog {

    private String name;
    private Integer age;
}