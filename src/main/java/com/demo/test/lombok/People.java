package com.demo.test.lombok;

import lombok.*;

/**
 * @author Shanks
 * @date 2019-05-16
 */
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class People {

    private Integer age;
    private String name;
}
