package com.lqcode.lucytv;

/**
 * Created by Administrator on 2016/9/7.
 */
public class TVSettings {
    private static TVSettings ourInstance = new TVSettings();

    public static TVSettings getInstance() {
        return ourInstance;
    }

    private TVSettings() {
    }


}
