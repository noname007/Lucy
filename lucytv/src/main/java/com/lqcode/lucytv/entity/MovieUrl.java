package com.lqcode.lucytv.entity;

/**
 * Created by Administrator on 2016/10/5.
 */
public class MovieUrl {
    private int id;
    private String movie_uuid;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie_uuid() {
        return movie_uuid;
    }

    public void setMovie_uuid(String movie_uuid) {
        this.movie_uuid = movie_uuid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
