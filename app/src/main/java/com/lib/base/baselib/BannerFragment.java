package com.lib.base.baselib;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingren.lib.baselibrary.BaseFragment;
import com.mingren.lib.baselibrary.inject.InjectView;

import java.util.ArrayList;
import java.util.List;

public class BannerFragment extends BaseFragment {
    @InjectView(R.id.bvp)
    BannerViewPager bvp;
    List<String> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_banner;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        list.clear();
       list.add("http://img1.imgtn.bdimg.com/it/u=980324694,3606002735&fm=23&gp=0.jpg");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3979340985,1400351320&fm=23&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=980324694,3606002735&fm=23&gp=0.jpg");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3979340985,1400351320&fm=23&gp=0.jpg");

        bvp.setData(list,getMyActivity());
    }



}
