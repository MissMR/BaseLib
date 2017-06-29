package com.lib.base.baselib;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ArrayAdapter;

import com.mingren.lib.baselibrary.basefragment.BaseFragment;
import com.mingren.lib.baselibrary.baseview.UpLoadListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */

public class UpLoadFragment extends BaseFragment  {

    SwipeRefreshLayout swipeRefreshLayout;
    UpLoadListView listView;
    List<String> list  = new ArrayList<>();
    ArrayAdapter adapter;
    int page = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment2;
    }

    private void  initData(){
        for (int i = 0; i <= 3; i++){
            list.add("选项"+i);
        }
    }
    @Override
    protected void initView(View view, Bundle bundle) {
        initView(view);
        initSwipeRefreshLayout();
        initListView();
    }

    private void initView(View view){
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        listView = (UpLoadListView) view.findViewById(R.id.listView);
    }

    private void initSwipeRefreshLayout(){
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                listView.setState(listView.LOADALLOW);
                list.clear();
                addData();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void addData(){
        for ( int i = 0; i < 5; i++){
            list.add("内容"+i);
        }
    }

    private void initListView(){
        listView.setSwipeRefreshLayout(swipeRefreshLayout);
        addData();
        adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setUpLoadListener(new UpLoadListView.OnUpLoadListener() {
            @Override
            public void onLoad() {
                if (page < 3){
                    page++;
                    addData();
                    adapter.notifyDataSetChanged();
                }else {
                    listView.setState(listView.LOADEND);
                }

            }
        });

    }

}
