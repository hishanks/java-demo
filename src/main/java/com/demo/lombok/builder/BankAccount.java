package com.demo.lombok.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 了解下@Builder.Default的用法，即设置默认值，如果不赋值，则使用初始化值，如果赋值，则覆盖初始化值
 *
 * @author Shanks
 * @date 2019-07-31
 */
@Getter
@Setter
@ToString
@Builder
public class BankAccount implements Serializable {

    private static final long serialVersionUID = -7764541507093849770L;

    private String name;
    private Integer age;
    @Builder.Default
    private List<String> cards = new ArrayList<>(Arrays.asList("001", "002"));
    private List<Integer> cardTypes = new ArrayList<>(Arrays.asList(1, 2, 3));
}