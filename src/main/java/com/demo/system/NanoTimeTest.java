package com.demo.system;

import org.junit.jupiter.api.Test;

/**
 * @author zhoukai
 * @date 2018-10-08
 */
public class NanoTimeTest {

    @Test
    void testNanoTime() throws InterruptedException {

        // 1003760810
        // 1002004440
        // 1000506943
        // 1005108960
        // 1004575606
        for (int i = 0; i < 5; i++) {
            long start = System.nanoTime();
            Thread.sleep(1000);
            long end = System.nanoTime();
            System.out.println(end - start);
        }
    }

    @Test
    void testCurrentTimeMillis() throws InterruptedException {

        // 1003
        // 1005
        // 1003
        // 1005
        // 1002
        for (int i = 0; i < 5; i++) {
            long start = System.currentTimeMillis();
            Thread.sleep(1000);
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }

    @Test
    void testDiffNanoTimeAndCurrentTimeMillis() {

        // 时、分、秒、微秒、纳秒

        // 11692541042676 - 14位
        long nanoTime = System.nanoTime();
        System.out.println(nanoTime);

        // 1539012259153 - 13位
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
    }
}
