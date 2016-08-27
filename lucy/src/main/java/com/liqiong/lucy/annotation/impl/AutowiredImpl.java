package com.liqiong.lucy.annotation.impl;

import android.util.Log;

import com.liqiong.lucy.annotation.Autowired;
import com.liqiong.lucy.module.Module;

import java.lang.reflect.Field;

/**
 * Created by LiQiong on 2016/8/27.19:59
 */
public class AutowiredImpl extends Module {

    public void execAuto(Class<? extends Object> clazz) {
        Field[] fields = clazz.getFields();
        for (Field mField : fields) {
            Autowired mAuto = mField.getAnnotation(Autowired.class);
            if (mAuto != null) {
                Class<? extends Object> mClass = mAuto.clazz();
                if (mClass == Object.class) mClass = mField.getType();
                Object m = null;
                try {
                    m = mClass.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    Log.e("liqiong===========>>>>>>>>>>>",m.toString());
                    mField.set(this, m);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
