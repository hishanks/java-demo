package com.demo.designpattern.singleton;

/**
 * 在并发场景下，通过双重检查锁（double-checked locking）实现延迟初始化的优化问题隐患(可参考 The "Double-Checked Locking is Broken" Declaration)，
 * 推荐解决方案中较为简单一种（适用于 JDK5 及以上版本），将目标属性声明为 volatile 型。
 * --------------------------------------------------
 * 双重校验锁
 * 解析：只有当instance为null时，需要获取同步锁，创建一次实例。当实例被创建，则无需试图加锁
 * --------------------------------------------------
 * 以下摘录自网上资料，正确性有待研究：
 * 双重检查锁定背后的理论是完美的。不幸地是，现实完全不同
 * 双重检查锁定的问题是：并不能保证它会在单处理器或多处理器计算机上顺利运行
 * 双重检查锁定失败的问题并不归咎于JVM中的bug，而是归咎于内存模型
 * 内存模型允许所谓的“无序写入”，编译器和CPU可以在保证输出结果一样的情况下对指令重排序，使性能得到优化，不过这也是这些习语失败的一个主要原因
 * 所以，对象声明加上volatile关键字，禁止编译的重排序，即插入一个内存屏障，相当于告诉CPU和编译器先于这个命令的必须先执行，后于这个命令的必须后执行
 * 内存屏障另一个作用是强制更新一次不同CPU的缓存
 * --------------------------------------------------
 * 单例模式关键三处：两私一公
 * 1.私有变量：private static volatile SingletonDoubleCheckedLocking instance;
 * 2.私有构造方法：private SingletonDoubleCheckedLocking() {}
 * 3.公有的getInstance方法：public static SingletonDoubleCheckedLocking getInstance() {}
 *
 * @author Shanks
 * @date 2018-07-19
 * @since 1.5
 */
public class SingletonDoubleCheckedLocking {

    private static volatile SingletonDoubleCheckedLocking instance;

    private SingletonDoubleCheckedLocking() {
    }

    public static SingletonDoubleCheckedLocking getInstance() {
        // 尽量避免重复进入同步块，二次检查，比直接用独占锁效率高
        if (instance == null) {
            // 同步.class，意味着对同步类方法调用
            synchronized (SingletonDoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new SingletonDoubleCheckedLocking();
                }
            }
        }
        return instance;
    }
}
