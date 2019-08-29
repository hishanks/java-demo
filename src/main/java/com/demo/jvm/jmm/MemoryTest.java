package com.demo.jvm.jmm;

import sun.management.VMManagement;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * -Xms1024m 设置初始化堆内存分配大小，默认为物理内存的1/64
 * -Xmx1024m 最大分配内存，默认为物理内存的1/4
 * -XX:+PrintGCDetails
 * --------------------------------------------------
 * 永久代（jdk7）、元空间（jdk8）只是逻辑上的堆内存，实际上并不是堆内存，或者称为非堆内存
 * 在过去（自定义类加载器还不是很常见的时候），类大多是static修饰的，很少被卸载或收集，因此被称为“永久的(Permanent)”；
 * 同时，由于类class是JVM实现的一部分，并不是由应用创建的，所以又被认为是“非堆(non-heap)”内存
 * 验证方式：(PSYoungGen + ParOldGen) / 1024 == Runtime.getRuntime().totalMemory() / (double) 1024 / 1024
 * 新生区，老年区的内存之和等于Java虚拟机的内存堆总量
 * --------------------------------------------------
 * 得出结论：
 * 1. JVM堆由PSYoungGen、ParOldGen、Metaspace组成
 * 2. 真正的堆内存实际上只有PSYoungGen、ParOldGen
 * -------------------------------------------------- Java 7 --------------------------------------------------
 * 当堆中不断的创建对象，首先使用的是Minor GC，超出老年代区，则使用Full GC(或叫Major GC)，则报错OutOfMemoryError: Java heap space
 * -------------------------------------------------- Java 8 --------------------------------------------------
 * Java 8中JVM不再有永久代（PermGen）。但类的元数据信息（Metadata）还在；
 * 只不过不再是存储在连续的堆空间上，而是移动到叫做元空间（Metaspace）的本地内存（Native memory）中；
 * --------------------------------------------------
 * 通过如下参数设定初始值和最大值：
 * 比如－Xms256M -Xmxl024M
 * 其中－X表示它是JVM运行参数，ms是memory start的简称， mx是memory max的简称，分别代表最小堆窑量和最大堆窑量。
 * --------------------------------------------------
 * 区别于永久代，元空间在本地内存中分配。在Java 8中，Perm区中的所有内容中字符串常量移至堆内存；
 * 其他内容包括类元信息、字段、静态属性、方法、常量等都移动至元空间内。
 *
 * @author Shanks
 * @date 2019-03-24
 */
public class MemoryTest {

    public static void main(String[] args) {

        int pid = getJavaVirtualMachinePID();
        System.out.println(String.format("PID: %s", pid));

        // Java虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println(maxMemory);
        // 返回Java虚拟机的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println(totalMemory);

        double maxMemoryMB = maxMemory / (double) 1024 / 1024;
        System.out.printf("MAX_MEMORY=%s字节，%sMB%n", maxMemoryMB, maxMemoryMB);
        double totalMemoryMB = totalMemory / (double) 1024 / 1024;
        System.out.printf("TOTAL_MEMORY=%s字节，%sMB%n", totalMemoryMB, totalMemoryMB);
    }

    public static int getJavaVirtualMachinePID() {
        VMManagement management;
        Method getProcessId;
        try {
            RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            Field jvm = runtime.getClass().getDeclaredField("jvm");
            jvm.setAccessible(true);
            management = (VMManagement) jvm.get(runtime);
            getProcessId = management.getClass().getDeclaredMethod("getProcessId");
            getProcessId.setAccessible(true);
            return (Integer) getProcessId.invoke(management);
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
