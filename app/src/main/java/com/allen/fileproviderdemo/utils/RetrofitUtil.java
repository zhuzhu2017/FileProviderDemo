package com.allen.fileproviderdemo.utils;

import com.allen.fileproviderdemo.app.Constants;
import com.allen.fileproviderdemo.net.NetApi;
import com.allen.fileproviderdemo.net.RetrofitInterceptor;
import com.allen.fileproviderdemo.net.converter.JsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 网络请求工具类
 * Created by allen on 2017/12/21.
 */

public class RetrofitUtil {
    /**
     * 网络请求
     *
     * @return
     */
    public static NetApi connToServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(genericClient())
                .addConverterFactory(JsonConverterFactory.create())
                .build();
        return retrofit.create(NetApi.class);
    }

    /**
     * 请求拦截，统一设置Header
     *
     * @return
     */
    private static OkHttpClient genericClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new RetrofitInterceptor()).build();
    }

}
