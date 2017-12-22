package com.allen.fileproviderdemo.model.splash;

import com.allen.fileproviderdemo.app.Constants;
import com.allen.fileproviderdemo.net.NetApi;
import com.allen.fileproviderdemo.net.ServiceGenerator;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 实现获取token的接口
 * Created by allen on 2017/12/21.
 */

public class ITokenActionImp implements ITokenAction {
    @Override
    public void onGetedTempToken(final OnTokenGetedListener listener) {
        ServiceGenerator.createService(NetApi.class, "authorization", "tongtongmall", Constants.AUTH_GET_TOKEN_SECRET)
                .getTempToken("android", "client_credentials", "random", "")
                .enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                        if (response.body() == null) {
                            listener.onError();
                            return;
                        }
                        try {
                            int code = response.body().getInt("code");
                            if (code != 1100) {
                                listener.onError();
                                return;
                            }
                            listener.onGetToken(response.body().getString("access_token"));
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                        listener.onNetError(t);
                    }
                });
    }
}
