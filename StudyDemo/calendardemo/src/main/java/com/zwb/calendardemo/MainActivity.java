package com.zwb.calendardemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private int selectYear = 2016;
    private int selectDay = 8;
    private int selectMonth = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void start(View view){
//        Intent intent = new Intent(this,CalendarActivity.class);
//        intent.putExtra("date","2016.08.05");
//        startActivity(intent);
//        CalendarPopupWindow calendarPopupWindow = new CalendarPopupWindow(this);
        CalendarPopupWindow.show(view, this,selectYear,selectMonth,selectDay, new CalendarPopupWindow.CallBack() {
            @Override
            public void selected(int year, int month, int day) {
                selectYear = year;
                selectMonth = month;
                selectDay = day;
            }
        });
    }
}
