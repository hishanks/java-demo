package com.demo.designpattern.adapter;

/**
 * SD卡功能接口：读，写
 *
 * @author Shanks
 * @date 2019-05-15
 */
public interface SDCard {

    /**
     * 读取SD卡方法
     *
     * @return str
     */
    String readSD();

    /**
     * 写入SD卡功能
     *
     * @param msg msg
     * @return int
     */
    int writeSD(String msg);
}
