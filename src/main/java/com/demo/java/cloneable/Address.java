package com.demo.java.cloneable;

/**
 * @author Shanks
 * @date 2019-03-28
 */
public class Address {

    private String province;
    private String street;

    public Address() {
    }

    public Address(String province, String street) {
        this.province = province;
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
