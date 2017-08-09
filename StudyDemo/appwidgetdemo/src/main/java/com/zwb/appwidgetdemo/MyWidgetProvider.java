package com.zwb.appwidgetdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by zwb
 * Description
 * Date 2017/8/9.
 */

public class MyWidgetProvider extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.e("info", "=MyWidgetProvider=========onReceive==========" + intent.getAction());
        if (intent.getAction().equals("text.com")) {
            Intent intent1 = new Intent(context, MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.e("info", "=MyWidgetProvider=========onUpdate==========");
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.appwidget);
        //分别绑定id
        remoteViews.setOnClickPendingIntent(R.id.textView, getPendingIntent(context, R.id.textView));
        //更新widget
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    private PendingIntent getPendingIntent(Context context, int resID) {
        Intent intent = new Intent();
        intent.setClass(context, MyWidgetProvider.class);//如果没有这一句，表示匿名的。加上表示是显式的。在单个按钮的时候是没啥区别的，但是多个的时候就有问题了
        intent.setAction("text.com");
        //设置data域的时候，把控件id一起设置进去，
        // 因为在绑定的时候，是将同一个id绑定在一起的，所以哪个控件点击，发送的intent中data中的id就是哪个控件的id
        intent.setData(Uri.parse("id:" + resID));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        return pendingIntent;
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.e("info", "=MyWidgetProvider=========onDeleted==========");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.e("info", "=MyWidgetProvider=========onEnabled==========");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.e("info", "=MyWidgetProvider=========onDisabled==========");
    }
}
