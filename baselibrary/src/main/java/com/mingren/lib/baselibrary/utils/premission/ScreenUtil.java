package com.mingren.lib.baselibrary.utils.premission;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 *  与屏幕相关的工具类
 */

public class ScreenUtil {

    /**
     *  获取WindowManager
     */
    public static WindowManager getWindowManager(Context context){
        return   (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public static float getWindowWidth(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager(context).getDefaultDisplay().getMetrics(metrics);
       return metrics.widthPixels;
    }

    public static float getWindowHeight(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager(context).getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static float px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
