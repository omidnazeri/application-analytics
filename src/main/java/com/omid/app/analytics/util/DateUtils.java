package com.omid.app.analytics.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private DateUtils() {
    }

    public static Date firstTimeOfToday() {
        return firstTimeOfDay(new Date());
    }

    public static Date firstTimeOfTomorrow() {
        return firstTimeOfDay(tomorrow());
    }

    public static Date firstTimeOfYesterday() {
        return firstTimeOfDay(yesterday());
    }

    public static Date tomorrow() {
        return nextDay(new Date());
    }

    public static Date yesterday() {
        return add(new Date(), Calendar.DAY_OF_MONTH, -1);
    }

    public static Date firstTimeOfDay(Date date) {
        Calendar c = getCalendar(date);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }
    //nextDay here should be date + 2 as result of campaignFinishDate is TemporalType.DATE and not contain hours.
    public static Date nextTwoDay(final Date date) {
        return add(date, Calendar.DAY_OF_MONTH, 2);
    }

    public static Date nextDay(final Date date) {
        return add(date, Calendar.DAY_OF_MONTH, 1);
    }

    public static boolean dateEquals(Date date1, Date date2) {
        return firstTimeOfDay(date1).equals(firstTimeOfDay(date2));
    }

    public static Date add(Date date, int dateField, int value) {
        final Calendar c = getCalendar(date);
        c.add(dateField, value);

        return c.getTime();
    }

    public static Calendar getCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;
    }

    public static int diffInDays(Date date1, Date date2) {
        return (int) Math.round((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static int diffInHours(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.SECOND, 0);

        return (int) Math.round((date.getTime() - calendar.getTime().getTime()) / (1000 * 60 * 60));
    }

    public static int diffInHours(Date date, Date date2) {

        return (int) Math.round((date.getTime() - date2.getTime()) / (1000 * 60 * 60));
    }

    public static int diffInSeconds(Date date1, Date date2) {
        return (int) Math.round((date1.getTime() - date2.getTime()) / (1000));
    }
}