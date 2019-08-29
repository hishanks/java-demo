package com.demo.designpattern.strategy;

/**
 * @author Shanks
 * @date 2019-05-17
 */
public class StrategyPatternTest {

    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println(context.executeStrategy(5, 10));

        context = new Context(new OperationSubstract());
        System.out.println(context.executeStrategy(5, 10));

        context = new Context(new OperationMultiply());
        System.out.println(context.executeStrategy(5, 10));
    }
}
