package com.demo.jdk8.collectors.model;

import java.util.List;

/**
 * @author zhoukai
 * @date 2018-10-16
 */
public class Artist {

    /**
     * 艺术家名字（独唱歌手或乐队组合）
     */
    private String name;
    /**
     * 独唱歌手：true、乐队组合：false
     */
    private Boolean solo;
    /**
     * 成员数
     */
    private Integer members;
    /**
     * 主唱
     */
    private String mainMusician;
    /**
     * 专辑曲目
     */
    private List<String> trackList;

    public Artist() {
    }

    public Artist(String name, Boolean solo, Integer members, String mainMusician, List<String> trackList) {
        this.name = name;
        this.solo = solo;
        this.members = members;
        this.mainMusician = mainMusician;
        this.trackList = trackList;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", solo=" + solo +
                ", members=" + members +
                ", mainMusician='" + mainMusician + '\'' +
                ", trackList=" + trackList +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSolo() {
        return solo;
    }

    public void setSolo(Boolean solo) {
        this.solo = solo;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public String getMainMusician() {
        return mainMusician;
    }

    public void setMainMusician(String mainMusician) {
        this.mainMusician = mainMusician;
    }

    public List<String> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<String> trackList) {
        this.trackList = trackList;
    }
}
