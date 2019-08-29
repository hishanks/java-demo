package com.demo.designpattern.strategy;

/**
 * @author Shanks
 * @date 2019-05-17
 */
public class OperationMultiply implements Strategy {

    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
