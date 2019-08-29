package com.demo.jdk8.collectors;

import com.demo.jdk8.collectors.model.Dish;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhoukai
 * @date 2018-10-14
 */
public class AveragingIntTest {

    @Test
    void testAveragingInt() {

        Dish chicken = new Dish("chicken", 50);
        Dish beef = new Dish("beef", 90);
        Dish pork = new Dish("pork", 60);

        List<Dish> list = new ArrayList<>();
        list.add(chicken);
        list.add(beef);
        list.add(pork);

        // 求平均值的int类型
        int caloriesAveraging = list.stream().collect(Collectors.averagingInt(Dish::getCalories)).intValue();
        System.out.println(caloriesAveraging);

        // 求平均值的double类型
        double doubleValue = list.stream().collect(Collectors.averagingDouble(Dish::getCalories));
        System.out.println(doubleValue);
    }
}
