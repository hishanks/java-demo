package com.demo.system;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author zhoukai
 * @date 2018-10-08
 */
public class CurrentTimeMillisTest {

    @Test
    void testTimeStamp() {

        /* 尝试传参数new Date().getTime()，被提示请使用System.currentTimeMillis()代替
         * 使用(new Date()).getTime()，较规范些
         */
        long time = (new Date()).getTime();
        // 1555343780815
        System.out.println(time);

        long currentTimeMillis = System.currentTimeMillis();
        // 1555343780815
        System.out.println(currentTimeMillis);

        Timestamp timestamp = new Timestamp(currentTimeMillis);
        // 2019-04-15 23:56:20.815
        System.out.println(timestamp);
    }
}
