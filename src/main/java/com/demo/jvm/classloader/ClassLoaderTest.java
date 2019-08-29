package com.demo.jvm.classloader;

import org.junit.jupiter.api.Test;

/**
 * 知识点：双亲委派机制 + 沙箱机制（沙箱机制的作用：防止恶意代码对Java的破坏）
 * sun.misc.Launcher，它是Java虚拟机的入口应用
 * 某个特定的类加载器在接到加载类的请求的时候，首先将加载任务委托给父类加载器，依次递归；
 * 如果父类加载器可以完成类加载任务，就成功返回；
 * 只有父类加载器无法完成此加载任务时，才自己去加载。
 * --------------------------------------------------
 * Parents Delegation Model（双亲委派模型、溯源委派加载模型）是Java设计者推荐给开发者的类加载器的实现方式，并不是强制规定的；
 * 大多数的类加载器都遵循这个模型，但是JDK中也有较大规模破坏双亲模型的情况，例如线程上下文类加载器（Thread Context ClassLoader）的出现。
 * --------------------------------------------------
 * 委托机制具体含义：当Java虚拟机要加载一个类时，到底派出哪个类加载器去加载呢？
 * 首先当前线程的类加载器去加载线程中的第一个类（假设为类A）
 * 注：当前线程的类加载器可以通过Thread类的getContextClassLoader()获得，也可以通过setContextClassLoader()自己设置类加载器
 * 如果类A中引用了类B，Java虚拟机将使用加载类A的类加载器去加载类B
 * 还可以直接调用ClassLoader.loadClass()方法来指定某个类加载器去加载某个类
 *
 * @author Shanks
 * @date 2019-03-23
 */
public class ClassLoaderTest {

    @Test
    void test1() {
        /*
         * 启动类加载器（Bootstrap），由 C++实现，此时值为null
         * 验证方式：直接创建Object的实例化对象，调用getClass().getClassLoader()即可
         */
        Object object = new Object();
        ClassLoader classLoader = object.getClass().getClassLoader();
        System.out.println(classLoader);

        /*
         * Java虚拟机的第一个类加载器是Bootstrap，这个加载器很特殊，它不是Java类，因此它不需要被别人加载，它嵌套在Java虚拟机内核里面；
         * 也就是JVM启动的时候Bootstrap就已经启动，它是用C++写的二进制代码（不是字节码），它可以去加载别的类；
         * 这也是我们在测试时为什么发现System.class.getClassLoader()结果为null的原因；
         * 这并不表示System这个类没有类加载器，而是它的加载器比较特殊，是BootstrapClassLoader，由于它不是Java类，因此获得它的引用肯定返回null。
         */
        ClassLoader loader = System.class.getClassLoader();
        System.out.println(loader);

        /*
         * 扩展类加载器（Extension）
         * 负责将JAVA_HOME/jre/lib/ext或者由系统变量java.ext.dir指定位置中的类库加载到内存中，开发者可以直接使用标准扩展类加载器
         * 验证方式：新建一个类Hello.java，然后export为jar，放在$JAVA_HOME/jre/lib/ext/目录下，然后删除工程中的Hello.java文件
         * 首先，我们得知在工程中原被删除的Hello.java的全限定名为com.demo.classloader.Hello.java
         * 然后，Class clazz = Class.forName("com.demo.jvm.classloader.Hello.java");
         * System.out.println(user.getClass().getClassLoader());
         * sun.misc.Launcher$ExtClassLoader@xxxxxx
         */
        System.out.println("ExtClassLoader");

        /*
         * sun.misc.Launcher$AppClassLoader@18b4aac2
         * 应用程序类加载器（App），也叫系统类加载器，它负责将系统类路径（CLASSPATH）中指定的类库加载到内存中
         * 由于这个类加载器是ClassLoader中的getSystemClassLoader()方法的返回值，因此一般称为系统（System）加载器
         * 验证方式：创建一个类，实例化一个对象，然后调用getClass().getClassLoader()
         */
        User user = new User();
        System.out.println(user.getClass().getClassLoader());
        // sun.misc.Launcher$ExtClassLoader@4d591d15
        System.out.println(user.getClass().getClassLoader().getParent());
        // null
        System.out.println(user.getClass().getClassLoader().getParent().getParent());
    }
}
