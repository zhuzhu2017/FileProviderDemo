package com.allen.fileproviderdemo.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求拦截器
 * Created by allen on 2017/12/21.
 */

public class RetrofitInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String authHeader = "";
        Request request = chain.request()
                .newBuilder()
                .header("ttm_token", authHeader)
                .header("version", "v1.92")
                .build();
        return chain.proceed(request);
    }
}
