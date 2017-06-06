package com.mingren.lib.baselibrary.tablayout;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.mingren.lib.baselibrary.BaseFragment;
import com.mingren.lib.baselibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/28.
 */

public  class TablayoutFragment extends BaseFragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fragments ;
    List<String> tabs;

    @Override
    protected int getLayoutId() {
        return  R.layout.fragment_tabhost;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        if (fragments != null && tabs != null){
           initViewPager(view);
           initTab(view);
       }
    }

    public  void setData(  List<Fragment> fragments,List<String> tabs){
        this.fragments = fragments;
        this.tabs = tabs;
    }

    private void initViewPager(View view){
        viewPager = (ViewPager) view.findViewById(R.id.vp);
        viewPager.setAdapter(new TablayoutViewPagerAdapter(getMyActivity().getSupportFragmentManager(),fragments,tabs));
    }

    private void initTab(View view){
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        //设置TabLayout的模式
        // tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(viewPager);
    }

}
