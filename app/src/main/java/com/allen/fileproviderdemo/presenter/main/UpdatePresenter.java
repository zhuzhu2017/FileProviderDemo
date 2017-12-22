package com.allen.fileproviderdemo.presenter.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import com.allen.fileproviderdemo.BuildConfig;
import com.allen.fileproviderdemo.app.Constants;
import com.allen.fileproviderdemo.model.main.IVersionActionImp;
import com.allen.fileproviderdemo.model.main.VersionBean;
import com.allen.fileproviderdemo.model.main.listener.UpdateListener;
import com.allen.fileproviderdemo.net.NetApi;
import com.allen.fileproviderdemo.net.download.DownloadProgressHandler;
import com.allen.fileproviderdemo.net.download.ProgressHelper;
import com.allen.fileproviderdemo.utils.CommonUtils;
import com.allen.fileproviderdemo.view.main.IUpdateView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
            public void onUpdate(final VersionBean.DataBean dataBean) {
                updateView.hideLoading();
                int curVersion = CommonUtils.getVersionCode(context);
                if (curVersion < Integer.valueOf(dataBean.getVercode())) {
                    //更新
                    File apkFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "ttmall.apk");
                    if (apkFile.exists()) {  //直接安装
                        installApk(context, apkFile);
                    } else { //先下载再安装
                        downloadApk(context, dataBean.getDownloadurl());
                    }
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

    /**
     * 下载APK
     *
     * @param context     上下文
     * @param downloadurl 下载Url
     */
    private void downloadApk(final Context context, String downloadurl) {
        //监听下载进度
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setProgressNumberFormat("%1d KB/%1d KB");
        dialog.setMessage("正在下载，请稍后...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();

        OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
        NetApi retrofit = new Retrofit.Builder()
                .baseUrl("http://123.103.15.164:8880/")
                .client(builder.build())
                .build().create(NetApi.class);
        retrofit.downloadFile(downloadurl)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (response.body() != null) {
                                InputStream is = response.body().byteStream();
                                File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), Constants.APK_NAME);
                                FileOutputStream fos = new FileOutputStream(file);
                                BufferedInputStream bis = new BufferedInputStream(is);
                                byte[] buffer = new byte[1024];
                                int len;
                                while ((len = bis.read(buffer)) != -1) {
                                    fos.write(buffer, 0, len);
                                    fos.flush();
                                }
                                fos.close();
                                bis.close();
                                is.close();
                                //跳转到安装页面
                                installApk(context, file);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
        //设置下载进度
        ProgressHelper.setProgressHandler(new DownloadProgressHandler() {
            @Override
            protected void onProgress(long bytesRead, long contentLength, boolean done) {
                dialog.setMax((int) (contentLength / 1024));
                dialog.setProgress((int) (bytesRead / 1024));
                if (done) {
                    dialog.dismiss();
                    return;
                }
            }
        });
    }

    /**
     * 安装APK
     *
     * @param context 上下文
     * @param apkFile apk文件
     */
    private void installApk(Context context, File apkFile) {
        Uri apkUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".myprovider", apkFile);
        Intent installIntent = new Intent(Intent.ACTION_VIEW);
        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        installIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        installIntent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        context.startActivity(installIntent);
    }

}
