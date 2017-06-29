package com.mingren.lib.baselibrary.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.mingren.lib.baselibrary.basefragment.BaseFragment;
import com.mingren.lib.baselibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/28.
 */

public abstract class TablayoutFragment extends BaseFragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fragments=new ArrayList() ;
    List<String> tabs = new ArrayList();

    public abstract void initData( List<Fragment> fragments ,List<String> tabs);

    @Override
    protected int getLayoutId() {
        return  R.layout.fragment_tabhost;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
      if (fragments.size() > 0 || tabs.size() > 0){
          fragments.clear();
          tabs.clear();
      }

        initData(fragments,tabs);
        initViewPager(view);
        initTab(view);
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
