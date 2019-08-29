package com.demo.designpattern.adapter;

/**
 * @author Shanks
 * @date 2019-05-15
 */
public class ThinkPadComputer implements Computer {

    @Override
    public String readSD(SDCard sdCard) {
        if (sdCard == null) {
            throw new NullPointerException("sd card null");
        }
        return sdCard.readSD();
    }
}
