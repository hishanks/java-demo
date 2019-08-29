package com.demo.interview.clazz;

import lombok.Getter;
import lombok.Setter;

/**
 * Class.forName("X")会初始化该类的静态代码块和静态变量
 * 而ClassLoader.loadClass("X")不初始化
 *
 * @author Shanks
 * @date 2019-05-10
 */
@Getter
@Setter
public class ClassLoaderDemo {

    static String identification = "静态变量";

    static {
        System.out.println("静态代码块");
    }

    private String name;
    private Integer age;

    public ClassLoaderDemo() {
        System.out.println(identification);
    }
}
