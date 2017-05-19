package com.lib.base.baselib;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.mingren.lib.baselibrary.*;
import com.mingren.lib.baselibrary.inject.InjectView;

/**
 * Created by Administrator on 2017/5/11.
 */

public class Fragment1 extends BaseFragment {
    @InjectView(R.id.tv)
    TextView textView;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment1;
    }
    Handler handler = new Handler();
    @Override
    protected void initView(View view, Bundle bundle) {
        textView.setText("dddddd");

    }
}
