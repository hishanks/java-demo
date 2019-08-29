package com.demo.jdk8;

import com.demo.jdk8.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shanks
 * @date 2019-03-07
 */
public class Test {

    public static void main(String[] args) {

        Student s1 = new Student(1, 2, "赵", "001");
        Student s2 = new Student(2, 1, "钱", "001");
        Student s3 = new Student(3, 2, "孙", "002");
        Student s4 = new Student(4, 3, "李", "002");
        Student s5 = new Student(5, 2, "周", "001");
        Student s6 = new Student(6, 2, "吴", "002");

        List<Student> list = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5, s6));
        long count = list.stream().filter(s -> s.getType() == 2 && "002".equals(s.getClassNo())).count();
        System.out.println(count);
    }
}
