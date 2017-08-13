package com.zwb.customlauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.zwb.customlauncher.bean.NewsBean;
import com.zwb.customlauncher.bean.WeatherBean;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private LinearLayout ll_info;
    private Animation animation;
    private OkHttpClient client;
    private List<NewsBean.T1348647909107Bean> news = new ArrayList<>();
    public static final int NEWS = 1;
    public static final int WEATHER = 2;
    private int newIndex = 0;

    private ImageView imgNews;
    private TextView tvTitle;
    private TextView tvContent;
    private WeatherBean weatherBean;

    //天气控件
    private TextView tvWenDu, tvWeatherState, tvRiqi, tvTime;
    private ImageView imgTianqi;
    private SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
    private int coner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new OkHttpClient();
        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_in);
        ll_info = (LinearLayout) findViewById(R.id.ll_info);
        imgNews = (ImageView) findViewById(R.id.img_news);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvContent = (TextView) findViewById(R.id.tvContent);

        tvWenDu = (TextView) findViewById(R.id.tvWenDu);
        tvWeatherState = (TextView) findViewById(R.id.tvWeatherState);
        tvRiqi = (TextView) findViewById(R.id.tvRiqi);
        tvTime = (TextView) findViewById(R.id.tvTime);
        imgTianqi = (ImageView) findViewById(R.id.imgTianqi);

        coner = (int) (5 * getResources().getDisplayMetrics().density);

        registerReceiver(mTimeRefreshReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
        setTime();
        handler.post(newsRunnable);
        getWeather();
    }

    private void setTime() {
        String time = hourFormat.format(new Date());
        tvTime.setText("" + time);
        //早晨八点以及下午4点更新天气
        if ("08:00".equals(time) || "16:00".equals(time)) {
            getWeather();
        }
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        String weekStr = "周";
        switch (week) {
            case 1:
                weekStr += "日";
                break;
            case 2:
                weekStr += "一";
                break;
            case 3:
                weekStr += "二";
                break;
            case 4:
                weekStr += "三";
                break;
            case 5:
                weekStr += "四";
                break;
            case 6:
                weekStr += "五";
                break;
            case 7:
                weekStr += "六";
                break;
        }
        tvRiqi.setText(timeFormat.format(new Date()) + "  " + weekStr);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what == NEWS) {
                newIndex = 0;
                handler.removeCallbacks(getDataRunnable);
                handler.post(getDataRunnable);
            } else if (what == WEATHER) {
                if (weatherBean != null && weatherBean.getResult() != null) {
                    if (weatherBean.getResult().getToday() != null) {
                        tvWenDu.setText("" + weatherBean.getResult().getToday().getTemperature());
                        tvWeatherState.setText("" + weatherBean.getResult().getToday().getWeather());

                        if (weatherBean.getResult().getToday().getWeather_id() != null) {
                            String fa = weatherBean.getResult().getToday().getWeather_id().getFa();
                            if ("00".equals(fa) || "01".equals(fa)) {
                                imgTianqi.setImageResource(R.mipmap.sun);
                            } else {
                                imgTianqi.setImageResource(R.mipmap.rain);
                            }
                        }
                    }
                }
            }
        }
    };

    private Runnable getDataRunnable = new Runnable() {
        @Override
        public void run() {
            if (newIndex >= news.size()) {
                newIndex = 0;
            }
            if (!TextUtils.isEmpty(news.get(newIndex).getTitle())) {
                tvContent.setText("" + news.get(newIndex).getTitle());
            }
            Glide.with(MainActivity.this)
                    .load(news.get(newIndex).getImgsrc())
                    .error(R.mipmap.photo_no)
                    .bitmapTransform(new RoundedCornersTransformation(MainActivity.this, 5, 0, RoundedCornersTransformation.CornerType.ALL))
                    .into(imgNews);
            ll_info.startAnimation(animation);
            newIndex++;
            handler.postDelayed(this, 5000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(getDataRunnable);
        handler.removeCallbacks(newsRunnable);
        unregisterReceiver(mTimeRefreshReceiver);
    }

    @Override
    public void onBackPressed() {

    }

    private float downX = 0;
    private float downY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                float dx = x - downX;
                float dy = y - downY;
                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx < 0) {
//                        Toast.makeText(this,"向左滑动",Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(this,"向右滑动",Toast.LENGTH_SHORT).show();
                        String url = null;
                        String imgUrl = null;
                        String title = null;
                        String time = null;
                        if (!news.isEmpty()) {
                            int i = newIndex - 1;
                            i = i < 0 ? 0 : i;
                            url = news.get(i).getDocid();
                            imgUrl = news.get(i).getImgsrc();
                            title = news.get(i).getTitle();
                            time = news.get(i).getLmodify();
                        }
                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                        intent.putExtra("title", title);
                        intent.putExtra("time", time);
                        intent.putExtra("imgUrl", imgUrl);
                        intent.putExtra("url", url);
                        intent.putExtra("weather", weatherBean);
                        startActivity(intent);
                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            String url = null;
            String imgUrl = null;
            String title = null;
            String time = null;
            if (!news.isEmpty()) {
                int i = newIndex - 1;
                i = i < 0 ? 0 : i;
                url = news.get(i).getDocid();
                imgUrl = news.get(i).getImgsrc();
                title = news.get(i).getTitle();
                time = news.get(i).getLmodify();
            }
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("time", time);
            intent.putExtra("imgUrl", imgUrl);
            intent.putExtra("url", url);
            intent.putExtra("weather", weatherBean);
            startActivity(intent);
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 以异步的方式去请求数据
     */
    private void getNews() {
        Request request = new Request.Builder().get().url(C.NEWS_API).addHeader("User-Agent", "Mozilla/4.0")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                handler.removeCallbacks(newsRunnable);
                handler.postDelayed(newsRunnable, 60 * 1000);
            }

            @Override // 这个方法在子线程
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Log.e("info", "====new bean=====" + result);
                try {
                    NewsBean newsBean = JSON.parseObject(result, NewsBean.class);
                    if (newsBean != null && newsBean.getT1348647909107() != null && !newsBean.getT1348647909107().isEmpty()) {
                        news.clear();
                        news.addAll(newsBean.getT1348647909107());
                        news.remove(0);
                        handler.sendEmptyMessage(1);
                    }
                    Log.e("info", "====new bean=====" + newsBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取消息
     */
    private Runnable newsRunnable = new Runnable() {
        @Override
        public void run() {
            getNews();
            handler.postDelayed(this, 1000 * 60 * 60);
        }
    };

    /**
     * 获取天气
     */
    private void getWeather() {
        Request request = new Request.Builder().get().url(C.WEATHER_API).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.postDelayed(new Runnable() {//失败之后隔一分钟尝试一次
                    @Override
                    public void run() {
                        getWeather();
                    }
                }, 1000 * 60);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                try {
                    weatherBean = JSON.parseObject(result, WeatherBean.class);
                    handler.sendEmptyMessage(2);
                    EventBus.getDefault().post(weatherBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private BroadcastReceiver mTimeRefreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
                Log.e("info", "====系统时间=====");
                setTime();
            }
        }
    };
}
