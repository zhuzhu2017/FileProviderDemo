package com.allen.fileproviderdemo.net;

import android.text.TextUtils;

import com.allen.fileproviderdemo.app.Constants;
import com.allen.fileproviderdemo.net.converter.JsonConverterFactory;

import java.io.UnsupportedEncodingException;

import okhttp3.OkHttpClient;
import okio.ByteString;
import retrofit2.Retrofit;

/**
 * Created by Gjw on 2017/3/10.
 */

public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Constants.AUTH_BASE_URL)
                    .addConverterFactory(JsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    /**
     * 不包含Header
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    /**
     * 商户公共授权
     *
     * @param serviceClass
     * @param headerKey    这里是：Basic
     * @param AppId        这里是：Appid
     * @param AppSecret    这里是：AppSecret
     * @param <S>
     * @return
     */
    public static <S> S createService(
            Class<S> serviceClass, String headerKey, String AppId, String AppSecret) {
        if (!TextUtils.isEmpty(AppId)
                && !TextUtils.isEmpty(AppSecret)) {
            String authToken = basic(AppId, AppSecret);
            return createService(serviceClass, headerKey, authToken);
        }

        return createService(serviceClass, null, null, null);
    }


    /**
     * 公共认证转换方法
     *
     * @param AppId
     * @param AppSecret
     * @return
     */
    private static String basic(String AppId, String AppSecret) {
        try {
            String content = AppId + ":" + AppSecret;
            byte[] bytes = content.getBytes("UTF-8");
            String encoded = ByteString.of(bytes).base64();
            return "Basic " + encoded;
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError();
        }
    }

    /**
     * 包含Header
     *
     * @param serviceClass
     * @param headerKey    这里是：ttm_token
     * @param authToken    这里是：accessToken
     * @param <S>
     * @return
     */
    public static <S> S createService(
            Class<S> serviceClass, String headerKey, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthInterceptor interceptor =
                    new AuthInterceptor(headerKey, authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }
}
