package com.demo.java.passbyvalue;

import org.junit.jupiter.api.Test;

/**
 * Java中只有值传递
 *
 * @author zhoukai
 * @date 2018/7/10
 */
class PassByValueTest {

    /**
     * 如何解释testByValue测试方法中的现象？
     */
    @Test
    void testPassByValue() {
        User user = new User("zhangsan", 25);
        System.out.println(user.toString());

        pass(user);
        System.out.println("=====>调用pass后打印");
        System.out.println(user.toString());

        System.out.println();
        passNew(user);
        System.out.println("=====>调用passNew后打印");
        System.out.println(user.toString());
    }

    private static void pass(User user) {
        user.setName("lisi");
        user.setAge(30);
        System.out.println("=====>pass方法内部打印");
        System.out.println(user.toString());
    }

    private static void passNew(User user) {
        user = new User();
        user.setName("wangwu");
        user.setAge(40);
        System.out.println("=====>passNew方法内部打印");
        System.out.println(user.toString());
    }

    /**
     * 如何解释testByBaseType方法的中出现的现象？
     */
    @Test
    void testByBaseType() {
        int value = 5;
        System.out.println("-----> value： " + value);
        change(value);
        System.out.println("-----> value： " + value);

        System.out.println("--------------------");

        System.out.println("-----> value:" + value);
        int result = add(value);
        System.out.println("-----> result: " + result);
        System.out.println("-----> add(): " + add(value));
        System.out.println("-----> value:" + value);
    }

    private static void change(int num) {
        num = num + 10;
        System.out.println("-----> change方法内部打印值");
        System.out.println(num);
    }

    private static int add(int num) {
        num = num + 2;
        return num;
    }

}
