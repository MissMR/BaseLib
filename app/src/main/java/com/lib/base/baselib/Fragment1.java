package com.lib.base.baselib;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import com.mingren.lib.baselibrary.BaseFragment;
import com.mingren.lib.baselibrary.inject.InjectView;
import com.mingren.lib.baselibrary.utils.premission.PermissionLisenter;
import com.mingren.lib.baselibrary.utils.premission.PermissionUtil;


/**
 * Created by Administrator on 2017/5/11.
 */

public class Fragment1 extends BaseFragment implements PermissionLisenter{
    @InjectView(R.id.tv)
    TextView textView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment1;
    }
    Handler handler = new Handler();
    @Override
    protected void initView(View view, Bundle bundle) {
        getMyActivity().setPermission(this);
        textView.setText("aaaaaaaaaa");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PermissionUtil.buildVersion()){
                    PermissionUtil.permission(getMyActivity(),"打开相机权限",PermissionUtil.cameraPermission,PermissionUtil.CAMERA);
                }
            }
        });
    }

    @Override
    public void permissionRunnable() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
}
