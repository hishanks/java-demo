package com.demo.commons.lang3.datetimeutils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhoukai
 * @date 2018-09-18
 */
public class DateTimeUtilsTest {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @Test
    void testDateFormatUtils() {

        System.out.println(new Date());
        String format = DateFormatUtils.format(new Date(), DEFAULT_PATTERN);
        System.out.println(format);
    }

    @Test
    void testDateUtils() throws ParseException {

        Calendar toCalendar = DateUtils.toCalendar(new Date());
        Date date = toCalendar.getTime();
        System.out.println(date);

        System.out.println("--> DateUtils.parseDate(String str, String... parsePatterns)");
        String dateStr = DateFormatUtils.format(new Date(), DEFAULT_PATTERN);
        System.out.println(dateStr);
        Date parseDate = DateUtils.parseDate(dateStr, DEFAULT_PATTERN);
        System.out.println(parseDate);
    }

    @Test
    void test() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = localDateTime.format(formatter);
        System.out.println(format);
    }
}