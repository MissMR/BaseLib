package com.lib.base.baselib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.mingren.lib.baselibrary.Fragment1;
import com.mingren.lib.baselibrary.tablayout.TablayoutFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class TabLayoutFragment extends TablayoutFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void initData(List<Fragment> fragments, List<String> tabs) {
        fragments.add(new Fragment1());
        fragments.add(new UpLoadFragment());
        fragments.add(new Fragment1());

        tabs.add("one");
        tabs.add("two");
        tabs.add("three");
    }
    
}
