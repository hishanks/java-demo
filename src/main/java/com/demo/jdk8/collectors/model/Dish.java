package com.demo.jdk8.collectors.model;

/**
 * 菜肴实体类
 *
 * @author zhoukai
 * @date 2018-10-14
 */
public class Dish {

    /**
     * 菜肴名
     */
    private String name;
    /**
     * 卡路里含量
     */
    private Integer calories;

    public Dish() {
    }

    public Dish(String name, Integer calories) {
        this.name = name;
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
}
