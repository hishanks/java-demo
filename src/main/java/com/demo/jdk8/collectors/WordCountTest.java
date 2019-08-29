package com.demo.jdk8.collectors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 统计一个文件里单词出现次数
 *
 * @author Shanks
 * @date 2019-07-10
 */
public class WordCountTest {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/shanks/test/vim-cmd.txt");
        Map<String, Long> count = Files.lines(path)
                .parallel()
                .flatMap(line -> Arrays.stream(line.trim().split("\\s")))
                .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
                .filter(word -> word.length() > 0)
                .map(word -> new AbstractMap.SimpleEntry<>(word, 1))
                .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.counting()));
        count.forEach((k, v) -> System.out.println(k + " .......... " + v));
        System.out.println("..................................................");

        List<String> collect = Files.lines(path)
                .parallel()
                .flatMap(line -> Arrays.stream(line.trim().split("\\s")))
                .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
                .filter(word -> word.length() > 0)
                .collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("..................................................");

        Map<String, Long> countMap = Files.lines(path)
                .parallel()
                .flatMap(line -> Arrays.stream(line.trim().split("\\s")))
                .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase().trim())
                .filter(word -> word.length() > 0)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        countMap.forEach((k, v) -> System.out.println(k + " .......... " + v));
    }
}