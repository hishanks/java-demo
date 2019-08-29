package com.demo.jdk8.collectors;

import com.demo.jdk8.collectors.model.Dish;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zhoukai
 * @date 2018-10-14
 */
public class MaxByTest {

    @Test
    void testMaxBy() {

        Dish chicken = new Dish("chicken", 50);
        Dish beef = new Dish("beef", 90);
        Dish pork = new Dish("pork", 60);

        List<Dish> list = new ArrayList<>();
        list.add(chicken);
        list.add(beef);
        list.add(pork);

        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> collect = list.stream().collect(Collectors.maxBy(dishComparator));
        Dish dish = collect.get();
        System.out.println(dish.toString());
    }
}
