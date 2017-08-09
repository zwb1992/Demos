package com.zwb.customlauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout ll_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_info = (LinearLayout) findViewById(R.id.ll_info);
        ll_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
//                startActivity(intent);
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_in);
                ll_info.startAnimation(animation);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
