package com.demo.java.cloneable;

/**
 * @author Shanks
 * @date 2019-03-28
 */
public class PersonDeepSerializable extends DeepCloneSerializable {

    private static final long serialVersionUID = -1426043052720908560L;

    private int id;
    private Integer age;
    private String name;
    private AddressDeepSerializable addressDeepSerializable;

    public PersonDeepSerializable() {
    }

    public PersonDeepSerializable(int id, Integer age, String name, AddressDeepSerializable addressDeepSerializable) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.addressDeepSerializable = addressDeepSerializable;
    }

    public String display() {
        return "PersonDeep{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", addressDeepSerializable=" + addressDeepSerializable +
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

    public AddressDeepSerializable getAddressDeepSerializable() {
        return addressDeepSerializable;
    }

    public void setAddressDeepSerializable(AddressDeepSerializable addressDeepSerializable) {
        this.addressDeepSerializable = addressDeepSerializable;
    }
}
