package com.zwb.customcalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zwb.customcalendar.view.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private CalendarView cv;
    private SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cv = (CalendarView)findViewById(R.id.cv);
        cv.setOnDateCallBack(new CalendarView.OnDateCallBack() {
            @Override
            public void onSelectedDate(Date date) {
                Toast.makeText(MainActivity.this,dFormat.format(date),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
