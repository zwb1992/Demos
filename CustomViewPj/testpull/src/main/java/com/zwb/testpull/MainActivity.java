package com.zwb.testpull;

import android.graphics.drawable.RotateDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private CustomPullView pl;
    private ImageView imgRotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pl = (CustomPullView) findViewById(R.id.pl);
        imgRotate = (ImageView)findViewById(R.id.imgRotate);
        setLevel(1000);

    }

    private int i = 10;

    public void padd(View v) {
        i += 10;
        pl.setTopPadding(i);
        setLevel(i);
    }

    /**
     * 设置图片的旋转角度
     *
     * @param progress
     */
    private void setLevel(int progress) {
        RotateDrawable rotateDrawable = (RotateDrawable)imgRotate.getDrawable();
        rotateDrawable.setLevel(progress);
    }
}
