package com.demo.lombok.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Shanks
 * @date 2019-07-31
 */
@Getter
@Setter
@ToString
@Builder(builderMethodName = "userBuilder", buildMethodName = "create", builderClassName = "UserBuilderClass")
public class User implements Serializable {

    private static final long serialVersionUID = -2164523248250808526L;

    private String name;
    private Integer age;
    private Date birthday;
}
