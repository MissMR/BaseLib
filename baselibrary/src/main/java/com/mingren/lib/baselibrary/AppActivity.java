package com.mingren.lib.baselibrary;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import java.util.List;

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
        List fragments = getSupportFragmentManager().getFragments();
        Log.e("fragmentSize",fragments.size()+"");
        if (null == fragments || fragments.size() == 0 ) {
             firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }
    }



}
