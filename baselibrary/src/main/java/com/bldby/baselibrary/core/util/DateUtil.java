package com.bldby.baselibrary.core.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bch on 2020/5/15
 */
public class DateUtil {
    /**
     * 单位:秒
     */
    public static String formatDateYYYYMMDD(long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time * 1000);
        return df.format(date);
    }

    public static String formatDateYYYYMMDDToDate(long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time * 1000);
        return df.format(date);
    }

    public static String formatDateYYYYMMDDWithDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static String formatDateTopic1(long time) {
        SimpleDateFormat df = new SimpleDateFormat("EEE,MMM d,yyyy \nh:mm a", Locale.ENGLISH);
        Date date = new Date(time);
        return df.format(date);
    }

    public static String formatDateTopic(long time) {
        SimpleDateFormat df = new SimpleDateFormat("EEE,MMM d,yyyy \nh:mm a", Locale.ENGLISH);
        Date date = new Date(time * 1000L);
        return df.format(date);
    }

    public static String formatDate(long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time * 1000);
        return df.format(date);
    }

    public static Date updateData(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, i);// +i天 月份自动变化
        return c.getTime();
    }

    public static Date addOneData(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);// +1天 月份自动变化
        return c.getTime();
    }

    public static Date subtractOneData(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, -1);// +1天 月份自动变化
        return c.getTime();
    }

    //获取今天的时间戳
    public static long getTodayTimeMillis() {
        long now = System.currentTimeMillis();
        return now - now % (24 * 60 * 60 * 1000);
    }

    //获取今天的时间戳
    public static long getDateTimeMillis(long timeMillis) {
        return timeMillis - timeMillis % (24 * 60 * 60 * 1000);
    }

    public static boolean isYesterday(long timestamp) {
        Calendar c = Calendar.getInstance();
        clearCalendar(c, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND);
        c.add(Calendar.DAY_OF_MONTH, -1);
        long firstOfDay = c.getTimeInMillis(); // 昨天最早时间

        c.setTimeInMillis(timestamp);
        clearCalendar(c, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND); // 指定时间戳当天最早时间

        return firstOfDay == c.getTimeInMillis();
    }

    /**
     * 返回true 代表是今天之前的
     *
     * @param timestamp
     * @return
     */
    public static boolean isBeforeDays(long timestamp) {
        Calendar c = Calendar.getInstance();
        clearCalendar(c, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND);
        long firstOfDay = c.getTimeInMillis(); // 当天最早时间
//给的时间戳小于今天最早的时间戳.在今天之前
        return timestamp < firstOfDay;
    }

    private static void clearCalendar(Calendar c, int... fields) {
        for (int f : fields) {
            c.set(f, 0);
        }
    }

    /*
     *
     * 2011-02-03得到周几
     * */
    @SuppressLint("SimpleDateFormat")
    public static String strToWeek(String pattern, String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatter.parse(strDate));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(c.getTime());
        String result = week.substring(week.length() - 1);
        return "周" + result;
    }

    /*
     * 将分钟转换成小时
     * */
    public static String minToHour(String str) {
        String result = "";
        if (Integer.valueOf(str) > 60) {
            int hour = Integer.valueOf(str) / 60;
            int min = Integer.valueOf(str) % 60;
            result = hour + "小时" + min + "分钟";
        } else {
            result = str + "分钟";
        }
        return result;
    }

    /*
     * 毫秒转化分秒毫秒
     */
    public static String formatTime2(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        //Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();

        if (minute >= 0 && minute < 10) {
            sb.append("0" + minute + "分");
        } else if (minute >= 10) {
            sb.append(minute + "分");
        }
        if (second >= 0 && second < 10) {
            sb.append("0" + second + "秒");
        } else if (second >= 10) {
            sb.append(second + "秒");
        }
       /* if (milliSecond > 0) {
            sb.append(milliSecond + "毫秒");
        }*/
        return sb.toString();
    }

    /*
     * 将YYYY-MM-dd转换成MM-dd
     * */
    public static String strToStr(String str) {
        String[] result = str.split("-");
        return result[1] + "-" + result[2];

    }

    /*
     * 将2020-03-31-19:00-21:15转换成YYYY-MM-dd
     * */
    public static String strToStr2(String str) {
        String[] result = str.split("-");
        return result[0] + "-" + result[1] + "-" + result[2];
    }

}
