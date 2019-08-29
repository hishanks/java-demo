package com.demo.java.cloneable;

/**
 * @author Shanks
 * @date 2019-03-28
 */
public class AddressDeep implements Cloneable {

    private String province;
    private String street;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public AddressDeep() {
    }

    public AddressDeep(String province, String street) {
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
