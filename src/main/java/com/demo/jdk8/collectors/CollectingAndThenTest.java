package com.demo.jdk8.collectors;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Shanks
 * @date 2018-10-15
 */
public class CollectingAndThenTest {

    @Test
    void testCollectingAndThen() {

        UserOrder order0 = new UserOrder("年会", "张三", "box0001");
        UserOrder order1 = new UserOrder("婚庆", "张三", "box0002");
        UserOrder order2 = new UserOrder("旅游", "李四", "box0003");
        UserOrder order3 = new UserOrder("发布会", "张三", "box0002");
        UserOrder order4 = new UserOrder("旅游", "李四", "box0004");

        List<UserOrder> list = Lists.newArrayList(order0, order1, order2, order3, order4);

        System.out.println("=====> 只根据单一属性photographerName进行去重");
        // <R, A> R collect(Collector<? super T, A, R> collector);
        // collectingAndThen(Collector<T, A, R> downstream, Function<R, RR> finisher)
        // toCollection(Supplier<C> collectionFactory)
        ArrayList<UserOrder> distinctByName = list.stream()
                .collect(
                        Collectors.collectingAndThen(
                                // 自定义收集器，利用TreeSet去重
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(UserOrder::getPhotographerName))),
                                // 然后数据使用ArrayList接收
                                ArrayList::new
                        )
                );
        distinctByName.forEach(System.out::println);

        System.out.println("=====> 同时根据photographerName和deviceCode两个属性进行去重");
        ArrayList<UserOrder> distinctByNameAndCode = list.stream()
                .collect(
                        Collectors.collectingAndThen(
                                // 根据自定义组合字段进行收集且去重
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(userOrder -> userOrder.getPhotographerName() + userOrder.getDeviceCode()))),
                                ArrayList::new
                        )
                );
        distinctByNameAndCode.forEach(System.out::println);
    }
}
