package com.demo.jdk8.datetime;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 如果是JDK8的应用，可以使用Instant代替Date，LocalDateTime代替 Calendar，DateTimeFormatter代替SimpleDateFormat
 * 官方给出的解释： simple beautiful strong immutable thread-safe
 *
 * @author Shanks
 * @date 2018-11-23
 */
public class DateTimeTest {

    @Test
    void testDateUtils() throws ParseException {

        Date date = DateUtils.addDays(new Date(), 2);
        String format = DateFormatUtils.format(date, "yyyy/MM/dd HH:mm:ss");
        System.out.println(format);

        /*
         * DateUtils.parseDate(String str, String... parsePatterns)，注意可变长参数：String... parsePatterns
         * 例如：DateUtils.parseDate("2019-02-04 13:14:00", "yyyy-MM-dd HH:mm:ss")，则时间格式要对应
         * 错误示例：
         *  1. DateUtils.parseDate("2019-02-04 13:14:00", "yyyy/MM/dd HH:mm:ss")
         *  2. DateUtils.parseDate("2019/02/04 13:14:00", "yyyy-MM-dd HH:mm:ss")
         * 正确案例：
         *  1. DateUtils.parseDate("2019-02-04 13:14:00", "yyyy-MM-dd HH:mm:ss")
         *  2. DateUtils.parseDate("2019/02/04 13:14:00", "yyyy/MM/dd HH:mm:ss")
         *  3. DateUtils.parseDate("2019-02-04 13:14:00", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss")
         */
        System.out.println("----- DateUtils.parseDate -----");
        Date parseDate = DateUtils.parseDate("2019-02-04 13:14:00", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss");
        System.out.println("parseDate: " + parseDate);

        System.out.println("----- DateFormatUtils.format -----");
        String formatDate = DateFormatUtils.format(parseDate, "yyyy~MM~dd HH:mm:ss");
        System.out.println("formatDate: " + formatDate);

        Date parseDateNeedPatternMapping = DateUtils.parseDate("2019-02-04", "yyyy-MM-dd");
        System.out.println("parseDateNeedPatternMapping: " + parseDateNeedPatternMapping);

        String dateFormat = DateFormatUtils.format(parseDateNeedPatternMapping, "yyyy~MM~dd HH:mm:ss");
        String dateFormatDefaultTime = DateFormatUtils.format(parseDateNeedPatternMapping, "yyyy~MM~dd");
        System.out.println("dateFormat:" + dateFormat);
        System.out.println("dateFormatDefaultTime:" + dateFormatDefaultTime);
    }

    /**
     * Instant代替Date
     */
    @Test
    void testInstant() {

        Instant now = Instant.now();
        System.out.println(now);

        Date date = new Date();
        System.out.println(date);
    }

    /**
     * LocalDateTime代替 Calendar
     */
    @Test
    void testLocalDateTime() {

        System.out.println("-----> localDate");
        // 2018-11-23 14:12:54 必须是和后面的pattern格式一样，即：yyyy-MM-dd HH:mm:ss
        LocalDate localDate = LocalDate.parse("2018-11-23 14:09:24", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(localDate);

        System.out.println("-----> LocalDateTime");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        System.out.println("-----> Calendar & Date");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        System.out.println(calendar);
        System.out.println(date);
    }

    /**
     * DateTimeFormatter代替SimpleDateFormat
     */
    @Test
    void testDateTimeFormatter() {

        System.out.printf("-----> %s%n", LocalDateTime.now());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy--MM--dd HH:mm:ss");
        String format = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(format);

        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String format1 = dateTimeFormatter1.format(LocalDate.now());
        System.out.println(format1);

        System.out.println("-----> SimpleDateFormat");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));
    }

    @Test
    void testLocalDate() {
        LocalDate localDate = LocalDate.now();
        // 2019
        System.out.println(localDate.getYear());

        // MARCH
        System.out.println(localDate.getMonth());
        // 3
        System.out.println(localDate.getMonth().getValue());

        // SUNDAY
        System.out.println(localDate.getDayOfWeek());
        // 7
        System.out.println(localDate.getDayOfWeek().getValue());

        // 31
        System.out.println(localDate.getDayOfMonth());
        // 90
        System.out.println(localDate.getDayOfYear());

        // 31
        System.out.println(localDate.lengthOfMonth());
        // 365
        System.out.println(localDate.lengthOfYear());

        // false
        System.out.println(localDate.isLeapYear());

        System.out.println("---------- ChronoField ----------");
        int year = localDate.get(ChronoField.YEAR);
        System.out.println(year);
        int monthOfYear = localDate.get(ChronoField.MONTH_OF_YEAR);
        System.out.println(monthOfYear);
        int dayOfMonth = localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.println(dayOfMonth);
    }

    @Test
    void getMonthAndDays() {
        LocalDate now = LocalDate.now();
        int monthValue = now.getMonth().getValue();
        int lengthOfMonth = now.lengthOfMonth();
        System.out.println(monthValue);
        System.out.println(lengthOfMonth);

    }

    @Test
    void testLocalTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int hour = localDateTime.getHour();
        System.out.println(hour);
        int minute = localDateTime.getMinute();
        System.out.println(minute);
        int second = localDateTime.getSecond();
        System.out.println(second);
        int nano = localDateTime.getNano();
        System.out.println(nano);
    }

    /**
     * 可以创建两个LocalTime对象、两个LocalDateTime对象，或者两个Instant对象之间的duration
     * 由于LocalDateTime和Instant是为不同的目的而设计的，一个是为了便于人阅读使用， 另一个是为了便于机器处理， 所以你不能将二者混用。
     * 如果你试图在这两类对象之间创建duration，会触发一个DateTimeException异常。
     * 此外，由于Duration类主要用于以秒和纳秒衡量时间的长短，你不能仅向between方法传递一个LocalDate对象做参数。
     */
    @Test
    void testDuration() {
        LocalDateTime anotherLocalDateTime = LocalDateTime.of(2018, 3, 31, 12, 20, 44);
        Duration betweenLocalDate = Duration.between(LocalDateTime.now(), anotherLocalDateTime);
        // 间隔毫秒数
        long toMillis = betweenLocalDate.abs().toMillis();
        System.out.println(toMillis);

        // 间隔秒数
        long seconds = betweenLocalDate.abs().get(ChronoUnit.SECONDS);
        System.out.println(seconds);

        // 间隔纳秒数
        int nano = betweenLocalDate.abs().getNano();
        System.out.println(nano);

        long minutes = betweenLocalDate.abs().get(ChronoUnit.NANOS);
        System.out.println(minutes);

        // 仅支持SECONDS、NANOS
        // System.out.println(betweenLocalDate.abs().get(ChronoUnit.HOURS));
        // System.out.println(betweenLocalDate.abs().get(ChronoUnit.MONTHS));
        // System.out.println(betweenLocalDate.abs().get(ChronoUnit.MINUTES));
        System.out.println(betweenLocalDate.abs().get(ChronoUnit.SECONDS));
        // System.out.println(betweenLocalDate.abs().get(ChronoUnit.MILLIS));
        System.out.println(betweenLocalDate.abs().get(ChronoUnit.NANOS));
    }

    /**
     * 如果你需要以年、月或者日的方式对多个时间单位建模，可以使用Period类
     * 使用该类的工厂方法between，你可以使用得到两个LocalDate之间的时长
     */
    @Test
    void testPeriod() {
        LocalDate oneLocalDate = LocalDate.of(2014, 3, 8);
        LocalDate anotherLocalDate = LocalDate.of(2014, 3, 18);
        Period period = Period.between(oneLocalDate, anotherLocalDate);
        System.out.println(period);

        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(period.getUnits());
    }
}
