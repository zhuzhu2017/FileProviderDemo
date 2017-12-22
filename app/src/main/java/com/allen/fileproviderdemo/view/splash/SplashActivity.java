package com.allen.fileproviderdemo.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.allen.fileproviderdemo.R;
import com.allen.fileproviderdemo.presenter.splash.SplashPresenter;
import com.allen.fileproviderdemo.view.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 启动页
 * Created by allen on 2017/12/22.
 */

public class SplashActivity extends AppCompatActivity implements ISplashView {
    @BindView(R.id.btn_get_token)
    Button btnGetToken;
    @BindView(R.id.pb_waitting)
    ProgressBar pbWaitting;

    private SplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        presenter = new SplashPresenter(this);
    }

    @OnClick(R.id.btn_get_token)
    public void onViewClicked() {
        presenter.getTempToken();
    }

    @Override
    public void showLoading() {
        pbWaitting.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoding() {
        pbWaitting.setVisibility(View.GONE);
    }

    @Override
    public void showNetError() {
        Toast.makeText(this, "网络异常！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "获取Token失败！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "2秒后跳转到首页", Toast.LENGTH_SHORT).show();
        pbWaitting.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 2000);
    }
}
