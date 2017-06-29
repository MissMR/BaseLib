package com.mingren.lib.baselibrary;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;

import com.mingren.lib.baselibrary.basefragment.TabHostActivity;

public class TabHostTestActivity extends TabHostActivity {

    @Override
    protected void initTabHost(FragmentTabHost tabHost) {
        tabHost.addTab(setTabHost("one",R.drawable.btn_one,R.string.one),Fragment1.class,null);
        tabHost.addTab(setTabHost("two",R.drawable.btn_two,R.string.two),Fragment2.class,null);
        tabHost.addTab(setTabHost("three",R.drawable.btn_three,R.string.three),Fragment1.class,null);
        tabHost.addTab(setTabHost("four",R.drawable.btn_four,R.string.four),Fragment1.class,null);
        tabHost.addTab(setTabHost("five",R.drawable.btn_five,R.string.five),Fragment1.class,null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
