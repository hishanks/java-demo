package com.demo.designpattern.proxy.jdk;

/**
 * @author Shanks
 * @date 2019-04-29
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello(String name, int age) {
        System.out.printf("WelcomeBack~ name: %s, age: %d%n", name, age);
    }
}