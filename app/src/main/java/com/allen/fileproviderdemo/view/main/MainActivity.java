package com.allen.fileproviderdemo.view.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.allen.fileproviderdemo.R;
import com.allen.fileproviderdemo.presenter.main.UpdatePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IUpdateView {

    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_take_photo)
    Button btnTakePhoto;
    @BindView(R.id.btn_sys_pic)
    Button btnSysPic;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private UpdatePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new UpdatePresenter(this);
    }

    @OnClick({R.id.btn_update, R.id.btn_take_photo, R.id.btn_sys_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                presenter.checkUpdate(this);
                break;
            case R.id.btn_take_photo:
                break;
            case R.id.btn_sys_pic:
                break;
        }
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "服务器端异常！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetError() {
        Toast.makeText(this, "网络异常！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unneedUpdate() {
        Toast.makeText(this, "已是最新版本，不需更新！", Toast.LENGTH_SHORT).show();
    }
}
