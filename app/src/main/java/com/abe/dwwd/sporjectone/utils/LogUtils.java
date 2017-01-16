package com.abe.dwwd.sporjectone.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/1/16.
 */

public class LogUtils {

    public static void d(String tag,Object... info){
        Logger.d(tag,info);
    }

    public static void d(Object object){
        Logger.d(object);
    }

    public static void json(String json){
        Logger.json(json);
    }

    public static void xml(String xml){
        Logger.xml(xml);
    }

    public static void e(String tag,Object... info){
        Logger.e(tag,info);
    }

}
