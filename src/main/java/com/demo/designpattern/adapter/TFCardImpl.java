package com.demo.designpattern.adapter;

/**
 * @author Shanks
 * @date 2019-05-15
 */
public class TFCardImpl implements TFCard {

    @Override
    public String readTF() {
        return "TF card reade msg: Hello, TF card";
    }

    @Override
    public int writeTF(String msg) {
        System.out.println("TF card write a msg: " + msg);
        return 1;
    }
}
