package com.mingren.lib.baselibrary.baseview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mingren.lib.baselibrary.R;


/**
 * 带有上拉加载的SwipeRefreshLayout
 */
public class VerticalSwipeRefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener{
    private int scaleTouchSlop;
    private float preX,preY;
    private OnLoadListener mOnLoadListener; //上拉加载监听
    private ListView mListView;
    private int mYDown; //按下时的y坐标
    private int mLastY; //抬起时的y坐标
    private View mListViewFooter; //底部加载时的布局
    private boolean isLoading; //是否正在加载
    private TextView tv_load_more;
    private ProgressBar pb_load_progress;

    public VerticalSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        scaleTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.footer_more, null,false);
        tv_load_more= (TextView) mListViewFooter.findViewById(R.id.tv_load_more);
        tv_load_more.setText("加载更多");
        mListViewFooter.setVisibility(View.GONE);
        pb_load_progress= (ProgressBar) mListViewFooter.findViewById(R.id.pb_load_progress);
        pb_load_progress.setVisibility(View.GONE);
    }

    public void setLoading(boolean loading){
        isLoading=loading;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //初始化listview对象
        if (mListView == null) {
            int childs = getChildCount();
            if (childs > 0) {
                View childView = getChildAt(0);
                if (childView instanceof ListView) {
                    mListView = (ListView) childView;
                    mListView.addFooterView(mListViewFooter);
                    mListView.setOnScrollListener(this);
                }
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mYDown = (int) ev.getRawY();
                preX=(int)ev.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
//                if(isLoading){
//                    break;
//                }
                mLastY = (int) ev.getRawY();
                float instanceX = Math.abs(moveX - preX);
                // 容差值大概是24，再加上60
                if(instanceX > scaleTouchSlop + 100){
                    return false;
                }

                break;

            case MotionEvent.ACTION_UP:
//                if (canLoad())
//
//                    loadData();
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    //判断是否可以加载更多
    private boolean canLoad() {
        return isBottom() && !isLoading && isPullUp();
    }

    //判断是否到达了底部
    private boolean isBottom() {
        if (mListView != null && mListView.getAdapter() != null) {
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        }
        return false;
    }

    //判断是否是上拉操作
    private boolean isPullUp() {
        return (mYDown - mLastY) >= scaleTouchSlop;
    }

    //加载操作
    private void loadData() {
        if (mOnLoadListener != null) {
            //setLoading(true);
            //isLoading=false;
            mOnLoadListener.onLoad(mListViewFooter);
        }
    }

//    public void setLoading(boolean loading) {
//        isLoading = loading;
//        if (isLoading) {
//            mListView.addFooterView(mListViewFooter);
//        }
//        else {
//            mListView.removeFooterView(mListViewFooter);
//            mYDown = 0;
//            mLastY = 0;
//        }
//    }

    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    @Override
    public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {

    }

    @Override
    public void onScrollStateChanged(AbsListView arg0, int arg1) {
        //滚动到底部也可以加载更多
        if (tv_load_more.getText().equals("加载更多")){
            if (canLoad()) {
                mListViewFooter.setVisibility(View.VISIBLE);
                loadData();
            }else{
                mListViewFooter.setVisibility(View.GONE);
            }
        }else{
            if (!isPullUp()){
                mListViewFooter.setVisibility(View.GONE);
            }else{
                mListViewFooter.setVisibility(View.VISIBLE);
            }
        }


//        if(tv_load_more.getText().toString().equals("暂无数据")){
//            mYDown = 0;
//            mLastY = 0;
//        }
    }


    //加载更多的监听器
    public static interface OnLoadListener {
        public void onLoad(View mListViewFooter);
    }
}
