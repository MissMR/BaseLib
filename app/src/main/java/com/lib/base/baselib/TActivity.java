package com.lib.base.baselib;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.mingren.lib.baselibrary.AppActivity;
import com.mingren.lib.baselibrary.BaseFragment;


/**
 * Created by Administrator on 2017/4/17.
 */

public class TActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return new Fragment1();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }
}
