package com.demo.jdk8;

import com.demo.jdk8.model.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Shanks
 * @date 2019-03-11
 */
public class MatchTest {

    @Test
    void testMatch() {
        Student student1 = new Student(1, null, null, null);
        Student student2 = new Student(2, null, null, null);
        Student student3 = new Student(3, null, null, null);
        Student student4 = new Student(4, null, null, null);
        List<Student> students = new ArrayList<>(Arrays.asList(student1, student2, student3, student4));
        List<Integer> studentIds = students.stream().map(Student::getId).collect(toList());

        List<Integer> ids = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> ids1 = new ArrayList<>(Arrays.asList(1, 2, 4));
        List<Integer> ids2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> ids3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        System.out.println(ids.stream().allMatch(studentIds::contains));
        System.out.println(studentIds.containsAll(ids1));
        System.out.println(ids2.stream().allMatch(studentIds::contains));
        System.out.println(studentIds.containsAll(ids3));

        System.out.println(students.stream().map(Student::getId).collect(toList()).containsAll(ids1));
        System.out.println(students.stream().map(Student::getId).collect(toList()).containsAll(ids3));
    }
}
