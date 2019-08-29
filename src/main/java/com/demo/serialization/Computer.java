package com.demo.serialization;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Shanks
 * @date 2019-07-08
 */
@Getter
@Setter
@ToString
@Builder
public class Computer implements Serializable {

    private static final long serialVersionUID = 392023451394535679L;
    private String name;
    private String brand;
    private Integer price;
}
