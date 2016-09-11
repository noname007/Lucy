package com.lqcode.lucytv.entity;

/**
 * Created by Administrator on 2016/9/11.
 */
public class MovieItem extends Entity {
    private String id;
    private String name;
    private String png;
    private String profile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "MovieItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", png='" + png + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
