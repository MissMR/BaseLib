package com.mingren.lib.baselibrary.baseadapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mingren.lib.baselibrary.Fragment1;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public  class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    List<String> list;
    public BaseFragmentPagerAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        //新建一个Fragment来展示ViewPager item的内容，并传递参数
        Fragment fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString("title", list.get(position));
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position % list.size());
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
