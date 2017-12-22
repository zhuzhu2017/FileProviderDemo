package com.allen.fileproviderdemo.view.splash;

/**
 * 启动页
 * Created by allen on 2017/12/22.
 */

public interface ISplashView {
    void showLoading();

    void hideLoding();

    void showNetError();

    void showError();

    void onSuccess();
}
