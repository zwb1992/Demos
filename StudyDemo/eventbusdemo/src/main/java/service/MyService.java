package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.greenrobot.eventbus.EventBus;

import event.MyEvent;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        EventBus.getDefault().post(new MyEvent("失败","从service中传递消息","成功"));
        return super.onStartCommand(intent, flags, startId);
    }
}
