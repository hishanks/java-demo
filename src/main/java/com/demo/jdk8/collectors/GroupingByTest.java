package com.demo.jdk8.collectors;

import com.alibaba.fastjson.JSON;
import com.demo.jdk8.collectors.model.Device;
import com.demo.jdk8.collectors.model.Salary;
import com.demo.jdk8.model.Person;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 目的：彻底理解Collectors.groupingBy的常用方式，包括对其重载方法中参数的作用的理解
 * 学习总结：要学会举一反三，善于思考
 * <p>
 * 这个比较重要且常用，多多复习理解。
 *
 * @author Shanks
 * @date 2019-04-15
 */
public class GroupingByTest {

    private static Person p1 = new Person("kevin", 25);
    private static Person p2 = new Person("andy", 30);
    private static Person p3 = new Person("tony", 25);
    private static Person p4 = new Person("kevin", 20);
    private static Person p5 = new Person("kevin", 20);
    private static List<Person> list = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5));

    /**
     * Collectors.groupingBy的基础、简单用法，classifier：分类器
     * 单参数方法：
     *  groupingBy(Function<? super T, ? extends K> classifier)
     * 重载方法：
     *  groupingBy(Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream)
     */
    @Test
    public void testGroupingBy() {
        System.out.println("=====> 初始集合list");
        System.out.println(list.toString());

        System.out.println("=====> 根据name进行分组");
        Map<String, List<Person>> groupByName = list.stream()
                .collect(Collectors.groupingBy(Person::getName));
        System.out.println(JSON.toJSONString(groupByName));

        System.out.println("=====> 根据name分组，并且统计每组元素个数");
        Map<String, Long> collect = list.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));
        System.out.println(JSON.toJSONString(collect));

        System.out.println("=====> 根据name分组，并且对分组的age求和");
        Map<String, Integer> groupByNameAndSumAge = list.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.summingInt(Person::getAge)));
        System.out.println(JSON.toJSONString(groupByNameAndSumAge));

        System.out.println("=====> 根据name进行分组，且对分组后的成员的age求平均值");
        Map<String, Double> doubleMap = list.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println(JSON.toJSONString(doubleMap));

        System.out.println("=====> summarizingInt");
        Map<String, IntSummaryStatistics> summaryStatisticsMap = list.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.summarizingInt(Person::getAge)));
        System.out.println(JSON.toJSONString(summaryStatisticsMap));
        IntSummaryStatistics tony = summaryStatisticsMap.get("tony");
        System.out.println("Average: " + tony.getAverage());
        System.out.println("Count: " + tony.getCount());
        System.out.println("Max: " + tony.getMax());
        System.out.println("Min: " + tony.getMin());
        System.out.println("Sum: " + tony.getSum());

        System.out.println("=====> 组合字段分组");
        Map<String, List<Person>> listMap = list.stream().collect(Collectors.groupingBy(person -> person.getName() + "-" + person.getAge()));
        System.out.println(JSON.toJSONString(listMap));
    }

    /**
     * 使用Collectors.groupingBy参数最多的重载方法，完成业务需要的数据结构
     * Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier,
     * Supplier<M> mapFactory,
     * Collector<? super T, A, D> downstream)
     * 1.Function<? super T, ? extends K> classifier， 顾名思义，分组按照什么来进行分类
     * 2.Supplier<M> mapFactory，含义很明显了，这个参数是Map类型的数据结构，因为分组最后存储数据结构肯定是Map结构的嘛
     * 3.Collector<? super T, A, D> downstream，也是一个收集器，指按照第一个参数分类后，对应的分类的结果如何收集
     */
    @Test
    void testGroupingByOverload() {

        Device d1 = new Device(2L, "box1");
        Device d2 = new Device(2L, "box2");
        Device d3 = new Device(2L, "box3");
        Device d4 = new Device(1L, "box1");
        Device d5 = new Device(1L, "box2");
        List<Device> devices = new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5));

        System.out.println("---------- Map ----------");
        Map<Long, Long> map = devices.stream().collect(
                Collectors.groupingBy(
                        Device::getPhotographerId,
                        Collectors.counting()
                )
        );
        System.out.println(JSON.toJSONString(map));

        /*
         * 在双参数的方法中，实际上调用的是三参数的方法：return groupingBy(classifier, HashMap::new, downstream);
         * 可以看到，调用的方法中，其中HashMap::new是默认值，如果需要的话，可以设置为需要的Supplier<M> mapFactory
         */
        System.out.println("---------- HashMap ----------");
        HashMap<Long, String> devicesHashMap = devices.stream().collect(
                Collectors.groupingBy(
                        // 按照什么进行分组
                        Device::getPhotographerId,
                        // 分组后用什么容器保存，默认是HashMap，也可以是其他Map的实现类
                        HashMap::new,
                        // 意思是：根据photographerId分组后，每组后面对应的数据为符合该组对应数据的所有code以逗号拼接起来
                        Collectors.mapping(Device::getCode, Collectors.joining(", "))
                )
        );
        // {1:"box1, box2",2:"box1, box2, box3"}
        System.out.println(JSON.toJSONString(devicesHashMap));

        System.out.println("---------- LinkedHashMap ----------");
        LinkedHashMap<Long, String> devicesLinkedHashMap = devices.stream().collect(
                Collectors.groupingBy(
                        Device::getPhotographerId,
                        LinkedHashMap::new,
                        Collectors.mapping(Device::getCode, Collectors.joining(", "))

                )
        );
        // {2:"box1, box2, box3",1:"box1, box2"}
        System.out.println(JSON.toJSONString(devicesLinkedHashMap));

        System.out.println("---------- TreeMapTest ----------");
        TreeMap<Long, String> devicesTreeMap = devices.stream()
                .collect(
                        Collectors.groupingBy(
                                Device::getPhotographerId,
                                TreeMap::new,
                                Collectors.mapping(Device::getCode, Collectors.joining(", "))
                        )
                );
        System.out.println(JSON.toJSONString(devicesTreeMap));

        System.out.println("---------- Map & Collectors.mapping ----------");
        Map<Long, String> devicesDefaultMap = devices.stream().collect(
                Collectors.groupingBy(
                        Device::getPhotographerId,
                        Collectors.mapping(Device::getCode, Collectors.joining(", "))

                )
        );
        System.out.println(JSON.toJSONString(devicesDefaultMap));

        System.out.println("---------- Collectors.toList() ----------");
        HashMap<Long, List<Device>> collect = devices.stream()
                .collect(
                        Collectors.groupingBy(
                                Device::getPhotographerId,
                                HashMap::new,
                                Collectors.toList()
                        )
                );
        System.out.println(JSON.toJSONString(collect));
    }

    @Test
    void test() {
        Salary s1 = Salary.builder().name("a").deptId(1).money(new BigDecimal(100)).build();
        Salary s2 = Salary.builder().name("b").deptId(1).money(new BigDecimal(200)).build();
        Salary s3 = Salary.builder().name("c").deptId(2).money(new BigDecimal(300)).build();
        Salary s4 = Salary.builder().name("d").deptId(2).money(new BigDecimal(400)).build();
        Salary s5 = Salary.builder().name("e").deptId(3).money(new BigDecimal(500)).build();
        List<Salary> salaryList = Lists.newArrayList(s1, s2, s3, s4, s5);
        Map<Integer, List<Salary>> collect = salaryList.stream().collect(Collectors.groupingBy(Salary::getDeptId, Collectors.toList()));
        Map<Integer, BigDecimal> map = Maps.newHashMap();
        collect.forEach((k, v) -> map.put(k, v.stream().map(Salary::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add)));
        System.out.println(map);
    }
}
