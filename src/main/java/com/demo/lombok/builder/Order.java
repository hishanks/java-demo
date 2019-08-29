package com.demo.lombok.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 注意：@Builder和@NoArgsConstructor不能搭配使用，必须要带上@AllArgsConstructor
 *
 * @author Shanks
 * @date 2019-07-31
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String code;
    private BigDecimal amount;
}