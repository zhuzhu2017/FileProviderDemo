package com.allen.fileproviderdemo.net;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

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

}
