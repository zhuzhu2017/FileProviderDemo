package com.allen.fileproviderdemo.net;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 网络请求接口
 * Created by allen on 2017/12/21.
 */

public interface NetApi {

    /**
     * 在线检测新版本
     *
     * @return
     */
    @GET("api/public/check-version")
    Call<JSONObject> checkUpdate(@QueryMap Map<String, String> updateMap);

    /**
     * 获取临时token
     *
     * @param platForm   HTML5,Android,IOS
     * @param grantType  client_credentials (固定值)
     * @param state      选填，任意值
     * @param sessionKey sessionkey(选填)
     * @return
     */
    @FormUrlEncoded
    @POST("api/oauth2.0/gettoken")
    Call<JSONObject> getTempToken(@Field("platform") String platForm,
                                  @Field("grant_type") String grantType,
                                  @Field("state") String state,
                                  @Field("sessionkey") String sessionKey);

    /**
     * 下载APk
     * @param fileUrl
     * @return
     */
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
