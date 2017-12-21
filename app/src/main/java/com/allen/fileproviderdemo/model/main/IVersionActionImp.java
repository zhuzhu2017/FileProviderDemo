package com.allen.fileproviderdemo.model.main;

import com.allen.fileproviderdemo.model.main.listener.UpdateListener;
import com.allen.fileproviderdemo.utils.RetrofitUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 更新实现类
 * Created by allen on 2017/12/21.
 */

public class IVersionActionImp implements IVersionAction {
    @Override
    public void update(final UpdateListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("versioncode", "26");
        params.put("type", "1");
        RetrofitUtil.connToServer()
                .checkUpdate(params)
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
                            JSONObject data = response.body().getJSONObject("data");
                            if (data == null) {
                                listener.onError();
                                return;
                            }
                            VersionBean.DataBean dataBean = new VersionBean.DataBean();
                            dataBean.setDesc(data.getString("desc"));
                            dataBean.setDownloadurl(data.getString("downloadurl"));
                            dataBean.setIver(data.getString("iver"));
                            dataBean.setMiniver(data.getString("miniver"));
                            dataBean.setVercode(data.getString("vercode"));
                            listener.onUpdate(dataBean);
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
