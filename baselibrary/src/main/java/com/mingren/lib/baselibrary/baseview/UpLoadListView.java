package com.mingren.lib.baselibrary.baseview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mingren.lib.baselibrary.R;


/**
 * Created by Administrator on 2017/6/27.
 */

public class UpLoadListView extends ListView implements AbsListView.OnScrollListener{

    private View footerView; //底部加载时的布局
    private TextView tv_load_more; // footerView中文字部分
    ProgressBar progressBar;

    private int state=0 ; // 加载状态
    public final int LOADALLOW = 0; // 允许加载状态
    public final int LOADEND = 1; //  加载到最后

    private OnUpLoadListener upLoadListener;
    SwipeRefreshLayout swipeRefreshLayout;

    public UpLoadListView(Context context) {
        super(context);
        init(context);
    }

    public UpLoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UpLoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    // 初始化并添加footView
    private void init(Context context){
        footerView =  LayoutInflater.from(context).inflate(R.layout.footer_more, null,false);
        tv_load_more= (TextView) footerView.findViewById(R.id.tv_load_more);
        progressBar = (ProgressBar) footerView.findViewById(R.id.pb_load_progress);
        tv_load_more.setText("加载更多");
        this.addFooterView(footerView);
        footerView.setVisibility(View.GONE);
        this.setOnScrollListener(this);
    }

    // 设置上拉加载回调
    public void setUpLoadListener(OnUpLoadListener upLoadListener){
        this.upLoadListener= upLoadListener;
    }
    // 状态切换
    public void setState(int state){
        this.state = state;
        switch (state){
            case LOADALLOW:
                progressBar.setVisibility(View.VISIBLE);
                tv_load_more.setText("加载更多");
                footerView.setVisibility(View.GONE);
                break;
            case LOADEND:
                tv_load_more.setText("没有更多数据了");
                progressBar.setVisibility(View.GONE);
                break;
        }
    }

    //判断是否到达了底部
    private boolean isBottom() {
        if (this != null && this.getAdapter() != null) {
            return this.getLastVisiblePosition() == (this.getAdapter().getCount() - 1);
        }
        return false;
    }

    // 滑动结束后，判断是否滑动到了底部，如果滑动到底部，判断当前状态
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isBottom()){
            // 如果当前状态为可以加载状态，则显示footerView
            if(state == LOADALLOW){
                footerView.setVisibility(View.VISIBLE);
                if (upLoadListener != null){
                    upLoadListener.onLoad();
                }
            }
        }
    }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (swipeRefreshLayout != null){
            boolean enable = false;
            if(this.getChildCount() > 0){
                // check if the first item of the list is visible
                boolean firstItemVisible = this.getFirstVisiblePosition() == 0;
                // check if the top of the first item is visible
                boolean topOfFirstItemVisible = this.getChildAt(0).getTop() == 0;
                // enabling or disabling the refresh layout
                enable = firstItemVisible && topOfFirstItemVisible;
            }
            swipeRefreshLayout.setEnabled(enable);
        }

    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout){
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    //加载更多的监听器
    public static interface OnUpLoadListener {
        public void onLoad();
    }
}
