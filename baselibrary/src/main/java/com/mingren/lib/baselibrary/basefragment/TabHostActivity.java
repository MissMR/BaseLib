package com.mingren.lib.baselibrary.basefragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.mingren.lib.baselibrary.MyApplication;
import com.mingren.lib.baselibrary.R;

public abstract class TabHostActivity extends BaseActivity {

    public static FragmentTabHost mTabHost;
    public static int id = 0;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentViewId() {
        return R.id.realtabcontent;
    }

    protected  abstract  void initTabHost(FragmentTabHost tabHost);
    TabHostReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        initTabHost(mTabHost);
        receiver = new TabHostReceiver();
        IntentFilter filter = new IntentFilter() ;
        filter.addAction("tabhost") ;
        this.registerReceiver(receiver,filter);

    }


    protected TabHost.TabSpec setTabHost(String tag,int icon,int text){

        View view = LayoutInflater.from(this).inflate(R.layout.activity_main_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(icon);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(text);

        TabHost.TabSpec spec= mTabHost.newTabSpec(tag).setIndicator(view);
        return  spec;
    }




    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            ((MyApplication)getApplication()).exit();
        }

        return super.onKeyDown(keyCode, event);
    }

    public class TabHostReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
                 int id = intent.getIntExtra("id",0);
                 mTabHost.setCurrentTab(id);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null){
            this.unregisterReceiver(receiver);
        }
    }

}





