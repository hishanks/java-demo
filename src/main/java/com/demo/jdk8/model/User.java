package com.demo.jdk8.model;

/**
 * @author zhoukai
 * @date 2018/8/10
 */
public class User {

    private String id;
    private Integer value;

    public User() {
    }

    public User(String id, Integer value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", value=" + value +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
