package com.mingren.lib.baselibrary.okhttp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.protobuf.ProtoConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Cmad on 2016/4/28.
 */
public class ServiceGenerator {
    private static final String HOST = "http://www.xxx.com/ ";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(HOST)
            .addConverterFactory(ProtoConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());


    public static <T> T createService(Class<T> tClass){
        return builder.build().create(tClass);
    }


    /**
     * 创建带响应进度(下载进度)回调的service
     */
    public static <T> T createResponseService(Class<T> tClass, ProgressResponseListener listener){
        return builder
                .client(HttpClientHelper.addProgressResponseListener(listener))
                .build()
                .create(tClass);
    }


    /**
     * 创建带请求体进度(上传进度)回调的service
     */
    public static <T> T createReqeustService(Class<T> tClass, ProgressRequestListener listener){
        return builder
                .client(HttpClientHelper.addProgressRequestListener(listener))
                .build()
                .create(tClass);
    }

}
