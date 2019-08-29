package com.demo.designpattern.adapter;

/**
 * @author Shanks
 * @date 2019-05-15
 */
public class SDCardImpl implements SDCard {

    @Override
    public String readSD() {
        return "SDCard read a msg: Hello, SD card";
    }

    @Override
    public int writeSD(String msg) {
        System.out.println("SDCard write msg: " + msg);
        return 1;
    }
}
