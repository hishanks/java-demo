package com.demo.java.threadsafe;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 5. 【强制】 SimpleDateFormat 是线程不安全的类，一般不要定义为 static 变量，如果定义为 static，必须加锁，或者使用 DateUtils 工具类。
 * 正例： 注意线程安全，使用 DateUtils。亦推荐如下处理：（代码在下方）
 * 如果是 JDK8 的应用，可以使用 Instant 代替 Date，LocalDateTime 代替 Calendar， DateTimeFormatter 代替 SimpleDateFormat，官方给出的解释：simple beautiful strong immutable thread-safe。
 *
 * @author Shanks
 * @date 2019-02-28
 */
public class DateFormatThreadLocal {

    private static final ThreadLocal<DateFormat> DATE_FORMAT_THREAD_LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
    private static final ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Date convert(String source) throws ParseException {
        return DATE_FORMAT.get().parse(source);
    }
}
