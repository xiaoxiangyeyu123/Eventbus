package com.eventbus.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import eventbus.yeyu.com.library.base.Eventbus;

/**
 * 作者：潇湘夜雨 on 2018/8/21.
 * 邮箱：879689064@qq.com
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }


    public void send(View view) {
        Eventbus.getDefault().post(new Demo("eventbus", "1234"));
    }

    public void exit(View view) {
        finish();
    }
}
