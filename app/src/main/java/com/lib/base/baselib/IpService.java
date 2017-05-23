package com.lib.base.baselib;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/5/16.
 */

public interface IpService {

    public final static String TIKUNAME = "app=newlandstudy";
    public final static  String Advertisement = ".poster";
        @GET(TIKUNAME+Advertisement)
        Call<LoginData> getTest();

    @POST(TIKUNAME+Advertisement)
    Call<LoginData> postTest();

    // 下载文件
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);
}
