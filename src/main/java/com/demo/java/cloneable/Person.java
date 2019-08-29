package com.demo.java.cloneable;

/**
 * @author Shanks
 * @date 2019-03-28
 */
public class Person implements Cloneable {

    private int id;
    private Integer age;
    private String name;
    private Address address;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * 这里不重写toString方法，是为了看对象的code
     *
     * @return String
     */
    public String display() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    public Person() {
    }

    public Person(int id, Integer age, String name, Address address) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
