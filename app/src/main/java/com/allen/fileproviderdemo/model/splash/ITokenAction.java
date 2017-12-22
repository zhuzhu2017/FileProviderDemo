package com.allen.fileproviderdemo.model.splash;

/**
 * 获取临时Token处理接口
 * Created by allen on 2017/12/21.
 */

public interface ITokenAction {
    void onGetedTempToken(OnTokenGetedListener listener);
}
