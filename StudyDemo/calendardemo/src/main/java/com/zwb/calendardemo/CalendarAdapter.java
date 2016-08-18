package com.zwb.calendardemo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zwb.calendardemo.R;
import com.zwb.calendardemo.LunarCalendar;
import com.zwb.calendardemo.SpecialCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日历gridview中的每一个item显示的textview
 *
 * @author Vincent Lee
 */
public class CalendarAdapter extends BaseAdapter {
    private boolean isLeapyear = false; // 是否为闰年
    private int daysOfMonth = 0; // 某月的天数
    private int dayOfWeek = 0; // 具体某一天是星期几
    private int lastDaysOfMonth = 0; // 上一个月的总天数
    private Context context;
    private String[] dayNumber = new String[42]; // 一个gridview中的日期存入此数组中
    // private static String week[] = {"周日","周一","周二","周三","周四","周五","周六"};
    private SpecialCalendar sc = null;
    private LunarCalendar lc = null;
    private Resources res = null;
    private Drawable drawable = null;

    private String currentYear = "";
    private String currentMonth = "";
    private String currentDay = "";
    private String selectYear = "";
    private String selectMonth = "";
    private String selectDay = "";

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
    private int currentFlag = -1; // 用于标记当天
    private int selectFlag = -1; // 用于标记选中的日期
    private int[] schDateTagFlag = null; // 存储当月所有的日程日期

    private String showYear = ""; // 用于在头部显示的年份
    private String showMonth = ""; // 用于在头部显示的月份
    private String animalsYear = "";
    private String leapMonth = ""; // 闰哪一个月
    private String cyclical = ""; // 天干地支
    // 系统当前时间
    private String sysDate = "";
    private String sys_year = "";
    private String sys_month = "";
    private String sys_day = "";

    public CalendarAdapter() {
        Date date = new Date();
        sysDate = sdf.format(date); // 当期日期
        sys_year = sysDate.split("-")[0];
        sys_month = sysDate.split("-")[1];
        sys_day = sysDate.split("-")[2];

    }

    /**
     * 获取两个日期之间的间隔天数
     *
     * @return
     */
    public int getGapCount(int year_c, int month_c) {
        Log.i("info","---year_c---"+year_c+"-----month_c-----"+month_c);
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.set(Calendar.YEAR, year_c);
        fromCalendar.set(Calendar.MONTH, month_c-1);
        fromCalendar.set(Calendar.DAY_OF_MONTH, 1);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        long fromTime = fromCalendar.getTime().getTime();
        int dayOfWeek = fromCalendar.get(Calendar.DAY_OF_WEEK);
        fromCalendar.add(Calendar.MONTH, 1);
        long toTime = fromCalendar.getTime().getTime();
        createLineNum((int) ((toTime - fromTime) / (1000 * 60 * 60 * 24)), dayOfWeek);
        return (int) ((toTime - fromTime) / (1000 * 60 * 60 * 24));
    }

    /**
     * 根据当前月份有多少天以及第一天是星期几来判断显示几行
     *
     * @param totalDay
     * @param dayOfWeek
     */
    private void createLineNum(int totalDay, int dayOfWeek) {
        Log.i("info", "------createLineNum---------" + totalDay + "--------dayOfWeek-------" + dayOfWeek);
        if (totalDay == 28) {
            if (dayOfWeek == 1)
                dayNumber = new String[28];
            else
                dayNumber = new String[35];
        } else if (totalDay == 29) {
            dayNumber = new String[35];
        }else if(totalDay ==30){
            if(dayOfWeek<=6){
                dayNumber = new String[35];
            }else{
                dayNumber = new String[42];
            }
        }else if(totalDay == 31){
            if(dayOfWeek<=5){
                dayNumber = new String[35];
            }else{
                dayNumber = new String[42];
            }
        }
    }

    public CalendarAdapter(Context context, Resources rs, int jumpMonth, int jumpYear, int year_c, int month_c, int day_c
            , int select_year, int select_month, int select_day) {
        this();
        this.context = context;
        sc = new SpecialCalendar();
        lc = new LunarCalendar();
        this.res = rs;
        Log.i("info", "-------------------CalendarAdapter--------------------");
        int stepYear = year_c + jumpYear;
        int stepMonth = month_c + jumpMonth;
        if (stepMonth > 0) {
            // 往下一个月滑动
            if (stepMonth % 12 == 0) {
                stepYear = year_c + stepMonth / 12 - 1;
                stepMonth = 12;
            } else {
                stepYear = year_c + stepMonth / 12;
                stepMonth = stepMonth % 12;
            }
        } else {
            // 往上一个月滑动
            stepYear = year_c - 1 + stepMonth / 12;
            stepMonth = stepMonth % 12 + 12;
            if (stepMonth % 12 == 0) {

            }
        }
        int totalDay = getGapCount(stepYear, stepMonth);
        currentYear = String.valueOf(stepYear); // 得到当前的年份
        currentMonth = String.valueOf(stepMonth); // 得到本月
        // （jumpMonth为滑动的次数，每滑动一次就增加一月或减一月）
        currentDay = String.valueOf(day_c); // 得到当前日期是哪天
        selectYear = String.valueOf(select_year); // 得到选中的年份
        selectMonth = String.valueOf(select_month); // 得到选中的月
        selectDay = String.valueOf(select_day); // 得到选中的日期是哪天

        getCalendar(Integer.parseInt(currentYear), Integer.parseInt(currentMonth));
    }

    public CalendarAdapter(Context context, Resources rs, int year, int month, int day) {
        this();
        this.context = context;
        sc = new SpecialCalendar();
        lc = new LunarCalendar();
        this.res = rs;
        currentYear = String.valueOf(year);// 得到跳转到的年份
        currentMonth = String.valueOf(month); // 得到跳转到的月份
        currentDay = String.valueOf(day); // 得到跳转到的天
        getCalendar(Integer.parseInt(currentYear), Integer.parseInt(currentMonth));
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return dayNumber.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.calendar_item, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tvtext);
        String d = dayNumber[position].split("\\.")[0];
        String dv = dayNumber[position].split("\\.")[1];

        //		SpannableString sp = new SpannableString(d + "\n" + dv);
        //		sp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, d.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //		sp.setSpan(new RelativeSizeSpan(1.2f), 0, d.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //		if (dv != null || dv != "") {
        //			sp.setSpan(new RelativeSizeSpan(0.75f), d.length() + 1, dayNumber[position].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //		}
        //		// sp.setSpan(new ForegroundColorSpan(Color.MAGENTA), 14, 16,
        //		// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        //		textView.setText(sp);
        //		textView.setTextColor(Color.GRAY);
        textView.setVisibility(View.INVISIBLE);
        textView.setText("" + d);
        textView.setTextColor(context.getResources().getColor(R.color.hint_grey));
        if (position < daysOfMonth + dayOfWeek && position >= dayOfWeek) {
            textView.setVisibility(View.VISIBLE);
            // 当前月信息显示
            textView.setTextColor(context.getResources().getColor(R.color.edit_text));// 当月字体设黑
            //			drawable = res.getDrawable(R.drawable.calendar_item_selected_bg);
            drawable = new ColorDrawable(Color.rgb(230, 230, 230));
            if (position % 7 == 0 || position % 7 == 6) {
                // 当前月信息显示
                textView.setTextColor(context.getResources().getColor(R.color.hint_grey));// 当月字体设黑
                //				drawable = res.getDrawable(R.drawable.calendar_item_selected_bg);
                drawable = new ColorDrawable(Color.rgb(230, 230, 230));
            }
        }

        if (currentFlag == position) {
            // 设置当天的背景
            //			drawable = res.getDrawable(R.drawable.calendar_item_selected_bg);
//            drawable = context.getResources().getDrawable(R.drawable.current_rili);
//            textView.setBackgroundDrawable(drawable);
            textView.setBackgroundColor(context.getResources().getColor(R.color.activity_bg));
            textView.setTextColor(context.getResources().getColor(R.color.yellow));
            textView.setText(textView.getText()+"\n"+"today");
        }
        if (currentFlag != selectFlag && selectFlag == position) {
            Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
            // 设置选中那天的背景
            //			drawable = res.getDrawable(R.drawable.calendar_item_selected_bg);
//            drawable = context.getResources().getDrawable(R.drawable.selected_rili);
//            textView.setBackgroundDrawable(drawable);
            textView.setBackgroundColor(context.getResources().getColor(R.color.red));
            textView.setTextColor(Color.WHITE);
        }
        return convertView;
    }

    // 得到某年的某月的天数且这月的第一天是星期几
    public void getCalendar(int year, int month) {
        isLeapyear = sc.isLeapYear(year); // 是否为闰年
        daysOfMonth = sc.getDaysOfMonth(isLeapyear, month); // 某月的总天数
        dayOfWeek = sc.getWeekdayOfMonth(year, month); // 某月第一天为星期几
        lastDaysOfMonth = sc.getDaysOfMonth(isLeapyear, month - 1); // 上一个月的总天数
        getweek(year, month);
    }

    // 将一个月中的每一天的值添加入数组dayNuber中
    public void getweek(int year, int month) {
        int j = 1;
        int flag = 0;
        String lunarDay = "";

        // 得到当前月的所有日程日期(这些日期需要标记)

        for (int i = 0; i < dayNumber.length; i++) {
            // 周一
            // if(i<7){
            // dayNumber[i]=week[i]+"."+" ";
            // }
            if (i < dayOfWeek) { // 前一个月
                int temp = lastDaysOfMonth - dayOfWeek + 1;
                lunarDay = lc.getLunarDate(year, month - 1, temp + i, false);
                dayNumber[i] = (temp + i) + "." + lunarDay;

            } else if (i < daysOfMonth + dayOfWeek) { // 本月
                String day = String.valueOf(i - dayOfWeek + 1); // 得到的日期
                lunarDay = lc.getLunarDate(year, month, i - dayOfWeek + 1, false);
                dayNumber[i] = i - dayOfWeek + 1 + "." + lunarDay;
                // 对于当前月才去标记当前日期
                if (sys_year.equals(String.valueOf(year)) && sys_month.equals(String.valueOf(month)) && sys_day.equals(day)) {
                    // 标记当前日期
                    currentFlag = i;
                }
                // 对于当前月才去标记当前日期
                if (selectYear.equals(String.valueOf(year)) && selectMonth.equals(String.valueOf(month)) && selectDay.equals(day)) {
                    // 标记当前日期
                    selectFlag = i;
                }
                setShowYear(String.valueOf(year));
                setShowMonth(String.valueOf(month));
                setAnimalsYear(lc.animalsYear(year));
                setLeapMonth(lc.leapMonth == 0 ? "" : String.valueOf(lc.leapMonth));
                setCyclical(lc.cyclical(year));
            } else { // 下一个月
                lunarDay = lc.getLunarDate(year, month + 1, j, false);
                dayNumber[i] = j + "." + lunarDay;
                j++;
            }
        }

        String abc = "";
        for (int i = 0; i < dayNumber.length; i++) {
            abc = abc + dayNumber[i] + ":";
        }
    }

    public void matchScheduleDate(int year, int month, int day) {

    }

    /**
     * 点击每一个item时返回item中的日期
     *
     * @param position
     * @return
     */
    public String getDateByClickItem(int position) {
        return dayNumber[position];
    }

    /**
     * 在点击gridView时，得到这个月中第一天的位置
     *
     * @return
     */
    public int getStartPositon() {
        return dayOfWeek + 7;
    }

    /**
     * 在点击gridView时，得到这个月中最后一天的位置
     *
     * @return
     */
    public int getEndPosition() {
        return (dayOfWeek + daysOfMonth + 7) - 1;
    }

    public String getShowYear() {
        return showYear;
    }

    public void setShowYear(String showYear) {
        this.showYear = showYear;
    }

    public String getShowMonth() {
        return showMonth;
    }

    public void setShowMonth(String showMonth) {
        this.showMonth = showMonth;
    }

    public String getAnimalsYear() {
        return animalsYear;
    }

    public void setAnimalsYear(String animalsYear) {
        this.animalsYear = animalsYear;
    }

    public String getLeapMonth() {
        return leapMonth;
    }

    public void setLeapMonth(String leapMonth) {
        this.leapMonth = leapMonth;
    }

    public String getCyclical() {
        return cyclical;
    }

    public void setCyclical(String cyclical) {
        this.cyclical = cyclical;
    }

}

