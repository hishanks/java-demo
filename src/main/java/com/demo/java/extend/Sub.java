package com.demo.java.extend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Shanks
 * @date 2019-07-28
 */
@Getter
@Setter
@ToString(callSuper = true)
public class Sub extends Super {

    private Integer code;
    private String desc;
}