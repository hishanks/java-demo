package com.demo.test.sunday;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Shanks
 * @date 2019-07-17
 */
public class SundayTest {

    @Test
    void test() {
        /*
         * 把list1中的list1与list2相同id的元素删除
         */
        /* ---------- list1 ---------- */
        Student s1 = Student.builder().id(1L).name("a").build();
        Student s2 = Student.builder().id(2L).name("b").build();
        Student s3 = Student.builder().id(3L).name("c").build();

        /* ---------- list2 ---------- */
        Student s4 = Student.builder().id(3L).name("e").build();
        Student s5 = Student.builder().id(2L).name("f").build();
        Student s6 = Student.builder().id(4L).name("g").build();

        // mock数据
        List<Student> list1 = Lists.newArrayList(s1, s2, s3);
        List<Student> list2 = Lists.newArrayList(s4, s5, s6);
        System.out.println(".......... print ..........");
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(".......... print ..........");

        list1.removeIf(student -> list2.stream().anyMatch(e -> e.getId().equals(student.getId())));
        System.out.println(list1);
    }

    @Test
    void list2SetByStudentId() {
        Student s1 = Student.builder().id(1L).name("a").build();
        Student s2 = Student.builder().id(1L).name("b").build();
        Student s3 = Student.builder().id(3L).name("c").build();
        List<Student> list = Lists.newArrayList(s1, s2, s3);
        ArrayList<Student> collect = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getId))),
                        ArrayList::new
                )
        );
        System.out.println(collect);
    }
}