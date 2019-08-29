package com.demo.java.generic.stove;

/**
 * 以下代码编译正确且能够正常运行
 * get()是一个泛型方法，first并非是java.lang.String类型，而是泛型标识<String>，second指代Alibaba
 * get()中其他没有被用到的泛型符号并不会导致编译错误，类名后的T与尖括号内的T相同也是合法的
 * 当然，在实际应用时，并不会存在这样的定义方式，这里只是期望能够对以下几点加深理解：
 * （1）尖括号里的每个元素都指代一种未知的数据类型，例如String出现在尖括号里，它就不是java.lang.String，而仅仅是个代号。
 * 类名后方定义的泛型<T>和get()方法前定义的<T>是两个指代，可以完全不同，互不影响。
 * （2）尖括号的位置非常讲究，必须在类名之后或者在方法返回值之前。
 * （3）泛型在定义处只具备执行Object方法的能力。
 * 因此想再get()内部执行string.longValue()+alibaba.intValue()是做不到的，此时泛型只能调用Object类中的方法，如toString。
 * （4）对于编译之后的字节码指令，其实没有这些花头花脑的方法签名，充分说明了泛型只是一种编写字节码的语法检查。
 * 在使用泛型元素时，会执行强制类型转换。
 *
 * @author Shanks
 * @date 2019-04-22
 */
public class GenericDefinitionDemo<T> {

    static <String, T, Alibaba> String get(String string, Alibaba alibaba) {
        return string;
    }

    public static void main(String[] args) {
        Integer first = 222;
        Long second = 333L;
        // 调用上方定义的get方法，最后输出结果为222
        Integer result = get(first, second);
        System.out.println(result);
    }
}
