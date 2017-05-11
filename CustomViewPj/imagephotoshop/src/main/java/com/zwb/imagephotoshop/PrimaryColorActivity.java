package com.zwb.imagephotoshop;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PrimaryColorActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.sb_hue)
    SeekBar sbHue;
    @BindView(R.id.sb_saturation)
    SeekBar sbSaturation;
    @BindView(R.id.sb_lum)
    SeekBar sbLum;

    private Unbinder unbinder;

    private static final int MAX = 255;
    private static final int MIN = 127;

    private float mHue, mSaturation, mLum;
    @BindBitmap(R.mipmap.bg)
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_color);
        unbinder = ButterKnife.bind(this);
        img.setImageBitmap(bitmap);

        sbHue.setMax(MAX);
        sbSaturation.setMax(MAX);
        sbLum.setMax(MAX);
        sbHue.setProgress(MIN);
        sbSaturation.setProgress(MIN);
        sbLum.setProgress(MIN);

        sbHue.setOnSeekBarChangeListener(this);
        sbSaturation.setOnSeekBarChangeListener(this);
        sbLum.setOnSeekBarChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        switch (seekBar.getId()) {
            case R.id.sb_hue://色相
                mHue = (progress - MIN) * 1.0f / MIN * 180;
                break;
            case R.id.sb_saturation://饱和度
                mSaturation = progress * 1.0f / MIN;
                break;
            case R.id.sb_lum://明亮度
                mLum = progress * 1.0f / MIN;
                break;
        }
        img.setImageBitmap(ImageHelper.handleImageEffect(bitmap,mHue,mSaturation,mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
