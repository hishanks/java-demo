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
public class SummingIntTest {

    @Test
    void testSummingInt() {

        Dish chicken = new Dish("chicken", 50);
        Dish beef = new Dish("beef", 90);
        Dish pork = new Dish("pork", 60);

        List<Dish> list = new ArrayList<>();
        list.add(chicken);
        list.add(beef);
        list.add(pork);

        Integer caloriesSum = list.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(caloriesSum);

        // 再简化的写法为⤵
        int sum = list.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(sum);
    }
}
