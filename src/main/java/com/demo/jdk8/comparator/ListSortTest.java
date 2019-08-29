package com.demo.jdk8.comparator;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shanks
 * @date 2018-10-30
 */
public class ListSortTest {

    @Test
    void testListSort() {

        User user = new User(3, "Tom");
        User user1 = new User(1, "Jack");
        User user2 = new User(2, "Shanks");
        List<User> list = Lists.newArrayList(user, user1, user2);
        list.forEach(System.out::println);

        // 最简单的排序操作，局限性为：排序属性值不能null，否则报异常
        list.sort(Comparator.comparing(User::getId));
        list.forEach(System.out::println);
        list.sort(Comparator.comparing(User::getId).reversed());
        list.forEach(System.out::println);

        System.out.println("---------- 分割线线 ----------");

        /* 本方法上面部分为本例示例代码，下面是我自己琢磨出问题了，感觉没有理解，(⊙﹏⊙)b
         * int compareTo(Integer anotherInteger)方法，其返回值调用方法：
         *     return compare(this.value, anotherInteger.value);
         * int compare(int x, int y)，返回值是：
         *     return (x < y) ? -1 : ((x == y) ? 0 : 1);
         * 所以最后的结果就是：
         *     o2.getId().compareTo(o1.getId())需要搭配id为空的情况下，返回1，倒序null放最后
         *     o1.getId().compareTo(o2.getId())需要搭配id为空的情况下，返回-1，正序null放前面
         * 这里应该是我理解偏差的，感觉还蛮有意思的，找个时间得彻底理解下~
         */
        User user3 = new User(null, "Katakuri");
        User user4 = new User(4, "Zoro");
        User user5 = new User(null, "Luffy");
        ArrayList<User> users = Lists.newArrayList(user, user1, user2, user3, user4, user5);
        System.out.println("-----> users");
        System.out.println(users);
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getId() == null || o2.getId() == null) {
                    return 1;
                }
                return o2.getId().compareTo(o1.getId());
            }
        });
        System.out.println("-----> sort");
        System.out.println(users);

        ArrayList<User> userArrayList = Lists.newArrayList(user, user1, user2, user3, user4, user5);
        System.out.println("-----> userArrayList");
        System.out.println(userArrayList);
        userArrayList.sort((o1, o2) -> {
            if (o1.getId() == null || o2.getId() == null) {
                return -1;
            }
            return o1.getId().compareTo(o2.getId());
        });
        System.out.println("-----> sort");
        System.out.println(userArrayList);
    }

    @Test
    void testListSortContainNull() {

        User user = new User(3, "Tom");
        User user1 = new User(null, "Jack");
        User user2 = new User(1, "Shanks");
        User user3 = new User(null, "Jerry");
        User user4 = new User(2, "Rose");
        List<User> list = Lists.newArrayList(user, user1, user2, user3, user4);
        List<User> userList = Lists.newArrayList(list);

        System.out.println("-----> list");
        list.forEach(System.out::println);
        System.out.println("-----> list sorted by id and nullsFirst");
        list.sort(Comparator.comparing(User::getId, Comparator.nullsFirst(Integer::compareTo)));
        list.forEach(System.out::println);

        /* 请注意，根据单属性name进行排序，若需要将name为null的对象也参与排序，则需要：
         * .sorted(Comparator.comparing(User::getName, Comparator.nullsLast((o1,o2)->o1.compareTo(o2))))
         * 使用方法引用优化（注意name的类型是String）即为：
         * .sorted(Comparator.comparing(User::getName, Comparator.nullsLast(String::compareTo)))
         */
        System.out.println("-----> userList");
        userList.forEach(System.out::println);
        System.out.println("-----> userList sorted by name and nullsLast");
        List<User> nullsLastCollect = userList.stream()
                .sorted(Comparator.comparing(User::getName, Comparator.nullsLast(String::compareTo)))
                .collect(Collectors.toList());
        nullsLastCollect.forEach(System.out::println);
    }

    @Test
    void testListSortMultiParams() {

        Employee employee = new Employee(3, "Tom", 20);
        Employee employee1 = new Employee(null, "Jack", 20);
        Employee employee2 = new Employee(1, "Shanks", 20);
        Employee employee3 = new Employee(null, "Jerry", 30);
        Employee employee4 = new Employee(4, "Rose", 30);
        Employee employee5 = new Employee(2, "Rose", 30);
        Employee employee6 = new Employee(5, "Jack", 30);
        List<Employee> list = Lists.newArrayList(employee, employee1, employee2, employee3, employee4, employee5, employee6);
        List<Employee> employeeList = Lists.newArrayList(list);
        List<Employee> employees = Lists.newArrayList(list);
        List<Employee> employeeArrayList = Lists.newArrayList(list);

        System.out.println("-----> list");
        list.forEach(System.out::println);
        System.out.println("-----> list sorted by age then by");
        list.sort(Comparator.comparing(Employee::getAge, Comparator.nullsFirst(Integer::compareTo)).thenComparing(Employee::getId, Comparator.nullsFirst(Integer::compareTo)));
        list.forEach(System.out::println);

        System.out.println("-----> employeeList");
        employeeList.forEach(System.out::println);
        System.out.println("-----> employeeList sorted");
        List<Employee> employeeListSorted = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getAge, Comparator.nullsLast(Integer::compareTo)).thenComparing(Employee::getId, Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());
        employeeListSorted.forEach(System.out::println);


        System.out.println("-----> employees");
        employees.forEach(System.out::println);
        System.out.println("-----> employees age reversed");
        List<Employee> employeesReversed = employees.stream()
                .sorted(Comparator.comparing(Employee::getAge, Comparator.nullsLast(Integer::compareTo)).reversed().thenComparing(Employee::getId, Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());
        employeesReversed.forEach(System.out::println);

        System.out.println("-----> employeeArrayList");
        employeeArrayList.forEach(System.out::println);
        System.out.println("-----> employeeArrayList sorted by name then by id");
        employeeArrayList.sort(Comparator.comparing(Employee::getName, Comparator.nullsFirst(String::compareTo)).thenComparing(Employee::getId, Comparator.nullsFirst(Integer::compareTo)));
        employeeArrayList.forEach(System.out::println);
    }
}
