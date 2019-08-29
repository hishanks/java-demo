package com.demo.jdk8.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Shanks
 * @date 2019-07-09
 */
public class FlatMapTest {

    public static void main(String[] args) throws IOException {
        /* nio-test.txt内容如下：
         * Hello World
         * Hello World
         * Hello World
         * Hi World
         * Hello World
         */
        Path path = Paths.get("/Users/shanks/nio-test.txt");
        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));
        Arrays.stream(words.toArray()).forEach(System.out::println);
    }
}