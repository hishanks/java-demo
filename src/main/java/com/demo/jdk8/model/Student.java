package com.demo.jdk8.model;

/**
 * @author Shanks
 * @date 2019-03-07
 */
public class Student {

    private Integer id;
    private Integer type;
    private String name;
    private String classNo;

    public Student() {
    }

    public Student(Integer id, Integer type, String name, String classNo) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.classNo = classNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }
}
