package com.mingren.lib.baselibrary.baseview;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/5/31.
 */

public abstract class BaseDialog extends AlertDialog {
    // 设置布局
    public abstract  void initView(Context context);

    protected BaseDialog(Context context, View.OnClickListener listener1, View.OnClickListener listener2, View.OnClickListener listener3) {
        super(context);
        initView(context);
        this.setCanceledOnTouchOutside(true); //设置点击空白不消失
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);

    }

    protected BaseDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    /**
     * 设置宽和高
     * @param width
     * @param height
     */
    public void setSize(float width,float height){
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.width =(int)width;
        params.height = (int)height;
        this.getWindow().setAttributes(params);
    }




}
