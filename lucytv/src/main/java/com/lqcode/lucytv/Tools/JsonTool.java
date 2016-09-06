package com.lqcode.lucytv.tools;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2016/9/2.
 */
public class JsonTool {
    public static Object objectToEntity(String json) {
        return JSON.parse(json);
    }
}