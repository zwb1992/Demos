package com.example.drawabledemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView img, imgClip, img_round,img_scale,img_rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        imgClip = (ImageView) findViewById(R.id.img_clip);
        ClipDrawable clipDrawable = (ClipDrawable) imgClip.getDrawable();
        clipDrawable.setLevel(7000);
        img_round = (ImageView) findViewById(R.id.img_round);
        setRoundImage();
        img_scale = (ImageView) findViewById(R.id.img_scale);
        setScale();
        img_rotate = (ImageView) findViewById(R.id.img_rotate);
        setRotate();
    }

    public void open(View view) {
        TransitionDrawable transitionDrawable = (TransitionDrawable) img.getDrawable();
        transitionDrawable.startTransition(2000);
    }

    public void close(View view) {
        TransitionDrawable transitionDrawable = (TransitionDrawable) img.getDrawable();
        transitionDrawable.reverseTransition(2000);
    }

    /**
     * 设置圆角图片
     */
    private void setRoundImage() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.round1);
        img_round.setImageDrawable(new RoundImageDrawable(bitmap));
    }
    /**
     * 设置图片缩放
     */
    private void setScale() {
        ScaleDrawable scaleDrawable = (ScaleDrawable)img_scale.getDrawable();
        scaleDrawable.setLevel(1);
    }
    /**
     * 设置图片旋转
     */
    private void setRotate() {
        RotateDrawable rotateDrawable = (RotateDrawable) img_rotate.getDrawable();
        rotateDrawable.setLevel(10000);
    }
}
