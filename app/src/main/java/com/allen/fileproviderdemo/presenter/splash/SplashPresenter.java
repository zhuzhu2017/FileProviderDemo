package com.allen.fileproviderdemo.presenter.splash;

import com.allen.fileproviderdemo.app.Constants;
import com.allen.fileproviderdemo.model.splash.ITokenActionImp;
import com.allen.fileproviderdemo.model.splash.OnTokenGetedListener;
import com.allen.fileproviderdemo.view.splash.ISplashView;

/**
 * 引导页Presenter
 * Created by allen on 2017/12/22.
 */

public class SplashPresenter {

    private ISplashView splashView;
    private ITokenActionImp tokenActionImp;

    public SplashPresenter(ISplashView splashView) {
        this.splashView = splashView;
        this.tokenActionImp = new ITokenActionImp();
    }

    public void getTempToken() {
        splashView.showLoading();
        tokenActionImp.onGetedTempToken(new OnTokenGetedListener() {
            @Override
            public void onGetToken(String tempToken) {
                splashView.hideLoding();
                Constants.TEMP_TOKEN = tempToken;
                splashView.onSuccess();
            }

            @Override
            public void onNetError(Throwable e) {
                splashView.hideLoding();
                splashView.showNetError();
            }

            @Override
            public void onError() {
                splashView.hideLoding();
                splashView.showError();
            }
        });
    }

}
