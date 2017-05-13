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
        fp.setInAnimation(this,R.anim.left_in);
        fp.setOutAnimation(this,R.anim.right_out);
        fp.showPrevious();
    }

    public void onNext(View view) {
        fp.setInAnimation(this,R.anim.right_in);
        fp.setOutAnimation(this,R.anim.left_out);
        fp.showNext();
    }

    private int x, y;
    private int state = 0;
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
                    if (Math.abs(fx) > 20 && fx < 0) {
                        state = 1;
                    } else if (Math.abs(fx) > 20 && fx > 0) {
                        state = -1;
                    }else {
                        state = 0;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(state == 1){
                    fp.setInAnimation(this,R.anim.right_in);
                    fp.setOutAnimation(this,R.anim.left_out);
                    fp.showNext();
                }else if(state == -1){
                    fp.setInAnimation(this,R.anim.left_in);
                    fp.setOutAnimation(this,R.anim.right_out);
                    fp.showPrevious();
                }
                break;
        }
        return true;
    }
}
