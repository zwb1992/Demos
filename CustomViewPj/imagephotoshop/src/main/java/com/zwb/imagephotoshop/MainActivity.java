package com.zwb.imagephotoshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btPrimaryColor(View v){
        startActivity(new Intent(this,PrimaryColorActivity.class));
    }

    public void btColorMatrix(View v){
        startActivity(new Intent(this,ColorMatrixActivity.class));
    }

    public void btPixelsEffect(View v){
        startActivity(new Intent(this,PixelsEffectActivity.class));
    }

    public void btImageMatrix(View v){
        startActivity(new Intent(this,ImageMatrixActivity.class));
    }

    public void btReflect(View v){
        startActivity(new Intent(this,ReflectActivity.class));
    }

    public void btMesh(View v){
        startActivity(new Intent(this,MeshActivity.class));
    }
}
