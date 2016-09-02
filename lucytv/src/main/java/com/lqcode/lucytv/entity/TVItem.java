package com.lqcode.lucytv.entity;

/**
 * Created by Administrator on 2016/9/2.
 */
public class TVItem extends Entity {
    private int id;
    private String name;
    private String png;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "TVItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", png='" + png + '\'' +
                '}';
    }
}
