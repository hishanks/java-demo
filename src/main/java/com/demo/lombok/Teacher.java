package com.demo.lombok;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Shanks
 * @date 2019-04-28
 */
@Data
@Builder
public class Teacher {

    private String name;
    private List<String> address;
}
