package com.demo.java;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Shanks
 * @date 2018/7/21
 */
public enum SeasonEnum {
    /**
     * 春天
     */
    SPRING(1, "春天"),
    /**
     * 夏天
     */
    SUMMER(2, "夏天"),
    /**
     * 秋天
     */
    AUTUMN(3, "秋天"),
    /**
     * 冬天
     */
    WINTER(4, "冬天");

    private int code;
    private String desc;

    public static String desc(int code) {
        for (SeasonEnum e : SeasonEnum.values()) {
            if (e.getCode() == code) {
                return e.getDesc();
            }
        }
        return StringUtils.EMPTY;
    }

    SeasonEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
