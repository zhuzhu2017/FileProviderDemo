package com.allen.fileproviderdemo.presenter.main;

import android.content.Context;
import android.widget.Toast;

import com.allen.fileproviderdemo.model.main.IVersionActionImp;
import com.allen.fileproviderdemo.model.main.VersionBean;
import com.allen.fileproviderdemo.model.main.listener.UpdateListener;
import com.allen.fileproviderdemo.utils.CommonUtils;
import com.allen.fileproviderdemo.view.main.IUpdateView;

/**
 * 在线更新presenter
 * Created by allen on 2017/12/21.
 */

public class UpdatePresenter {

    private IUpdateView updateView;
    private IVersionActionImp versionActionImp;

    public UpdatePresenter(IUpdateView updateView) {
        this.updateView = updateView;
        this.versionActionImp = new IVersionActionImp();
    }

    public void checkUpdate(final Context context) {
        updateView.showLoading();
        versionActionImp.update(new UpdateListener() {
            @Override
            public void onUpdate(VersionBean.DataBean dataBean) {
                updateView.hideLoading();
                int curVersion = CommonUtils.getVersionCode(context);
                if (curVersion < Integer.valueOf(dataBean.getVercode())) {
                    Toast.makeText(context, "开始下载了", Toast.LENGTH_SHORT).show();
                } else {
                    updateView.unneedUpdate();
                }
            }

            @Override
            public void onError() {
                updateView.showError();
                updateView.hideLoading();
            }

            @Override
            public void onNetError(Throwable e) {
                updateView.showNetError();
                updateView.hideLoading();
            }
        });
    }

}
