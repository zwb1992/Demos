package com.zwb.calendardemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zwb
 * Description
 * Date 16/8/5.
 */
public class CalendarPopupWindow implements View.OnClickListener{
    private static PopupWindow popupWindow;

    private GestureDetector gestureDetector = null;
    private static CalendarAdapter calV = null;
    private ViewFlipper flipper = null;
    private GridView gridView = null;
    private static int jumpMonth = 0; // 每次滑动，增加或减去一个月,默认为0（即显示当前月）
    private static int jumpYear = 0; // 滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private int select_year = 0;
    private int select_month = 0;
    private int select_day = 0;
    private String currentDate = "";
    /** 每次添加gridview到viewflipper中时给的标记 */
    private int gvFlag = 0;
    /** 当前的年月，现在日历顶端 */
    private TextView currentMonth;
    /** 上个月 */
    private ImageView prevMonth;
    /** 下个月 */
    private ImageView nextMonth;

    private Context mContext;
    private static CallBack mCallBack;
    public CalendarPopupWindow(Context context,int year,int month,int day){
        mContext = context;
        select_year = year;
        select_month = month;
        select_day = day;
        View view = LayoutInflater.from(context).inflate(R.layout.calendar,null,false);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.transparent)));
        popupWindow.setOutsideTouchable(false);
        setTime("2016.08.05");
        currentMonth = (TextView) view.findViewById(R.id.currentMonth);
        prevMonth = (ImageView) view.findViewById(R.id.prevMonth);
        nextMonth = (ImageView) view.findViewById(R.id.nextMonth);
        setListener();

        gestureDetector = new GestureDetector(context, new MyGestureListener());
        flipper = (ViewFlipper) view.findViewById(R.id.flipper);
        flipper.removeAllViews();
//        jumpMonth = 0;jumpYear = 0;
        calV = new CalendarAdapter(context, context.getResources(), jumpMonth, jumpYear, year_c, month_c, day_c
                ,select_year,select_month,select_day);
        addGridView();
        gridView.setAdapter(calV);
        flipper.addView(gridView, 0);
        addTextToTopTextView(currentMonth);
    }

    public void setTime(String dateStr) {
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy.MM.dd");
        try{
            Date date = dFormat.parse(dateStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
            currentDate = sdf.format(date); // 当期日期
            year_c = Integer.parseInt(currentDate.split("-")[0]);
            month_c = Integer.parseInt(currentDate.split("-")[1]);
            day_c = Integer.parseInt(currentDate.split("-")[2]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void show(View view,Context context,int year,int month,int day,CallBack callBack){
        mCallBack = callBack;
        new CalendarPopupWindow(context,year,month,day);
        if (popupWindow != null) {
            calV.notifyDataSetChanged();
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            } else {
                popupWindow.showAsDropDown(view, 0, 0);
            }
        }

    }
    public static void dismiss(){
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        popupWindow = null;
    }
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            int gvFlag = 0; // 每次添加gridview到viewflipper中时给的标记
            if (e1.getX() - e2.getX() > 120) {
                // 像左滑动
                enterNextMonth(gvFlag);
                return true;
            } else if (e1.getX() - e2.getX() < -120) {
                // 向右滑动
                enterPrevMonth(gvFlag);
                return true;
            }
            return false;
        }
    }

    /**
     * 移动到下一个月
     *
     * @param gvFlag
     */
    private void enterNextMonth(int gvFlag) {
        addGridView(); // 添加一个gridView
        jumpMonth++; // 下一个月

        calV = new CalendarAdapter(mContext, mContext.getResources(), jumpMonth, jumpYear, year_c, month_c, day_c
                ,select_year,select_month,select_day);
        gridView.setAdapter(calV);
        addTextToTopTextView(currentMonth); // 移动到下一月后，将当月显示在头标题中
        gvFlag++;
        flipper.addView(gridView, gvFlag);
        flipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_left_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_left_out));
        flipper.showNext();
        flipper.removeViewAt(0);
    }

    /**
     * 移动到上一个月
     *
     * @param gvFlag
     */
    private void enterPrevMonth(int gvFlag) {
        addGridView(); // 添加一个gridView
        jumpMonth--; // 上一个月

        calV = new CalendarAdapter(mContext, mContext.getResources(), jumpMonth, jumpYear, year_c, month_c, day_c
                ,select_year,select_month,select_day);
        gridView.setAdapter(calV);
        gvFlag++;
        addTextToTopTextView(currentMonth); // 移动到上一月后，将当月显示在头标题中
        flipper.addView(gridView, gvFlag);

        flipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_right_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_right_out));
        flipper.showPrevious();
        flipper.removeViewAt(0);
    }

    /**
     * 添加头部的年份 闰哪月等信息
     *
     * @param view
     */
    public void addTextToTopTextView(TextView view) {
        StringBuffer textDate = new StringBuffer();
        // draw = getResources().getDrawable(R.drawable.top_day);
        // view.setBackgroundDrawable(draw);
        textDate.append(calV.getShowYear()).append("年").append(calV.getShowMonth()).append("月").append("\t");
        view.setText(textDate);
    }

    private void addGridView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);
        gridView = new GridView(mContext);
        gridView.setNumColumns(7);
        gridView.setColumnWidth(40);
        // gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
//        if (Width == 720 && Height == 1280) {
//            gridView.setColumnWidth(40);
//        }
        gridView.setGravity(Gravity.CENTER_VERTICAL);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        // 去除gridView边框
        gridView.setVerticalSpacing(1);
        gridView.setHorizontalSpacing(1);
        gridView.setOnTouchListener(new View.OnTouchListener() {
            // 将gridview中的触摸事件回传给gestureDetector

            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return CalendarPopupWindow.this.gestureDetector.onTouchEvent(event);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // TODO Auto-generated method stub
                // 点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))

                int startPosition = calV.getStartPositon();
                int endPosition = calV.getEndPosition();
                if (startPosition <= position + 7 && position <= endPosition - 7) {
                    String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0]; // 这一天的阳历
                    // String scheduleLunarDay =
                    // calV.getDateByClickItem(position).split("\\.")[1];
                    // //这一天的阴历
                    String scheduleYear = calV.getShowYear();
                    String scheduleMonth = calV.getShowMonth();
                    select_year = Integer.parseInt(scheduleYear);
                    select_day = Integer.parseInt(scheduleDay);
                    select_month = Integer.parseInt(scheduleMonth);
                    mCallBack.selected(select_year,select_month,select_day);
                    Toast.makeText(mContext,scheduleYear + "-" + scheduleMonth + "-" + scheduleDay,Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });
        gridView.setLayoutParams(params);
    }

    private void setListener() {
        prevMonth.setOnClickListener(this);
        nextMonth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.nextMonth: // 下一个月
                enterNextMonth(gvFlag);
                break;
            case R.id.prevMonth: // 上一个月
                enterPrevMonth(gvFlag);
                break;
        }
    }
    public interface CallBack{
        void selected(int year,int month,int day);
    }
}
