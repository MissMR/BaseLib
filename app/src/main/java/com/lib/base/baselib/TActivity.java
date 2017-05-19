package com.lib.base.baselib;


import com.mingren.lib.baselibrary.*;



/**
 * Created by Administrator on 2017/4/17.
 */

public class TActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return new Fragment1();
    }
}
