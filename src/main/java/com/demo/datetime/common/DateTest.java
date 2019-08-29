package com.demo.datetime.common;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 一般最常见的对Date进行格式化，即为SimpleDateFormat
 * 再进阶一点，可以用到commons包的工具类
 *
 * @author zhoukai
 * @date 2018-10-08
 */
public class DateTest {

    @Test
    void testUtilDate() {
        final Date date = new Date();
        final long time = date.getTime();
        // Mon Oct 08 23:48:06 CST 2018
        System.out.println(date);
        // 1539013686809 - 等同于System.currentTimeMillis()
        System.out.println(time);
        System.out.println(System.currentTimeMillis());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        final String formatDate = sdf.format(date);
        System.out.println(formatDate);
    }
}
