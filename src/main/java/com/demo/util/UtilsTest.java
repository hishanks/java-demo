package com.demo.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * 2019-04-19 20:18:43
 *
 * @author Shanks
 * @date 2018-09-25
 */
public class UtilsTest {

    @Test
    void testMd5Utils() {

        // 7341b5e7c789c1cb68870d50075f3294
        String md5 = Md5Utils.md5("shanks");
        System.out.println(md5);

        // 7341B5E7C789C1CB68870D50075F3294
        String md5UpperCase = Md5Utils.md5UpperCase("shanks");
        System.out.println(md5UpperCase);

        System.out.println(Md5Utils.nanoTimeMd5());
        System.out.println(Md5Utils.nanoTimeMd5UpperCase());

        System.out.println("-----");
        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());
    }

    @Test
    void testCalendar() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        /* yyyy-MM-dd 00:00:00 */
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = sdf.format(calendar.getTime());
        System.out.println(formatDate);
    }

    @Test
    void testDateTimeUtils() {
        // String
        System.out.println(DateTimeUtils.getCurrentDateString());
        System.out.println(DateTimeUtils.getCurrentDateTimeString());
        // LocalDate、LocalDateTime
        System.out.println(DateTimeUtils.getCurrentLocalDate());
        System.out.println(DateTimeUtils.getCurrentLocalDateTime());
    }

    @Test
    void testCommonUtils() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        /* 当前系统时间，完全符合 */
        System.out.println("----------> DateFormatUtils.format");
        String format = DateFormatUtils.format(new Date(), pattern);
        System.out.println(format);

        /* 比期望系统时间早了8小时 */
        System.out.println("----------> DateFormatUtils.formatUTC");
        String formatUTC = DateFormatUtils.formatUTC(new Date(), pattern);
        System.out.println(formatUTC);

        Date date1 = new Date();
        System.out.printf("date1: %s%n", date1);
        Date date2 = new Date();
        System.out.printf("date2: %s%n", date2);
        boolean sameDay = DateUtils.isSameDay(date1, date2);
        System.out.println(sameDay);
        System.out.println(DateUtils.isSameDay(new Date(), DateUtils.addDays(new Date(), 2)));
        Date addDays = DateUtils.addDays(new Date(), 2);
        System.out.println(DateFormatUtils.format(addDays, pattern));
    }

    @Test
    void jodaUtils() {

    }

    @Test
    void testStringDesensitizationUtils() {
        String id = "342201199103105613";
        String phone = "15257117674";

        System.out.println(DesensitizationUtils.idCardNo(id));
        System.out.println(DesensitizationUtils.phoneNo(phone));

        System.out.println(DesensitizationUtils.common(id));
        System.out.println(DesensitizationUtils.common(phone));
    }

    @Test
    void lambda() {
        double plus = OperationEnum.PLUS.apply(0.5, 1.5);
        System.out.println(plus);
        double minus = OperationEnum.MINUS.apply(2.5, 3.6);
        System.out.println(minus);
        double times = OperationEnum.TIMES.apply(3, 4);
        System.out.println(times);
        double divide = OperationEnum.DIVIDE.apply(1, 3);
        System.out.println(divide);

        String operator = OperationEnum.PLUS.toString();
        System.out.println(operator);

        // Infinity - 两个double类型的相除，被除数为0时，返回的结果是无穷大，即Infinity
        divide = OperationEnum.DIVIDE.apply(1, 0);
        System.out.println(divide);

        // java.lang.ArithmeticException: / by zero
        int value = 1 / 0;
        System.out.println(value);
    }

    @Test
    void testOperation() {
        String symbol = "+";
        OperationEnum operationEnum = Optional.ofNullable(OperationEnum.getBySymbol(symbol)).orElseThrow(NullPointerException::new);
        double v = operationEnum.apply(5, 10);
        System.out.println(v);

        symbol = "-";
        operationEnum = Optional.ofNullable(OperationEnum.getBySymbol(symbol)).orElseThrow(NullPointerException::new);
        v = operationEnum.apply(20, 30);
        System.out.println(v);
    }
}
