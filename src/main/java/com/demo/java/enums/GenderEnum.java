package com.demo.java.enums;

/**
 * @author Shanks
 * @date 2019-08-03
 */
public enum GenderEnum {

    FEMALE(0, "女"),
    MALE(1, "男"),
    UNKNOWN(2, "未知");

    private int seq;
    private String desc;

    GenderEnum(int seq, String desc) {
        this.seq = seq;
        this.desc = desc;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}