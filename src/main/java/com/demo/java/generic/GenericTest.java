package com.demo.java.generic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shanks
 * @date 2019-02-28
 */
public class GenericTest {

    @Test
    void testExtends() {
        /*
         * 只能接收，不能往里面写数据
         */
        List<? extends Fruit> e = new ArrayList<Fruit>();
        // 编译报错
        // e.add(new Apple());

        // 编译错误，=后面的菱形里面必须是Fruit或是其子类
        // List<? extends Fruit> appleArrayList = new ArrayList<Food>();
        List<? extends Fruit> arrayList = new ArrayList<Apple>();

        List<? extends Food> list = new ArrayList<Apple>();
        // 编译报错
        // add(capture<? extends com.demo.java.generic.Food>) in List cannot be applied to com.demo.java.generic.Apple
        // list.add(new Apple());
    }

    @Test
    void testSuper() {
        /*
         * 应用写数据，对于读数据，读取的则全部是Object类型的
         * 如下，? super Fruit，表示可以向里面add的元素可以是Fruit以及Fruit的子类
         */
        List<? super Fruit> s = new ArrayList<>();
        s.add(new Apple());
        s.add(new Fruit());
        s.add(new RedApple());
        // 编译报错
        // s.add(new Food());

        // 编译报错，不能确定获取数据的类型，获取出来的也只能用Object接
        // Fruit fruit = s.get(0);
        // 编译正确
        Object object = s.get(1);

        // 编译报错，就是说在初始化的时候，new ArrayList<>()的菱形符号里面的类型必须是Fruit或是其父类
        // List<? super Fruit> appleList = new ArrayList<Apple>();
        List<? super Fruit> foodList = new ArrayList<Food>();
        List<? super Fruit> objectList = new ArrayList<Object>();
    }
}
