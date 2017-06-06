package com.lib.base.baselib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mingren.lib.baselibrary.Fragment1;
import com.mingren.lib.baselibrary.tablayout.TablayoutFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class TabLayoutFragment extends TablayoutFragment {
    List<Fragment> fragments = new ArrayList<>();
    List<String> tabs = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
      initData();
        setData(fragments,tabs);
        super.onCreate(savedInstanceState);
    }

    void initData(){
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment1());

        tabs.add("one");
        tabs.add("two");
        tabs.add("three");
    }
}
