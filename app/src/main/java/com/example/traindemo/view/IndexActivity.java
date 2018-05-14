package com.example.traindemo.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.traindemo.R;

/*
* 进入主界面
* */

public class IndexActivity extends AppCompatActivity implements View.OnClickListener{
    private Button trainManagerBtn;
    private Button guestLoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        /*
        * 绑定视图中的控件
        * */
        initView();
        /*
        * 设置点击事件
        * */
        initEvents();
    }


    private void initEvents() {
        guestLoginBtn.setOnClickListener(this);
        trainManagerBtn.setOnClickListener(this);
    }

    private void initView() {
        trainManagerBtn = findViewById(R.id.trainManagerBtn);
        guestLoginBtn = findViewById(R.id.guestLoginBtn);
    }

    private void initUI() {
        //状态栏处理
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);//设为透明色

        }
        setContentView(R.layout.activity_index);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trainManagerBtn:
                Intent intent = new Intent(this, TrainActivity.class);
                startActivity(intent);
                break;
            case R.id.guestLoginBtn:
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
