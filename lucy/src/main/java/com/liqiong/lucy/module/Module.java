package com.liqiong.lucy.module;

import com.liqiong.lucy.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiQiong on 2016/8/24.
 */
public abstract class Module {
    public abstract void onCreate();

    public abstract void onDestroy();

    private List<Module> allModule = new ArrayList<>();

    /**
     * 初始化Module
     */
    protected void exec(Class<? extends Module> module) {
        loadAnnotaionMethon(module);
    }

    protected void allModuleDestory() {
        for (Module module : allModule)
            module.onDestroy();
        allModule.clear();
        allModule = null;
    }


    /**
     * 初始化 module 的变量
     * 是指：Autowired 自动注入的变量类
     *
     * @param module
     */
    private void loadAnnotaionMethon(Class<? extends Module> module) {
        //这个是管理的module
        try {
            Module conModule = module.newInstance();
            //执行onCreate 方法
            execMethonOnCreate(conModule);
            allModule.add(conModule);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //下面的是参数
        Field[] fields = module.getFields();
        for (Field mField : fields) {
            Autowired mAuto = mField.getAnnotation(Autowired.class);
            if (mAuto != null) {
                Class<? extends Module> mClass = mAuto.clazz();
                // 这个是管理变量的module
                if (mClass == Autowired.NOT_IMPL_MODULE.class)
                    mClass = (Class<? extends Module>) mField.getType();
                Module m = null;
                try {
                    m = mClass.newInstance();
                    allModule.add(m);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    mField.set(this, m);
                    //执行onCreate 方法
                    execMethonOnCreate(m);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 执行onCreate 方法
     *
     * @param module
     */
    private void execMethonOnCreate(Module module) {
        module.onCreate();
    }

}