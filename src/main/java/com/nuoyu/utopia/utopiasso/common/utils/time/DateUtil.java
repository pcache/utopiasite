package com.nuoyu.utopia.utopiasso.common.utils.time;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liuxin3 on 2015/1/24.
 * 日期工具类  提供常用的日期工具  格式转换
 */
public class DateUtil {

    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date      日期字符串
     * @param dateStyle 日期风格
     * @return 日期
     */
    public static Date StringToDate(String date, DateStyle dateStyle) {
        Date mDate = null;
        try {
            mDate = DateUtils.parseDate(date, new String[]{dateStyle.getValue()});
        } catch (Exception e) {
            log.error("date Format exception(StringToDate)====>date:" + date + "==>dateStyle:" + dateStyle.getValue());
        }
        return mDate;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date      日期
     * @param dateStyle 日期风格
     * @return 日期字符串
     */
    public static String DateToString(Date date, DateStyle dateStyle) {
        String dateString = null;
        try {
            dateString = new SimpleDateFormat(dateStyle.getValue()).format(date);
        } catch (Exception e) {
            log.error("date Format exception(DateToString)====>date:" + date + "==>dateStyle:" + dateStyle.getValue());
        }
        return dateString;
    }


    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期
     * @return 星期
     */
    public static Week getWeek(Date date) {
        Week week = null;
        if (null == date) {
            return week;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (weekNumber) {
            case 0:
                week = Week.SUNDAY;
                break;
            case 1:
                week = Week.MONDAY;
                break;
            case 2:
                week = Week.TUESDAY;
                break;
            case 3:
                week = Week.WEDNESDAY;
                break;
            case 4:
                week = Week.THURSDAY;
                break;
            case 5:
                week = Week.FRIDAY;
                break;
            case 6:
                week = Week.SATURDAY;
                break;
        }
        return week;
    }


    /**
     * 获得两个日期的间隔天数
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差天数
     */
    public static int getIntervalDays(Date date, Date otherDate) {
        long time = Math.abs(date.getTime() - otherDate.getTime());
        return (int) time / (24 * 60 * 60 * 1000);
    }

    /**
     * 获得某某日期的年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获得某某日期的日
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得某某日期的月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }
}