package com.allen.fileproviderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_take_photo)
    Button btnTakePhoto;
    @BindView(R.id.btn_sys_pic)
    Button btnSysPic;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_update, R.id.btn_take_photo, R.id.btn_sys_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                break;
            case R.id.btn_take_photo:
                break;
            case R.id.btn_sys_pic:
                break;
        }
    }
}
