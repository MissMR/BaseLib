package com.mingren.lib.baselibrary.baseview;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mingren.lib.baselibrary.R;
import com.mingren.lib.baselibrary.utils.premission.ScreenUtil;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

/**
 * 带点的ViewPager
 */

public class BannerViewPager extends RelativeLayout{
    private ViewPager viewPager;
    List<ImageView> vpList ;
    private LinearLayout spotLayout;
    private int mPointDrawableResId = R.drawable.selector_banner_point;
    public int perPosition = 0;
    int direction ;
    private Handler mHandler = new Handler() ;

    public BannerViewPager(Context context) {
        super(context);
        initView(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        initViewPager(context);
    }

    public void setData(List<String> list,Context context){
        vpList = new ArrayList<>();
        initSpotLayout(list,context);
        initImageView(list,context);
        PagerAdapter  adapter = new PagerAdapter(vpList,context);
        viewPager.setAdapter(adapter );
        mHandler.postDelayed(new TimedRunnable(),2000);
    }

    private void  initImageView(List<String> list ,Context context){
        ImageView iv = null;
        for (int i = 0; i < list.size();i++){
            iv = new ImageView(context);
            iv.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(context).load(list.get(i)).into(iv);
            vpList.add(iv);
        }
    }

    /**
     *  创建ViewPager
     */
    private void initViewPager(Context context){
        viewPager = new ViewPager(context);
        ViewGroup.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(params);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                spotLayout.getChildAt(perPosition).setEnabled(false);
                spotLayout.getChildAt(position).setEnabled(true);
                perPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        this.addView(viewPager);
    }

    /**
     *  创建底部圆点
     */
    private void initSpotLayout(List<String> list,Context context){
        spotLayout = new LinearLayout(context);
        spotLayout.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        spotLayout.setLayoutParams(params);
        spotLayout.setPadding(0,20,0,20);
        initSpot(list,context);
        this.addView(spotLayout);
    }

    private void initSpot(List<String> list,Context context){
        ImageView imageView;
      if (list.size() > 0){
          for (int i = 0; i < list.size(); i++) {
              imageView = new ImageView(context);
              LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
              imageView.setLayoutParams(params);
              imageView.setPadding(0,0, (int) ScreenUtil.dip2px(context,5),0);
              imageView.setImageResource(mPointDrawableResId);
            if (i == 0){
                imageView.setEnabled(true);
            }else{
                imageView.setEnabled(false);
            }
              spotLayout.addView(imageView);
          }
      }
    }

    class  PagerAdapter extends android.support.v4.view.PagerAdapter{
        List<ImageView> list;
        Context context;

        public PagerAdapter(List<ImageView> list, Context context) {
            this.list = list;
            this.context = context;
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {          //销毁Item
            ((ViewPager)container).removeView(list.get(position));
        }



        @Override
        public Object instantiateItem(ViewGroup container, int position) {    //实例化Item
            // TODO Auto-generated method stub
            ((ViewPager)container).addView(list.get(position));
            return list.get(position);
        }

    }


    class TimedRunnable implements  Runnable{

        @Override
        public void run() {
            if (direction == 0){
                if (perPosition <= vpList.size()-1 ){
                    if (perPosition >= vpList.size()-2){
                        direction = 1;
                    }
                    viewPager.setCurrentItem(perPosition+1);
                }
            }else{
                if(perPosition >= 0){
                    if (perPosition <= 1){
                        direction = 0;
                    }
                    viewPager.setCurrentItem(perPosition - 1);
                }
            }

          mHandler.postDelayed(new TimedRunnable(),2000);
        }
    }


}
