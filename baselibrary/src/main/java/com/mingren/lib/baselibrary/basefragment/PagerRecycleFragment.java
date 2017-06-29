package com.mingren.lib.baselibrary.basefragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mingren.lib.baselibrary.R;
import com.mingren.lib.baselibrary.baseadapter.BaseFragmentPagerAdapter;
import com.mingren.lib.baselibrary.baseadapter.PagerRecycleAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public abstract class PagerRecycleFragment extends BaseFragment {

    PagerRecycleAdapter pagerRecycleAdapter;
    ViewPager viewPager;
    RecyclerView recyclerView;

    protected abstract List<String> initData();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager_recycle;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        initRecyclerView(view);
        setData(initData());

    }

    private void initRecyclerView(View view){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     *  为RecycleView设置数据
     * @param list
     */
    public void setData(List<String> list){
        pagerRecycleAdapter =  new PagerRecycleAdapter(list,getActivity());
        pagerRecycleAdapter.setOnItemClickLisitener(new PagerRecycleAdapter.OnItemClickLisitener() {
            @Override
            public void OnItemClick(int position) {
                viewPager.setCurrentItem(position);

            }
        });

        recyclerView.setAdapter(pagerRecycleAdapter);

        viewPager.setAdapter(new BaseFragmentPagerAdapter(getChildFragmentManager(),list));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                recyclerView.smoothScrollToPosition(position);
                pagerRecycleAdapter.select(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
