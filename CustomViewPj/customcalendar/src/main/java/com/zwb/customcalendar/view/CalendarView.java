package com.zwb.customcalendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.zwb.customcalendar.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 17/5/13.
 */

public class CalendarView extends LinearLayout implements View.OnClickListener {

    private ImageView prevMonth, nextMonth;
    private TextView tvTitle;
    private ViewFlipper flipper;

    //当前日期
    private Calendar mCalendar = Calendar.getInstance();

    //格式化日期，显示title
    private SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM");
    private List<Date> dateList = new ArrayList<>();
    private Date selectDate;//被选中的那一天
    //日期适配器
    private CalendarAdapter adapter;
    //用来填充数据添加到flipper
    private GridView gridView;
    //选择日期回调
    private OnDateCallBack onDateCallBack;

    private GestureDetector gestureDetector;

    public void setOnDateCallBack(OnDateCallBack onDateCallBack) {
        this.onDateCallBack = onDateCallBack;
    }

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initDate();
        addGridView();
    }

    /**
     * 初始化view
     */
    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.calendar, this, true);
        prevMonth = (ImageView) findViewById(R.id.prevMonth);
        prevMonth.setOnClickListener(this);
        nextMonth = (ImageView) findViewById(R.id.nextMonth);
        nextMonth.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.currentMonth);
        flipper = (ViewFlipper) view.findViewById(R.id.flipper);
        gestureDetector = new GestureDetector(new MyGestureListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.prevMonth:
                prevMonth();
                break;
            case R.id.nextMonth:
                nextMonth();
                break;
        }
    }

    /**
     * 翻到上一个月
     */
    private void prevMonth() {
        mCalendar.add(Calendar.MONTH,-1);
        initDate();
        addGridView();
        flipper.setInAnimation(getContext(), R.anim.left_in);
        flipper.setOutAnimation(getContext(), R.anim.right_out);
        flipper.showPrevious();
        flipper.removeViewAt(0);
    }

    /**
     * 翻到下一个月
     */
    private void nextMonth() {
        mCalendar.add(Calendar.MONTH,1);
        initDate();
        addGridView();
        flipper.setInAnimation(getContext(), R.anim.right_in);
        flipper.setOutAnimation(getContext(), R.anim.left_out);
        flipper.showNext();
        flipper.removeViewAt(0);
    }

    /**
     * 添加gridView
     */
    private void addGridView() {
        gridView = (GridView) LayoutInflater.from(getContext()).inflate(R.layout.calendar_grid_view, null);
        if (adapter == null) {
            adapter = new CalendarAdapter(dateList, getContext());
            adapter.setmCalendar(mCalendar);
        } else {
            adapter.setmCalendar(mCalendar);
            adapter.setList(dateList);
        }
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(onDateCallBack != null){
                    onDateCallBack.onSelectedDate(dateList.get(position));
                }
                adapter.setSelectedDate(dateList.get(position));
            }
        });
        gridView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
        flipper.addView(gridView);
    }

    /**
     * 初始化数据--6*7的网格，总共42条数据
     */
    private void initDate() {
        tvTitle.setText(dFormat.format(mCalendar.getTime()));
        dateList.clear();
        Calendar calendar = (Calendar)mCalendar.clone();
        //先设置成当月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //当前月的第一天是星期几,下标
        int offSetDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        //向前移动几天
        calendar.set(Calendar.DAY_OF_MONTH, -offSetDay);
        int maxCount = 6 * 7;
        for (int i = 0;i<maxCount;i++){
            calendar.add(Calendar.DAY_OF_MONTH,1);
            dateList.add(calendar.getTime());
        }
    }

    public interface OnDateCallBack{
        void onSelectedDate(Date date);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            int gvFlag = 0; // 每次添加gridview到viewflipper中时给的标记
            if (e1.getX() - e2.getX() > 120) {
                // 像左滑动
                nextMonth();
                return true;
            } else if (e1.getX() - e2.getX() < -120) {
                // 向右滑动
                prevMonth();
                return true;
            }
            return false;
        }
    }
}
