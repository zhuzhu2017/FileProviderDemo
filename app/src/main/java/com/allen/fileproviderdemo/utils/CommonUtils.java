package com.allen.fileproviderdemo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 通用工具类
 * Created by allen on 2017/12/21.
 */

public class CommonUtils {

    //版本号
    public static int getVersionCode(Context context) {
        if (context == null) return 0;
        int versionCode = 0;
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getApplicationContext().getPackageName(), PackageManager.GET_CONFIGURATIONS);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

}
