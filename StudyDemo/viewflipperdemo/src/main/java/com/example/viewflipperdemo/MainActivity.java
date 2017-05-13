package com.example.viewflipperdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper fp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fp = (ViewFlipper) findViewById(R.id.fp);
    }

    public void onPre(View view) {
        fp.showPrevious();
    }

    public void onNext(View view) {
        fp.showNext();
    }

    private int x, y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int fx = (int) event.getX() - x;
                int fy = (int) event.getY() - y;
                if (Math.abs(fx) > Math.abs(fy)) {
                    if (Math.abs(fx) > 10 && fx > 0) {
                        fp.showNext();
                    } else if (Math.abs(fx) > 10 && fx < 0) {
                        fp.showPrevious();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
