package com.mingren.lib.baselibrary.utils;

import android.content.Context;

/**
 * Created by Administrator on 2017/4/4.
 */

public class MyUtils {

    private static long lastClickTime = 0;
    public static final long INTERVAL = 500L; //防止连续点击的时间间隔

    public static boolean isFastDoubleClick(Context context) {
        long time = System.currentTimeMillis();

        if (lastClickTime != 0 && ( time - lastClickTime ) < INTERVAL )
        {
            lastClickTime = time;
            ToastUtils.showToast(context,"双击退出应用！");
            return false;
        }
        lastClickTime = time;
        return true;
    }




}
