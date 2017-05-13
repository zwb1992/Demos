package com.zwb.customcalendar.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zwb.customcalendar.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 17/5/13.
 */

public class CalendarAdapter extends BaseAdapter {
    private List<Date> list;
    private Context mContext;
    private Calendar mCalendar;//当前日期
    private Date selectedDate;

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
        notifyDataSetChanged();
    }

    public void setmCalendar(Calendar mCalendar) {
        this.mCalendar = mCalendar;
    }

    public void setList(List<Date> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public CalendarAdapter(List<Date> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.calendar_item,null);
        }
        Date date = list.get(position);
        TextView textView = (TextView)convertView;
        int day = date.getDate();
        textView.setText(String.valueOf(day));
        Date nowDate = mCalendar.getTime();
        //当前月--根据滑动的月份来计算
        if(nowDate.getMonth() == date.getMonth()){
            textView.setTextColor(Color.BLACK);
        }else {
            textView.setTextColor(Color.GRAY);
        }
        //今天--根据当前时间来计算
        nowDate = new Date();
        if(nowDate.getDate() == day && nowDate.getMonth() == date.getMonth()){
            textView.setTextColor(Color.RED);
        }

        //被选中的那一天
        if(selectedDate != null && date.getDate() == selectedDate.getDate() && date.getMonth() == selectedDate.getMonth()){
            textView.setTextColor(Color.GREEN);
        }

        return convertView;
    }

}
