package com.lqcode.lucytv.entity;

/**
 * Created by Administrator on 2016/9/2.
 */
public class TVItem extends Entity {

    private String channelName;
    private String isLive;
    private String liveName;
    private long liveSt;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getIsLive() {
        return isLive;
    }

    public void setIsLive(String isLive) {
        this.isLive = isLive;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public long getLiveSt() {
        return liveSt;
    }

    public void setLiveSt(long liveSt) {
        this.liveSt = liveSt;
    }

    @Override
    public String toString() {
        return "TVItem{" +
                "channelName='" + channelName + '\'' +
                ", isLive='" + isLive + '\'' +
                ", liveName='" + liveName + '\'' +
                ", liveSt=" + liveSt +
                '}';
    }
}
