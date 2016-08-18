package com.zwb.toucheventdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (Button) findViewById(R.id.tv);
//        tv.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        Log.d("info","OnTouchListener-------onTouch==ACTION_DOWN=");
//                        return false;
//
//                    case MotionEvent.ACTION_MOVE:
//                        Log.d("info","OnTouchListener-------onTouch==ACTION_MOVE=");
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Log.d("info","OnTouchListener-------onTouch==ACTION_UP=");
//                        break;
//                }
//                return false;
//            }
//        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d("info","MainActivity-------dispatchKeyEvent===");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("info","MainActivity-------onTouchEvent===");
        return super.onTouchEvent(event);
    }
}
