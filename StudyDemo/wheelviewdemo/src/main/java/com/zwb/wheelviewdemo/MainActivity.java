package com.zwb.wheelviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private WheelView wl_sex;
    private PopupWindow mPopupWindow = null;//更换头像、性别
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add("male");
        list.add("female");
        list.add("female");
        list.add("female");
        list.add("female");
        list.add("female");
        list.add("female");
        list.add("female");
        list.add("female");
        list.add("female");
        list.add("female");
        list.add("female");
        list.add("female");
        list.add("female");
        list.add("female");
    }

    public void start(View view){
        showSexPopupWindow();
    }
    /**
     * 显示修改性别窗口
     */
    private void showSexPopupWindow() {
        View window = getLayoutInflater().inflate(R.layout.popup_setsex, null);
        RelativeLayout main = (RelativeLayout) window.findViewById(R.id.main);
        RelativeLayout content= (RelativeLayout) window.findViewById(R.id.rl_content);
        wl_sex = (WheelView) window.findViewById(R.id.wl_sex);
        TextView btn_cancel = (TextView) window.findViewById(R.id.btn_cancel);
        TextView btn_sure = (TextView) window.findViewById(R.id.btn_sure);
        mPopupWindow = new PopupWindow(window,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        wl_sex.setOffset(1);
        wl_sex.setSelectColor(getResources().getColor(R.color.c8));
        wl_sex.setItems(list);
        wl_sex.setSeletion(0);
        btn_cancel.setOnClickListener(this);
        btn_sure.setOnClickListener(this);
        main.setOnClickListener(this);
        content.setOnClickListener(this);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                mPopupWindow.dismiss();
                break;
            case R.id.btn_sure:
                Toast.makeText(this,wl_sex.getSeletedItem(),Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
                break;
            case R.id.main:
                mPopupWindow.dismiss();
                break;
            case R.id.rl_content:

                break;
        }
    }
}
