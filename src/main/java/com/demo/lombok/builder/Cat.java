package com.demo.lombok.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 使用@Builder，没有默认无参构造方法，但是有全参构造方法
 *
 * @author Shanks
 * @date 2019-07-31
 */
@Getter
@Setter
@ToString
@Builder
public class Cat {

    private String name;
    private Integer age;
}