package com.demo.jdk8.collections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shanks
 * @date 2019-05-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private Integer age;
    private String name;
}
