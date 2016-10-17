package com.iwanna.learn.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zwb
 * Description
 * Date 16/8/28.
 */
public class CalendarUtils {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat format1 = new SimpleDateFormat("MM月dd日 HH:mm");
    private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy.MM.dd");

    public static Date getDate(String time){
        try {
            return format.parse(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String getTime1(Date date){
        return format1.format(date);
    }
    public static String getTime(Date date){
        return format.format(date);
    }

    /**
     * 根据时间返回字符串 如 刚刚，一天前，2016.08.11
     */

    public static String getTimeTip(String time){
        String tip = "";
        Date date = getDate(time);
        if(date == null){
            return  tip;
        }
        Calendar old = Calendar.getInstance();
        old.setTime(date);
        Calendar now = Calendar.getInstance();
        if(now.get(Calendar.DAY_OF_YEAR) - old.get(Calendar.DAY_OF_YEAR) > 1){//时间大于两天以上
            return format2.format(date);
        }else if(now.get(Calendar.DAY_OF_YEAR) - old.get(Calendar.DAY_OF_YEAR) == 1){//时间在前一天
            return "1天前";
        }else {//时间在一天之内
            if(now.get(Calendar.HOUR_OF_DAY) - old.get(Calendar.HOUR_OF_DAY)>1){//大于一个小时
                return (now.get(Calendar.HOUR_OF_DAY) - old.get(Calendar.HOUR_OF_DAY))+"小时前";
            }else if(now.get(Calendar.MINUTE) - old.get(Calendar.MINUTE)>1){//大于一分钟
                return (now.get(Calendar.MINUTE) - old.get(Calendar.MINUTE))+"分钟前";
            }else if(now.get(Calendar.SECOND) - old.get(Calendar.SECOND)>10){//大于10秒钟
                return (now.get(Calendar.SECOND) - old.get(Calendar.SECOND))+"秒钟前";
            }else{
                return  "刚刚";
            }
        }
    }
}
