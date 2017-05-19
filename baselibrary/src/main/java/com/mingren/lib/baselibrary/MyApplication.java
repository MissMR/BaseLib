package com.mingren.lib.baselibrary;

import android.app.Activity;
import android.app.Application;

import com.mingren.lib.baselibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/4.
 */

public class MyApplication extends Application {

    List<Activity> activityList = new ArrayList<Activity>();

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
