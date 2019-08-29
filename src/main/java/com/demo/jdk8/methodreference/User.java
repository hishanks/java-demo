package com.demo.jdk8.methodreference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Shanks
 * @date 2019-03-15
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;
    private Integer age;

    public User(Integer age) {
        this.age = age;
    }
}