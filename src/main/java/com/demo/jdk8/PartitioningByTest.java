package com.demo.jdk8;

import com.demo.jdk8.collectors.model.Artist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhoukai
 * @date 2018-10-19
 */
public class PartitioningByTest {

    @Test
    void testPartitioningBy() {

        Artist a = new Artist("张三", Boolean.TRUE, 1, null, null);
        Artist b = new Artist("李四", Boolean.TRUE, 1, null, null);
        Artist c = new Artist("王五", Boolean.FALSE, 3, null, null);
        Artist d = new Artist("赵六", Boolean.FALSE, 4, null, null);
        List<Artist> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);

        // 与groupingBy不同的是，partitioningBy只对可以转换成Boolean类型的值进行Predict
        System.out.println("=====>partitioningBy");
        Map<Boolean, List<Artist>> collect = list.stream().collect(Collectors.partitioningBy(Artist::getSolo));
        collect.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("=====>groupingBy");
        Map<Boolean, List<Artist>> booleanListMap = list.stream().collect(Collectors.groupingBy(Artist::getSolo));
        booleanListMap.forEach((k, v) -> System.out.println(k + ":" + v));

        // 像这面这种情况，partitioningBy就实现不了了，还得groupingBy来
        System.out.println("=====>groupingBy members");
        Map<Integer, List<Artist>> membersMap = list.stream().collect(Collectors.groupingBy(Artist::getMembers));
        membersMap.forEach((k, v) -> System.out.println(k + ":" + v));

    }
}
