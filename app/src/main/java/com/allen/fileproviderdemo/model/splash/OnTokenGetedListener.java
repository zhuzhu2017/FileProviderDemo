package com.allen.fileproviderdemo.model.splash;

/**
 * 获取到临时Token的监听
 * Created by allen on 2017/12/21.
 */

public interface OnTokenGetedListener {
    void onGetToken(String tempToken);

    void onNetError(Throwable e);

    void onError();
}
