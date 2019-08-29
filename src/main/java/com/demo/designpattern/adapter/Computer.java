package com.demo.designpattern.adapter;

/**
 * @author Shanks
 * @date 2019-05-15
 */
public interface Computer {
    /**
     * 读取SD卡
     *
     * @param sdCard SD
     * @return str
     */
    String readSD(SDCard sdCard);
}
