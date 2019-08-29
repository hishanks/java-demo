package com.demo.nio;

import org.junit.jupiter.api.Test;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Shanks
 * @date 2019-07-09
 */
public class PathTest {

    @Test
    void testPaths() {
        Path path = Paths.get("/Users/shanks");

        Path parent = path.getParent();
        System.out.println(parent.toString());

        Path fileName = path.getFileName();
        System.out.println(fileName.toString());

        FileSystem fileSystem = path.getFileSystem();
        System.out.println(fileSystem.toString());

        int nameCount = path.getNameCount();
        System.out.println(nameCount);

        Path root = path.getRoot();
        System.out.println(root.toString());
    }
}
