package com.demo.jdk8;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * 函数式接口Predicate<T>
 * Predicate<T>接口是一个函数式接口，它接受一个输入参数T，返回一个布尔类型
 * 该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与、或、非）
 * 该接口用于测试对象是true或false
 *
 * @author zhoukai
 * @date 2018/6/24
 */
class PredicateTest {

    @Test
    void testPredicate() {
        List<String> list = new ArrayList<>(Arrays.asList("oracle", "mysql", "redis", "mongodb"));

        Predicate<String> strContains = s -> s.contains("o");
        Predicate<String> strLength = s -> s.length() >= 5;

        System.out.println("=====>filter by strContains");
        list.stream().filter(strContains).forEach(System.out::println);

        System.out.println("=====>filter by strLength");
        list.stream().filter(strLength).forEach(System.out::println);

        System.out.println("=====>filter by strContains and strLength");
        list.stream().filter(strContains.and(strLength)).forEach(System.out::println);

        System.out.println("=====>-filter by strContains or strLength");
        list.stream().filter(strContains.or(strLength)).forEach(System.out::println);
    }

    @Test
    void testLambdaAndPredicate() {
        List<String> list = new ArrayList<>(Arrays.asList("oracle", "mysql", "redis", "mongodb"));
        Predicate<String> strContains = s -> s.contains("o");
        Predicate<String> strLength = s -> s.length() >= 5;
        // strContains是个Predicate
        filter(list, strContains);

        System.out.println("利用or搭配其他的Predicate");
        filter(list, strContains.or(strLength));

        System.out.println("-----filterAlso");
        Predicate<String> strNoContains = s -> !s.contains("l");
        filterAlso(list, strNoContains);

        System.out.println("-----filterOtherWay");
        // 其实没啥变化，本质还是一样的，写法差异了一点点而已
        filterOtherWay(6, v -> v > 5);
        // v -> v > 7本质上就是要传入的Predicate类型的参数
        Predicate<Integer> x = v -> v > 7;
        filterOtherWay(6, x);
    }

    /**
     * 自定义的filter
     *
     * @param list      待过滤的list
     * @param predicate predicate
     */
    private static void filter(List<String> list, Predicate<String> predicate) {
        list.stream().filter(predicate).forEach(System.out::println);
    }

    /**
     * 按照规则，会给predicate传递如下参数，参数定义外部显示声明为：Predicate<String> strNoContains = s -> !s.contains("l");
     * 那么，在执行方法体中的predicate.add操作时，add方法参数传入的表达式也必须为与strNoContains同类型的
     * 所以方法体中，写法为predicate.and(p1 -> !p1.contains("db"))理解上不仅不存在障碍（因为就得那么写啊）
     * 然后，组合的predicate再调用test方法，以上！
     * 友情提示：泛型加上会更好
     *
     * @param list      待过滤的list
     * @param predicate 参数格式为strNoContains：Predicate<String> strNoContains = s -> !s.contains("l");
     */
    private static void filterAlso(List<String> list, Predicate<String> predicate) {
        list.stream().filter(p -> predicate.and(p1 -> !p1.contains("db")).test(p)).forEach(System.out::println);
    }

    /**
     * predicate.add(这里面也是个predicate).model(这里是需要被断言的数据)
     * predicate.add(anotherPredicate).model(t)返回值是个boolean
     *
     * @param value     待过滤的int类型参数
     * @param predicate 注意这里传的参数，也是对int做判断的Predicate
     */
    private static void filterOtherWay(int value, Predicate<Integer> predicate) {
        int len = 8;
        if (predicate.and(v -> v < len).test(value)) {
            System.out.println("yes! and value = " + value);
        } else {
            System.out.println("no result");
        }
    }

    @Test
    void testSth() {
        Predicate<String> isEmpty = String::isEmpty;
        // 哈哈~，这里的negate()是否定的意思，就是反义词
        Predicate<String> isNotEmpty = isEmpty.negate();

        List<String> list = Arrays.asList("x", "y", "z");
        System.out.println("x: " + "x".hashCode());
        System.out.println("y: " + "y".hashCode());
        System.out.println("z: " + "z".hashCode());
        Predicate<String> predicate = p -> p.hashCode() >= "y".hashCode();
        Function<String, String> function = p -> p + "***";
        final List<String> collect = list.stream().filter(predicate).map(function).collect(toList());
        collect.forEach(System.out::println);
    }

    @Test
    void testTest() {

        Predicate<Integer> predicate = x -> x >= 5;
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(6);
        list.add(9);
        List<Integer> collect = list.stream().filter(predicate).collect(toList());
        System.out.println(collect);

        System.out.println("-----> anyMatch");
        boolean anyMatch = list.stream().anyMatch(e -> e <= 6);
        System.out.println(anyMatch);

        System.out.println("-----> noneMatch");
        boolean noneMatch = list.stream().noneMatch(e -> e <= 6);
        System.out.println(noneMatch);

        System.out.println("-----> allMatch");
        boolean allMatch = list.stream().allMatch(e -> e <= 6);
        System.out.println(allMatch);
    }

}
