package com.allen.fileproviderdemo.view.main;

/**
 * 更新视图
 * Created by allen on 2017/12/21.
 */

public interface IUpdateView {
    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void hideLoading();

    /**
     * 服务器异常
     */
    void showError();

    /**
     * 网络异常
     */
    void showNetError();

    /**
     * 不需要更新提示
     */
    void unneedUpdate();
}
