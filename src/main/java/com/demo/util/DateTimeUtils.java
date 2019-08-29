package com.demo.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期时间工具类
 *
 * @author zhoukai
 * @date 2018-09-25
 */
public class DateTimeUtils {

    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = PatternEnum.DATE_PATTERN_LINE.formatter;
    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = PatternEnum.DATETIME_PATTERN_LINE.formatter;

    public DateTimeUtils() {
    }

    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public static String getCurrentDateString() {
        return LocalDate.now().format(DEFAULT_DATE_FORMATTER);
    }

    public static String getCurrentDateTimeString() {
        return LocalDateTime.now().format(DEFAULT_DATETIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String dateString, String pattern) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeString, String pattern) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(pattern));
    }

}

enum PatternEnum {

    /**
     * 短时间格式
     */
    DATE_PATTERN_LINE("yyyy-MM-dd"),
    DATE_PATTERN_SLASH("yyyy/MM/dd"),
    DATE_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd"),
    DATE_PATTERN_NONE("yyyyMMdd"),

    /**
     * 长时间格式
     */
    DATETIME_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"),
    DATETIME_PATTERN_SLASH("yyyy/MM/dd HH:mm:ss"),
    DATETIME_PATTERN_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss"),
    DATETIME_PATTERN_NONE("yyyyMMdd HH:mm:ss"),

    /**
     * 长时间格式（带毫秒）
     */
    DATETIME_PATTERN_WITH_MILSEC_LINE("yyyy-MM-dd HH:mm:ss.SSS"),
    DATETIME_PATTERN_WITH_MILSEC_SLASH("yyyy/MM/dd HH:mm:ss.SSS"),
    DATETIME_PATTERN_WITH_MILSEC_DOUBLE_SLASH("yyyy\\MM\\dd HH:mm:ss.SSS"),
    DATETIME_PATTERN_WITH_MILSEC_NONE("yyyyMMdd HH:mm:ss.SSS");

    public transient DateTimeFormatter formatter;

    PatternEnum(String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
    }
}
