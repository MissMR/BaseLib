package com.mingren.lib.baselibrary;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.mingren.lib.baselibrary.inject.InjectView;

import java.lang.reflect.Field;

public abstract class BaseActivity extends AppCompatActivity {
    // 获取ContentView
    protected abstract int getContentViewId();

    // 获取承载Fragment的View
    protected abstract int getFragmentViewId();

    MyApplication myApplication;
    FragmentTransaction ft;
    // 添加Fragment
    protected void addFragment(BaseFragment fragment) {
               ft.replace(getFragmentViewId(), fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    // 移除当前Fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            Log.e("log", "remove");
        } else {
            finish();
            Log.e("log", "finish");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        myApplication = (MyApplication) getApplication();
        myApplication.addActivity(this);
        ft  =  getSupportFragmentManager().beginTransaction();
        InjectView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myApplication.removeActivity(this);
    }

    private void InjectView() {
        try {
            Class clazz = this.getClass();
            // 获取Activity中所有声明的变量
            Field[] fields = clazz.getDeclaredFields();
            // 遍历判断变量是否被注解
            for (Field field : fields) {
                if (field.isAnnotationPresent(InjectView.class)) {
                    InjectView injectView = field.getAnnotation(InjectView.class);
                    int id = injectView.value();
                    if (id > 0) {
                        field.setAccessible(true);
                        field.set(this, this.findViewById(id));//给我们要找的字段设置值
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
