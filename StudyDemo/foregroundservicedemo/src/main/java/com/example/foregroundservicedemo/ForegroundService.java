package com.example.foregroundservicedemo;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class ForegroundService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Foreground", "=======onCreate======");
        if (Build.VERSION.SDK_INT < 18) {//sdk 18之前启动前台服务不会弹出通知
            startForeground(9999, new Notification());
        }
    }

    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Foreground", "=======onStartCommand======");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setDefaults(0);
        startForeground(9898, builder.build());
        Intent intent1 = new Intent(getApplicationContext(), HelpService.class);
        startService(intent1);
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 500);
        return super.onStartCommand(intent, flags, startId);
    }

    private Handler handler = new Handler();
    private int i = 0;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.e("Foreground", "=======Foreground======" + i);
            Intent intent = new Intent("com.push.msg");
            intent.putExtra("msg", i + "");
            getApplicationContext().sendBroadcast(intent);
            i++;
            handler.postDelayed(this, 500);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Foreground", "=======onDestroy======");
        if (Build.VERSION.SDK_INT < 18) {
            stopForeground(true);
        }
        handler.removeCallbacks(runnable);
    }

    public static class HelpService extends Service {
        public HelpService() {
        }

        @Override
        public IBinder onBind(Intent intent) {
            // TODO: Return the communication channel to the service.
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getApplicationContext());
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setDefaults(0);
            this.startForeground(9898, builder.build());
            this.stopForeground(true);
            this.stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }
}
