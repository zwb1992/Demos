package com.example.guaguaka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.guaguaka.view.GuaGuaKa;

public class MainActivity extends AppCompatActivity {
    private GuaGuaKa guaguaka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guaguaka = (GuaGuaKa) findViewById(R.id.guaguaka);
        guaguaka.setOnComplteListenner(new GuaGuaKa.OnComplteListenner() {
            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "已经刮得差不多了！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
