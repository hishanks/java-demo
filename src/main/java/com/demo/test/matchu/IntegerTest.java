package com.demo.test.matchu;

import org.junit.jupiter.api.Test;

/**
 * 关于IntegerCache
 * 1. private static class IntegerCache {}
 * 2. -XX:AutoBoxCacheMax=<size>
 *
 * 关于Integer.valueOf()
 * Returns an Integer instance representing the specified int value.
 * If a new Integer instance is not required, this method should generally be used in preference to the constructor Integer(int),
 * as this method is likely to yield significantly better space and time performance by caching frequently requested values.
 * 返回表示指定int值的整数实例
 * 如果不需要新的Integer实例，通常应该优先使用该方法，而不是构造函数Integer(int)
 * 因为通过缓存频繁请求的值，该方法可能会显著提高空间和时间性能
 * 一句话总结：就是只要是Integer i = <num>;的形式，都会使用Integer.value(<num>);而不是new Integer(<num>);这样时间空间性能较优
 *
 * This method will always cache values in the range -128 to 127,
 * inclusive, and may cache other values outside of this range.
 * 该方法总是缓存范围在-128~127之间的数值
 * 并且可能缓存此范围之外的其他值（即通过修改-XX:AutoBoxCacheMax=<size>自定义IntegerCache的范围）
 *
 * @author Shanks
 * @date 2019-08-29
 */
public class IntegerTest {

    /**
     * 反编译之后：
     * Integer f1 = 100;
     * Integer f2 = 100;
     * Integer f3 = 150;
     * Integer f4 = 150;
     * Integer f6 = 100;
     * Integer f7 = new Integer(100);
     * Integer f8 = 150;
     * Integer f9 = new Integer(150);
     * System.out.println(f1 == f2);
     * System.out.println(f3 == f4);
     * System.out.println(f1 == f6);
     * System.out.println(f1 == f7);
     * System.out.println(f3 == f8);
     * System.out.println(f3 == f9);
     */
    @Test
    void test() {
        Integer f1 = 100;
        Integer f2 = 100;
        Integer f3 = 150;
        Integer f4 = 150;
        // 等价于Integer f6 = 100;
        Integer f6 = Integer.valueOf(100);
        Integer f7 = new Integer(100);
        // 反编译之后Integer f8 = 150;可能会存在误导，但是没事，其实它还是走的Integer.valueOf方法，最后因为超过Cache范围，还是走了new Integer(i)
        Integer f8 = Integer.valueOf(150);
        Integer f9 = new Integer(150);
        // true
        System.out.println(f1 == f2);
        // false
        System.out.println(f3 == f4);
        // true
        System.out.println(f1 == f6);
        // false
        System.out.println(f1 == f7);
        // false
        System.out.println(f3 == f8);
        // false
        System.out.println(f3 == f9);
    }
}