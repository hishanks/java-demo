package com.demo.java;

/**
 * ----------> 以上是针对velocity调用POJO类的属性时
 * velocity调用POJO类的属性时，建议直接使用属性名取值即可
 * 模板引擎会自动按规范调用POJO的getXxx()
 * 如果是boolean基本数据类型变量（boolean命名不需要加is前缀），会自动调用isXxx()方法
 * 说明：注意如果是Boolean包装类对象，优先调用getXxx()的方法
 * ----------> 下面是针对POJO类属性
 * 《阿里巴巴Java开发手册》：【强制】所有的POJO类属性必须使用包装数据类型
 *
 * @author zhoukai
 * @date 2018-09-25
 */
public class Model {

    private String comment;
    /**
     * 如果是POJO类属性，则布尔类型也要用封装类Boolean，而不是基本数据类型boolean
     */
    private Boolean delete;
    private boolean usage;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public boolean isUsage() {
        return usage;
    }

    public void setUsage(boolean usage) {
        this.usage = usage;
    }
}
