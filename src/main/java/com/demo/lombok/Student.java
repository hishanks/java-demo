package com.demo.lombok;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Shanks
 * @date 2019-04-28
 */
@Data
@Builder
public class Student {

    private String name;
    private Integer age;
    private Long num;
    @Builder.Default
    private List<String> address;

}
