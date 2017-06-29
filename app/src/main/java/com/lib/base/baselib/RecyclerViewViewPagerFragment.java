package com.lib.base.baselib;

import android.os.Bundle;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.View;

import com.mingren.lib.baselibrary.basefragment.BaseFragment;
import com.mingren.lib.baselibrary.basefragment.PagerRecycleFragment;
import com.mingren.lib.baselibrary.inject.InjectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/6/8.
 */

public class RecyclerViewViewPagerFragment extends PagerRecycleFragment {
    private static final String[] TITLE = new String[] { "今日头条", "全国房产", "另一面", "女人",
            "财经", "数码", "情感", "科技","GG","MM","CC","AA", };

    @Override
    protected List<String> initData() {
        return Arrays.asList(TITLE);
    }
}
