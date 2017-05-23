package com.lib.base.baselib;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mingren.lib.baselibrary.*;
import com.mingren.lib.baselibrary.inject.InjectView;
import com.mingren.lib.baselibrary.okhttp.ProgressRequestListener;
import com.mingren.lib.baselibrary.okhttp.ProgressResponseListener;
import com.mingren.lib.baselibrary.okhttp.ServiceGenerator;
import com.mingren.lib.baselibrary.utils.ToastUtils;
import com.mingren.lib.baselibrary.utils.premission.PermissionLisenter;
import com.mingren.lib.baselibrary.utils.premission.PermissionUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


/**
 * Created by Administrator on 2017/5/11.
 */

public class Fragment1 extends BaseFragment implements PermissionLisenter,ProgressResponseListener{
    @InjectView(R.id.tv)
    TextView textView;
    String url = "http://linux.zhongyuedu.com/php/apk/vlinchuangzhiye.apk";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment1;
    }

    Call<ResponseBody> call;
    IpService downloadService;
    @Override
    protected void initView(View view, Bundle bundle) {
        getMyActivity().setPermission(this);
        textView.setText("下载");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtil.permission(Fragment1.this,"打开读写权限",PermissionUtil.writePermission,PermissionUtil.WRITE_EXTERNAL_STORAGE);
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
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body(), "1.apk");
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


    @Override
    public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
        Log.e("read",bytesRead+""+done);
    }

    private boolean writeResponseBodyToDisk(ResponseBody body,String savaName) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getMyActivity().getExternalFilesDir(null) + File.separator + savaName);
            Log.e("file",futureStudioIconFile.toString());
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.w("saveFile", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
