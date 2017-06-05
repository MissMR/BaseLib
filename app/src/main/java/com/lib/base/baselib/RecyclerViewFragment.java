package com.lib.base.baselib;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.mingren.lib.baselibrary.BaseFragment;
import com.mingren.lib.baselibrary.inject.InjectView;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewFragment extends BaseFragment {
    @InjectView(R.id.recylerView)
    RecyclerView recyclerView;
    List<String> list = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler_view;
    }

    private void initData(){
        for (int i = 0; i<10;i++){
            list.add("城市"+i);
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        LinearLayoutManager  layoutManager = new LinearLayoutManager(getMyActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getMyActivity(),4);

        initData();
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new RecycleViewAdapter(list,getMyActivity()));
    }
}
