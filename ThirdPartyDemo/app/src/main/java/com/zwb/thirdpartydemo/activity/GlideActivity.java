package com.zwb.thirdpartydemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwb.thirdpartydemo.R;

public class GlideActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvTitle;
    private ImageView back;
    private Button bt_common,bt_list,bt_transfer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.bt_common:
                startActivity(new Intent(this,GlideBaseActivity.class));
                break;
            case R.id.bt_list:
                startActivity(new Intent(this,GlideRecylerViewActivity.class));
                break;
            case R.id.bt_transfer:
                startActivity(new Intent(this,GlideTransferActivity.class));
                break;
        }
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Glide");
        back = (ImageView) findViewById(R.id.img_back);
        back.setOnClickListener(this);
        bt_common = (Button)findViewById(R.id.bt_common);
        bt_common.setOnClickListener(this);
        bt_list = (Button)findViewById(R.id.bt_list);
        bt_list.setOnClickListener(this);
        bt_transfer = (Button)findViewById(R.id.bt_transfer);
        bt_transfer.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
