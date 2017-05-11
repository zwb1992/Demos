package com.zwb.imagephotoshop;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ColorMatrixActivity extends AppCompatActivity {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.group)
    GridLayout group;
    private Unbinder unbinder;

    private EditText[] editTexts = new EditText[20];
    private float[] matrixs = new float[20];

    private int width,height;

    @BindBitmap(R.mipmap.bg1)
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);
        unbinder = ButterKnife.bind(this);

        img.setImageBitmap(bitmap);

        group.post(new Runnable() {
            @Override
            public void run() {
                width = group.getWidth()/5;
                height = group.getHeight()/4;
                initView();
                initData();
            }
        });
    }

    private void initView(){
        for (int i = 0;i<20;i++){
            EditText editText = new EditText(this);
            editTexts[i] = editText;
            group.addView(editText,width,height);
        }
    }

    private void initData(){
        for (int i = 0;i<20;i++){
            if(i % 6 == 0){
                editTexts[i].setText("1");
                matrixs[i] = 1;
            }else {
                editTexts[i].setText("0");
                matrixs[i] = 0;
            }
        }
    }

    private void getMatrix(){
        for (int i = 0;i<20;i++){
            matrixs[i] = Float.parseFloat(editTexts[i].getText().toString());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_change, R.id.bt_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_change:
                getMatrix();
                img.setImageBitmap(ImageHelper.setColorMatrix(bitmap,matrixs));
                break;
            case R.id.bt_reset:
                initData();
                getMatrix();
                img.setImageBitmap(ImageHelper.setColorMatrix(bitmap,matrixs));
                break;
        }
    }
}
