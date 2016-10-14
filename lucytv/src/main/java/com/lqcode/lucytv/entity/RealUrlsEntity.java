package com.lqcode.lucytv.entity;

/**
 * Created by Administrator on 2016/10/14.
 */

public class RealUrlsEntity {
    private int size;
    private String[] src;
    private String video_profile;
    private String container;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String[] getSrc() {
        return src;
    }

    public void setSrc(String[] src) {
        this.src = src;
    }

    public String getVideo_profile() {
        return video_profile;
    }

    public void setVideo_profile(String video_profile) {
        this.video_profile = video_profile;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }
}
