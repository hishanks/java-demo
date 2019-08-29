package com.demo.jvm.classloader;

/**
 * 可以尝试改变，static变量、代码块以及非static代码块定义的先后顺序，再测试一下~
 * 你会发现，代码里的顺序不影响，唯一影响的就是static修饰的部分，是按照顺序加载的
 *
 * @author zhoukai
 * @date 2018/8/7
 */
public class Super {

    public static String supStaticVariable = "Super Static Variable";

    static {
        System.out.println("Super Class Static Code Block");
    }

    {
        System.out.println("Super Class Non Static Code Block");
    }

    public Super() {
        System.out.println("Super Constructor");
    }
}
