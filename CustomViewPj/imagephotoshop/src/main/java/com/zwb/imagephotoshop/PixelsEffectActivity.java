package com.zwb.imagephotoshop;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PixelsEffectActivity extends AppCompatActivity {

    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img3)
    ImageView img3;
    @BindView(R.id.img4)
    ImageView img4;
    private Unbinder unbinder;

    @BindBitmap(R.mipmap.bg1)
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixels_effect);
        unbinder = ButterKnife.bind(this);

        img1.setImageBitmap(bitmap);
        //底片效果
        img2.setImageBitmap(ImageHelper.pixelsEffect(bitmap));
        //怀旧效果
        img3.setImageBitmap(ImageHelper.pixelsEffect1(bitmap));
        //浮雕
        img4.setImageBitmap(ImageHelper.pixelsEffect2(bitmap));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
