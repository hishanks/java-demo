package com.demo.jvm.jmm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Options意欲设置永久代（PermGen）的大小： -XX:PermSize=8m -XX:MaxPermSize=8m
 * Java 8中已经完全移除了永久代的概念，所以控制台会提示警告：support was removed in 8.0
 * Java 7中，倒不会提示
 * JDK1.6及下，会出现java.lang.OutOfMemoryError: PermGen Space（永久代内存溢出）
 * 结论：
 *  1.Java 7和Java 8将字符串常量由永久代转移到堆中
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at java.util.Arrays.copyOf(Arrays.java:3332)
 * 	at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
 * 	at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:448)
 * 	at java.lang.StringBuilder.append(StringBuilder.java:136)
 * 	at com.demo.jvm.jmm.OutOfMemoryErrorTest.main(OutOfMemoryErrorTest.java:17)
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=8m; support was removed in 8.0
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=8m; support was removed in 8.0
 *
 * 元空间的本质和永久代类似，都是对JVM规范中方法区的实现。不过元空间与永久代之间最大的区别在于：元空间并不在虚拟机中，而是使用本地内存；
 * 因此，默认情况下，元空间的大小仅受本地内存限制，但可以通过以下参数来指定元空间的大小：
 *  -XX:MetaspaceSize： 初始空间大小，达到该值就会触发垃圾收集进行类型卸载，同时GC会对该值进行调整：如果释放了大量的空间，就适当降低该值；如果释放了很少的空间，那么在不超过MaxMetaspaceSize时，适当提高该值。
 *  -XX:MaxMetaspaceSize： 最大空间，默认是没有限制的
 * 除了上面两个指定大小的选项以外，还有两个与 GC 相关的属性：
 * -XX:MinMetaspaceFreeRatio： 在GC之后，最小的Metaspace剩余空间容量的百分比，减少为分配空间所导致的垃圾收集
 * -XX:MaxMetaspaceFreeRatio： 在GC之后，最大的Metaspace剩余空间容量的百分比，减少为释放空间所导致的垃圾收集
 *
 * @author Shanks
 * @date 2019-03-25
 */
public class OutOfMemoryErrorTest {

    private static String base = "Hello World";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            base = base + base;
            list.add(base);
        }
        System.out.println(list);
    }
}
