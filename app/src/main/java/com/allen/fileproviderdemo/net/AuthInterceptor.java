package com.allen.fileproviderdemo.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LKZ on 2017/3/10.
 *
 *  Auth拦截器：加Header
 */

public class AuthInterceptor implements Interceptor {
    private String keyToken;
    private String valueToken;

    public AuthInterceptor(String keyToken, String valueToken) {
        this.keyToken = keyToken;
        this.valueToken = valueToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header(keyToken, valueToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
