package com.demo.datastructure.collections.intersection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shanks
 * @date 2019-04-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeAlpha {

    private Long id;
    private Integer type;
    private Double weight;
}
