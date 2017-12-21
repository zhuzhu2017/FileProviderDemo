package com.allen.fileproviderdemo.model.main;

import com.allen.fileproviderdemo.model.main.listener.UpdateListener;

/**
 * 版本信息操作接口
 * Created by allen on 2017/12/21.
 */

public interface IVersionAction {
    /**
     * 更新
     *
     * @param listener
     */
    void update(UpdateListener listener);
}
