package com.demo.jdk8.collectors;

import com.demo.jdk8.model.Person;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Shanks
 * @date 2019-01-16
 */
public class JoiningTest {

    private static Person p1 = new Person("kevin", 25);
    private static Person p2 = new Person("andy", 30);
    private static Person p3 = new Person("tony", 25);
    private static Person p4 = new Person("kevin", 20);
    private static Person p5 = new Person("kevin", 20);
    private static List<Person> list = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5));

    @Test
    void testJoining() {

        String join = list.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(join);

        // delimiter：分隔符
        String collect1 = list.stream()
                .map(person -> person.getAge().toString())
                .collect(Collectors.joining("-|-"));
        System.out.println(collect1);

        String collect2 = list.stream()
                .map(Person::getName)
                .collect(Collectors.joining());
        System.out.println(collect2);

        // 创建临时文件名
        String filename = "index.png";
        String suffix = filename.substring(filename.lastIndexOf("."));
        System.out.println(suffix);
        try {
            File tempFile = File.createTempFile(System.currentTimeMillis() + "", suffix);
            System.out.println(tempFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
