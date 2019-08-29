package com.demo.guava.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.jupiter.api.Test;

/**
 * @author Shanks
 * @date 2019-05-22
 */
public class BloomFilterTest {

    /**
     * -Xms64m -Xmx64m -XX:+PrintHeapAtGC
     */
    @Test
    public void guavaTest() {
        long star = System.currentTimeMillis();
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 10000000, 0.01);
        for (int i = 0; i < 10000000; i++) {
            filter.put(i);
        }
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        System.out.println(filter.mightContain(3));
        System.out.println(filter.mightContain(10000000));
        long end = System.currentTimeMillis();
        System.out.println("执行时间（ms）：" + (end - star));
    }

    @Test
    public void bloomFilterTest() {
        long star = System.currentTimeMillis();
        BloomFilters bloomFilters = new BloomFilters(10000000);
        for (int i = 0; i < 10000000; i++) {
            bloomFilters.add(i + "");
        }
        System.out.println(bloomFilters.check(1 + ""));
        System.out.println(bloomFilters.check(2 + ""));
        System.out.println(bloomFilters.check(3 + ""));
        System.out.println(bloomFilters.check(999999 + ""));
        System.out.println(bloomFilters.check(400230340 + ""));
        long end = System.currentTimeMillis();
        System.out.println("bloomFilterTest执行时间（ms）：" + (end - star));
    }
}
