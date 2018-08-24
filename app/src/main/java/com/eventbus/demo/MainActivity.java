package com.eventbus.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import eventbus.yeyu.com.library.anno.Subscribe;
import eventbus.yeyu.com.library.anno.ThreadMode;
import eventbus.yeyu.com.library.base.Eventbus;

public class MainActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Eventbus.getDefault().register(this);
        text = (TextView) findViewById(R.id.text_content);
    }

    public void change(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    //主线程---子线程接受
    @Subscribe(threadMode = ThreadMode.Async)
    public void receive(Demo demo) {
        Log.i("MainActivity", "thread: " + Thread.currentThread().getName());
        text.setText(demo.getName()+demo.getPassword());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Eventbus.getDefault().unregister(this);
    }
}
