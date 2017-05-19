package com.mingren.lib.baselibrary;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public abstract class AppActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_app;
    }

    @Override
    protected int getFragmentViewId() {
        return R.id.fragment;
    }

    protected abstract BaseFragment getFirstFragment();

    BaseFragment firstFragment;

    // 添加Fragment
    protected  void addFragment(BaseFragment fragment){
      addFragment(fragment);
    }
    // 移除当前Fragment
    protected  void removeFragment(){
        if (getSupportFragmentManager().getBackStackEntryCount() >1){
            getSupportFragmentManager().popBackStack();
            Log.e("log","remove");
        }else{
            finish();
            Log.e("log","finish");
        }
    }


    // 返回键处理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == keyCode){
              if (getSupportFragmentManager().getBackStackEntryCount() == 1){
                  finish();
                  return true;
              }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments()) {
             firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }
    }



}
