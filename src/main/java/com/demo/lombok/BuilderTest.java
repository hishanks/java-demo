package com.demo.lombok;

import java.util.Objects;

/**
 * @author Shanks
 * @date 2019-04-28
 */
public class BuilderTest {

    public static void main(String[] args) {
        Student student = Student.builder().num(100L).build();
        System.out.println(student.getName());
        System.out.println(student.getAge());
        if (Objects.nonNull(student.getAddress())) {
            System.out.println("Student address is not null...");
        }
        System.out.println(student);

        System.out.println("..................................................");
        Teacher teacher = Teacher.builder().build();
        System.out.println(teacher);
        if (Objects.nonNull(teacher.getAddress())) {
            System.out.println("Teacher address is not null...");
        }
    }
}
