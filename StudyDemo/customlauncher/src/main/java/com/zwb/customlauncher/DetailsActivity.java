package com.zwb.customlauncher;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;


public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rbNews, rbMovies;
    private NewsFragment newsFragment;
    private MoviesFragment moviesFragment;
    private FragmentManager fragmentManager;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉Activity上面的状态栏
        setContentView(R.layout.activity_details);
        fragmentManager = getSupportFragmentManager();

        rbNews = (RadioButton) findViewById(R.id.rbNews);
        rbNews.setOnClickListener(this);
        rbMovies = (RadioButton) findViewById(R.id.rbMovies);
        rbMovies.setOnClickListener(this);
        index = 0;
        setSelected(index);

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
                    if (dx < 0) {
//                        Toast.makeText(this,"向左滑动",Toast.LENGTH_SHORT).show();
                        finish();
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    } else {
                        index++;
                        if(index > 1){
                            index = 0;
                        }
                        setSelected(index);
//                        Toast.makeText(this,"向右滑动",Toast.LENGTH_SHORT).show();
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
            if(index > 1){
                index = 0;
            }
            setSelected(index);
        }
        return super.onKeyDown(keyCode, event);
    }
}
