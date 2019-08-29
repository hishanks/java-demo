package com.demo.jdk8.collectors.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Shanks
 * @date 2019-05-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Salary {

    private String name;
    private Integer deptId;
    private BigDecimal money;
}
