package com.demo.jdk8.methodreference;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * 方法引用
 *
 * @author Shanks
 * @date 2019-03-15
 */
public class MethodReferenceTest {

    private static User user = new User("Tom", 3);
    private static User user2 = new User("Jerry", 2);
    private static List<User> list = new ArrayList<>(Arrays.asList(user, user2));

    /**
     * 类::实例方法名 - lambda方法体的具体实现方法的调用者和被调用者为lambda参数时，可以使用类名::示例方法名
     */
    @Test
    void testClassMethod() {
        /*
         * 解析如下：(x, y) -> x.equals(y)
         *  1.x和y参数类型是String
         *  2.lambda里面是String类型的x调用String的实例方法equals（反之亦然）
         * 再：list.stream().map(u -> u.getName())
         *  1.参数u的类型为User
         *  2.lambda方法体是User类型的u调用User的实例方法getName
         * 再：(a, b) -> a.compareTo(b) - 本质类似equals
         *  1.a和b参数类型是Integer
         *  2.lambda方法体是Integer类型的a调用Integer的实例方法compareTo（反之亦然）
         * 结论：以上均满足， lambda方法体的具体实现方法的调用者等于lambda参数
         */
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        BiPredicate<String, String> biPredicate1 = String::equals;
        System.out.println(biPredicate.test("abc", "ade"));
        System.out.println(biPredicate1.test("Hello", "Hello"));

        List<String> collect = list.stream().map(u -> u.getName()).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
        List<String> collect1 = list.stream().map(User::getName).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect1));

        Comparator<Integer> comparator = (a, b) -> a.compareTo(b);
        Comparator<Integer> comparator1 = Integer::compareTo;
        System.out.println(comparator.compare(1, 2));
        System.out.println(comparator1.compare(3, 3));
    }

    /**
     * 类名::静态方法名 - lambda具体实现的方法体中调用方法的参数列表与返回值类型要与函数式接口的抽象方法保持一致
     */
    @Test
    void testClassStaticMethod() {
        /*
         * 函数式接口Predicate的抽象方法：boolean test(T t);
         * Integer的静态方法：public static int compare(int x, int y)
         */
        Comparator<Integer> integerComparator = (a, b) -> Integer.compare(a, b);
        Comparator<Integer> integerComparator1 = Integer::compare;
        System.out.println(integerComparator.compare(1, 2));
        System.out.println(integerComparator1.compare(3, 3));

        /*
         * 函数式接口Predicate的抽象方法：boolean test(T t);
         * StringUtils的静态方法：public static boolean isNotBlank(CharSequence cs)
         */
        Predicate<String> predicate = x -> StringUtils.isNotBlank(x);
        Predicate<String> predicate1 = StringUtils::isNotBlank;

        /*
         * 函数式接口Predicate的抽象方法：boolean test(T t);
         * Objects的静态方法：public static boolean isNull(Object obj)
         */
        Predicate<String> predicate2 = y -> Objects.isNull(y);
        Predicate<String> predicate4 = Objects::isNull;
    }

    /**
     * 对象::方法名 - lambda具体实现的方法体中调用方法的参数列表与返回值类型要与函数式接口的抽象方法保持一致
     */
    @Test
    void testInstanceMethod() {

        Consumer<String> consumer = s -> System.out.println(s);
        Consumer<String> consumer1 = System.out::println;

        PrintStream printStream = System.out;
        Consumer<String> consumer2 = printStream::println;
    }

    /**
     * 构造器引用
     * 类名::new ，需要调用的构造器的参数列表与函数式接口的参数列表保持一致
     */
    @Test
    void test() {
        // 默认构造方法引用 - 无参有返回值，与Supplier格式一致
        Supplier<User> supplier = () -> new User();
        Supplier<User> supplier1 = User::new;
        System.out.println(supplier1.get());

        // 有参构造方法引用 - 有参有返回值，与BiFunction格式一致
        BiFunction<String, Integer, User> biFunction = (name, age) -> new User(name, age);
        BiFunction<String, Integer, User> biFunction1 = User::new;
        User shanks = biFunction1.apply("Shanks", 30);
        System.out.println(JSON.toJSONString(shanks));

        // 单个参数构造器
        Function<Integer, User> function = integer -> new User(integer);
        Function<Integer, User> function1 = User::new;
        User user = function1.apply(10);
        System.out.println(JSON.toJSONString(user));
    }

    /**
     * 数组引用
     * type[]::new - 与构造器引用类似
     */
    @Test
    void testArray() {
        Function<Integer, String[]> function = integer -> new String[integer];
        System.out.println(function.apply(10).length);
        Function<Integer, String[]> function1 = String[]::new;
        System.out.println(function1.apply(20).length);
    }
}