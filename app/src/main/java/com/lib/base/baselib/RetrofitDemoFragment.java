package com.lib.base.baselib;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mingren.lib.baselibrary.basefragment.BaseFragment;
import com.mingren.lib.baselibrary.inject.InjectView;
import com.mingren.lib.baselibrary.okhttp.ProgressResponseListener;
import com.mingren.lib.baselibrary.okhttp.ServiceGenerator;
import com.mingren.lib.baselibrary.utils.FileUtils;
import com.mingren.lib.baselibrary.utils.premission.PermissionLisenter;
import com.mingren.lib.baselibrary.utils.premission.PermissionUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitDemoFragment extends BaseFragment implements PermissionLisenter,ProgressResponseListener{
    @InjectView(R.id.tv)
    TextView textView;
    @InjectView(R.id.jindu)
    TextView jindu;

    String url = "http://linux.zhongyuedu.com/php/apk/vlinchuangzhiye.apk";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment1;
    }

    Call<ResponseBody> call;
    IpService downloadService;
    boolean isDownLoad = false;
    @Override
    protected void initView(View view, Bundle bundle) {
        getMyActivity().setPermission(this);
        jindu.setText("下载进度 ： 0");
        textView.setText("下载");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isDownLoad){
                    isDownLoad = true;
                    PermissionUtil.permission(RetrofitDemoFragment.this,"打开读写权限",PermissionUtil.writePermission,PermissionUtil.WRITE_EXTERNAL_STORAGE);
                }else{
                    if (call != null){
                        call.cancel();
                        isDownLoad = false;
                    }
                }


            }
        });
        downloadService = ServiceGenerator.createResponseService(IpService.class, this);
    }

    @Override
    public void permissionRunnable() {
        call = downloadService.downloadFileWithDynamicUrlSync(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    boolean writtenToDisk = FileUtils.writeResponseBodyToDisk(getMyActivity(),response.body(), "1.apk");
                } else {
                    Log.e("Download", "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Download", "error :"+t.getMessage());
            }
        });
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String str = (String) msg.obj;
                    jindu.setText(str);
                    break;
            }
        }
    };

    @Override
    public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
        Log.e("read",bytesRead+""+done);
        Message message = new Message();
        message.what = 0;
        message.obj = "下载进度 ： "+bytesRead;
        handler.sendMessage(message);
    }


}
