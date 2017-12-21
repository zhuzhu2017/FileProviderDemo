package com.allen.fileproviderdemo.model.main.listener;

import com.allen.fileproviderdemo.model.main.VersionBean;

/**
 * 监听更新接口
 * Created by allen on 2017/12/21.
 */

public interface UpdateListener {
    /**
     * 需要更新时调用
     *
     * @param dataBean
     */
    void onUpdate(VersionBean.DataBean dataBean);

    /**
     * 不需要更新时调用
     */
    void onError();

    /**
     * 网络异常时调用
     *
     * @param e
     */
    void onNetError(Throwable e);
}
