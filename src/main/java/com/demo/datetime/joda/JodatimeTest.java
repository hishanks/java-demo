package com.demo.datetime.joda;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

/**
 * @author Lenovo
 * @date 2018-10-20
 */
public class JodatimeTest {

    @Test
    void testJodatime() {

        DateTime dateTime = new DateTime();
        System.out.println(dateTime);
    }
}
