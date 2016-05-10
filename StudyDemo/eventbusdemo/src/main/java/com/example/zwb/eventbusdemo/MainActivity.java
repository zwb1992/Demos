package com.example.zwb.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import event.MyEvent;
import service.MyService;

//eventBus的基本使用
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.bt)
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUIThread(MyEvent event) {//在ui线程
        tv.setText(event.getSuccess() + "\n" + event.getResponse() + "\n" + event.getFailed());
        Log.i("info", "在UI线程中执行");
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void backGround(MyEvent event) {//在后台线程
        Log.i("info", "=backGround  主-子，子-子===" + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void posting(MyEvent event) {//默认方式, 在发送线程执行
        Log.i("info", "==ThreadMode.POSTING  默认方式, 在发送线程执行====" + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void async(MyEvent event) {
        Log.i("info", "ThreadMode.ASYNC  子线程执行====" + Thread.currentThread().getName());
    }

    @OnClick(R.id.bt)
    public void onClick() {
//        goNext(); //从另外一个actiivty的ui线程传递消息
//        testThread();//从子线程发送消息
        goService();//从service中传递消息，不能跨进程
    }

    private void goService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private void testThread() {
        MyThread thread = new MyThread();
        thread.setName("子线程");
        thread.start();
    }

    private void goNext() {
        Intent intent = new Intent(this, NextActivity.class);
        startActivity(intent);
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            EventBus.getDefault().post(new MyEvent("失败", "子线程发送消息----原因", "成功"));
        }
    }
}
