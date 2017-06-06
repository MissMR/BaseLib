package com.lib.base.baselib;



import android.support.v4.app.FragmentTabHost;

import com.mingren.lib.baselibrary.Fragment1;
import com.mingren.lib.baselibrary.TabHostActivity;



/**
 * Created by Administrator on 2017/4/17.
 */

public class MainActivity extends TabHostActivity {

    @Override
    protected void initTabHost(FragmentTabHost tabHost) {
        tabHost.addTab(setTabHost("one", com.mingren.lib.baselibrary.R.drawable.btn_one,R.string.one),RetrofitDemoFragment.class,null);
        tabHost.addTab(setTabHost("two", com.mingren.lib.baselibrary.R.drawable.btn_two, R.string.two), TabLayoutFragment.class,null);
        tabHost.addTab(setTabHost("three", com.mingren.lib.baselibrary.R.drawable.btn_three, R.string.three),BannerFragment.class,null);
        tabHost.addTab(setTabHost("four", com.mingren.lib.baselibrary.R.drawable.btn_four, R.string.four),Fragment1.class,null);
        tabHost.addTab(setTabHost("five", com.mingren.lib.baselibrary.R.drawable.btn_five, R.string.five),Fragment1.class,null);
    }
}
