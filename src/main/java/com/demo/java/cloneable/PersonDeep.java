package com.demo.java.cloneable;

/**
 * @author Shanks
 * @date 2019-03-28
 */
public class PersonDeep implements Cloneable {

    private int id;
    private Integer age;
    private String name;
    private AddressDeep addressDeep;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        PersonDeep personDeep = (PersonDeep) super.clone();
        personDeep.addressDeep = (AddressDeep) addressDeep.clone();
        return personDeep;
    }

    public PersonDeep() {
    }

    public PersonDeep(int id, Integer age, String name, AddressDeep addressDeep) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.addressDeep = addressDeep;
    }

    public String display() {
        return "PersonDeep{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", addressDeep=" + addressDeep +
                '}';
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

    public AddressDeep getAddressDeep() {
        return addressDeep;
    }

    public void setAddressDeep(AddressDeep addressDeep) {
        this.addressDeep = addressDeep;
    }
}
