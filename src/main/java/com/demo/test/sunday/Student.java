package com.demo.test.sunday;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Shanks
 * @date 2019-07-17
 */
@Getter
@Setter
@ToString
@Builder
public class Student {

    private Long id;
    private String name;
}