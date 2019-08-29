package com.demo.jdk8;

import com.alibaba.fastjson.JSONArray;
import com.demo.jdk8.model.User;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * reduce、以及Collectors.reducing()
 *
 * @author zhoukai
 * @date 2018/8/10
 */
public class ReduceTest {

    @Test
    void testSb() {

        List<User> list = Lists.newArrayList();

        User user1 = new User("1", 10);
        User user2 = new User("2", 20);
        User user3 = new User("1", 30);

        list.add(user1);
        list.add(user2);
        list.add(user3);
        // [{"id":"1","value":10},{"id":"2","value":20},{"id":"1","value":30}]
        System.out.println(JSONArray.toJSON(list).toString());

        /* 以下写法都是API里的工厂方法，都是可以被优化的
         * 会被提示：先利用map特性，然后再reduce
         * */
        list.stream().collect(Collectors.reducing(0, User::getValue, (x, y) -> x + y));
        list.stream().collect(Collectors.reducing(0, User::getValue, Integer::sum));

        /* 先利用map将User对象映射为其value属性值，然后利用reduce进行汇总
         * reduce((x, y) -> x + y)这个更明显了，可以优化为：
         * reduce(Integer::sum)
         * */
        Integer sum = list.stream().map(User::getValue).reduce((x, y) -> x + y).orElse(0);
        System.out.println(sum.intValue());
        // 给一个初始值0，保证结果不为空
        Integer sum1 = list.stream().map(User::getValue).reduce(0, (x, y) -> x + y);
        System.out.println(sum1);

        /*
         * reduce时，利用方法引用的方式简写代码
         * 利用Optional进行null时给默认值
         * */
        Integer integer = list.stream().map(User::getValue).reduce(Integer::sum).orElse(0);
        System.out.println(integer);

        /* 以下为利用mapToInt方法进行代码最简化！！
         * mapToInt联合方法引用，打造最简化代码，且可读性也很高
         * */
        System.out.println("=====>mapToInt登场..");
        /*
         * 查看mapToInt的参数类型为ToIntFunction，然后查看此函数式接口抽象方法为int applyAsInt(T value);
         * 应该算是Function的子集了吧，R apply(T t)
         * */
        int test = list.stream().mapToInt(User::getValue).sum();
        System.out.println(test);
        int sumUsingMap2Int = list.stream().mapToInt(User::getValue).sum();
        System.out.println(sumUsingMap2Int);
    }

    @Test
    void testReduce() {
        List<Integer> costBeforeTax = new ArrayList<>(Arrays.asList(100, 200, 300));
        Double costSum = costBeforeTax.stream()
                .map(cost -> cost + 0.1 * cost)
                .reduce((x, y) -> x + y)
                .orElse(0.0);
        System.out.println(costSum);

        System.out.println("----------> accumulator和element");
        Double reduce = costBeforeTax.stream()
                .map(cost -> cost + 0.1 * cost)
                .reduce(0.0, (accumulator, element) -> accumulator + element);
        System.out.println(reduce);
    }
}
