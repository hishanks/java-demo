package com.demo.jdk8.collectors;

import com.demo.jdk8.collectors.model.Dish;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhoukai
 * @date 2018-10-14
 */
public class SummarizingIntTest {

    @Test
    void testSummarizingInt() {

        Dish chicken = new Dish("chicken", 50);
        Dish beef = new Dish("beef", 90);
        Dish pork = new Dish("pork", 60);

        List<Dish> list = new ArrayList<>();
        list.add(chicken);
        list.add(beef);
        list.add(pork);

        IntSummaryStatistics summaryStatistics = list.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(summaryStatistics.toString());

        double average = summaryStatistics.getAverage();
        System.out.println(average);
        long count = summaryStatistics.getCount();
        System.out.println(count);
        int max = summaryStatistics.getMax();
        System.out.println(max);
        int min = summaryStatistics.getMin();
        System.out.println(min);
        long sum = summaryStatistics.getSum();
        System.out.println(sum);
    }
}
