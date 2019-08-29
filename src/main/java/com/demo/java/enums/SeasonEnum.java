package com.demo.java.enums;

/**
 * 【推荐】如果变量值仅在一个固定范围内变化用 enum 类型来定义。
 * 说明：如果存在名称之外的延伸属性应使用 enum 类型，下面正例中的数字就是延伸信息，表示一年中的第几个季节。
 *
 * @author Shanks
 * @date 2019-08-03
 */
public enum SeasonEnum {

    SPRING(1, "春季"),
    SUMMER(2, "夏季"),
    AUTUMN(3, "秋季"),
    WINTER(4, "冬季");

    private int seq;
    private String desc;

    SeasonEnum(int seq, String desc) {
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