package com.lqcode.lucytv.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/7.
 */
public class CCTVItem extends Entity implements Serializable{
    //当前播放的节目
    private String t;
    //播放时间
    private String s;
    //未知
    private String d;
    //cctv1
    private String c;
    //CCTV-1 综合
    private String n;
    //结束时间
    private String e;

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "CCTVItem{" +
                "t='" + t + '\'' +
                ", s='" + s + '\'' +
                ", d='" + d + '\'' +
                ", c='" + c + '\'' +
                ", n='" + n + '\'' +
                ", e='" + e + '\'' +
                '}';
    }
}
