package com.lqcode.lucytv.Tools;

import com.alibaba.fastjson.JSON;
import com.lqcode.lucytv.entity.Entity;

/**
 * Created by Administrator on 2016/9/2.
 */
public class JsonTool {
    public static Object objectToEntity(String json) {
        return JSON.parse(json);
    }
}