package com.zwb.customlauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zwb.customlauncher.bean.WeatherBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rbNews, rbMovies;
    private NewsFragment newsFragment;
    private MoviesFragment moviesFragment;
    private FragmentManager fragmentManager;
    private int index = 0;
    private String url;
    private String imgUrl;
    private String title;
    private String time;
    private WeatherBean weatherBean;
    //天气控件
    private TextView tvWenDu, tvWeatherState, tvRiqi, tvTime;
    private ImageView imgTianqi;
    private SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉Activity上面的状态栏
        setContentView(R.layout.activity_details);

        EventBus.getDefault().register(this);

        url = getIntent().getStringExtra("url");
        imgUrl = getIntent().getStringExtra("imgUrl");
        title = getIntent().getStringExtra("title");
        time = getIntent().getStringExtra("time");
        weatherBean = (WeatherBean) getIntent().getSerializableExtra("weather");

        tvWenDu = (TextView) findViewById(R.id.tvWenDu);
        tvWeatherState = (TextView) findViewById(R.id.tvWeatherState);
        tvRiqi = (TextView) findViewById(R.id.tvRiqi);
        tvTime = (TextView) findViewById(R.id.tvTime);
        imgTianqi = (ImageView) findViewById(R.id.imgTianqi);
        fragmentManager = getSupportFragmentManager();

        rbNews = (RadioButton) findViewById(R.id.rbNews);
        rbNews.setOnClickListener(this);
        rbMovies = (RadioButton) findViewById(R.id.rbMovies);
        rbMovies.setOnClickListener(this);
        index = 0;
        setSelected(index);
        setWeather();
        registerReceiver(mTimeRefreshReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
        setTime();
    }

    /**
     * 设置时间
     */
    private void setTime() {
        tvTime.setText("" + hourFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        Log.e("info", "====" + calendar.get(Calendar.DAY_OF_WEEK));
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

    /**
     * 设置天气
     */
    private void setWeather() {
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbNews:
                setSelected(0);
                break;
            case R.id.rbMovies:
                setSelected(1);
                break;
        }
    }

    private void setSelected(int i) {
        if (index > 1) {
            return;
        }
        switch (i) {
            case 0:
                rbNews.setChecked(true);
                rbNews.setTextSize(20);
                rbMovies.setTextSize(16);
                showFragment(0);
                break;
            case 1:
                rbMovies.setChecked(true);
                rbNews.setTextSize(16);
                rbMovies.setTextSize(20);
                showFragment(1);
                break;
        }
    }

    private void showFragment(int i) {
        index = i;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (i) {
            case 0:
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("url", url);
                    bundle.putString("title", title);
                    bundle.putString("time", time);
                    bundle.putString("imgUrl", imgUrl);
                    newsFragment.setArguments(bundle);
                    transaction.add(R.id.frameLayout, newsFragment);
                } else {
                    transaction.show(newsFragment);
                }
                if (moviesFragment != null) {
                    transaction.hide(moviesFragment);
                }
                break;
            case 1:
                if (moviesFragment == null) {
                    moviesFragment = new MoviesFragment();
                    transaction.add(R.id.frameLayout, moviesFragment);
                } else {
                    transaction.show(moviesFragment);
                }
                if (newsFragment != null) {
                    transaction.hide(newsFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
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
                    Log.e("info", "滑动的距离 x====" + dx);
                    Log.e("info", "滑动的距离 y====" + dy);
                    if (Math.abs(dx) >= 40) {//移动了40个像素才切换
                        if (dx < 0) {
//                        Toast.makeText(this,"向左滑动",Toast.LENGTH_SHORT).show();
                            finish();
                            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        } else {
                            index++;
                            if (index > 1) {
                                index = 0;
                            }
                            setSelected(index);
//                        Toast.makeText(this,"向右滑动",Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {//上下滑动
                    Log.e("info", "滑动的距离 x====" + dx);
                    Log.e("info", "滑动的距离 y====" + dy);
                    if (Math.abs(dy) >= 40) {
                        if (dy < 0) {//向上移动
                            if (index == 1 && moviesFragment != null) {
                                moviesFragment.pre();
                            }
                        } else {//向下移动
                            if (index == 1 && moviesFragment != null) {
                                moviesFragment.next();
                            }
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            index++;
            if (index > 1) {
                index = 0;
            }
            setSelected(index);
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            if (index == 1 && moviesFragment != null) {
                moviesFragment.pre();
            }
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            if (index == 1 && moviesFragment != null) {
                moviesFragment.next();
            }
        }
        return super.onKeyDown(keyCode, event);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mTimeRefreshReceiver);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateWeather(WeatherBean weatherBean) {
        this.weatherBean = weatherBean;
        setWeather();
    }

}
