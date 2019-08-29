package com.demo.test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Shanks
 * @date 2019-02-13
 */
public class CopyFiles {

    /**
     * 当前路径.\work\digital\
     */
    private static String currentPath = CopyFiles.class.getResource("/").getFile();
    /**
     * 源文件夹路径
     */
    private static String directorySource = currentPath + File.separator + "app" + File.separator + "source";
    /**
     * 目标文件夹路径
     */
    private static String directoryTarget = currentPath + File.separator + "RealTime" + File.separator;
    /**
     * 存放文件的栈
     */
    private static Stack<File> fileStack;
    /**
     * JDK8时间格式化
     */
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) {
        System.out.println();
        System.out.println("---------->当前路径：" + currentPath);
        System.out.println("---------- 线程开始 ----------");

        File[] files = new File(directorySource).listFiles();
        assert files != null;
        List<File> list = new ArrayList<>(Arrays.asList(files));
        list.sort(Comparator.comparing(File::getName).reversed());

        System.out.println("倒序打印源文件夹文件列表：");
        System.out.println("---------- 打印开始 ----------");
        list.forEach(System.out::println);
        System.out.println("---------- 打印结束 ----------");
        fileStack = new Stack<>();
        fileStack.addAll(list);

        System.out.println("目标文件夹RealTime已存在文件数：" + new File(directoryTarget).list().length);

        File[] filesTarget = new File(directoryTarget).listFiles();
        List<File> listTarget = new ArrayList<>(Arrays.asList(filesTarget));
        listTarget.forEach((file) -> fileStack.pop());

        while (true) {
            if (fileStack.empty()) {
                return;
            }
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new CopyFileTask().run();
        }

    }

    static class CopyFileTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                if (fileStack.empty()) {
                    return;
                }
                File file = fileStack.pop();
                try {
                    if (!new File(directoryTarget + File.separator + file.getName()).exists()) {
                        FileUtils.copyFile(file, new File(directoryTarget + File.separator + file.getName()));
                        System.out.println("文件复制中..." + dateTimeFormatter.format(LocalDateTime.now()) + ": " + file.getName());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
