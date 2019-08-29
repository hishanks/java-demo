package com.demo.jdk8.collectors.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shanks
 * @date 2019-04-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    private Long photographerId;
    private String code;
}
