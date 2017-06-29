package com.mingren.lib.baselibrary;

import android.app.Activity;
import android.app.Application;

import com.mingren.lib.baselibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/4/4.
 */

public class MyApplication extends Application {

    List<Activity> activityList = new ArrayList<Activity>();
    public static Retrofit retrofit;
    public static String HOST = "https://technology.zhongyuedu.com/";

    public static void setHost(String host){
        HOST = host;
    }

    public static Retrofit getRetrofit(){
        // 初始化网络框架
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(HOST)
                    //增加返回值为String的支持
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    public void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    public void removeAllActivity() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();
    }

    //退出方法
    long time = 0;
    public void exit() {
        if (System.currentTimeMillis() - time > 2000) {
            time = System.currentTimeMillis();
            ToastUtils.showToast(this, "再次点击退出应用！");
        } else {
            removeAllActivity();
        }
    }

}
