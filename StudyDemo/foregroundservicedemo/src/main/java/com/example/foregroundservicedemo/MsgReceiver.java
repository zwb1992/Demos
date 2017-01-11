package com.example.foregroundservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MsgReceiver extends BroadcastReceiver {
    public MsgReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Foreground","====MsgReceiver=======");
        Intent intent1 = new Intent("msg");
        intent1.putExtra("text", intent.getStringExtra("msg"));
        context.sendBroadcast(intent1);
    }
}
