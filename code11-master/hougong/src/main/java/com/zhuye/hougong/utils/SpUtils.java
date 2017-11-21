package com.zhuye.hougong.utils;

import android.content.Context;

/**
 * Created by zzzy on 2017/11/21.
 */

public class SpUtils {

    public static String getString(Context context,String key,String def){

       return context.getSharedPreferences("con",Context.MODE_PRIVATE).getString(key,def);
    }

    public static void setString(Context context,String key,String def){

        context.getSharedPreferences("con",Context.MODE_PRIVATE).edit().putString(key,def).commit();
    }
}
