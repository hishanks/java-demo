package com.demo.designpattern.adapter;

/**
 * @author Shanks
 * @date 2019-05-15
 */
public interface TFCard {

    String readTF();
    int writeTF(String msg);
}
