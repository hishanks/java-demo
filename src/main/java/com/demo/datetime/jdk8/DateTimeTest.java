package com.demo.datetime.jdk8;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author zhoukai
 * @date 2018/8/7
 */
class DateTimeTest {

    @Test
    void testClock() {

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock);

        final long millis = clock.millis();
        System.out.println(millis);

        Instant instant = clock.instant();
        System.out.println(instant);

        final Date date = Date.from(instant);
        System.out.println(date);
    }

    @Test
    void testLocalDate() {
        LocalDate nowDate = LocalDate.now();
        // 2018-10-01
        System.out.println(nowDate);
        int year = nowDate.getYear();
        int month = nowDate.getMonth().getValue();
        int monthValue = nowDate.getMonthValue();
        int dayOfMonth = nowDate.getDayOfMonth();

        System.out.println(year);
        System.out.println(month);
        System.out.println(monthValue);
        System.out.println(dayOfMonth);
    }

    @Test
    void testLocalTime() {
        System.out.println(LocalTime.now());
    }

    @Test
    void testLocalDateTime() {
        final LocalDateTime localDateTime = LocalDateTime.now();
        // 2018-10-01T12:45:56.238
        System.out.println(localDateTime);
    }

    @Test
    void testDateTimeFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        String formattedDate = date1.format(formatter);
        System.out.println(formattedDate);

        LocalDate date2 = LocalDate.parse(formattedDate, formatter);
        System.out.println(date2);
    }
}
