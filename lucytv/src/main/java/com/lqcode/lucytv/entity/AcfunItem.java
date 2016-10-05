package com.lqcode.lucytv.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/5.
 */
public class AcfunItem extends Entity implements Serializable {

    private int videoId;
    private String title;
    private String image;


    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "AcfunItem{" +
                "videoId=" + videoId +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
