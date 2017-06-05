package com.lib.base.baselib;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.mingren.lib.baselibrary.AppActivity;
import com.mingren.lib.baselibrary.BaseFragment;
import com.mingren.lib.baselibrary.tablayout.TablayoutFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/4/17.
 */

public class TActivity extends AppActivity {
    List<Fragment> fragments = new ArrayList<>();
    List<String> tabs = new ArrayList<>();

    @Override
    protected BaseFragment getFirstFragment() {

        return  new RecyclerViewFragment();
        //return new RetrofitDemoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    private void initData(){
        for (int i = 0; i < 8;i++){
            fragments.add(new Fragment2());
            tabs.add("fragment"+i);
        }
    }
}
