package com.demo.json.netsfjson;

import net.sf.json.JSONString;

import java.io.Serializable;

/**
 * @author Shanks
 * @date 2018-12-12
 */
public class Remark implements JSONString, Serializable {

    private static final long serialVersionUID = 2693856501702738646L;

    private Long id;
    private String content;
    private Integer userId;

    @Override
    public String toJSONString() {
        return "{\"id\":" + this.id + ",\"content\":" + "\"" + this.content + "\"" + ",\"userId\":" + this.userId + "}";
    }

    @Override
    public String toString() {
        return "Remark{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
