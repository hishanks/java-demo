package com.demo.jvm.jmm;

/**
 * 内存模型：Java Stack
 * Exception in thread "main" java.lang.StackOverflowError
 * 最经典的重现StackOverflowError的方式：死循环调用
 * <p>
 * 知识点：栈，也叫栈内存，是在线程创建时被创建的，它的生命周期跟随线程的生命周期，线程结束时，栈内存也同时释放；
 * 对于栈来说，不存在垃圾回收的问题，因为只要线程一结束栈内存也跟着结束，生命周期和线程一致，是线程私有的；
 * 基本类型的变量、实例方法、引用类型变量都是在函数的栈内存中分配的。
 * </p>
 *
 * @author Shanks
 * @date 2019-03-23
 */
public class StackOverflowErrorTest {

    public static void main(String[] args) {
        hello();
    }

    /**
     * IDEA提示表示该方法被无限递归调用了，只能通过抛出异常来结束
     * Method 'hello()' recurses infinitely, and can only end by throwing an exception less... (⌘F1)
     */
    public static void hello() {
        hello();
    }
}
