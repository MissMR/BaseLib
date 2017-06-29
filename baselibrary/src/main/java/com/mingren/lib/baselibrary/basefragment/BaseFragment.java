package com.mingren.lib.baselibrary.basefragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingren.lib.baselibrary.inject.InjectView;

import java.lang.reflect.Field;

public abstract class BaseFragment extends Fragment {
    // 获取布局id
    protected  abstract int getLayoutId();
    protected  abstract  void initView(View view , Bundle savedInstanceState);
    View view;
    // 防止getActivity == null
    BaseActivity mActivity;

    public BaseActivity getMyActivity(){
        return mActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity)context;
    }

    // 添加Fragment
    protected  void addFragment(BaseFragment fragment){
        if (fragment != null){
            mActivity.addFragment(fragment);
        }
    }
    // 移除Fragment
    protected  void removeFragment(){
        mActivity.removeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(getLayoutId(),null);
        InjectView();
        initView(view,savedInstanceState);
        return  view;
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
                        field.set(this,view.findViewById(id));//给我们要找的字段设置值
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
